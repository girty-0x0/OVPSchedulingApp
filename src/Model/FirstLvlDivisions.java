package Model;

/** FirstLvlDivisions class. Represent First Level Administrative Divisions stored in MySQL database. */
public class FirstLvlDivisions {
    /** id instance variable. Unique identifier. */
    private int id;
    /** name instance variable. Stores the First Level Administrative Division's name. */
    private String name;
    /** countryId instance variable. Stores the First Level Administrative Division's related country ID. */
    private int countryId; //dependency to Countries

    /** Constructor for an FirstLvlDivisions object. Returns FirstLvlDivisions object. */
    public FirstLvlDivisions(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    /** Getter for the FirstLvlDivisions object id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /** Getter for the FirstLvlDivisions object name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** Getter for the FirstLvlDivisions object countryId.
     * @return the related country's ID
     */
    public int getCountryId() {
        return countryId;
    }

    /** Override for toString function to change the FirstLvlDivisions object representation in combo boxes. */
    @Override
    public String toString(){
        return name + " (ID: " + id + ")";
    }
}
