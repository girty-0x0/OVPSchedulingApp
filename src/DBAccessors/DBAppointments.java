package DBAccessors;

import Model.Appointments;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public abstract class DBAppointments {
    private static ZoneId utcZone = ZoneId.of("UTC");

    public static ObservableList getAllAppointments() {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()){
                int id = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                String title = result.getString("Title");
                LocalDateTime startTmp = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTmp = result.getTimestamp("End").toLocalDateTime();

                ZonedDateTime start = ZonedDateTime.of(startTmp, utcZone);
                ZonedDateTime end = ZonedDateTime.of(endTmp, utcZone);

                Appointments appt = new Appointments(id, title, description, location, type, start, end, customerId, userId, contactId);
                appointments.add(appt);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointments;
    }
    public static Appointments getAppointment(int appointmentId){
        try {
            String sql = "SELECT * FROM appointments WHERE Appointment_ID=?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ResultSet result = ps.executeQuery();

            if (result.next()){
                int id = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                String title = result.getString("Title");
                LocalDateTime startTmp = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTmp = result.getTimestamp("End").toLocalDateTime();

                ZonedDateTime start = ZonedDateTime.of(startTmp, utcZone);
                ZonedDateTime end = ZonedDateTime.of(endTmp, utcZone);

                return new  Appointments(id, title, description, location, type, start, end, customerId, userId, contactId);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
