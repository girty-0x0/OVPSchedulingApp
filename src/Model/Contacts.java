package Model;

public class Contacts {

    int id;
    String contactName;
    String email;

    public Contacts(int id, String contactName, String email){
        this.id = id;
        this.contactName = contactName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

}
