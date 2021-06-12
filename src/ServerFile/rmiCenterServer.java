package ServerFile;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface rmiCenterServer extends Remote {
    /**
 * 远程接口方法必须抛出 java.rmi.RemoteException
     * @return
     */
    // 所有方法必须抛出RemoteException


    public boolean createTRecord (String managerID, String firstName, String lastName, String Address,
                                  String Phone, String Specialization, String Location)throws java.rmi.RemoteException;

    public boolean createSRecord (String managerID, String firstName, String lastName, String CoursesRegistered,
                                  String Status, String StatusDate)throws java.rmi.RemoteException;

    public boolean editRecord (String managerID, String recordID, String fieldName, String newValue) throws java.rmi.RemoteException;

    public boolean printRecord (String ManagerID) throws java.rmi.RemoteException;


    public String getRecordCounts() throws java.rmi.RemoteException;
}
