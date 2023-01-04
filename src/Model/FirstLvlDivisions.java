package Model;

public class FirstLvlDivisions {
    int id;
    String name;
    int countryId; //dependency to Countries

    public FirstLvlDivisions(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountryId() {
        return countryId;
    }

    @Override
    public String toString(){
        return name + " (ID: " + id + ")";
    }
}
