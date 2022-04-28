package org.example.provide;

import org.example.message.Message;
import org.example.message.MessageSender;

public class PushNotificationMessage implements MessageSender {
    @Override
    public void sendMessage(Message message) {
        System.out.println("Sending Push Notification with message = " + message.message);
    }
}
