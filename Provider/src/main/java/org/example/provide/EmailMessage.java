package org.example.provide;

import org.example.message.Message;

public class EmailMessage implements Message {

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending Email with Message = "+message);
    }
}
