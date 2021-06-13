package ServerFile;

import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RMIServerDDO {
    public static void main(String[] args) {
        DatagramSocket server = null;
        try {

            rmiCenterServer r_Interface = new rmiMethodDDO();

            File DDOFile = new File("");
            String FilePath = DDOFile.getAbsolutePath();
            DDOFile = new File(FilePath + "/" + "LogFile" + "/" + "DDOFile" + "/" + "DDOServer" + ".txt");
            if (!DDOFile.exists()) {
                try {
                    DDOFile.createNewFile();
                } catch (IOException e) {
                    //e.printStackTrace();
                    System.out.println("The Map is Empty!");
                }
            }

            try {

                ObjectInputStream l_ois = null;
                l_ois = new ObjectInputStream(new FileInputStream(FilePath + "/" + "LogFile" + "/" + "DDOFile" + "/" + "DDOServer" + ".txt"));

                try {
                    r_Interface = (rmiCenterServer) l_ois.readObject();
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

            try {
                server = new DatagramSocket(5051);
                byte[] recvBuf = new byte[1000];


                while (true) {
                    DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
                    server.receive(recvPacket);
                    String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
                    System.out.println("Hello World!" + recvStr);

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
        server.close();
    }
}
