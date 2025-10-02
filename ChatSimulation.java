import java.util.Scanner;    //prajwal kapase (C02066)

class ChatUser extends Thread {
    private String name;
    private volatile boolean running = true;

    public ChatUser(String name) {
        this.name = name;
    }

    // Called by main thread for message delivery
    public void postMessage(String msg, boolean isPriority) {
        if (isPriority) {
            System.out.println("[HIGH PRIORITY] " + name + ": " + msg);
        } else {
            System.out.println(name + ": " + msg);
        }
    }

    public void stopUser() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(100); // simulate waiting for messages
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println(name + " stopped.");
    }
}

public class ChatSimulation {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        ChatUser    alice = new ChatUser("alice");
        ChatUser bob = new ChatUser("bob");
        ChatUser carol = new ChatUser("carol");

        alice.setPriority(Thread.NORM_PRIORITY);
        bob.setPriority(Thread.MAX_PRIORITY); // bob is high-priority
        carol.setPriority(Thread.MIN_PRIORITY);

        alice.start();
        bob.start();
     carol.start();

        for (int i = 0; i < 2; i++) {
            System.out.print("Enter message for alice: ");
            String msgA = scanner.nextLine();
            alice.postMessage(msgA,    alice.getPriority() == Thread.MAX_PRIORITY);

            System.out.print("Enter message for bob: ");
            String msgB = scanner.nextLine();
            bob.postMessage(msgB, bob.getPriority() == Thread.MAX_PRIORITY);

            System.out.print("Enter message for carol: ");
            String msgC = scanner.nextLine();
         carol.postMessage(msgC, carol.getPriority() == Thread.MAX_PRIORITY);

            System.out.println();
        }

        alice.stopUser();
        bob.stopUser();
     carol.stopUser();

        alice.join();
        bob.join();
        carol.join();

        scanner.close();
        System.out.println("All users stopped.");
    }
}
