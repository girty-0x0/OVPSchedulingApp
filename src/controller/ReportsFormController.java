package controller;

import DBAccessors.DBAppointments;
import DBAccessors.DBContacts;
import DBAccessors.DBCustomers;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import helper.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

/** Controller class for ReportsForm.fxml view. Controls all functions that occur inside the Reports form after selecting to View Reports in the primary form. */
public class ReportsFormController implements Initializable {

    /** FXML TableView column to represent the contactId of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColContact;

    /** FXML TableView column to represent the contactId of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColContact1;

    /** FXML TableView column to represent the contactId of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColContact2;

    /** FXML TableView column to represent the customerId of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColCustomerId;

    /** FXML TableView column to represent the customerId of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColCustomerId1;

    /** FXML TableView column to represent the customerId of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColCustomerId2;

    /** FXML TableView column to represent the date of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColDate;

    /** FXML TableView column to represent the date of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColDate1;

    /** FXML TableView column to represent the date of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColDate2;

    /** FXML TableView column to represent the description of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColDesc;

    /** FXML TableView column to represent the description of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColDesc1;

    /** FXML TableView column to represent the description of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColDesc2;

    /** FXML TableView column to represent the end time of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColEndTime;

    /** FXML TableView column to represent the end time of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColEndTime1;

    /** FXML TableView column to represent the end time of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColEndTime2;

    /** FXML TableView column to represent the id of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColId;

    /** FXML TableView column to represent the id of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColId1;

    /** FXML TableView column to represent the id of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColId2;

    /** FXML TableView column to represent the location of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColLocation;

    /** FXML TableView column to represent the location of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColLocation1;

    /** FXML TableView column to represent the location of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColLocation2;

    /** FXML TableView column to represent the start time of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColStartTime;

    /** FXML TableView column to represent the start time of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColStartTime1;

    /** FXML TableView column to represent the start time of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColStartTime2;

    /** FXML TableView column to represent the title of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColTitle;

    /** FXML TableView column to represent the title of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColTitle1;

    /** FXML TableView column to represent the title of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColTitle2;

    /** FXML TableView column to represent the type of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColType;

    /** FXML TableView column to represent the type of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColType1;

    /** FXML TableView column to represent the type of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColType2;

    /** FXML TableView column to represent the userId of an Appointments object in the apptTbl. */
    @FXML
    private TableColumn<?, ?> apptColUserId;

    /** FXML TableView column to represent the userId of an Appointments object in the apptTbl1. */
    @FXML
    private TableColumn<?, ?> apptColUserId1;

    /** FXML TableView column to represent the userId of an Appointments object in the apptTbl2. */
    @FXML
    private TableColumn<?, ?> apptColUserId2;

    /** FXML table view that displays all appointments stored in the database depending on the type and month selected in the related ComboBoxes. */
    @FXML
    private TableView<Appointments> apptTbl;

    /** FXML table view that displays all appointments stored in the database depending on the contact selected in the related ComboBox. */
    @FXML
    private TableView<Appointments> apptTbl1;

    /** FXML table view that displays all upcoming appointments stored in the database depending on the customer selected in the related ComboBox. */
    @FXML
    private TableView<Appointments> apptTbl2;

    /** An FXML ComboBox for the contact apptTbl1 will be filtered by. Drop-down Menu to select the contact whose schedule will be displayed. */
    @FXML
    private ComboBox<Contacts> comboTbl1Contact;

    /** An FXML ComboBox for the customer apptTbl1 will be filtered by. Drop-down Menu to select the contact whose upcoming appointments will be displayed. */
    @FXML
    private ComboBox<Customers> comboTbl2Cx;

    /** An FXML ComboBox for the month apptTbl will be filtered by. Drop-down Menu to select the month for displayed appointments. */
    @FXML
    private ComboBox<String> comboTblMonth;

    /** An FXML ComboBox for the type apptTbl will be filtered by. Drop-down Menu to select the type for displayed appointments. */
    @FXML
    private ComboBox<String> comboTblType;

    /** Exit FXML button. Exits current screen. */
    @FXML
    private Button exitBtn;

    /** Get Total FXML button. Makes query to display all filtered appointments on apptTbl and show the total number of appointments. */
    @FXML
    private Button totalBtn;

    /** Label for the apptTbl total number of appointments. FXML Label that changes dynamically dependent the total number of appointments in apptTbl. */
    @FXML
    private Label totalLbl;

    /** Initializes the Reports form and populates required FXML ComboBoxes while also assigning properties to the displayed tables. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> months = FXCollections.observableArrayList();
        months.addAll("January (1)", "February (2)", "March (3)", "April (4)", "May (5)", "June (6)", "July (7)", "August (8)", "September (9)", "October (10)", "November (11)", "December (12)");

        comboTblMonth.setItems(months);
        comboTblType.setItems(Utilities.getTypes());
        comboTbl1Contact.setItems(DBContacts.getAllContacts());
        comboTbl2Cx.setItems(DBCustomers.getAllCustomers());

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

        apptColId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptColTitle1.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptColDesc1.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptColLocation1.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptColContact1.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptColCustomerId1.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptColUserId1.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptColType1.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptColEndTime1.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptColStartTime1.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptColDate1.setCellValueFactory(new PropertyValueFactory<>("day"));

        apptColId2.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptColTitle2.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptColDesc2.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptColLocation2.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptColContact2.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptColCustomerId2.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptColUserId2.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptColType2.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptColEndTime2.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptColStartTime2.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptColDate2.setCellValueFactory(new PropertyValueFactory<>("day"));

    }

    /** Exits current view and the PrimaryForm.FXML view is loaded. */
    @FXML
    public void onActionExit(ActionEvent event) throws IOException {
        Utilities.loadView("PrimaryForm.fxml", event);
    }

    /** Validates user selected a month and a type to filter appointments in the apptTbl and displays filtered appointments. User is notified if no appointments were found with the specified filters and also reports on the total number of filtered appointments. */
    @FXML
    public void onActionGetTotal(ActionEvent event) {
        ObservableList<Appointments> allAppts = DBAppointments.getAllAppointments();
        ObservableList<Appointments> filteredAppts = FXCollections.observableArrayList();
        String type;
        int total = 0;
        Month month = Month.JANUARY; //needed to prevent compilation errors

        if (comboTblType.getValue() == null && comboTblMonth.getValue() == null){
            Utilities.warning.alert("Select a Type and a Month before querying results.");
        } else if (comboTblType.getValue() == null){
            Utilities.warning.alert("Select a Type before querying results.");
        } else if(comboTblMonth.getValue() == null){
            Utilities.warning.alert("Select a Month before querying results.");
        } else{
            for(int i = 1; i <=12; i++) if(comboTblMonth.getValue().contains(String.valueOf(i))) month = Month.of(i); //makes a month object from the month selection

            type = comboTblType.getValue();

            for(Appointments appt : allAppts){
                if(appt.getDay().getMonth() == month && appt.getType().equals(type)) {
                    filteredAppts.add(appt);
                    total++;
                }
            }
            totalLbl.setText(String.valueOf(total));
            if(total == 0){
                Utilities.inform.alert("Sorry! No appointments have your selected Month and Type.");
            }
            apptTbl.setItems(filteredAppts);
        }
    }

    /** Displays all appointments for the specified contact. User is notified if no appointments were found with the specified filters. */
    @FXML
    public void onActionTbl1(ActionEvent event) {
        ObservableList<Appointments> allAppts = DBAppointments.getAllAppointments();
        ObservableList<Appointments> filteredAppts = FXCollections.observableArrayList();

        int contactId = comboTbl1Contact.getValue().getId();

        for(Appointments appt : allAppts){
            if(appt.getContactId() == contactId) filteredAppts.add(appt);
        }

        if(filteredAppts.size() == 0){
            Utilities.inform.alert("Your selected contact has no appointments at the moment.");
        }
        apptTbl1.setItems(filteredAppts);
    }

    /** Displays all upcoming appointments for the specified customer. User is notified if no appointments were found with the specified filters. */
    @FXML
    public void onActionTbl2(ActionEvent event) {
        ObservableList<Appointments> allAppts = DBAppointments.getAllAppointments();
        ObservableList<Appointments> filteredAppts = FXCollections.observableArrayList();
        LocalDateTime rightNow =  LocalDateTime.now();
        LocalDateTime DT;

        int  cxId = comboTbl2Cx.getValue().getId();

        for(Appointments appt : allAppts){
            DT = LocalDateTime.of(appt.getDay(), appt.getStart());
            if(appt.getCustomerId() == cxId && rightNow.isBefore(DT)) filteredAppts.add(appt);
        }

        if(filteredAppts.size() == 0){
            Utilities.inform.alert("Your selected customer has no upcoming appointments at the moment.");
        }
        apptTbl2.setItems(filteredAppts);
    }

}
