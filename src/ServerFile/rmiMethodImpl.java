package ServerFile;


import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class rmiMethodImpl extends UnicastRemoteObject implements rmiInterface {

    protected rmiMethodImpl() throws RemoteException {
        super();
    }

    @Override
    public void editRecord(String recordID, File fieldName, String newValue) throws RemoteException {

    }

    @Override
    public String getRecordCounts() throws RemoteException {
        return null;
    }
}
