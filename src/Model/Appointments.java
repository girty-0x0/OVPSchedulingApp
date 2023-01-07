package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/** Appointments class. Represent Appointments stored in MySQL database. */
public class Appointments {
    /** id instance variable. Unique identifier. */
    private int id;
    /** title instance variable. */
    private String title;
    /** description instance variable. Describes appointment. */
    private String description;
    /** location instance variable. Describes appointment location. */
    private String location;
    /** type instance variable. Describes appointment type. */
    private String type;
    /** day instance variable. LocalDate value for appointment starting date. */
    private LocalDate day;
    /** start instance variable. LocalTime value for appointment starting time. */
    private LocalTime start;
    /** end instance variable. LocalTime value for appointment ending time. */
    private LocalTime end;
    /** customerId instance variable. Connects Appointment to a Customers object. */
    private int customerId;
    /** userId instance variable. Connects Appointment to a Users object. */
    private int userId;
    /** contactId instance variable. Connects Appointment to a Contacts object. */
    private int contactId;

    /** Constructor for an Appointments object. Returns Appointments object. */
    public Appointments(int id, String title, String description, String location, String type, ZonedDateTime start, ZonedDateTime end, int customerId, int userId, int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start.toLocalTime(); //translates from utc time to local time zone's time for easy display
        this.end = end.toLocalTime(); //translates from utc time to local time zone's time
        this.day = start.toLocalDate(); //translates from utc date to local time zone's date
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /** Getter for the Appointments object id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /** Setter for the Appointments object id.
     * @param id the Appointment ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Getter for the Appointments object title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /** Setter for the Appointments object title.
     * @param title the appointment title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Getter for the Appointments object description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /** Setter for the Appointments object description.
     * @param description the appointment description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /** Getter for the Appointments object location.
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /** Setter for the Appointments object location.
     * @param location the appointment location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /** Getter for the Appointments object type.
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /** Setter for the Appointments object type.
     * @param type the appointment type
     */
    public void setType(String type) {
        this.type = type;
    }

    /** Getter for the Appointments object start.
     * @return the start time
     */
    public LocalTime getStart() {
        return start;
    }

    /** Setter for the Appointments object start.
     * @param start the appointment start time
     */
    public void setStart(LocalTime start) {
        this.start = start;
    }

    /** Getter for the Appointments object end.
     * @return the end time
     */
    public LocalTime getEnd() {
        return end;
    }

    /** Setter for the Appointments object end.
     * @param end the appointment end time
     */
    public void setEnd(LocalTime end) {
        this.end = end;
    }

    /** Getter for the Appointments object customerId.
     * @return the related customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /** Setter for the Appointments object customerId.
     * @param customerId the appointment's related customer ID
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** Getter for the Appointments object userId.
     * @return the related user ID
     */
    public int getUserId() {
        return userId;
    }

    /** Setter for the Appointments object userId.
     * @param userId the appointment's related user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Getter for the Appointments object contactId.
     * @return the related contact ID
     */
    public int getContactId() {
        return contactId;
    }

    /** Setter for the Appointments object contactId.
     * @param contactId the appointment's related contact ID
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** Getter for the Appointments object day.
     * @return the appointment's date
     */
    public LocalDate getDay() {
        return day;
    }

    /** Setter for the Appointments object day.
     * @param day the appointment's date
     */
    public void setDay(LocalDate day) {
        this.day = day;
    }
}
