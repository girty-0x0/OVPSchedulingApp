package controller;

import DBAccessors.DBContacts;
import DBAccessors.DBCustomers;
import DBAccessors.DBUsers;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import helper.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsFormController implements Initializable {
    private static ObservableList<Customers> allCustomers = DBCustomers.getAllCustomers();
    private static ObservableList<Contacts> allContacts = DBContacts.getAllContacts();
    private static ObservableList<Users> allUsers = DBUsers.getAllUsers();

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<Contacts> comboContactId;

    @FXML
    private ComboBox<Customers> comboCustomerId;

    @FXML
    private ComboBox<Integer> comboEndHr;

    @FXML
    private ComboBox<Integer> comboEndMin;

    @FXML
    private ComboBox<Integer> comboStartHr;

    @FXML
    private ComboBox<Integer> comboStartMin;

    @FXML
    private ComboBox<Users> comboUserId;

    @FXML
    private DatePicker fieldDate;

    @FXML
    private TextField fieldDescription;

    @FXML
    private TextField fieldId;

    @FXML
    private Label titleLbl;

    @FXML
    private TextField fieldLocation;

    @FXML
    private TextField fieldTitle;

    @FXML
    private TextField fieldType;

    @FXML
    private Button saveBtn;

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

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Utilities.loadView("PrimaryForm.fxml", event);
    }

    @FXML
    void onActionSave(ActionEvent event) {
        //make sure to use id -1 when making a new Appointments object unless updating one

    }

}
