package m2dl.pcr.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by benja135 on 28/04/17.
 */
public interface IClient extends Remote {
    void receive(String message) throws RemoteException;
}
