package clientFile;

import ServerFile.rmiInterface;

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
                        "4. Edit Record");
                ManagerInput = ManagerScanner.nextInt();

                switch(ManagerInput){

                    case 1:
                        System.out.println("Please Create Teacher Record.");
                        System.out.println("Enter: firstName lastName address phone specialization location(mtl,lvl,ddo) ");
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
