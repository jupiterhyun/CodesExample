/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseyexample.RESTfulApplication;

import javax.inject.Inject;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author jupiter
 */
public class RESTfulApplication extends ResourceConfig {
    @Inject
    public RESTfulApplication() {
        packages("com.mycompany.jerseyexample.resources");
    }
}
