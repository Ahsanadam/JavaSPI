
import org.example.message.Message;
import org.example.message.MessageSender;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {

        ServiceLoader<MessageSender> serviceLoader = ServiceLoader.load(MessageSender.class);
        for( MessageSender messageSender  : serviceLoader ) {
                messageSender.sendMessage(new Message("Hello"));
        }

    }
}
