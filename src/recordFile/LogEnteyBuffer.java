package recordFile;

import java.io.*;


public class LogEnteyBuffer  {
    //这个地方可以直接在程序中调用 比如： write2LogFile("hello world", "../JAVA1/src/Test/helloLog", "helloLog.txt"); 来打印log
    public static void write2LogFile(String p_PrintRecord, String P_getDirectory, String p_getFileName) {
        //创建一个可写入文件
         FileWriter l_fileWrite = null;
        try {
            File p_directory = new File(P_getDirectory);
            if (!p_directory.isDirectory()) {
                p_directory.mkdirs();
            }
            File file = new File(p_directory, p_getFileName);
            if (!file.isFile()) {
                file.createNewFile();
            }
            l_fileWrite = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //创建一个可打印的文件
        PrintWriter l_printwriter = new PrintWriter(l_fileWrite);
        l_printwriter.println(p_PrintRecord);
        l_printwriter.flush();
        try {
            l_fileWrite.flush();
            l_printwriter.close();
            l_fileWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
