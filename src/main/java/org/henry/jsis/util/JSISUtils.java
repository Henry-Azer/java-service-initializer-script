package org.henry.jsis.util;

import java.util.Scanner;

import static org.henry.jsis.constants.JSISConstants.*;

/**
 * @author Henry Azer
 * @since 21/01/2023
 **/
public class JSISUtils {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String scanInputString(String message) {
        print(message);
        return SCANNER.nextLine();
    }

    public static void printWarn(String message) throws Exception {
        System.err.println(WARN + message);
        Thread.sleep(100);
    }

    public static void printError(String message) throws Exception {
        println(BREAK_LINE);
        System.err.println(ERROR + message);
        Thread.sleep(100);
    }

    public static void printIfNoError(String content, Boolean error) {
        if (!error) println(content);
    }

    public static void println(String content) {

        System.out.println(content);
    }

    public static void print(String content) {
        System.out.print(content);
    }

    public static void jsisInfoPrints() {
        println(BREAK_LINE);
        println(JSIS);
        println(CREATOR);
        println(DATE);
        println(BREAK_LINE);
    }

    public static String getPackageNameFromServicePath(String servicePath) {
        return servicePath.substring(servicePath.indexOf("\\src\\main\\java\\") + 15).replace("\\", ".");
    }

    public static String getServiceDirectoryPath(String basePath, String servicePath) {
        return basePath + servicePath;
    }

    public static String getServiceJavaPath(String basePath, String servicePath, String serviceName) {
        return basePath + servicePath + serviceName + JAVA_FILE_EXTENSION;
    }

    public static String uncapitalize(String str) {
        return uncapitalize(str, (char[]) null);
    }

    private static String uncapitalize(String str, char... delimiters) {
        int delimLen = delimiters == null ? -1 : delimiters.length;
        if (!str.isEmpty() && delimLen != 0) {
            char[] buffer = str.toCharArray();
            boolean uncapitalizeNext = true;
            for (int i = 0; i < buffer.length; ++i) {
                char ch = buffer[i];
                if (isDelimiter(ch, delimiters)) {
                    uncapitalizeNext = true;
                } else if (uncapitalizeNext) {
                    buffer[i] = Character.toLowerCase(ch);
                    uncapitalizeNext = false;
                }
            }
            return new String(buffer);
        } else {
            return str;
        }
    }

    private static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        } else {
            for (char delimiter : delimiters) {
                if (ch == delimiter) {
                    return true;
                }
            }
            return false;
        }
    }
}
