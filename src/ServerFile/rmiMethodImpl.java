package ServerFile;


import recordFile.Record;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * 服务器端实现远程接口。
 * 必须继承UnicastRemoteObject，以允许JVM创建远程的存根/代理。
 */
public class rmiMethodImpl extends UnicastRemoteObject implements rmiInterface {

    HashMap<Character, Record> HashMapMTL = new HashMap<Character, Record>();
    HashMap<Character, Record> HashMapLVL = new HashMap<Character, Record>();
    HashMap<Character, Record> HashMapDDO = new HashMap<Character, Record>();


    protected rmiMethodImpl() throws RemoteException {

        super();
    }

    @Override
    public boolean createTRecord(String managerID, String firstName, String lastName, String Address, String Phone, String Specialization, String Location) throws RemoteException {

        System.out.println(managerID);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(Address);
        System.out.println(Phone);
        System.out.println(Specialization);

        return true;
    }

    @Override
    public void createSRecord(String managerID, String firstName, String lastName, String CoursesRegistered, String multiple, String Status, String StatusDate) throws RemoteException {

    }

    @Override
    public void editRecord(String recordID, File fieldName, String newValue) throws RemoteException {

    }

    @Override
    public String getRecordCounts() throws RemoteException {
        return null;
    }
}
