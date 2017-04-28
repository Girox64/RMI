package m2dl.pcr.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by benja135 on 28/04/17.
 */
public interface IServer extends Remote {
    void sendMessage(String m, Client source, String dest) throws RemoteException;
    void sendBroadcast(String m, Client source) throws RemoteException;
    void enregistrement(Client client) throws RemoteException;
    void quitter(Client client) throws RemoteException;
}
