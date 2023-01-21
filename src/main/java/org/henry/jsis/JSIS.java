package org.henry.jsis;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author Henry Azer
 * @since 20/01/2023
 * @description java service initializer script
 **/
public class JSIS {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static String SERVICE_PACKAGE;
    private static String SERVICE_PATH;
    private static String SERVICE_NAME;
    private static Boolean ERROR = false;

    public static void main(String[] args) throws Exception {
        jsisInfoPrints();
        jsisProcess();
        jsisInfoPrints();
    }

    private static void jsisProcess() throws Exception {
        boolean hasAnother;
        do {
            getServiceParams();
            initializeServices();
            if (ERROR) return;
            System.out.print("Another Service? (yes, no): ");
            String choice = SCANNER.nextLine();
            hasAnother = choice.equalsIgnoreCase("yes");
            if (hasAnother) System.out.println("-----------------------------------------");
        } while (hasAnother);
    }

    private static void initializeServices() throws Exception {
        if (ERROR) return;
        System.out.println("-----------------------------------------");
        printIfNoError("*** Initializing services -- started");
        initializeDao();
        initializeRepository();
        initializeTransformer();
        initializeMapper();
        initializeService();
        initializeController();
        printIfNoError("*** Initializing services -- ended");
        System.out.println("-----------------------------------------");
    }

    private static void initializeController() throws Exception {
        if (ERROR) return;
        printIfNoError("** Initialize controller -- started");
        implementServiceFileWithContent(Service.CONTROLLER);
        printIfNoError("** Initialize controller -- ended");
    }

    private static void initializeService() throws Exception {
        if (ERROR) return;
        printIfNoError("** Initialize service -- started");
        implementServiceFileWithContent(Service.SERVICE);
        implementServiceFileWithContent(Service.SERVICE_IMPL);
        printIfNoError("** Initialize service -- end");
    }

    private static void initializeTransformer() throws Exception {
        if (ERROR) return;
        printIfNoError("** Initialize transformer -- started");
        implementServiceFileWithContent(Service.TRANSFORMER);
        printIfNoError("** Initialize transformer -- end");
    }

    private static void initializeMapper() throws Exception {
        if (ERROR) return;
        printIfNoError("** Initialize mapper -- started");
        implementServiceFileWithContent(Service.MAPPER);
        printIfNoError("** Initialize mapper -- end");
    }

    private static void initializeDao() throws Exception {
        if (ERROR) return;
        printIfNoError("** Initialize dao -- started");
        implementServiceFileWithContent(Service.DAO);
        implementServiceFileWithContent(Service.DAO_IMPL);
        printIfNoError("** Initialize dao -- end");
    }

    private static void initializeRepository() throws Exception {
        printIfNoError("** Initialize repository -- started");
        implementServiceFileWithContent(Service.REPOSITORY);
        printIfNoError("** Initialize repository -- end");
    }

    private static void implementServiceFileWithContent(Service service) throws Exception {
        // check serviceDirectory exists and create if not
        File serviceDirectory = new File(getServiceDirectoryPath(service));
        if (!serviceDirectory.exists()) {
            boolean isDirectoryCreated = serviceDirectory.mkdir();
            if (isDirectoryCreated) printIfNoError("- " + service.getName() + " directory created successfully");
        }

        // create java service file
        File javaServiceFile = new File(getServiceJavaPath(service));
        try {
            // check if  java service file created
            boolean isFileCreated = javaServiceFile.createNewFile();
            if (!isFileCreated) printWarn(SERVICE_NAME + service.getName() + " file already exists");

            // write java service file content
            FileWriter fileWriter = new FileWriter(javaServiceFile.getPath());
            fileWriter.write(ServiceContent.getServiceContent(service.getName(), SERVICE_NAME , SERVICE_PACKAGE));
            fileWriter.close();
        } catch (Exception exception) {
            printError("- " + SERVICE_NAME + service.getName() + " file");
        }
    }

    private static String getServiceDirectoryPath(Service service) {
        return SERVICE_PATH + service.getPath();
    }

    private static String getServiceJavaPath(Service service) {
        return SERVICE_PATH + service.getPath() + SERVICE_NAME + service.getName() + ".java";
    }

    private static void getServiceParams() throws Exception {
        // get service path and name
        System.out.print("Service Path: ");
        SERVICE_PATH = SCANNER.nextLine();
        System.out.print("Service Name: ");
        SERVICE_NAME = SCANNER.nextLine();
        System.out.print("Package Name: ");
        SERVICE_PACKAGE = SCANNER.nextLine();
        // check if service path, package and name are valid
        checkIfServicePathValid();
        checkIfServiceNameValid();
        checkIfServicePackageValid();
    }

    private static void checkIfServicePackageValid() throws Exception {
        // get service package name form service path if not specified
        if (SERVICE_PACKAGE.isBlank() || SERVICE_PACKAGE.isEmpty()) {
            if (SERVICE_PATH.contains("\\src\\main\\java\\"))
                SERVICE_PACKAGE = SERVICE_PATH.substring(SERVICE_PATH.indexOf("\\src\\main\\java\\") + 15).replace("\\", ".");
            else printError("Invalid service package");
        }
    }

    private static void checkIfServicePathValid() throws Exception {
        // check if service path is valid and is directory
        if (isStringValid(SERVICE_PATH) || (!new File(SERVICE_PATH).isDirectory())) printError("Invalid service path");
    }

    private static void checkIfServiceNameValid() throws Exception {
        // check if service name is valid or not only characters
        if (isStringValid(SERVICE_NAME) || SERVICE_NAME.contains(" ") || !SERVICE_NAME.matches("[a-zA-Z]+"))
            printError("Invalid service name");
    }

    private static boolean isStringValid(String content) {
        return content.isEmpty() || content.isBlank();
    }

    private static void jsisInfoPrints() {
        System.out.println("-----------------------------------------");
        System.out.println("---> Java Service Initializer Script <---");
        System.out.println("-------> Creator : Henry Azer XD <-------");
        System.out.println("----------> Date : 20/01/2023 <----------");
        System.out.println("-----------------------------------------");
    }

    private static void printError(String message) throws Exception {
        System.out.println("-----------------------------------------");
        System.err.println("ERROR: " + message);
        Thread.sleep(100);
        ERROR = true;
    }

    private static void printWarn(String message) throws Exception {
        System.err.println("WARN! - " + message);
        Thread.sleep(100);
    }

    private static void printIfNoError(String content) {
        if (ERROR) return;
        System.out.println(content);
    }
}
