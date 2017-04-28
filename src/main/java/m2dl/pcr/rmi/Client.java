package m2dl.pcr.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client implements IClient {

    private String name;

    public Client() {
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom ? ");
        name = scanner.nextLine().trim();

        try {
            Registry registry = LocateRegistry.getRegistry(null);
            IServer stub = (IServer) registry.lookup("IServer");
            stub.enregistrement(this);

            while (true) {
                scanner = new Scanner(System.in);
                System.out.print("Message ? ");
                String message = scanner.nextLine().trim();
                stub.sendBroadcast(message, this);
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void receive(String message) {
        System.out.println(message);
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        new Client().run();
    }
}
