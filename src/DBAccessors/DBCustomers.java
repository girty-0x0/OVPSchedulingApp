package DBAccessors;

import Model.Customers;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBCustomers {

    public static ObservableList getAllCustomers() {
        ObservableList<Customers> customers = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()){
                int id = result.getInt("Customer_ID");
                int divisionId = result.getInt("Division_ID");
                String name = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postal = result.getString("Postal_Code");
                String phone = result.getString("Phone");
                Customers customer = new Customers(id, name, address, postal, phone, divisionId);
                customers.add(customer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    public static Customers getCustomer(int customerId) {

        try {
            String sql = "SELECT * FROM customers WHERE Customer_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet result = ps.executeQuery();

            if (result.next()){
                int id = result.getInt("Customer_ID");
                int divisionId = result.getInt("Division_ID");
                String name = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postal = result.getString("Postal_Code");
                String phone = result.getString("Phone");

                return new Customers(id, name, address, postal, phone, divisionId);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}