package DBAccessors;

import Model.Contacts;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {

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
