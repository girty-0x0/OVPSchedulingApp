package Model;

public class Customers {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private int firstLvlDivisionId;

    public Customers(int id, String name, String address, String postalCode, String phone, int firstLvlDivisionId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.firstLvlDivisionId = firstLvlDivisionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getFirstLvlDivisionId() {
        return firstLvlDivisionId;
    }

    public void setFirstLvlDivisionId(int firstLvlDivisionId) {
        this.firstLvlDivisionId = firstLvlDivisionId;
    }

    @Override
    public String toString(){
        return (id + " (" + name + ")");
    }
}
