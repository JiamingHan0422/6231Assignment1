package recordFile;

public class StudentRecord extends Record{

    private static int idCounter=10000;

    String CoursesRegistered;
    String Status;
    String StatusDate;
    public StudentRecord(String firstName, String lastName, String CoursesRegistered,
                          String Status, String StatusDate){

        super(firstName, lastName);
        this.recordID="SR"+String.valueOf(++idCounter);
        this.CoursesRegistered = CoursesRegistered;
        this.Status = Status;
        this.StatusDate = StatusDate;

    }

    public boolean changeValue(String fieldName, String newValue){

        if(fieldName.equalsIgnoreCase("Status")){
            if(newValue.equals("active")||newValue.equals("inactive")){
                this.Status=newValue;
                return true;
            }
            else
                System.out.println("The Status is invalid.(active/inactive)");
                return false;
        }
        else if(fieldName.equalsIgnoreCase("CoursesRegistered")){
            this.CoursesRegistered=newValue;
            return true;
        }
        else if(fieldName.equalsIgnoreCase("StatusDate")){
            this.StatusDate=newValue;
            return true;
        }
        else {
            return false;
        }
    }
}
