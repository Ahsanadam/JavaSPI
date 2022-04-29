
import org.example.message.Message;
import org.example.message.MessageCategory;
import org.example.message.MessageSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ServiceLoader;

public class Menu {
    Scanner scanner;

    public void run() {

        applicationGreeting();

        while (true) {
            System.out.println("\nIn which format do you want to write your message?");
            System.out.print("Write 1, if you want to write it as a ");
            pushNotificationMessageOption();
            System.out.print("Write 2, if you want to write it as an ");
            emailMessageOption();
            System.out.println("Write e, if you want to exit the application.");
            Scanner readUserInput = new Scanner(System.in);
            String choice = readUserInput.nextLine();

            switch (choice) {
                case "1" -> pushNotificationMessage();
                case "2" -> emailNotificationMessage();
                case "e" -> {
                    System.out.println(" Goodbye!");
                    return;
                }
            }
        }
    }

    private void emailMessageOption() {
        getMessageOptions().stream().filter(messageOptions -> messageOptions.startsWith("Email")).forEach(System.out::println);
    }

    private void pushNotificationMessageOption() {
        getMessageOptions().stream().filter(messageOptions -> messageOptions.startsWith("Push")).forEach(System.out::println);
    }


    private void emailNotificationMessage() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("Write your message:");
            String userInput = scanner.nextLine();

            ServiceLoader<MessageSender> serviceLoader = ServiceLoader.load(MessageSender.class);
            serviceLoader.stream()
                    .map(ServiceLoader.Provider::get)
                    .filter(messageSenderProvider -> messageSenderProvider.getClass().getSimpleName().startsWith("Email"))
                    .forEach(messageSenderProvider -> messageSenderProvider.sendMessage(new Message(userInput)));
        } catch (Exception e) {
            System.out.println("The format is invalid, try again");
        }

    }


    private void pushNotificationMessage() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("Write your message:");
            String userInput = scanner.nextLine();

            ServiceLoader<MessageSender> serviceLoader = ServiceLoader.load(MessageSender.class);
            serviceLoader.stream()
                    .map(ServiceLoader.Provider::get)
                    .filter(messageSenderProvider -> messageSenderProvider.getClass().getSimpleName().startsWith("Push"))
                    .forEach(messageSenderProvider -> messageSenderProvider.sendMessage(new Message(userInput)));
        } catch (Exception e) {
            System.out.println("The format is invalid, try again");
        }

    }



    private List<String> getMessageOptions() {
        ServiceLoader<MessageSender> serviceLoader = ServiceLoader.load(MessageSender.class);
        List<String> list = new ArrayList<>();

        for (MessageSender messageSender : serviceLoader) {
            MessageCategory annotation = messageSender.getClass().getAnnotation(MessageCategory.class);
            list.add(annotation.value());
        }
        return list;
    }


    private void applicationGreeting () {

        System.out.println(
                """
                        =============================================================================
                        Hi! Welcome to our message provider!
                        """
        );

    }
}

