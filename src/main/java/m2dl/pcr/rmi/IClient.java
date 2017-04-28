package m2dl.pcr.rmi;

import java.rmi.RemoteException;

/**
 * Created by benja135 on 28/04/17.
 */
public interface IClient {
    void receive(String message) throws RemoteException;
    String getName() throws RemoteException;
}
