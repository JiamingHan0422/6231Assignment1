package ServerFile;

import java.rmi.Remote;

public interface rmiCenterServer extends Remote {
    /**
 * remote interface method should throw java.rmi.RemoteException
     * @return
     */
    // all the method should throw RemoteException


    public boolean createTRecord (String managerID, String firstName, String lastName, String Address,
                                  String Phone, String Specialization, String Location)throws java.rmi.RemoteException;

    public boolean createSRecord (String managerID, String firstName, String lastName, String CoursesRegistered,
                                  String Status, String StatusDate)throws java.rmi.RemoteException;

    public boolean editRecord (String managerID, String recordID, String fieldName, String newValue) throws java.rmi.RemoteException;

    public boolean printRecord (String ManagerID) throws java.rmi.RemoteException;


    public String getRecordCounts() throws java.rmi.RemoteException;


}
