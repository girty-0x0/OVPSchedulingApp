package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Class used to connect with the MySQL database. Thanks for this WGU C195 resources page! */
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    /** Method to open the connection with the SQL database. */
    public static Connection openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(SQLException | ClassNotFoundException e)
        {
            e.printStackTrace(); //more elaborate error message in case issues arise

        }
        return connection;
    }

    /** Method to retrieve the connection established with an SQL database.
     * @return the Connection object with an established connection. */
    public static Connection getConnection(){
        return connection; //prevents need to close and open connections often
    }

    /** Method to close the connection with the SQL database. */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e) {
            //silently catches error
             }
    }

}
