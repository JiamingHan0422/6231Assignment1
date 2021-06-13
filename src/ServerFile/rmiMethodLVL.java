package ServerFile;


import recordFile.Record;
import recordFile.StudentRecord;
import recordFile.TeacherRecord;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * 服务器端实现远程接口。
 * 必须继承UnicastRemoteObject，以允许JVM创建远程的存根/代理。
 */
public class rmiMethodLVL extends UnicastRemoteObject implements rmiCenterServer {

    HashMap<Character, ArrayList<Record>> HashMapMTL = new HashMap<Character, ArrayList<Record>>();
    HashMap<Character, ArrayList<Record>> HashMapLVL = new HashMap<Character, ArrayList<Record>>();
    HashMap<Character, ArrayList<Record>> HashMapDDO = new HashMap<Character, ArrayList<Record>>();

    File loggingFile = new File("");
    String FilePath = loggingFile.getAbsolutePath();
    int LVLcount = 0;

    public rmiMethodLVL() throws RemoteException {

        super();
        loggingFile = new File( FilePath + "/" + "LogFile" + "/" + "LVLFile"+ "/" + "LVLLog" +".txt");
        if(!loggingFile.exists()){
            try {
                loggingFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeLog(String log){
        if(!this.loggingFile.exists()){
            try {
                this.loggingFile.createNewFile();
                FileWriter fileWriter = new FileWriter(this.loggingFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(log);
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                synchronized (this.loggingFile) {
                    FileWriter fileWriter = new FileWriter(this.loggingFile, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(log);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean createTRecord(String managerID, String firstName, String lastName, String Address, String Phone, String Specialization, String Location) throws RemoteException {

        if(!(Location.equals("mtl")||Location.equals("lvl")||Location.equals("ddo"))){
            System.out.println("The location is invalid.");
            return false;
        }
        TeacherRecord NewTRecord = new TeacherRecord(firstName, lastName, Address, Phone, Specialization, Location);
        LVLcount++;
        NewTRecord.setRecordID(LVLcount);
        ArrayList<Record> Recordlist = new ArrayList<>();


        if(managerID.startsWith("MTL")){
            char Mark;
            Mark = firstName.charAt(0);
            if(HashMapMTL.containsKey(Mark)){
                Recordlist = HashMapMTL.get(Mark);
                Recordlist.add(NewTRecord);
                HashMapMTL.replace(Mark, Recordlist);

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
            return false;
        }

        String writeInLog = "ManagerID: " + managerID + "\n" +
                "Create Teacher Record." + "\n" +
                "Name: " + firstName + " " + lastName + "\n" +
                "Address: " + Address + " " + "\n" +
                "Phone: " + Phone + " " + "\n" +
                "Specialization: " + Specialization + " " + "\n" +
                "Location: " + Location + " " + "\n" +
                "Time: " + getTime() + " " + "\n" + "\n";
        writeLog(writeInLog);
        save();

        return true;
    }

    @Override
    public boolean createSRecord(String managerID, String firstName, String lastName, String CoursesRegistered, String Status, String StatusDate) throws RemoteException {

        if(!(Status.equals("active")||Status.equals("inactive"))){
            System.out.println("The Status is invalid.");
            return false;
        }
        StudentRecord NewSRecord = new StudentRecord(firstName, lastName, CoursesRegistered, Status, StatusDate);
        LVLcount++;
        NewSRecord.setRecordID(LVLcount);
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
            return false;
        }

        String writeInLog = "ManagerID: " + managerID + "\n" +
                "Create Student Record." + "\n" +
                "Name: " + firstName + " " + lastName + "\n" +
                "CoursesRegister: " + CoursesRegistered + " " + "\n" +
                "Status: " + Status + " " + "\n" +
                "StatusDate: " + StatusDate + " " + "\n" +
                "Time: " + getTime() + " " + "\n" + "\n";
        this.writeLog(writeInLog);
        save();
        return true;

    }

    @Override
    public boolean editRecord(String managerID, String recordID, String fieldName, String newValue) throws RemoteException {
        boolean result = false;
        Collection<ArrayList<Record>> allRecord = new ArrayList<>();
        int mark = 0;
        Record target = null;
        if(managerID.startsWith("MTL")){

            allRecord = HashMapMTL.values();

            for(ArrayList<Record> recordList : allRecord){
                for(Record record : recordList){

                    if (record.getID().equals(recordID)){
                        target = record;
                        mark = 1;
                    }
                    break;
                }
                if(mark == 1){
                    break;
                }
            }
            if(target != null){
                if(target instanceof TeacherRecord){
                    synchronized (target) {
                        result = ((TeacherRecord) target).changeValue(fieldName, newValue);
                        System.out.println(target);
                    }
                }
                else {
                    synchronized (target) {
                        result = ((StudentRecord) target).changeValue(fieldName, newValue);
                        System.out.println(target);
                    }
                }
                String writeInLog = "Edit Record." + "\n" +
                        "ManagerID: " + managerID + "\n" +
                        "RecordID: " + target.getID() + "\n" +
                        "fieldName: " + fieldName + " " + "\n" +
                        "newValue: " + newValue + " " + "\n" +
                        "Time: " + getTime() + " " + "\n" + "\n";
                writeLog(writeInLog);
                save();
            }
            else{
                System.out.println("No Record.");
                result =  false;
            }
        }


        else if(managerID.startsWith("LVL")){
            allRecord = HashMapLVL.values();

            for(ArrayList<Record> recordList : allRecord){
                for(Record record : recordList){

                    if (record.getID().equals(recordID)){
                        target = record;
                        mark = 1;
                    }
                    break;
                }
                if(mark == 1){
                    break;
                }
            }
            if(target != null){
                if(target instanceof TeacherRecord){
                    synchronized (target) {
                        result = ((TeacherRecord) target).changeValue(fieldName, newValue);
                        System.out.println(target);
                    }
                }
                else {
                    synchronized (target) {
                        result = ((StudentRecord) target).changeValue(fieldName, newValue);
                        System.out.println(target);
                    }
                }
                String writeInLog = "Edit Record." + "\n" +
                        "ManagerID: " + managerID + "\n" +
                        "RecordID: " + target.getID() + "\n" +
                        "fieldName: " + fieldName + " " + "\n" +
                        "newValue: " + newValue + " " + "\n" +
                        "Time: " + getTime() + " " + "\n" + "\n";
                writeLog(writeInLog);
                save();
            }
            else{
                System.out.println("No Record.");
                result =  false;
            }


        }


        else if (managerID.startsWith("DDO")){

            allRecord = HashMapDDO.values();

            for(ArrayList<Record> recordList : allRecord){
                for(Record record : recordList){

                    if (record.getID().equals(recordID)){
                        target = record;
                        mark = 1;
                    }
                    break;
                }
                if(mark == 1){
                    break;
                }
            }
            if(target != null){
                if(target instanceof TeacherRecord){
                    synchronized (target) {
                        result = ((TeacherRecord) target).changeValue(fieldName, newValue);
                        System.out.println(target);
                    }
                }
                else {
                    synchronized (target) {
                        result = ((StudentRecord) target).changeValue(fieldName, newValue);
                        System.out.println(target);
                    }
                }
                String writeInLog = "Edit Record." + "\n" +
                        "ManagerID: " + managerID + "\n" +
                        "RecordID: " + target.getID() + "\n" +
                        "fieldName: " + fieldName + " " + "\n" +
                        "newValue: " + newValue + " " + "\n" +
                        "Time: " + getTime() + " " + "\n" + "\n";
                writeLog(writeInLog);
                save();
            }
            else{
                System.out.println("No Record.");
                result =  false;
            }

        }

        return result;
    }

    @Override
    public boolean printRecord(String managerID) throws RemoteException {

        ArrayList<Record> Recordlist = new ArrayList<>();
        if(managerID.startsWith("MTL")){

            for(char key: HashMapMTL.keySet()) {

                System.out.print("\n" + key + ", ");
                Recordlist = HashMapMTL.get(key);
                for (int i = 0; i < Recordlist.size(); i++) {
                    System.out.print("{ID:" + Recordlist.get(i).getID() + " Name: " + Recordlist.get(i).getName() + "} ");
                }
                System.out.println("\n");
            }

        }
        else if(managerID.startsWith("LVL")){

            for(char key: HashMapLVL.keySet()) {

                System.out.print("\n" + key + ", ");
                Recordlist = HashMapLVL.get(key);
                for (int i = 0; i < Recordlist.size(); i++) {
                    System.out.print("{ID:" + Recordlist.get(i).getID() + " Name: " + Recordlist.get(i).getName() + "} ");
                }
                System.out.println("\n");
            }
        }
        else if(managerID.startsWith("DDO")){

            for(char key: HashMapDDO.keySet()) {

                System.out.print("\n" + key + ", ");
                Recordlist = HashMapDDO.get(key);
                for (int i = 0; i < Recordlist.size(); i++) {
                    System.out.print("{ID:" + Recordlist.get(i).getID() + " Name: " + Recordlist.get(i).getName() + "} ");
                }
                System.out.println("\n");
            }

        }

        return true;
    }

    @Override
    public String getRecordCounts() throws RemoteException {
        String sendStr = "LVL " + String.valueOf(LVLcount);
        return sendStr;
    }

    public  String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = date.toString();
        return time;
    }

    public void save(){
        try {
            FileOutputStream l_saveFile = null;
            l_saveFile = new FileOutputStream(FilePath + "/" + "LogFile" + "/" + "LVLFile" + "/" + "LVLServer" + ".txt");
            ObjectOutputStream l_Save = new ObjectOutputStream(l_saveFile);
            l_Save.writeObject(this);
            l_Save.flush();
            l_Save.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("write object success!");
    }
}
