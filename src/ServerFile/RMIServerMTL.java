package ServerFile;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServerMTL {
    public static void main(String[] args){
        try{

            // 注册远程对象,向客户端提供远程对象服务。
            // 远程对象是在远程服务上创建的，你无法确切地知道远程服务器上的对象的名称，
            // 但是,将远程对象注册到RMI Registry之后,
            // 客户端就可以通过RMI Registry请求到该远程服务对象的stub，
            // 利用stub代理就可以访问远程服务对象了。
            rmiCenterServer r_Interface = new rmiMethodMTL();
            LocateRegistry.createRegistry(6231);
            java.rmi.Naming.rebind("rmi://localhost:6231/r_Interface", r_Interface);
            System.out.print("Server Ready! " + "\n");
            // 如果不想再让该对象被继续调用，使用下面一行
            // UnicastRemoteObject.unexportObject(remoteMath, false);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
