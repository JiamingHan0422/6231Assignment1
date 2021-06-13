package Test;

import ServerFile.rmiMethodDDO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;

public class MTLServerTest {

    rmiMethodDDO rmiMethodDDO;
    File DDOFile = new File("");
    String FilePath = DDOFile.getAbsolutePath();

    /**
     * Loading the database in DDO.
     */
    @Before
    public void before(){
        try {
            rmiMethodDDO = new rmiMethodDDO();
            ObjectInputStream l_ois = new ObjectInputStream(new FileInputStream(FilePath + "/" + "LogFile" + "/" + "DDOFile" + "/" + "DDOServer" + ".txt"));
            rmiMethodDDO = (ServerFile.rmiMethodDDO) l_ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * The test is finish.
     */
    @After
    public void after(){

        System.out.println("This test is OK.");

    }


    /**
     * Test for creating the teacher record.
     */
    @Test
    public void DDOServerTrecordTest(){
        boolean result;
        try {
            result = rmiMethodDDO.createTRecord(
                    "DDO11111","firstName", "lastName",
                    "Address", "Phone", "Specialization", "mtl");
            Assert.assertEquals(result, true);

            result = rmiMethodDDO.createTRecord(
                    "DDO11111","firstName", "lastName",
                    "Address", "Phone", "Specialization", "XXX");
            Assert.assertEquals(result, false);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test for creating the student record.
     */
    @Test
    public void DDOServerSrecordTest(){
        boolean result;
        try {
            result = rmiMethodDDO.createSRecord(
                    "DDO11111","firstName", "lastName",
                    "CoursesRegistered", "active", "StatusDate");
            Assert.assertEquals(result, true);

            result = rmiMethodDDO.createSRecord(
                    "DDO11111","firstName", "lastName",
                    "CoursesRegistered", "XXX", "StatusDate");
            Assert.assertEquals(result, false);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test for editing record.
     */
    @Test
    public void DDOServerEditrecordTest(){
        boolean result;
        try {
            rmiMethodDDO.createSRecord(
                    "DDO11111","firstName", "lastName",
                    "CoursesRegistered", "active", "StatusDate");
            rmiMethodDDO.printRecord("DDO11111");
            result = rmiMethodDDO.editRecord("DDO11111", "SR10001","status","inactive");
            Assert.assertEquals(result, true);


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test for editing record.
     */
    @Test
    public void DDOServerGetCountTest(){
        String result;
        try {
            rmiMethodDDO.createSRecord(
                    "DDO11111","firstName", "lastName",
                    "CoursesRegistered", "active", "StatusDate");
            rmiMethodDDO.printRecord("DDO11111");

            result = rmiMethodDDO.getRecordCounts();
            System.out.println(result);


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
