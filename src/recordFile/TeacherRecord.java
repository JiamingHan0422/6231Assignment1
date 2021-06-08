package recordFile;

public class TeacherRecord extends Record{
    String Address;
    String Phone;
    String Specialization;
    String Location;

    public TeacherRecord(String firstName, String lastName, String Address,
                         String Phone, String Specialization, String Location) {

        super(firstName, lastName);
        this.Address = Address;
        this.Phone = Phone;
        this.Location = Location;
        this.Specialization = Specialization;
    }

}
