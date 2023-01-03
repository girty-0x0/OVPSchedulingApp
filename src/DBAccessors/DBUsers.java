package DBAccessors;

import Model.Users;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBUsers {

    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> users = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()){
                int id = result.getInt("User_ID");
                String username = result.getString("User_Name");
                String password = result.getString("Password");

                Users user = new Users(id, username, password);
                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public static Users getUser(String username) {

        try {
            String sql = "SELECT * FROM users WHERE User_Name=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet result = ps.executeQuery();
            if (result.next()){
                int id = result.getInt("User_ID");
                String uname = result.getString("User_Name");
                String password = result.getString("Password");

                return new Users(id, uname, password);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null; //returns null if no user matches
    }

    public static Users getUser(int userId) {

        try {
            String sql = "SELECT * FROM users WHERE User_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet result = ps.executeQuery();
            if (result.next()){
                int id = result.getInt("User_ID");
                String uname = result.getString("User_Name");
                String password = result.getString("Password");

                return new Users(id, uname, password);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null; //returns null if no user matches
    }
}

