package recordFile;

public class TeacherRecord extends Record{

    private static int idCounter=10000;

    String Address;
    String Phone;
    String Specialization;
    String Location;

    public TeacherRecord(String firstName, String lastName, String Address,
                         String Phone, String Specialization, String Location) {

        super(firstName, lastName);
        this.recordID="SR"+String.valueOf(++idCounter);
        this.Address = Address;
        this.Phone = Phone;
        this.Location = Location;
        this.Specialization = Specialization;
    }

    public boolean changeValue(String fieldName, String newValue){

        if(fieldName.equalsIgnoreCase("Address")){
            this.Address=newValue;
            return true;
        }
        else if(fieldName.equalsIgnoreCase("Phone")){
            this.Phone=newValue;
            return true;
        }
        else if(fieldName.equalsIgnoreCase("Location")){
            if(newValue.equals("MTL")||newValue.equals("LVL")||newValue.equals("DDO")){
                this.Location=newValue;
                return true;
            }
            else
                System.out.println("The Location is invalid.(MTL/LVL/DDO)");
                return false;
        }
        else {
            return false;
        }
    }
}
