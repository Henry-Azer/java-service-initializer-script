package org.henry.jsis.services;

import org.henry.jsis.initializer.JavaServiceInitialization;
import org.henry.jsis.initializer.JavaServiceObject;

/**
 * @author Henry Azer
 * @since 21/01/2023
 **/
public class JavaMapper implements JavaServiceObject {
    protected final JavaServiceInfo javaServiceInfo;
    protected final String name = "Mapper";
    protected final String path = "/transformer/mapper/";

    public JavaMapper(JavaServiceInfo javaServiceInfo) {
        this.javaServiceInfo = javaServiceInfo;
    }

    @Override
    public String initialize(JavaServiceInitialization javaServiceInitialization) {
        return javaServiceInitialization.initialize(this);
    }

    @Override
    public JavaServiceInfo getJavaServiceInfo() {
        return javaServiceInfo;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPath() {
        return path;
    }
}
