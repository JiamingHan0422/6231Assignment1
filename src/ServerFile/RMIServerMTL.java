package ServerFile;

import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RMIServerMTL {
    public static void main(String[] args) {
        DatagramSocket server = null;
        rmiCenterServer r_Interface = null;
        try {

            // 注册远程对象,向客户端提供远程对象服务。
            // 远程对象是在远程服务上创建的，你无法确切地知道远程服务器上的对象的名称，
            // 但是,将远程对象注册到RMI Registry之后,
            // 客户端就可以通过RMI Registry请求到该远程服务对象的stub，
            // 利用stub代理就可以访问远程服务对象了。
            r_Interface = new rmiMethodMTL();

            File MTLFile = new File("");
            String FilePath = MTLFile.getAbsolutePath();
            MTLFile = new File(FilePath + "/" + "LogFile" + "/" + "MTLFile" + "/" + "MTLServer" + ".txt");
            if (!MTLFile.exists()) {
                try {
                    MTLFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {

                ObjectInputStream l_ois = null;
                l_ois = new ObjectInputStream(new FileInputStream(FilePath + "/" + "LogFile" + "/" + "MTLFile" + "/" + "MTLServer" + ".txt"));

                try {
                    r_Interface = (rmiCenterServer) l_ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("The Map is Empty!");
            }

            LocateRegistry.createRegistry(6231);
            java.rmi.Naming.rebind("rmi://localhost:6231/r_Interface", r_Interface);
            System.out.print("Server Ready! " + "\n");
            // 如果不想再让该对象被继续调用，使用下面一行
            // UnicastRemoteObject.unexportObject(remoteMath, false);

            try {
                server = new DatagramSocket(5053);
                byte[] recvBuf = new byte[1000];


                while (true) {
                    DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
                    server.receive(recvPacket);
                    String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
                    System.out.println("Hello World!" + recvStr);

                    //根据获得的端口和IP地址的发送过程
                    int port = recvPacket.getPort();
                    InetAddress addr = recvPacket.getAddress();
                    String sendStr = r_Interface.getRecordCounts();
                    byte[] sendBuf = sendStr.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, addr, port);
                    server.send(sendPacket);


                }

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        //UnicastRemoteObject.unexportObject(r_Interface, false);
        server.close();
    }
}
