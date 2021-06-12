package clientFile;

import ServerFile.rmiInterface;
import managerFile.Manager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIClient {

    /**
     * Check manager's validation
     * @return
     */
    int ManagerValid(){return 0;}

    /**
     * main method
     */
    void init() { }


    public static void main(String[] args){
        try{

            String ManagerID;
            int ManagerInput;
            Scanner ManagerScanner = new Scanner(System.in);
            System.out.println("Please input the ManagerID.");
            ManagerID = ManagerScanner.nextLine();
            Manager manager = new Manager();
            manager.setManagerID(ManagerID);

            // 如果RMI Registry就在本地机器上，URL就是:rmi://localhost:1099/hello
            // 否则，URL就是：rmi://RMIService_IP:1099/hello
            Registry registry = LocateRegistry.getRegistry("localhost");
            rmiInterface r_Interface = (rmiInterface) Naming.lookup("rmi://localhost:6231/r_Interface");
            // 从Registry中检索远程对象的存根/代理
            //System.out.println(r_Interface.getRecordCounts());
            // 调用远程对象的方法
            //double addResult = r_Interface.add(5.0, 3.0);
            //System.out.println("5.0 + 3.0 = " + addResult);
            do{


                System.out.println("Welcome: " + ManagerID);
                System.out.println("Please select the function.");
                System.out.println(
                        "1.Create Teacher Record. " + "\n" +
                        "2.Create Student Record."  + "\n" +
                        "3.Get Record Counts." + "\n" +
                        "4. Edit Record." + "\n" +
                        "5. Print Record.");
                ManagerInput = ManagerScanner.nextInt();

                switch(ManagerInput){

                    case 1:
                        System.out.println("Please Create Teacher Record.");
                        System.out.println("Enter: firstName, lastName, address, phone, specialization, location(mtl,lvl,ddo) ");
                        String firstName = ManagerScanner.next();
                        String lastName = ManagerScanner.next();
                        String address = ManagerScanner.next();
                        String phone = ManagerScanner.next();
                        String specialization = ManagerScanner.next();
                        String location = ManagerScanner.next();

                        boolean result = r_Interface.createTRecord(ManagerID, firstName, lastName, address, phone, specialization, location );
                        if (result){
                            System.out.println("success!");
                        }
                        else{
                            System.out.println("access deny.");
                        }
                        break;

                    case 2:
                        System.out.println("Please Create Student Record.");
                        System.out.println("Enter: firstName, lastName, CoursesRegister, Status(active/inactive), StatusDate");
                        firstName = ManagerScanner.next();
                        lastName = ManagerScanner.next();
                        String CoursesRegister = ManagerScanner.next();
                        String Status = ManagerScanner.next();
                        String StatusDate = ManagerScanner.next();

                        result = r_Interface.createSRecord(ManagerID, firstName, lastName, CoursesRegister, Status, StatusDate);
                        if (result){
                            System.out.println("success!");
                        }
                        else{
                            System.out.println("access deny.");
                        }
                        break;
                    case 3:

                    case 4:
                        System.out.println("------------------------------------------------------");
                        System.out.println("Please Input the RecordID, fieldName and the newValue.");
                        System.out.println("------------------------------------------------------");
                        System.out.println("The Teacher Record: address, phone, specialization, location(mtl,lvl,ddo) can be changed");
                        System.out.println("------------------------------------------------------");
                        System.out.println("The Student Record: CoursesRegister, Status(active/inactive), StatusDate can be changed");
                        System.out.println("------------------------------------------------------");
                        System.out.println("Enter: RecordID, fieldName, newValue,");
                        System.out.println("------------------------------------------------------");

                        String RecordID = ManagerScanner.next();
                        String fieldName = ManagerScanner.next();
                        String newValue = ManagerScanner.next();

                        result = r_Interface.editRecord(ManagerID, RecordID, fieldName, newValue);
                        if (result){
                            System.out.println("success!");
                        }
                        else{
                            System.out.println("access deny.");
                        }
                        break;
                    case 5:
                        result = r_Interface.printRecord(ManagerID);
                        if (result){
                            System.out.println("success!");
                        }
                        else{
                            System.out.println("access deny.");
                        }
                        break;
                }

            }while(true);

        }

        catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
