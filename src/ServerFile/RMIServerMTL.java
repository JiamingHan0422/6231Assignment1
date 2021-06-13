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


            r_Interface = new rmiMethodMTL();

            File MTLFile = new File("");
            String FilePath = MTLFile.getAbsolutePath();
            MTLFile = new File(FilePath + "/" + "LogFile" + "/" + "MTLFile" + "/" + "MTLServer" + ".txt");
            if (!MTLFile.exists()) {
                try {
                    MTLFile.createNewFile();
                } catch (IOException e) {
                    //e.printStackTrace();
                    System.out.println("The Map is Empty!");
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

            try {
                server = new DatagramSocket(5053);
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
        //UnicastRemoteObject.unexportObject(r_Interface, false);
        server.close();
    }
}
