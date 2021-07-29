package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;



public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        // All the web servies to be registered Here
//        register(StudentRestAPI.class);
//        register(UserRestAPI.class);
//        register(ProfessorRestAPI.class);
//        register(AdminRestAPI.class);
        register(UserRESTAPI.class);
        register(AdminRESTAPI.class);
        register(ProfessorRESTAPI.class);
        register(StudentRESTAPI.class);
        register(HelloController.class);

    }

}