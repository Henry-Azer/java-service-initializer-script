package org.henry.jsis.initializer;

import org.henry.jsis.services.*;

/**
 * @author Henry Azer
 * @since 21/01/2023
 **/
public interface JavaServiceInitialization {
    String initialize(JavaService javaService);
    String initialize(JavaServiceImpl javaServiceImpl);
    String initialize(JavaRepository javaRepository);
    String initialize(JavaDao javaDao);
    String initialize(JavaDaoImpl javaDaoImpl);
    String initialize(JavaController javaController);
    String initialize(JavaTransformer javaTransformer);
    String initialize(JavaMapper javaMapper);
}