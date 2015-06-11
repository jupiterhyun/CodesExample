/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.googleguiceexample.consumer;

import com.google.inject.Inject;
import com.mycompany.googleguiceexample.interfaces.MessageService;

/**
 *
 * @author jupiter
 */
public class MyApplication {
    private MessageService service;
    //setter method injector
    @Inject
    public void setService(MessageService svc){
        this.service=svc;
    }
     
    public boolean sendMessage(String msg, String rec){
        //some business logic here
        return service.sendMessage(msg, rec);
    }
    
}
