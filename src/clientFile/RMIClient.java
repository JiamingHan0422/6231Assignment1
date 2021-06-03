package clientFile;

import ServerFile.rmiInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args){
        try{
            rmiInterface r_Interface = (rmiInterface) Naming.lookup("rmi://localhost:6231/r_Interface");
            System.out.println(r_Interface.getRecordCounts());
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
