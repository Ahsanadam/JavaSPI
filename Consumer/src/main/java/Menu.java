
import org.example.message.Message;
import org.example.message.MessageSender;
import java.util.Scanner;
import java.util.ServiceLoader;

public class Menu {
    Scanner scanner;

    public void run() {

        applicationGreeting();

        while (true) {
            System.out.println("\nIn which format do you want to write your message?");
            System.out.println("Write 1, as a push notification message.");
            System.out.println("Write 2, as an email message.");
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

    private void emailNotificationMessage() {
        scanner = new Scanner(System.in);
        System.out.println("Write your message:");
        String userInput = scanner.nextLine();

        ServiceLoader<MessageSender> serviceLoader = ServiceLoader.load(MessageSender.class);
        serviceLoader.stream()
                .map(ServiceLoader.Provider::get)
                .filter(messageSenderProvider -> messageSenderProvider.getClass().getSimpleName().startsWith("Email"))
                .forEach(messageSenderProvider -> messageSenderProvider.sendMessage(new Message(userInput)));

    }



    private void pushNotificationMessage() {
        scanner = new Scanner(System.in);
        System.out.println("Write your message:");
        String userInput = scanner.nextLine();

        ServiceLoader<MessageSender> serviceLoader = ServiceLoader.load(MessageSender.class);
        serviceLoader.stream()
                .map(ServiceLoader.Provider::get)
                .filter(messageSenderProvider -> messageSenderProvider.getClass().getSimpleName().startsWith("Push"))
                .forEach(messageSenderProvider -> messageSenderProvider.sendMessage(new Message(userInput)));

    }


    private void applicationGreeting() {

        System.out.println(
                """
                        =============================================================================
                        Hi! Welcome to our message provider!
                        """
        );

    }
}


