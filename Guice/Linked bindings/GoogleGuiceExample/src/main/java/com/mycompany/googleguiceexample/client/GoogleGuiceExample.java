/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.googleguiceexample.client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycompany.googleguiceexample.consumer.MyApplication;
import com.mycompany.googleguiceexample.injector.AppInjector;

/**
 *
 * @author jupiter
 */
public class GoogleGuiceExample {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppInjector());        
        MyApplication app = injector.getInstance(MyApplication.class);
        app.sendMessage("Hi Xin", "pankaj@abc.com");
    }
}
