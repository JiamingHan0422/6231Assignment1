package ServerFile;


import recordFile.Record;
import recordFile.StudentRecord;
import recordFile.TeacherRecord;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 服务器端实现远程接口。
 * 必须继承UnicastRemoteObject，以允许JVM创建远程的存根/代理。
 */
public class rmiMethodImpl extends UnicastRemoteObject implements rmiInterface {

    HashMap<Character, ArrayList<Record>> HashMapMTL = new HashMap<Character, ArrayList<Record>>();
    HashMap<Character, ArrayList<Record>> HashMapLVL = new HashMap<Character, ArrayList<Record>>();
    HashMap<Character, ArrayList<Record>> HashMapDDO = new HashMap<Character, ArrayList<Record>>();


    protected rmiMethodImpl() throws RemoteException {

        super();
    }

    @Override
    public boolean createTRecord(String managerID, String firstName, String lastName, String Address, String Phone, String Specialization, String Location) throws RemoteException {

        System.out.println(managerID);
        System.out.println(firstName);
        System.out.println(lastName);

        TeacherRecord NewTRecord = new TeacherRecord(firstName, lastName, Address, Phone, Specialization, Location);
        ArrayList<Record> Recordlist = new ArrayList<>();


        if(managerID.startsWith("MTL")){
            char Mark;
            Mark = firstName.charAt(0);
            if(HashMapMTL.containsKey(Mark)){
                Recordlist = HashMapMTL.get(Mark);
                Recordlist.add(NewTRecord);
                HashMapMTL.replace(Mark, Recordlist);

                /**
                for(int i = 0; i < HashMapMTL.get(Mark).size(); i++) {
                    System.out.println(HashMapMTL.get(Mark).get(i).firstName + "\n");
                }
                 **/
            }
            else{
                Recordlist.add(NewTRecord);
                HashMapMTL.put(Mark, Recordlist);
            }

        }
        else if(managerID.startsWith("LVL")){

            char Mark;
            Mark = firstName.charAt(0);
            if(HashMapLVL.containsKey(Mark)){
                Recordlist = HashMapLVL.get(Mark);
                Recordlist.add(NewTRecord);
                HashMapLVL.replace(Mark, Recordlist);

            }
            else{
                Recordlist.add(NewTRecord);
                HashMapLVL.put(Mark, Recordlist);
            }
        }
        else if(managerID.startsWith("DDO")){

            char Mark;
            Mark = firstName.charAt(0);
            if(HashMapDDO.containsKey(Mark)){
                Recordlist = HashMapDDO.get(Mark);
                Recordlist.add(NewTRecord);
                HashMapDDO.replace(Mark, Recordlist);

            }
            else{
                Recordlist.add(NewTRecord);
                HashMapDDO.put(Mark, Recordlist);
            }
        }
        else{
            System.out.println("Access Deny!(ManagerID is invalid)");
        }

        return true;
    }

    @Override
    public boolean createSRecord(String managerID, String firstName, String lastName, String CoursesRegistered, String multiple, String Status, String StatusDate) throws RemoteException {
        System.out.println(managerID);
        System.out.println(firstName);
        System.out.println(lastName);

        StudentRecord NewSRecord = new StudentRecord(firstName, lastName, CoursesRegistered, multiple, Status, StatusDate);
        ArrayList<Record> Recordlist = new ArrayList<>();

        if(managerID.startsWith("MTL")){
            char Mark;
            Mark = firstName.charAt(0);
            if(HashMapMTL.containsKey(Mark)){
                Recordlist = HashMapMTL.get(Mark);
                Recordlist.add(NewSRecord);
                HashMapMTL.replace(Mark, Recordlist);

            }
            else{
                Recordlist.add(NewSRecord);
                HashMapMTL.put(Mark, Recordlist);
            }
        }

        else if(managerID.startsWith("LVL")){

            char Mark;
            Mark = firstName.charAt(0);
            if(HashMapLVL.containsKey(Mark)){
                Recordlist = HashMapLVL.get(Mark);
                Recordlist.add(NewSRecord);
                HashMapLVL.replace(Mark, Recordlist);

            }
            else{
                Recordlist.add(NewSRecord);
                HashMapLVL.put(Mark, Recordlist);
            }
        }
        else if(managerID.startsWith("DDO")){

            char Mark;
            Mark = firstName.charAt(0);
            if(HashMapDDO.containsKey(Mark)){
                Recordlist = HashMapDDO.get(Mark);
                Recordlist.add(NewSRecord);
                HashMapDDO.replace(Mark, Recordlist);

            }
            else{
                Recordlist.add(NewSRecord);
                HashMapDDO.put(Mark, Recordlist);
            }
        }
        else{
            System.out.println("Access Deny!(ManagerID is invalid)");
        }
        return true;

    }

    @Override
    public void editRecord(String recordID, File fieldName, String newValue) throws RemoteException {

    }

    @Override
    public int getRecordCounts() throws RemoteException {
        return 0;
    }
}
