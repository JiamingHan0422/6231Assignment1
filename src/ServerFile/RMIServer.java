package ServerFile;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args){
        try{
            rmiInterface r_Interface = new rmiMethodImpl();
            LocateRegistry.createRegistry(6231);
            java.rmi.Naming.rebind("rmi://localhost:6231/r_Interface", r_Interface);
            System.out.print("Server Ready! ");

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
