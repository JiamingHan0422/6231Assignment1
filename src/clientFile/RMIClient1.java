package clientFile;

import ServerFile.rmiCenterServer;
import managerFile.Manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * The client (JavaRmi)
 */
public class RMIClient1 {

    public static void main(String[] args){

        while(true) {
            try {
                String ManagerID;
                int ManagerInput;
                boolean ManagerValid = false;

                Scanner ManagerScanner = new Scanner(System.in);
                System.out.println("Please input the ManagerID. Enter 0, if you want exit.");
                ManagerID = ManagerScanner.nextLine();
                ManagerID = ManagerID.toUpperCase();
                Manager manager = new Manager();
                manager.setManagerID(ManagerID);
                rmiCenterServer r_Interface = null;

                if(ManagerID.equals("0")){
                    break;
                }
                if (ManagerID.startsWith("MTL")) {
                    ManagerValid = true;
                    Registry registry = LocateRegistry.getRegistry("localhost");
                    r_Interface = (rmiCenterServer) Naming.lookup("rmi://localhost:6231/r_Interface");

                } else if (ManagerID.startsWith("LVL")) {
                    ManagerValid = true;
                    Registry registry = LocateRegistry.getRegistry("localhost");
                    r_Interface = (rmiCenterServer) Naming.lookup("rmi://localhost:6232/r_Interface");

                } else if (ManagerID.startsWith("DDO")) {
                    ManagerValid = true;
                    Registry registry = LocateRegistry.getRegistry("localhost");
                    r_Interface = (rmiCenterServer) Naming.lookup("rmi://localhost:6233/r_Interface");

                }else{
                    System.out.println("ManagerID is invalid, please try again.");
                    ManagerValid = false;
                }
                // if the manager ID is valid, then user will have 6 option on the menu, using switch case to do the next operation based on user`s enter.
                if(ManagerValid) {
                    do {
                        int out = 1;
                        System.out.println("Welcome: " + ManagerID);
                        System.out.println("Please select the function.");
                        System.out.println(
                                "1.Create Teacher Record. " + "\n" +
                                        "2.Create Student Record." + "\n" +
                                        "3.Get Record Counts." + "\n" +
                                        "4. Edit Record." + "\n" +
                                        "5. Print Record." + "\n" +
                                        "0. EXIT");
                        ManagerInput = ManagerScanner.nextInt();

                        switch (ManagerInput) {
                            // terminal the program.
                            case 0:
                                out = 0;
                                break;

                            //create Teacher Record
                            case 1:
                                System.out.println("Please Create Teacher Record.");
                                System.out.println("Enter: lastName, firstName, address, phone, specialization, location(mtl,lvl,ddo) ");
                                String firstName = ManagerScanner.next();
                                String lastName = ManagerScanner.next();
                                String address = ManagerScanner.next();
                                String phone = ManagerScanner.next();
                                String specialization = ManagerScanner.next();

                                firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
                                lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

                                String location = ManagerScanner.next();
                                while (!(location.equals("mtl") || location.equals("lvl") || location.equals("ddo"))) {
                                    System.out.println("Location is invalid, please try again.(mtl,lvl,ddo)\n");
                                    location = ManagerScanner.next();
                                }

                                boolean result = r_Interface.createTRecord(ManagerID, firstName, lastName, address, phone, specialization, location);
                                if (result) {
                                    System.out.println("success!");

                                    String writeInLog = "Create Teacher Record." + "\n" +
                                            "Name: " + firstName + " " + lastName + "\n" +
                                            "Address: " + address + " " + "\n" +
                                            "Phone: " + phone + " " + "\n" +
                                            "Specialization: " + specialization + " " + "\n" +
                                            "Location: " + location + " " + "\n" +
                                            "Time: " + getTime() + " " + "\n" + "\n";

                                    manager.writeLog(writeInLog);
                                } else {
                                    System.out.println("access deny.");
                                }
                                break;

                            //create Student Record
                            case 2:
                                System.out.println("Please Create Student Record.");
                                System.out.println("Enter: lastName, firstName, CoursesRegister, Status(active/inactive), StatusDate");
                                firstName = ManagerScanner.next();
                                lastName = ManagerScanner.next();
                                String CoursesRegister = ManagerScanner.next();
                                String Status = ManagerScanner.next();
                                while (!(Status.equals("active") || Status.equals("inactive"))) {
                                    System.out.println("Status is invalid, please try again.(active/inactive)\n");
                                    Status = ManagerScanner.next();
                                }
                                String StatusDate = ManagerScanner.next();

                                firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
                                lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
                                result = r_Interface.createSRecord(ManagerID, firstName, lastName, CoursesRegister, Status, StatusDate);
                                if (result) {
                                    System.out.println("success!");

                                    String writeInLog = "Create Student Record." + "\n" +
                                            "Name: " + firstName + " " + lastName + "\n" +
                                            "CoursesRegister: " + CoursesRegister + " " + "\n" +
                                            "Status: " + Status + " " + "\n" +
                                            "StatusDate: " + StatusDate + " " + "\n" +
                                            "Time: " + getTime() + " " + "\n" + "\n";

                                    manager.writeLog(writeInLog);

                                } else {
                                    System.out.println("access deny.");
                                }
                                break;

                            // get record count
                            case 3:

                                getCount("DDO");
                                getCount("LVL");
                                getCount("MTL");

                                break;

                            //edit record
                            case 4:
                                System.out.println("------------------------------------------------------");
                                System.out.println("Please Input the RecordID, fieldName and the newValue.");
                                System.out.println("------------------------------------------------------");
                                System.out.println("The Teacher Record: address, phone, specialization, location(mtl,lvl,ddo) can be changed");
                                System.out.println("------------------------------------------------------");
                                System.out.println("The Student Record: coursesregistered, status(active/inactive), statusdate can be changed");
                                System.out.println("------------------------------------------------------");
                                System.out.println("Enter: RecordID, fieldName, newValue,");
                                System.out.println("------------------------------------------------------");

                                String RecordID = ManagerScanner.next();
                                String fieldName = ManagerScanner.next();
                                String newValue = ManagerScanner.next();

                                result = r_Interface.editRecord(ManagerID, RecordID, fieldName, newValue);
                                if (result) {
                                    System.out.println("success!");
                                    String writeInLog = "Edit Record." + "\n" +
                                            "RecordID: " + RecordID + "\n" +
                                            "fieldName: " + fieldName + " " + "\n" +
                                            "newValue: " + newValue + " " + "\n" +
                                            "Time: " + getTime() + " " + "\n" + "\n";

                                    manager.writeLog(writeInLog);

                                } else {
                                    System.out.println("access deny.");
                                }
                                break;

                             // print record
                            case 5:
                                result = r_Interface.printRecord(ManagerID);
                                if (result) {
                                    System.out.println("success!");

                                } else {
                                    System.out.println("access deny.");
                                }
                                break;
                            default:
                                break;
                        }

                        if (out == 0) {
                            break;
                        }
                    } while (true);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
    }

    public static String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = date.toString();
        return time;
    }

    public static String getCount(String location) {
        int port = 0;
        if (location.equals("DDO")) {
            port = 5051;
        } else if (location.equals("LVL")) {
            port = 5052;
        } else if (location.equals("MTL")) {
            port = 5053;
        }

        String recvStr = null;
        try {
            DatagramSocket client = new DatagramSocket();
            // sending process
            String sendStr = "I'm Client";
            byte[] sendBuf = sendStr.getBytes();
            InetAddress addr = InetAddress.getByName("127.0.0.1");

            DatagramPacket sendPacketDDO = new DatagramPacket(sendBuf, sendBuf.length, addr, port);
            client.send(sendPacketDDO);

            // receiving process
            byte[] recvBuf = new byte[1000];
            DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
            client.receive(recvPacket);
            recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
            System.out.println(recvStr);
            client.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recvStr;
    }
}
