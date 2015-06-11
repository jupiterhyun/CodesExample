/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.googleguiceexample.injector;

import com.google.inject.AbstractModule;
import com.mycompany.googleguiceexample.interfaces.MessageService;
import com.mycompany.googleguiceexample.services.FacebookService;
import com.mycompany.googleguiceexample.services.EmailService;

/**
 *
 * @author jupiter
 */
public class AppInjector extends AbstractModule {
 
    @Override
    protected void configure() {
        //bind the service to implementation class
        //bind(MessageService.class).to(EmailService.class);
         
        //bind MessageService to Facebook Message implementation
        bind(MessageService.class).to(FacebookService.class);
    }
}
