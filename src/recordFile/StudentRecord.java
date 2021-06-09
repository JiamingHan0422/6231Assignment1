package recordFile;

public class StudentRecord extends Record{

    String CoursesRegistered;
    String multiple;
    String Status;
    String StatusDate;
    public StudentRecord(String firstName, String lastName, String CoursesRegistered,
                         String multiple, String Status, String StatusDate){

        super(firstName, lastName);
        this.CoursesRegistered = CoursesRegistered;
        this.multiple = multiple;
        this.Status = Status;
        this.StatusDate = StatusDate;

    }

}
