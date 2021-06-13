package Test;

import ServerFile.rmiMethodDDO;
import ServerFile.rmiMethodLVL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;

public class LVLServerTest {

    ServerFile.rmiMethodLVL rmiMethodLVL;
    File LVLFile = new File("");
    String FilePath = LVLFile.getAbsolutePath();

    /**
     * Loading the database in DDO.
     */
    @Before
    public void before(){
        try {
            rmiMethodLVL = new rmiMethodLVL();
            ObjectInputStream l_ois = new ObjectInputStream(new FileInputStream(FilePath + "\\" + "LogFile" + "\\" + "LVLFile" + "\\" + "LVLServer" + ".txt"));
            rmiMethodLVL = (ServerFile.rmiMethodLVL) l_ois.readObject();
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
    public void LVLServerTrecordTest(){
        boolean result;
        try {
            result = rmiMethodLVL.createTRecord(
                    "LVL11111","firstName", "lastName",
                    "Address", "Phone", "Specialization", "mtl");
            Assert.assertEquals(result, true);

            result = rmiMethodLVL.createTRecord(
                    "LVL11111","firstName", "lastName",
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
    public void LVLServerSrecordTest(){
        boolean result;
        try {
            result = rmiMethodLVL.createSRecord(
                    "LVL11111","firstName", "lastName",
                    "CoursesRegistered", "active", "StatusDate");
            Assert.assertEquals(result, true);

            result = rmiMethodLVL.createSRecord(
                    "LVL11111","firstName", "lastName",
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
    public void LVLServerEditrecordTest(){
        boolean result;
        try {
            rmiMethodLVL.createSRecord(
                    "LVL11111","firstName", "lastName",
                    "CoursesRegistered", "active", "StatusDate");
            rmiMethodLVL.printRecord("LVL11111");
            result = rmiMethodLVL.editRecord("LVL11111", "SR10001","status","inactive");
            Assert.assertEquals(result, true);


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test for editing record.
     */
    @Test
    public void LVLServerGetCountTest(){
        String result;
        try {
            rmiMethodLVL.createSRecord(
                    "LVL11111","firstName", "lastName",
                    "CoursesRegistered", "active", "StatusDate");
            rmiMethodLVL.printRecord("LVL11111");

            result = rmiMethodLVL.getRecordCounts();
            System.out.println(result);


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
