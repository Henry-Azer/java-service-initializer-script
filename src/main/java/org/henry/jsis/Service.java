package org.henry.jsis;

/**
 * @author Henry Azer
 * @since 20/01/2023
 **/
enum Service {
    REPOSITORY("Repo", "/dao/repo/"), DAO("Dao", "/dao/"), DAO_IMPL("DaoImpl", "/dao/"),
    TRANSFORMER("Transformer", "/transformer/"), MAPPER("Mapper", "/transformer/mapper/"),
    SERVICE("Service", "/service/"), SERVICE_IMPL("ServiceImpl", "/service/"),
    CONTROLLER("Controller", "/controller/");

    private final String name;
    private final String path;

    Service(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}