package ServerFile;

import java.io.File;
import java.rmi.Remote;

public interface rmiInterface extends Remote {
    public void editRecord (String recordID, File fieldName, String newValue) throws java.rmi.RemoteException;
    public String getRecordCounts() throws java.rmi.RemoteException;
}
