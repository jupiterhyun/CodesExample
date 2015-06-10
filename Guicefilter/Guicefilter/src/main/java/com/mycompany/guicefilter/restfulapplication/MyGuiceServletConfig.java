/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guicefilter.restfulapplication;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.AbstractModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.mycompany.guicefilter.resources.Myresource;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import com.sun.jersey.guice.JerseyServletModule;
import java.util.*;
/**
 *
 * @author jupiter
 */
public class MyGuiceServletConfig extends GuiceServletContextListener {
/* 简单写法
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
*/
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new SomeModule(), new JerseyModule());
    }
    private static class SomeModule extends AbstractModule {
        @Override
        protected void configure() {
            //injection statements
        }
    }
    private static class JerseyModule extends JerseyServletModule {
        @Override
        protected void configureServlets() {
            Map<String, String> params = new HashMap<>();
            params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.mycompany.guicefilter.resources");
            params.put(ServletContainer.FEATURE_FILTER_FORWARD_ON_404, "true");
            //serve( "/*" ).with( GuiceContainer.class );
            //bind(Myresource.class);
            filter("/*").through(GuiceContainer.class, params);
        }
    }
}
