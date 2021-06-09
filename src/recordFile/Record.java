package recordFile;

import java.io.Serializable;

public class Record implements Serializable {


    public String firstName;
    public String lastName;

    public Record(String firstName,String lastName){
        this.firstName=firstName;
        this.lastName=lastName;

    }
}
