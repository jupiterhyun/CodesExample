/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.googleguiceexample.services;

import com.mycompany.googleguiceexample.interfaces.MessageService;

/**
 *
 * @author jupiter
 */
public class FacebookService implements MessageService {
 
    @Override
    public boolean sendMessage(String msg, String receipient) {
        //some complex code to send Facebook message
        System.out.println("Message sent to Facebook user "+receipient+" with message="+msg);
        return true;
    }
}
