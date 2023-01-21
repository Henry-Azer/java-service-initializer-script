package org.henry.jsis;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Henry Azer
 * @description java service initializer script
 * @since 20/01/2023
 **/
public class JSIS {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static String SERVICE_PACKAGE;
    private static String SERVICE_PATH;
    private static String SERVICE_NAME;
    private static Boolean ERROR = false;

    public static void main(String[] args) throws InterruptedException {
        jsisInfoPrints();
        jsisProcess();
        jsisInfoPrints();
    }

    private static void jsisProcess() throws InterruptedException {
        boolean hasAnother;
        do {
            getServiceParams();
            initializeServices();
            System.out.print("Another Service? (yes, no): ");
            String choice = SCANNER.nextLine();
            hasAnother = choice.equalsIgnoreCase("yes");
            if (hasAnother) System.out.println("-----------------------------------------");
        } while (hasAnother);
    }

    private static void initializeServices() throws InterruptedException {
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

    private static void initializeController() throws InterruptedException {
        if (ERROR) return;
        printIfNoError("** Initialize controller -- started");
        createServiceFile(Service.CONTROLLER);

        printIfNoError("** Initialize controller -- ended");
    }

    private static void initializeService() throws InterruptedException {
        if (ERROR) return;
        printIfNoError("** Initialize service -- started");
        createServiceFile(Service.SERVICE);

        createServiceFile(Service.SERVICE_IMPL);

        printIfNoError("** Initialize service -- end");
    }

    private static void initializeTransformer() throws InterruptedException {
        if (ERROR) return;
        printIfNoError("** Initialize transformer -- started");
        createServiceFile(Service.TRANSFORMER);

        printIfNoError("** Initialize transformer -- end");
    }

    private static void initializeMapper() throws InterruptedException {
        if (ERROR) return;
        printIfNoError("** Initialize mapper -- started");
        createServiceFile(Service.MAPPER);

        printIfNoError("** Initialize mapper -- end");
    }

    private static void initializeDao() throws InterruptedException {
        if (ERROR) return;
        printIfNoError("** Initialize dao -- started");
        createServiceFile(Service.DAO);

        createServiceFile(Service.DAO_IMPL);

        printIfNoError("** Initialize dao -- end");
    }

    private static void initializeRepository() throws InterruptedException {
        printIfNoError("** Initialize repository -- started");
        createServiceFile(Service.REPOSITORY);

        printIfNoError("** Initialize repository -- end");
    }

    private static void createServiceFile(Service service) throws InterruptedException {
        // check serviceDirectory exists and create if not
        File serviceDirectory = new File(getServiceDirectoryPath(service));
        if (!serviceDirectory.exists()) {
            boolean isDirectoryCreated = serviceDirectory.mkdir();
            if (isDirectoryCreated) printIfNoError("- " + service.getName() + " directory created successfully");
        }
        // create java service file
        File javaServiceFile = new File(getServiceJavaPath(service));
        try {
            boolean isFileCreated = javaServiceFile.createNewFile();
            if (!isFileCreated) printWarn(SERVICE_NAME + service.getName() + " file already exists");
        } catch (IOException exception) {
            printError("- " + SERVICE_NAME + service.getName() + " file creation");
        }
    }

    private static String getServiceDirectoryPath(Service service) {
        return SERVICE_PATH + service.getPath();
    }

    private static String getServiceJavaPath(Service service) {
        return SERVICE_PATH + service.getPath() + SERVICE_NAME + service.getName() + ".java";
    }

    private static void getServiceParams() throws InterruptedException {
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

    private static void checkIfServicePackageValid() throws InterruptedException {
        // get service package name form service path if not specified
        if (SERVICE_PACKAGE.isBlank() || SERVICE_PACKAGE.isEmpty()) {
            if (SERVICE_PATH.contains("\\src\\main\\java\\"))
                SERVICE_PACKAGE = SERVICE_PATH.substring(SERVICE_PATH.indexOf("\\src\\main\\java\\") + 15).replace("\\", ".");
            else printError("Invalid service package");
        }
    }

    private static void checkIfServicePathValid() throws InterruptedException {
        // check if service path is valid and is directory
        if (isStringValid(SERVICE_PATH) || (!new File(SERVICE_PATH).isDirectory())) printError("Invalid service path");
    }

    private static void checkIfServiceNameValid() throws InterruptedException {
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

    private static void printError(String message) throws InterruptedException {
        System.out.println("-----------------------------------------");
        System.err.println("ERROR: " + message);
        Thread.sleep(100);
        ERROR = true;
    }

    private static void printWarn(String message) throws InterruptedException {
        System.err.println("WARN! - " + message);
        Thread.sleep(100);
    }

    private static void printIfNoError(String content) {
        if (ERROR) return;
        System.out.println(content);
    }
}
