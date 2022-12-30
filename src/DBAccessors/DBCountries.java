package DBAccessors;

import Model.Countries;
import helper.JDBC;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountries {
    public static ObservableList<Countries> getAllCountries(){
        ObservableList<Countries> countries = FXCollections.observableArrayList();

        try{
            String statement = "SELECT * from countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(statement); //makes a connection and prepares the statement
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

}
