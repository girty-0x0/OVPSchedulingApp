package DBAccessors;

import Model.Customers;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Class to interact with MySQL database by making updates and queries related to the Customers model class. */
public abstract class DBCustomers {

    /** Method to retrieve all Customers from the MySQL database.
     * @return ObservableList of Customers objects representing customers stored in the database */
    public static ObservableList<Customers> getAllCustomers() {
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

    /** Method to retrieve one Customer from the MySQL database.
     * @param customerId the customer's id
     * @return Customers object with a matching id */
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

    /** Method to add one Customers to the MySQL database.
     * @param customer a Customers object with the new customer's details
     * @return number of rows added; 0 if no customers were stored successfully */
    public static int addCustomer(Customers customer){
        int firstLvlDivisionId = customer.getFirstLvlDivisionId();
        String name = customer.getName();
        String address = customer.getAddress();
        String postalCode = customer.getPostalCode();
        String phone = customer.getPhone();

        try {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);

            ps.setInt(5, firstLvlDivisionId);

            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /** Method to modify an existing Customer in the MySQL database.
     * @param customer a Customers object with the customer's new details
     * @return number of rows modified; 0 if no customers were updated successfully */
    public static int updateCustomer(Customers customer){
        int id = customer.getId();
        int firstLvlDivisionId = customer.getFirstLvlDivisionId();
        String name = customer.getName();
        String address = customer.getAddress();
        String postalCode = customer.getPostalCode();
        String phone = customer.getPhone();

        try {
            String sql = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);

            ps.setInt(5, firstLvlDivisionId);
            ps.setInt(6, id);

            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /** Method to delete an existing Customers from the MySQL database.
     * @param customer a Customers object with the details of the customer to be deleted
     * @return number of rows modified; 0 if no customers were deleted successfully */
    public static int delCustomer(Customers customer){

        try {
            String sqlAppt = "DELETE FROM appointments WHERE Customer_ID=?";
            PreparedStatement psAppt = JDBC.getConnection().prepareStatement(sqlAppt);
            psAppt.setInt(1, customer.getId());

            String sqlCx = "DELETE FROM customers WHERE Customer_ID=?";
            PreparedStatement psCx = JDBC.getConnection().prepareStatement(sqlCx);
            psCx.setInt(1, customer.getId());

            return psAppt.executeUpdate() + psCx.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
