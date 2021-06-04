package clientFile;

import ServerFile.rmiInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
            // 如果RMI Registry就在本地机器上，URL就是:rmi://localhost:1099/hello
            // 否则，URL就是：rmi://RMIService_IP:1099/hello
            Registry registry = LocateRegistry.getRegistry("localhost");
            rmiInterface r_Interface = (rmiInterface) Naming.lookup("rmi://localhost:6231/r_Interface");
            // 从Registry中检索远程对象的存根/代理
            System.out.println(r_Interface.getRecordCounts());
            // 调用远程对象的方法
            //double addResult = r_Interface.add(5.0, 3.0);
            //System.out.println("5.0 + 3.0 = " + addResult);
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
