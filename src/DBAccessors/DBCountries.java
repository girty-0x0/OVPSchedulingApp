package DBAccessors;

import Model.Countries;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class to interact with MySQL database and make queries related to the Countries model class. */
public abstract class DBCountries {

    /** Method to retrieve all countries from the MySQL database.
     * @return ObservableList of Countries objects representing countries stored in the database */
    public static ObservableList<Countries> getAllCountries(){
        ObservableList<Countries> countries = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql); //makes a connection and prepares the statement
            ResultSet result = ps.executeQuery(); //set of results from a sql query

            while(result.next()){
                int countryID = result.getInt("Country_ID");
                String countryName = result.getString("Country");
                Countries curCountry = new Countries(countryID, countryName);
                countries.add(curCountry);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countries;
    }

    /** Method to retrieve a single Country from the MySQL database.
     * @param divisionID the division ID for a First Level Administrative Division within the desired country
     * @return Countries object with a matching divisionId stored in the database */
    public static Countries getCountry(int divisionID){

        try{
            String sql = "SELECT * from countries WHERE Country_ID=(SELECT Country_ID FROM first_level_divisions WHERE Division_ID=?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, divisionID);
            ResultSet result = ps.executeQuery();

            result.next();
            int countryID = result.getInt("Country_ID");
            String countryName = result.getString("Country");
            return new Countries(countryID, countryName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
