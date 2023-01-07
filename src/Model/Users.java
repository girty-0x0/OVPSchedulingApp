package Model;

/** Users class. Represent users stored in MySQL database. */
public class Users {
    /** id instance variable. Unique identifier. */
    private int id;
    /** userName instance variable. Stores the user's username. */
    private String userName;
    /** password instance variable. Stores the user's password. */
    private String password;

    /** Constructor for an Users object. Returns Users object. */
    public Users(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    /** Getter for the Users object id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /** Getter for the Users object username.
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /** Getter for the Users object password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /** Override for toString function to change the Users object representation in combo boxes. */
    @Override
    public String toString(){
        return (id + " (" + userName + ")");
    }
}
