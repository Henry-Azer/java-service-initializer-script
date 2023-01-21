package org.henry.jsis.util;

import java.io.File;

import static org.henry.jsis.constants.JSISConstants.*;

/**
 * @author Henry Azer
 * @since 21/01/2023
 **/
public class JSISValidator {
    public static boolean isServicePathContainsServicePackage(String servicePath) {
        return servicePath.contains(JAVA_PACKAGE_PATH);
    }

    public static boolean isServicePathValid(String servicePath) {
        return isStringValid(servicePath) || !(new File(servicePath).isDirectory());
    }

    public static boolean isServiceNameValid(String serviceName) {
        return isStringValid(serviceName) || serviceName.contains(SPACE) || !serviceName.matches(ONLY_CHARS_REGEX);
    }

    public static boolean isStringValid(String content) {
        return content.isEmpty() || content.isBlank();
    }
}
