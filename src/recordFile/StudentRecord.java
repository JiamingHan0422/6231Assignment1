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

    public String getCoursesRegistered() {
        return CoursesRegistered;
    }

    public String getStatus() {
        return Status;
    }

    public String getStatusDate() {
        return StatusDate;
    }

    public boolean changeValue(String fieldName, String newValue){

        if(fieldName.equalsIgnoreCase("Status")){
            if(newValue.equals("active")||newValue.equals("inactive")){

                System.out.println("Old value:" + this.Status);
                this.Status=newValue;
                System.out.println("New value:" + this.Status);
                return true;
            }
            else
                System.out.println("The Status is invalid.(active/inactive)");
                return false;
        }
        else if(fieldName.equalsIgnoreCase("CoursesRegistered")){
            System.out.println("Old value:" + this.CoursesRegistered);
            this.CoursesRegistered=newValue;
            System.out.println("New value:" + this.CoursesRegistered);
            return true;
        }
        else if(fieldName.equalsIgnoreCase("StatusDate")){
            System.out.println("Old value:" + this.CoursesRegistered);
            this.StatusDate=newValue;
            System.out.println("New value:" + this.CoursesRegistered);
            return true;
        }
        else {
            return false;
        }
    }
}
