package project.Model;

public class Contact {
    private static int Count=0;
    private int id;
    private  String FirstName;
    private String MiddleName;
    private String LastName;
    private String ContactNumber;

    public Contact(String firstName, String middleName, String lastName, String contactNumber) {
        Count++;
        this.id = Count;
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
        ContactNumber = contactNumber;
    }

    @Override
    public String toString() {
      StringBuilder str= new StringBuilder();
      str.append(id).append(" ");
      str.append(FirstName).append(" ");
      if(!MiddleName.isEmpty()){
          str.append(MiddleName).append(" ");
      }
      if(!LastName.isEmpty()){
            str.append(LastName).append(" ");
        }
      str.append("->").append(ContactNumber);
      return str.toString();
    }

    public static int getCount() {
        return Count;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getLastName() {
        return LastName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }
}
