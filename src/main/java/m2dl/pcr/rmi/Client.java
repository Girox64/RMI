package m2dl.pcr.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client implements Serializable {

    private String name;
    private int test = 0;

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
                System.out.print(test + " Message ? ");
                String message = scanner.nextLine().trim();
                stub.sendBroadcast(message, this);
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void receive(String message) {
        test++;
        System.out.println(message);
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {

        try {
            new Client().run();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
