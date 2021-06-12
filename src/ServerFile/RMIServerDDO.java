package ServerFile;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RMIServerDDO {
    public static void main(String[] args){
        try{

            // 注册远程对象,向客户端提供远程对象服务。
            // 远程对象是在远程服务上创建的，你无法确切地知道远程服务器上的对象的名称，
            // 但是,将远程对象注册到RMI Registry之后,
            // 客户端就可以通过RMI Registry请求到该远程服务对象的stub，
            // 利用stub代理就可以访问远程服务对象了。
            rmiCenterServer r_Interface = new rmiMethodDDO();

            File DDOFile = new File("");
            String FilePath = DDOFile.getAbsolutePath();
            DDOFile = new File(FilePath + "/" + "LogFile" + "/" + "DDOFile"+ "/" + "DDOServer" +".txt");
            if(!DDOFile.exists()){
                try {
                    DDOFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {

                ObjectInputStream l_ois= null;
                l_ois = new ObjectInputStream(new FileInputStream(FilePath + "/" + "LogFile" + "/" + "DDOFile"+ "/" + "DDOServer" +".txt"));

                try {
                    r_Interface=(rmiCenterServer)l_ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Empty!");
                }
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("The Map is Empty!");
            }

            LocateRegistry.createRegistry(6233);
            java.rmi.Naming.rebind("rmi://localhost:6233/r_Interface", r_Interface);
            System.out.print("Server Ready! " + "\n");
            // 如果不想再让该对象被继续调用，使用下面一行
            // UnicastRemoteObject.unexportObject(remoteMath, false);


            Scanner stopScanner = new Scanner(System.in);
            System.out.println("If you want to stop, please enter: exit.");
            String input = stopScanner.nextLine();
            while(true){

                if(input.equals("exit")){
                    UnicastRemoteObject.unexportObject(r_Interface, false);

                    try {
                        FileOutputStream l_saveFile= null;
                        l_saveFile = new FileOutputStream(FilePath + "/" + "LogFile" + "/" + "DDOFile" + "/" + "DDOServer" +".txt");
                        ObjectOutputStream l_Save = new ObjectOutputStream(l_saveFile);
                        l_Save.writeObject(r_Interface);
                        l_Save.flush();
                        l_Save.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("write object success!");
                    break;
                }
                System.out.println("If you want to stop, please enter: exit.");
                input = stopScanner.nextLine();
            }


        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
