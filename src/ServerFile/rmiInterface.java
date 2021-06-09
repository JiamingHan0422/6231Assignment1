package ServerFile;

import java.io.File;
import java.rmi.Remote;

public interface rmiInterface extends Remote {
    /**
 * 远程接口方法必须抛出 java.rmi.RemoteException
     * @return
     */
    // 所有方法必须抛出RemoteException


    public boolean createTRecord (String managerID, String firstName, String lastName, String Address,
                                  String Phone, String Specialization, String Location)throws java.rmi.RemoteException;

    public void createSRecord (String managerID, String firstName, String lastName, String CoursesRegistered,
                               String multiple, String Status, String StatusDate)throws java.rmi.RemoteException;

    public void editRecord (String recordID, File fieldName, String newValue) throws java.rmi.RemoteException;

    public String getRecordCounts() throws java.rmi.RemoteException;
}
