package org.henry.jsis.services;

public class JavaServiceInfo {
    private final String servicePackage;
    private final String servicePath;
    private final String serviceName;
    private final String serviceNameUncapitalized;

    public JavaServiceInfo(String servicePackage, String servicePath, String serviceName, String serviceNameUncapitalized) {
        this.servicePackage = servicePackage;
        this.servicePath = servicePath;
        this.serviceName = serviceName;
        this.serviceNameUncapitalized = serviceNameUncapitalized;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public String getServicePath() {
        return servicePath;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceNameUncapitalized() {
        return serviceNameUncapitalized;
    }
}
