package m2dl.pcr.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
    void sendMessage(String message, Client source, String dest) throws RemoteException;
    void sendBroadcast(String message, Client source) throws RemoteException;
    void enregistrement(Client client) throws RemoteException;
    void quitter(Client client) throws RemoteException;
}
