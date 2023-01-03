package controller;

import DBAccessors.DBContacts;
import DBAccessors.DBCustomers;
import DBAccessors.DBUsers;
import helper.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsFormController implements Initializable {
    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<?> comboContactId;

    @FXML
    private ComboBox<?> comboCustomerId;

    @FXML
    private ComboBox<Integer> comboEndHr;

    @FXML
    private ComboBox<Integer> comboEndMin;

    @FXML
    private ComboBox<Integer> comboStartHr;

    @FXML
    private ComboBox<Integer> comboStartMin;

    @FXML
    private ComboBox<?> comboUserId;

    @FXML
    private DatePicker fieldDate;

    @FXML
    private TextField fieldDescription;

    @FXML
    private TextField fieldId;

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
        ObservableList<Integer> hrs = FXCollections.observableArrayList();
        ObservableList<Integer> mins = FXCollections.observableArrayList();

        for(int i = 1; i <= 60; i++) {
            if(i <= 24){
                hrs.add(i);
            }
            mins.add(i);
        }
        comboStartHr.setItems(hrs);
        comboStartMin.setItems(mins);
        comboEndHr.setItems(hrs);
        comboEndMin.setItems(mins);
        comboCustomerId.setItems(DBCustomers.getAllCustomers());
        comboContactId.setItems(DBContacts.getAllContacts());
        comboUserId.setItems(DBUsers.getAllUsers());
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
