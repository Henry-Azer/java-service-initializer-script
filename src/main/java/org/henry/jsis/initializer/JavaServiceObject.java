package org.henry.jsis.initializer;

import org.henry.jsis.services.JavaServiceInfo;

/**
 * @author Henry Azer
 * @since 21/01/2023
 **/
public interface JavaServiceObject {
    String getPath();
    String getName();
    JavaServiceInfo getJavaServiceInfo();
    String initialize(JavaServiceInitialization javaServiceInitialization);
}