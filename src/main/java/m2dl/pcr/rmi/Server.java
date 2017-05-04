package m2dl.pcr.rmi;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server extends UnicastRemoteObject implements IServer {
    List<Client> clients = new ArrayList<Client>();

    public Server() throws RemoteException {
        super();
    }

    public void sendMessage(String message, Client source, String dest) throws RemoteException {
        for (Client c : clients) {
            if (c.getName().equals(dest)) {
                c.receive(source.getName() + " : " + message);
            }
        }
    }

    public void sendBroadcast(String message, Client source) throws RemoteException {
        for (Client c : clients) {
            c.receive(source.getName() + " : " + message);
        }
    }

    public void enregistrement(Client client) throws RemoteException {
        clients.add(client);
    }

    public void quitter(Client client) throws RemoteException {
        clients.remove(client);
    }

    public static void main(String args[]) {

        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        System.setProperty("java.rmi.server.codebase", IServer.class.getProtectionDomain().getCodeSource().getLocation().toString());

        try {
            Server obj = new Server();

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("server", obj);

            System.err.println("Server ready");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
