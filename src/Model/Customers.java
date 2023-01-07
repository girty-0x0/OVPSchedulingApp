package Model;

/** Customers class. Represent Customers stored in MySQL database. */
public class Customers {
    /** id instance variable. Unique identifier. */
    private int id;
    /** name instance variable. Holds the customer's name. */
    private String name;
    /** address instance variable. Describes customer's address. */
    private String address;
    /** postalCode instance variable. Holds the customer's postal code. */
    private String postalCode;
    /** phone instance variable. Holds the customer's phone number. */
    private String phone;
    /** firstLvlDivisionId instance variable. Holds the customer's First Level Administrative Division ID. */
    private int firstLvlDivisionId;

    /** Constructor for an Customers object. Returns Customers object. */
    public Customers(int id, String name, String address, String postalCode, String phone, int firstLvlDivisionId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.firstLvlDivisionId = firstLvlDivisionId;
    }

    /** Getter for the Customers object id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /** Setter for the Customers object id.
     * @param id the Customer ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Getter for the Customers object name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** Setter for the Customers object name.
     * @param name the Customer name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Getter for the Customers object address.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /** Setter for the Customers object address.
     * @param address the Customer address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Getter for the Customers object postalCode.
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /** Setter for the Customers object postalCode.
     * @param postalCode the Customer postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /** Getter for the Customers object phone.
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /** Setter for the Customers object phone.
     * @param phone the Customer phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** Getter for the Customers object firstLvlDivisionId.
     * @return the First Level Administrative Division ID
     */
    public int getFirstLvlDivisionId() {
        return firstLvlDivisionId;
    }

    /** Setter for the Customers object firstLvlDivisionId.
     * @param firstLvlDivisionId the Customer First Level Administrative Division ID
     */
    public void setFirstLvlDivisionId(int firstLvlDivisionId) {
        this.firstLvlDivisionId = firstLvlDivisionId;
    }

    /** Override for toString function to change the Customers object representation in combo boxes. */
    @Override
    public String toString(){
        return (id + " (" + name + ")");
    }
}
