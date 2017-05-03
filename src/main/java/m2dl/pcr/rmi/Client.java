package m2dl.pcr.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements IClient, Serializable {

    private String name;

    public Client() throws RemoteException {
        super();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom ? ");
        name = scanner.nextLine().trim();

        try {
            Registry registry = LocateRegistry.getRegistry(null);
            IServer stub = (IServer) registry.lookup("server");
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

        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        System.setProperty("java.rmi.server.codebase", IClient.class.getProtectionDomain().getCodeSource().getLocation().toString());

        try {
            Client obj = new Client();

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("client", (IClient) obj);

            System.err.println("Client ready");
            obj.run();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
