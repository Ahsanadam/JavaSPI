package org.example.provide;

import org.example.message.Message;
import org.example.message.MessageSender;

public class EmailMessage implements MessageSender {

    @Override
    public void sendMessage(Message message) {
        System.out.println("Sending Email with message = " + message.message);

    }
}
