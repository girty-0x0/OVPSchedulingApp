package controller;

import DBAccessors.DBAppointments;
import DBAccessors.DBCustomers;
import Model.Appointments;
import Model.Customers;
import Model.Users;
import helper.TimeZConversion;
import helper.Utilities;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/** Controller class for PrimaryForm.fxml view. Controls all functions that occur inside the Primary form after logging in. */
public class PrimaryFormController implements Initializable {

    /** Stage instance variable to hold the primary stage when transferring data across forms. */
    private static Stage stage; //sets the primary stage

    /** Add Appointment FXML button. Loads AppointmentsForm.FXML to add a new appointment in the database. */
    @FXML
    private Button addApptBtn;

    /** Add Customer FXML button. Loads CustomersForm.FXML to add a new customer in the database. */
    @FXML
    private Button addCxBtn;

    /** FXML TableView column to represent the contactId of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColContact;

    /** FXML TableView column to represent the customerId of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColCustomerId;

    /** FXML TableView column to represent the date of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColDate;

    /** FXML TableView column to represent the description of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColDesc;

    /** FXML TableView column to represent the end time of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColEndTime;

    /** FXML TableView column to represent the id of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColId;

    /** FXML TableView column to represent the location of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColLocation;

    /** FXML TableView column to represent the start time of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColStartTime;

    /** FXML TableView column to represent the title of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColTitle;

    /** FXML TableView column to represent the type of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColType;

    /** FXML TableView column to represent the userId of an Appointments object. */
    @FXML
    private TableColumn<?, ?> apptColUserId;

    /** FXML table view that displays all appointments stored in the database. View can be changed by selecting a radio button for all, week, and month. */
    @FXML
    private TableView<Appointments> apptTbl;

    /** Toggle group for Appointments table view. Assures only one radio button can be selected when choosing the desired monthly, weekly, or all view for the appointments table. */
    @FXML
    private ToggleGroup apptTblView;

    /** FXML TableView column to represent the address of a Customers object. */
    @FXML
    private TableColumn<?, ?> cxColAddr;

    /** FXML TableView column to represent the firstLvlDivisionId of a Customers object. */
    @FXML
    private TableColumn<?, ?> cxColDivisionId;

    /** FXML TableView column to represent the id of a Customers object. */
    @FXML
    private TableColumn<?, ?> cxColId;

    /** FXML TableView column to represent the name of a Customers object. */
    @FXML
    private TableColumn<?, ?> cxColName;

    /** FXML TableView column to represent the phone of a Customers object. */
    @FXML
    private TableColumn<?, ?> cxColPhone;

    /** FXML TableView column to represent the postalCode of a Customers object. */
    @FXML
    private TableColumn<?, ?> cxColPostal;

    /** FXML table view that displays all customers stored in the database. */
    @FXML
    private TableView<Customers> cxTbl;

    /** Delete Appointment FXML button. Deletes a selected appointment from the database. */
    @FXML
    private Button delApptBtn;

    /** Delete Customer FXML button. Deletes a selected Customer from the database. */
    @FXML
    private Button delCxBtn;

    /** Logout FXML button. Loads LoginForm.FXML and prompts a user to log in to be able to use the application again. */
    @FXML
    private Button logoutBtn;

    /** Modify Appointment FXML button. Loads AppointmentsForm.FXML to modify a selected appointment in the Appointments table from the database. */
    @FXML
    private Button modApptBtn;

    /** Modify Customer FXML button. Loads CustomersForm.FXML to modify a selected customer in the Customers table from the database. */
    @FXML
    private Button modCxBtn;

    /** View all Appointments FXML radio button. Reloads appointments table to show all appointments stored in the database. */
    @FXML
    private RadioButton radioBtnAll;

    /** View all Appointments this month FXML radio button. Reloads appointments table to show all appointments in the upcoming month that are stored in the database. */
    @FXML
    private RadioButton radioBtnMonth;

    /** View all Appointments this week FXML radio button. Reloads appointments table to show all appointments in the upcoming week that are stored in the database. */
    @FXML
    private RadioButton radioBtnWeek;

    /** View Reports FXML button. Loads ReportsForm.FXML to view all scenario-specific reports produced in the application. */
    @FXML
    private Button reportsBtn;

    /** Initializes the primary form after logging in and populates all displayed tables and their columns. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cxTbl.setItems(DBCustomers.getAllCustomers());
        apptTbl.setItems(DBAppointments.getAllAppointments());

        cxColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cxColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cxColAddr.setCellValueFactory(new PropertyValueFactory<>("address"));
        cxColPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        cxColPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cxColDivisionId.setCellValueFactory(new PropertyValueFactory<>("firstLvlDivisionId"));

        apptColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptColDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptColLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptColContact.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptColCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptColUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptColEndTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptColStartTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptColDate.setCellValueFactory(new PropertyValueFactory<>("day"));

        modApptBtn.disableProperty().bind(Bindings.isEmpty(apptTbl.getSelectionModel().getSelectedItems())); //disables modify button by apptTbl until an appt is selected
        delApptBtn.disableProperty().bind(Bindings.isEmpty(apptTbl.getSelectionModel().getSelectedItems()));
        modCxBtn.disableProperty().bind(Bindings.isEmpty(cxTbl.getSelectionModel().getSelectedItems()));
        delCxBtn.disableProperty().bind(Bindings.isEmpty(cxTbl.getSelectionModel().getSelectedItems()));
    }

    /** Sends the user logging in from LoginForm.FXML to the current form and notifies if they have any upcoming appointments in the next 15 minutes. */
    public void sendUsr(Users usr){
        ZonedDateTime curZDT = TimeZConversion.getCurrentZDT();
        boolean hasAppt = false;

        for(Appointments appt : DBAppointments.getAllAppointments()){
            if(usr.getId() == appt.getUserId()){
                if(appt.getDay().isEqual(curZDT.toLocalDate())){
                    if(curZDT.toLocalTime().isBefore(appt.getStart()) && curZDT.toLocalTime().isAfter(appt.getStart().minusMinutes(15))){
                        Utilities.inform.alert("Your appointment (ID: " + appt.getId() + ") scheduled for " + appt.getDay() + " from " + appt.getStart() + " to " + appt.getEnd() + " starts in the next 15 minutes.");
                        hasAppt = true;
                    }
                }
            }
        }
        if(!hasAppt) Utilities.inform.alert("You have no upcoming appointments in the next 15 minutes.");
    }

    /** Loads AppointmentsForm.FXML to create a new appointment in the database. */
    @FXML
    public void onActionAddAppt(ActionEvent event) throws IOException {
        Utilities.loadView("AppointmentsForm.fxml", event);
    }

    /** Loads CustomersForm.FXML to create a new customer in the database. */
    @FXML
    public void onActionAddCx(ActionEvent event) throws IOException {
        Utilities.loadView("CustomersForm.fxml", event);
    }

    /** Prompts user to confirm they want to delete the selected appointment in apptTbl from the database and reloads table to reflect change. */
    @FXML
    public void onActionDelAppt(ActionEvent event) {
        String msg, infoMsg;

        Appointments appt = apptTbl.getSelectionModel().getSelectedItem();
        msg = "Are you sure you want to delete Appointment ID: " + appt.getId() + " Type: " + appt.getType()+ "?";
        infoMsg = "Appointment ID: " + appt.getId() + " Type: " + appt.getType() + " has been deleted.";
        if(Utilities.confirmPopUp(msg)){
            if(DBAppointments.delAppointment(appt) > 0){
                if(radioBtnMonth.isSelected()) onActionMonthView(event);
                else if(radioBtnWeek.isSelected()) onActionWeekView(event);
                else onActionAllView(event);
                Utilities.inform.alert(infoMsg);
            }
        }
    }

    /** Prompts user to confirm they want to delete the selected customer in cxTbl from the database and reloads table to reflect change. */
    @FXML
    public void onActionDelCx(ActionEvent event) {
        String msg, infoMsg;

        Customers customer = cxTbl.getSelectionModel().getSelectedItem();
        msg = "Are you sure you want to delete Customer ID: " + customer.getId() + " Name: " + customer.getName() + "? All associated appointments will also be deleted.";
        infoMsg = "Customer ID: " + customer.getId() + " Name: " + customer.getName() + " and their appointments have been deleted.";
        if(Utilities.confirmPopUp(msg)){
            if(DBCustomers.delCustomer(customer) > 0){
                cxTbl.setItems(DBCustomers.getAllCustomers());
                if(radioBtnMonth.isSelected()) onActionMonthView(event);
                else if(radioBtnWeek.isSelected()) onActionWeekView(event);
                else onActionAllView(event);
                Utilities.inform.alert(infoMsg);
            }
        }
    }

    /** Loads AppointmentsForm.FXML and sends the selected appointment from the apptTbl for modification in the database. */
    @FXML
    public void onActionModAppt(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/AppointmentsForm.fxml"));
        loader.load();

        AppointmentsFormController apptForm = loader.getController();
        apptForm.sendAppointment(apptTbl.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); //stage is loaded with the Modify form in the next four lines; object's attributes are appropriately placed in their respective fields
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.showAndWait();
    }

    /** Loads CustomersForm.FXML and sends the selected user from the cxTbl for modification in the database. */
    @FXML
    public void onActionModCx(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CustomersForm.fxml"));
        loader.load();

        CustomersFormController cxForm = loader.getController();
        cxForm.sendCustomer(cxTbl.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); //stage is loaded with the Modify form in the next four lines; object's attributes are appropriately placed in their respective fields
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.showAndWait();
    }

    /** Loads all appointments of the upcoming month stored in the database to the apptTbl when radioBtnMonth is selected. */
    @FXML
    public void onActionMonthView(ActionEvent event) {
        ObservableList<Appointments> apptView = FXCollections.observableArrayList();
        ObservableList<Appointments> allAppts;
        allAppts = DBAppointments.getAllAppointments();

        for(Appointments appt : allAppts) {
            if(Utilities.compareDates(1, appt.getDay())){
                apptView.add(appt);
            }
        }
        apptTbl.setItems(apptView);
    }

    /** Loads all appointments of the upcoming week stored in the database to the apptTbl when radioBtnWeek is selected. */
    @FXML
    public void onActionWeekView(ActionEvent event) {
        ObservableList<Appointments> apptView = FXCollections.observableArrayList();
        ObservableList<Appointments> allAppts;
        allAppts = DBAppointments.getAllAppointments();

        for(Appointments appt : allAppts) {
            if(Utilities.compareDates(2, appt.getDay())){
                apptView.add(appt);
            }
        }
        apptTbl.setItems(apptView);
    }

    /** Loads all appointments stored in the database to the apptTbl when radioBtnAll is selected. */
    @FXML
    public void onActionAllView(ActionEvent event) {
        apptTbl.setItems(DBAppointments.getAllAppointments());
    }

    /** Loads the ReportsForm.FXML view. */
    @FXML
    public void onActionViewReports(ActionEvent event) throws IOException {
        Utilities.loadView("ReportsForm.fxml", event);
    }

    /** Loads the LoginForm.FXML view. User would be prompted to log-in again to use the application. */
    @FXML
    public void onActionLogout(ActionEvent event) throws IOException {
        Utilities.loadView("LoginForm.fxml", event);
    }
}
