package m2dl.pcr.rmi;

import java.io.File;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {

    public Server() {}

    public String sayHello() {
        return "Hello, world!";
    }

    public static void main(String args[]) {

        System.setProperty("java.rmi.server.hostname","127.0.0.1");
        //File file = new File("/home/benja135/Documents/M2/PCR/TP/m2-pcr-rmi-etu-master/src/main/java/m2dl/pcr/rmi/Hello.java");
        //System.setProperty("java.rmi.server.codebase", file.toURI().toString());

        System.setProperty("java.rmi.server.codebase", Hello.class.getProtectionDomain().getCodeSource().getLocation().toString());

        try {
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
