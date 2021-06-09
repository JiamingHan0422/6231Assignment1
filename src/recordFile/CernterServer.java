package recordFile;

public class CernterServer {

    String T_FirstName,T_LastName, Address, Phone, Specialization, Location;
    String S_FirstName,S_LastName, CoursesRegistered, multiple, Status, StatusDate;

    TeacherRecord TRecord = new TeacherRecord(T_FirstName,T_LastName, Address, Phone, Specialization, Location);
    StudentRecord SRecord = new StudentRecord(S_FirstName,S_LastName, CoursesRegistered, multiple, Status, StatusDate);

    public CernterServer(){

    }

}
