package DBAccessors;

import Model.FirstLvlDivisions;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class to interact with MySQL database and make queries related to the FirstLvlDivisions model class. */
public abstract class DBFirstLvlDivisions {

    /** Method to retrieve all First Level Divisions from the MySQL database.
     * @return ObservableList of FirstLvlDivisions objects representing First Level Administrative Divisions stored in the database */
    public static ObservableList<FirstLvlDivisions> getCountryDivisions(int countryId) {

        ObservableList<FirstLvlDivisions> divisions = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id = result.getInt("Division_ID");
                int countryIdDB = result.getInt("Country_ID");
                String name = result.getString("Division");

                FirstLvlDivisions division = new FirstLvlDivisions(id, name, countryIdDB);

                divisions.add(division);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisions;
    }
}
