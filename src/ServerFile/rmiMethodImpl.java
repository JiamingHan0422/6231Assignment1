package ServerFile;


import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class rmiMethodImpl extends UnicastRemoteObject implements rmiInterface {

    protected rmiMethodImpl() throws RemoteException {
        super();
    }

    @Override
    public void createTRecord(String firstName, String lastName, String Address, String Phone, String Specialization, String Location) throws RemoteException {

    }

    @Override
    public void createSRecord(String firstName, String lastName, String CoursesRegistered, String multiple, String Status, String StatusDate) throws RemoteException {

    }

    @Override
    public void editRecord(String recordID, File fieldName, String newValue) throws RemoteException {

    }

    @Override
    public String getRecordCounts() throws RemoteException {
        return null;
    }
}
