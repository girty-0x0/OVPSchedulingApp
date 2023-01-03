package DBAccessors;

import Model.Appointments;
import helper.JDBC;
import helper.TimeZConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;

public abstract class DBAppointments {
    private static ZoneId utcZone = ZoneId.of("UTC");

    public static ObservableList<Appointments> getAllAppointments() {
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
                LocalDateTime startTmp = result.getTimestamp("Start").toInstant().atZone(TimeZConversion.getLocalZone()).toLocalDateTime();
                LocalDateTime endTmp = result.getTimestamp("End").toInstant().atZone(TimeZConversion.getLocalZone()).toLocalDateTime();
                ZonedDateTime start = ZonedDateTime.of(startTmp, TimeZConversion.getLocalZone());
                ZonedDateTime end = ZonedDateTime.of(endTmp, TimeZConversion.getLocalZone());

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
                //.toLocalDateTime does implicit conversion; changed to .toInstant() to specify time zone conversion
                LocalDateTime startTmp = result.getTimestamp("Start").toInstant().atZone(TimeZConversion.getLocalZone()).toLocalDateTime();
                LocalDateTime endTmp = result.getTimestamp("End").toInstant().atZone(TimeZConversion.getLocalZone()).toLocalDateTime();

                ZonedDateTime start = ZonedDateTime.of(startTmp, TimeZConversion.getLocalZone());
                ZonedDateTime end = ZonedDateTime.of(endTmp, TimeZConversion.getLocalZone());

                return new  Appointments(id, title, description, location, type, start, end, customerId, userId, contactId);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static int addAppointment(Appointments appt){
        int customerId = appt.getCustomerId();
        int userId = appt.getUserId();
        int contactId = appt.getContactId();
        String title = appt.getTitle();
        String description = appt.getDescription();
        String location = appt.getLocation();
        String type = appt.getType();
        LocalDateTime start;
        LocalDateTime end;

        LocalDate day = appt.getDay();
        LocalTime startTime = appt.getStart();
        LocalTime endTime = appt.getEnd();
        //convert from LocalTime to DateTime
        LocalDateTime startDT = LocalDateTime.of(day, startTime);
        LocalDateTime endDT = LocalDateTime.of(day, endTime);
        //convert from DateTime to ZonedDateTime
        ZonedDateTime startZDT = ZonedDateTime.of(startDT, TimeZConversion.getLocalZone());
        ZonedDateTime endZDT = ZonedDateTime.of(endDT, TimeZConversion.getLocalZone());
        //convert from user's timezone to utc and then to DateTime for database storage
        start = (TimeZConversion.localToUtc(startZDT)).toLocalDateTime();
        end = (TimeZConversion.localToUtc(endZDT)).toLocalDateTime();

        try{
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);

            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
