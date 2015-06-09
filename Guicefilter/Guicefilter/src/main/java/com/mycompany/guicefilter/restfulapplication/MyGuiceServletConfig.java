/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guicefilter.restfulapplication;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.mycompany.guicefilter.resources.Myresource;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
/**
 *
 * @author jupiter
 */
public class MyGuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
              bind(Myresource.class);
              filter("/*").through(GuiceContainer.class);
            }
        });
    }
}
