package Model;

/** Countries class. Represent Countries stored in MySQL database. */
public class Countries {
    /** id instance variable. Unique identifier. */
    private int id;
    /** name instance variable. Holds the Country's name. */
    private String name;

    /** Constructor for an Countries object. Returns Countries object. */
    public Countries(int id, String name){
        this.id = id;
        this.name = name;
    }

    /** Getter for the Countries object id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /** Getter for the Countries object name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** Override for toString function to change the Countries object representation in combo boxes. */
    @Override
    public String toString(){
        return name;
    }
}
