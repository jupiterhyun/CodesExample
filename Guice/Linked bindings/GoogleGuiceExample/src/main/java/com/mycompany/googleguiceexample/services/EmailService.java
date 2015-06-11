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
public class EmailService implements MessageService {
 
    @Override
    public boolean sendMessage(String msg, String receipient) {
        //some fancy code to send email
        System.out.println("Email Message sent to "+receipient+" with message="+msg);
        return true;
    }
}
