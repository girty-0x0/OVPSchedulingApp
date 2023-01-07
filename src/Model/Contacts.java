package Model;

/** Contacts class. Represent contacts stored in MySQL database. */
public class Contacts {

    /** id instance variable. Unique identifier. */
    private int id;
    /** contactName instance variable. Stores the contact's name. */
    private String contactName;
    /** email instance variable. Stores the contact's email. */
    private String email;

    /** Constructor for an Contacts object. Returns Contacts object. */
    public Contacts(int id, String contactName, String email){
        this.id = id;
        this.contactName = contactName;
        this.email = email;
    }

    /** Getter for the Contacts object id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /** Getter for the Contacts object contactName.
     * @return the contact's name
     */
    public String getContactName() {
        return contactName;
    }

    /** Getter for the Contacts object email.
     * @return the contact's email
     */
    public String getEmail() {
        return email;
    }

    /** Override for toString function to change the Contacts object representation in combo boxes. */
    @Override
    public String toString(){
        return (id + " (" + contactName + ")");
    }
}
