package org.henry.jsis.initializer;

import org.henry.jsis.services.*;

/**
 * @author Henry Azer
 * @since 21/01/2023
 **/
public class JavaServiceInitializer implements JavaServiceInitialization {
    @Override
    public String initialize(JavaService javaService) {
        return JavaServiceContentInitializer.initJavaServiceContent(javaService);
    }

    @Override
    public String initialize(JavaServiceImpl javaServiceImpl) {
        return JavaServiceContentInitializer.initJavaServiceImplContent(javaServiceImpl);
    }

    @Override
    public String initialize(JavaRepository javaRepository) {
        return JavaServiceContentInitializer.initJavaRepositoryContent(javaRepository);
    }

    @Override
    public String initialize(JavaDao javaDao) {
        return JavaServiceContentInitializer.initJavaDaoContent(javaDao);
    }

    @Override
    public String initialize(JavaDaoImpl javaDaoImpl) {
        return JavaServiceContentInitializer.initJavaDaoImplContent(javaDaoImpl);
    }

    @Override
    public String initialize(JavaController javaController) {
        return JavaServiceContentInitializer.initJavaControllerContent(javaController);
    }

    @Override
    public String initialize(JavaTransformer javaTransformer) {
        return JavaServiceContentInitializer.initJavaTransformerContent(javaTransformer);
    }

    @Override
    public String initialize(JavaMapper javaMapper) {
        return JavaServiceContentInitializer.initJavaMapperContent(javaMapper);
    }
}