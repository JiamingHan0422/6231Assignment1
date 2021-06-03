package ServerFile;

import java.io.File;
import java.rmi.Remote;

public interface rmiInterface extends Remote {
    public void createTRecord (String firstName, String lastName, String Address,
                               String Phone, String Specialization, String Location)throws java.rmi.RemoteException;

    public void createSRecord (String firstName, String lastName, String CoursesRegistered,
                               String multiple, String Status, String StatusDate)throws java.rmi.RemoteException;

    public void editRecord (String recordID, File fieldName, String newValue) throws java.rmi.RemoteException;

    public String getRecordCounts() throws java.rmi.RemoteException;
}
