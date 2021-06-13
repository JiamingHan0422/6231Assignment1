package ServerFile;

import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RMIServerLVL {
    public static void main(String[] args){
        DatagramSocket server = null;
        try{

            //Load the LVL server.
            rmiCenterServer r_Interface = new rmiMethodLVL();

            File LVLFile = new File("");
            String FilePath = LVLFile.getAbsolutePath();
            LVLFile = new File(FilePath + "/" + "LogFile" + "/" + "LVLFile"+ "/" + "LVLServer" +".txt");
            if(!LVLFile.exists()){
                try {
                    LVLFile.createNewFile();
                } catch (IOException e) {
                    //e.printStackTrace();
                    System.out.println("The Map is Empty!");
                }
            }

            try {

                ObjectInputStream l_ois= null;
                l_ois = new ObjectInputStream(new FileInputStream(FilePath + "/" + "LogFile" + "/" + "LVLFile"+ "/" + "LVLServer" +".txt"));

                try {
                    r_Interface=(rmiCenterServer)l_ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("The Map is Empty!");
            }

            //Create the RMI connection.
            LocateRegistry.createRegistry(6232);
            java.rmi.Naming.rebind("rmi://localhost:6232/r_Interface", r_Interface);
            System.out.print("Server Ready! " + "\n");

            try {
                server = new DatagramSocket(5052);
                byte[] recvBuf = new byte[1000];


                //Create the UDP connection.
                while (true) {
                    DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
                    server.receive(recvPacket);
                    String recvStr = new String(recvPacket.getData(), 0, recvPacket.getLength());
                    System.out.println("Hello World!" + recvStr);

                    int port = recvPacket.getPort();
                    InetAddress addr = recvPacket.getAddress();
                    //Get the record count.
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

