package DBAccessors;

import Model.Contacts;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class to interact with MySQL database and make queries related to the Contacts model class. */
public abstract class DBContacts {

    /** Method to retrieve all contacts from the MySQL database.
     * @return ObservableList of Contacts objects representing contacts stored in the database */
    public static ObservableList<Contacts> getAllContacts() {

        ObservableList<Contacts> contacts = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int id = result.getInt("Contact_ID");
                String name = result.getString("Contact_Name");
                String email = result.getString("Email");


                Contacts contact = new Contacts(id, name, email);
                contacts.add(contact);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;
    }

    /** Method to retrieve a single contact from the MySQL database.
     * @param contactId the desired contact's id
     * @return Contacts object with a matching contactId stored in the database */
    public static Contacts getContact(int contactId){
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contactId);
            ResultSet result = ps.executeQuery();

            if (result.next()){
                int id = result.getInt("Contact_ID");
                String name = result.getString("Contact_Name");
                String email = result.getString("Email");

                return new Contacts(id, name, email);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
