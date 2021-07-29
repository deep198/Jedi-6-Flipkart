package com.dropwizard;

import com.flipkart.controller.AdminRESTAPI;
import com.flipkart.controller.HelloRestController;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Hello world!
 *
 */
public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        //  e.jersey().register(new EmployeeRESTController(e.getValidator()));
        e.jersey().register(new HelloRestController());
        e.jersey().register(new Hello2Controller());
        e.jersey().register(new AdminRESTAPI());
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}