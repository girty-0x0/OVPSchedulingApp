package controller;

import DBAccessors.DBAppointments;
import DBAccessors.DBContacts;
import DBAccessors.DBCustomers;
import DBAccessors.DBUsers;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import helper.TimeZConversion;
import helper.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/** Controller class for AppointmentsForm.fxml view. Controls all functions that occur inside the Modify/Add Appointments form. */
public class AppointmentsFormController implements Initializable {
    /** ObservableList array that holds all customers stored in the database. */
    private static ObservableList<Customers> allCustomers = DBCustomers.getAllCustomers();
    /** ObservableList array that holds all contacts stored in the database. */
    private static ObservableList<Contacts> allContacts = DBContacts.getAllContacts();
    /** ObservableList array that holds all users stored in the database. */
    private static ObservableList<Users> allUsers = DBUsers.getAllUsers();

    /** Cancel FXML button. Exits current screen. */
    @FXML
    private Button cancelBtn;

    /** An FXML ComboBox for Contacts objects. Drop-down Menu for selecting related Contacts. */
    @FXML
    private ComboBox<Contacts> comboContactId;

    /** An FXML ComboBox for Customers objects. Drop-down Menu for selecting related Customers. */
    @FXML
    private ComboBox<Customers> comboCustomerId;

    /** An FXML ComboBox for the ending hour. Drop-down Menu to select the hour an appointment ends. */
    @FXML
    private ComboBox<Integer> comboEndHr;

    /** An FXML ComboBox for the ending minute. Drop-down Menu to select the minute an appointment ends. */
    @FXML
    private ComboBox<Integer> comboEndMin;

    /** An FXML ComboBox for the starting hour. Drop-down Menu to select the hour an appointment starts. */
    @FXML
    private ComboBox<Integer> comboStartHr;

    /** An FXML ComboBox for the starting minute. Drop-down Menu to select the minute an appointment starts. */
    @FXML
    private ComboBox<Integer> comboStartMin;

    /** An FXML ComboBox for Users objects. Drop-down Menu for selecting related Users. */
    @FXML
    private ComboBox<Users> comboUserId;

    /** An FXML DatePicker object to pick an appointment's scheduled date. */
    @FXML
    private DatePicker fieldDate;

    /** Editable field for an appointment's description. FXML TextField. */
    @FXML
    private TextField fieldDescription;

    /** Field for an appointment's ID. Disabled FXML TextField. */
    @FXML
    private TextField fieldId;

    /** Label for the Form's title. FXML Label that changes dynamically dependent on whether a user is modifying or adding appointments. */
    @FXML
    private Label titleLbl;

    /** Editable field for an appointment's location. FXML TextField. */
    @FXML
    private TextField fieldLocation;

    /** Editable field for an appointment's title. FXML TextField. */
    @FXML
    private TextField fieldTitle;

    /** Editable field for an appointment's type. FXML TextField. */
    @FXML
    private TextField fieldType;

    /** Save FXML button. Saves data for an appointment and exits current screen. */
    @FXML
    private Button saveBtn;

    /** Initializes the Appointment's form and populates required FXML ComboBoxes. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleLbl.setText("Add Appointment");
        allCustomers = DBCustomers.getAllCustomers();
        allContacts = DBContacts.getAllContacts();
        allUsers = DBUsers.getAllUsers();

        ObservableList<Integer> hrs = FXCollections.observableArrayList();
        ObservableList<Integer> mins = FXCollections.observableArrayList();

        for(int i = 0; i <= 59; i++) {
            if(i <= 23){
                hrs.add(i);
            }
            mins.add(i);
        }
        comboStartHr.setItems(hrs);
        comboStartMin.setItems(mins);
        comboEndHr.setItems(hrs);
        comboEndMin.setItems(mins);
        comboCustomerId.setItems(allCustomers);
        comboContactId.setItems(allContacts);
        comboUserId.setItems(allUsers);
    }

    /** Sends a selected appointment from PrimaryForm.FXML to be modified in AppointmentsForm.FXML. */
    public void sendAppointment(Appointments appt){
        titleLbl.setText("Modify Appointment");

        fieldId.setText(String.valueOf(appt.getId()));
        fieldTitle.setText(appt.getTitle());
        fieldDescription.setText(appt.getDescription());
        fieldDate.setValue(appt.getDay());
        fieldLocation.setText(appt.getLocation());
        fieldType.setText(appt.getType());
        comboStartHr.getSelectionModel().select(appt.getStart().getHour());
        comboStartMin.getSelectionModel().select(appt.getStart().getMinute());
        comboEndHr.getSelectionModel().select(appt.getEnd().getHour());
        comboEndMin.getSelectionModel().select(appt.getEnd().getMinute());
        comboUserId.setValue(DBUsers.getUser(appt.getUserId()));
        comboContactId.setValue(DBContacts.getContact(appt.getContactId()));
        comboCustomerId.setValue(DBCustomers.getCustomer(appt.getCustomerId()));

    }

    /** Discards all information in TextFields and reloads the PrimaryForm.FXML view. */
    @FXML
    public void onActionCancel(ActionEvent event) throws IOException {
        Utilities.loadView("PrimaryForm.fxml", event);
    }

    /** Validates user input when adding or modifying an appointment then updates the database or notifies a user of any errors. If the database is updated successfully, the PrimaryForm.FXML view is loaded.*/
    @FXML
    public void onActionSave(ActionEvent event) throws IOException {
        //make sure to use id -1 when making a new Appointments object unless updating one
        boolean isMod = false;
        int startMin, startHr, endMin, endHr;
        int customerId, contactId, userId;
        int id = -1;
        LocalDate day;
        LocalTime startTime, endTime;
        String title, description, location, type;

        if(titleLbl.getText().contains("Modify")) {
            isMod = true;
            id = Integer.parseInt(fieldId.getText());
        }

        for(int i = 0; i<1; i++){

            if(comboStartHr.getValue() == null){
                Utilities.warning.alert("Please choose a starting hour.");
                break;
            } else if(comboStartMin.getValue() == null){
                Utilities.warning.alert("Please choose a starting minute.");
                break;
            } else if(comboEndHr.getValue() == null){
                Utilities.warning.alert("Please choose an ending hour.");
                break;
            } else if(comboEndMin.getValue() == null){
                Utilities.warning.alert("Please choose an ending minute.");
                break;
            } else if(comboUserId.getValue() == null){
                Utilities.warning.alert("Please choose a User ID.");
                break;
            } else if(comboContactId.getValue() == null){
                Utilities.warning.alert("Please choose a Contact ID.");
                break;
            } else if(comboCustomerId.getValue() == null){
                Utilities.warning.alert("Please choose a Customer ID.");
                break;
            } else if(fieldDate.getValue() == null){
                Utilities.warning.alert("Please select a Date for the appointment.");
                break;
            } else if(fieldTitle.getText().isEmpty()){
                Utilities.warning.alert("Please type a Title for the Appointment");
                break;
            } else if(fieldType.getText().isEmpty()){
                Utilities.warning.alert("Please type a Type for the Appointment");
                break;
            } else if(fieldLocation.getText().isEmpty()){
                Utilities.warning.alert("Please type a Location for the Appointment");
                break;
            } else if(fieldDescription.getText().isEmpty()){
                Utilities.warning.alert("Please type a Description for the Appointment");
                break;
            }
            userId = comboUserId.getValue().getId();
            customerId = comboCustomerId.getValue().getId();
            contactId = comboContactId.getValue().getId();

            startHr = comboStartHr.getValue();
            startMin = comboStartMin.getValue();
            endHr = comboEndHr.getValue();
            endMin = comboEndMin.getValue();

            title = fieldTitle.getText();
            description = fieldDescription.getText();
            location = fieldLocation.getText();
            type = fieldType.getText();

            startTime = LocalTime.of(startHr, startMin);
            endTime = LocalTime.of(endHr, endMin);
            day = fieldDate.getValue();
            ZonedDateTime startZDT = ZonedDateTime.of((LocalDateTime.of(day, startTime)), TimeZConversion.getLocalZone());
            ZonedDateTime endZDT = ZonedDateTime.of((LocalDateTime.of(day, endTime)), TimeZConversion.getLocalZone());

            //check starting hour is before end hour
            if(startZDT.isAfter(endZDT)){
                Utilities.warning.alert("The starting time must be before the ending time.");
                break;
            }
            //check if between working hours
            if(!(Utilities.isBetweenWorkingHours(startZDT, endZDT))){
                ZonedDateTime[] timeArr = Utilities.getWorkingLZDT(day);
                Utilities.warning.alert("Please make sure to schedule between our working hours: " + timeArr[0].toLocalTime() + "-" + timeArr[1].toLocalTime());
                break;
            }
            //check if conflicting appointments exist
            Appointments conflictingAppt = Utilities.isConflicting(startZDT, endZDT, customerId, id);
            if(conflictingAppt != null){
                Utilities.warning.alert("This appointment conflicts with Appointment ID: " + conflictingAppt.getId() + " scheduled for " + conflictingAppt.getDay() + " from " + conflictingAppt.getStart() + " to " + conflictingAppt.getEnd());
                break;
            }
            Appointments appt = new Appointments(id, title, description, location, type, startZDT, endZDT, customerId, userId, contactId);
            if(isMod){
                if(!(DBAppointments.modAppointment(appt) > 0)){ //if no rows were updated
                    Utilities.error.alert("There was an error updating this Appointment.");
                    break;
                }
            } else{
                if(!(DBAppointments.addAppointment(appt) > 0)){ //if no rows were updated
                    Utilities.error.alert("There was an error adding this Appointment.");
                    break;
                }
            }
            Utilities.loadView("PrimaryForm.fxml", event);
        }
    }
}
