package org.henry.jsis;

import org.henry.jsis.initializer.JavaServiceInitialization;
import org.henry.jsis.initializer.JavaServiceInitializer;
import org.henry.jsis.initializer.JavaServiceObject;
import org.henry.jsis.services.*;

import java.io.File;
import java.io.FileWriter;

import static org.henry.jsis.constants.JSISConstants.*;
import static org.henry.jsis.util.JSISUtils.*;
import static org.henry.jsis.util.JSISValidator.*;

/**
 * @author Henry Azer
 * @since 20/01/2023
 **/
public class JavaServiceInitializerScript {
    private static JavaServiceInfo javaServiceInfo;
    private static Boolean errorOccurred = false;

    public static void main(String[] args) throws Exception {
        jsisInfoPrints();
        jsisProcess();
        jsisInfoPrints();
    }

    private static void jsisProcess() throws Exception {
        setServiceParams();
        initializeServices();
        boolean hasAnother;
        do {
            if (errorOccurred) return;
            String choice = scanInputString(INPUT_ANOTHER_SERVICE);
            hasAnother = choice.equalsIgnoreCase(YES);
            if (hasAnother) {
                println(BREAK_LINE);
                setServiceName();
                initializeServices();
            }
        } while (hasAnother);
    }

    private static void setServiceParams() throws Exception {
        // scan service path, name and package
        String path = scanInputString(INPUT_SERVICE_PATH);
        String name = scanInputString(INPUT_SERVICE_NAME);
        String servicePackage = scanInputString(INPUT_SERVICE_PACKAGE);

        // check if service path, name and package are valid
        if (isServicePathValid(path)) {
            errorOccurred = true;
            printError(INVALID_SERVICE_PATH);
        }
        if (isServiceNameValid(name)) {
            errorOccurred = true;
            printError(INVALID_SERVICE_NAME);
        }
        if (isStringValid(servicePackage)) {
            if (isServicePathContainsServicePackage(path)) servicePackage = getPackageNameFromServicePath(path);
            else {
                errorOccurred = true;
                printError(INVALID_SERVICE_PACKAGE);
            }
        }

        // set java service info
        javaServiceInfo = new JavaServiceInfo(servicePackage, path, name, uncapitalize(name));
    }

    private static void setServiceName() throws Exception {
        // scan service name
        String name = scanInputString(INPUT_SERVICE_NAME);

        // check if service name is valid
        if (isServiceNameValid(name)) {
            errorOccurred = true;
            printError(INVALID_SERVICE_NAME);
        }

        // set java service info
        javaServiceInfo = new JavaServiceInfo(javaServiceInfo.getServicePackage(), javaServiceInfo.getServicePath(), name, uncapitalize(name));
    }

    private static void initializeServices() throws Exception {
        if (errorOccurred) return;
        println(BREAK_LINE);
        println(INIT_SERVICES_STARTED);
        implementServiceFileAndContent(declareJavaServices());
        printIfNoError(INIT_SERVICES_ENDED, errorOccurred);
        println(BREAK_LINE);
    }

    private static JavaServiceObject[] declareJavaServices() {
        return new JavaServiceObject[]{
                new JavaDao(javaServiceInfo),
                new JavaDaoImpl(javaServiceInfo),
                new JavaRepository(javaServiceInfo),
                new JavaController(javaServiceInfo),
                new JavaService(javaServiceInfo),
                new JavaServiceImpl(javaServiceInfo),
                new JavaTransformer(javaServiceInfo),
                new JavaMapper(javaServiceInfo),
        };
    }

    private static void implementServiceFileAndContent(JavaServiceObject[] services) throws Exception {
        JavaServiceInitialization javaServiceInitialization = new JavaServiceInitializer();
        for (JavaServiceObject service : services) {
            if (errorOccurred) return;
            println(INITIALIZE + service.getName() + STARTED);
            try {
                // check serviceDirectory exists and create if not
                boolean isDirectoryCreated = false;
                File serviceDirectory = new File(getServiceDirectoryPath(javaServiceInfo.getServicePath(), service.getPath()));
                if (!serviceDirectory.exists()) isDirectoryCreated = serviceDirectory.mkdir();
                if (isDirectoryCreated)
                    printIfNoError(HYPHEN_SPACE + service.getName() + DIRECTORY_CREATED, errorOccurred);

                // create java javaServiceInfo file and check if created
                File javaServiceFile = new File(getServiceJavaPath(javaServiceInfo.getServicePath(), service.getPath(), javaServiceInfo.getServiceName() + service.getName()));
                boolean isFileCreated = javaServiceFile.createNewFile();
                if (!isFileCreated)
                    printWarn(javaServiceInfo.getServiceName() + service.getName() + FILE_ALREADY_EXISTS);

                // write java javaServiceInfo file content
                FileWriter fileWriter = new FileWriter(javaServiceFile.getPath());
                fileWriter.write(service.initialize(javaServiceInitialization));
                fileWriter.close();
            } catch (Exception exception) {
                errorOccurred = true;
                printError(HYPHEN_SPACE + javaServiceInfo.getServiceName() + service.getName() + FILE);
            }
            printIfNoError(INITIALIZE + service.getName() + ENDED, errorOccurred);
        }
    }
}
