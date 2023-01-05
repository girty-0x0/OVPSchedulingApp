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

public class PrimaryFormController implements Initializable {
    private static Stage stage; //sets the primary stage

    @FXML
    private Button addApptBtn;

    @FXML
    private Button addCxBtn;

    @FXML
    private TableColumn<?, ?> apptColContact;

    @FXML
    private TableColumn<?, ?> apptColCustomerId;

    @FXML
    private TableColumn<?, ?> apptColDate;

    @FXML
    private TableColumn<?, ?> apptColDesc;

    @FXML
    private TableColumn<?, ?> apptColEndTime;

    @FXML
    private TableColumn<?, ?> apptColId;

    @FXML
    private TableColumn<?, ?> apptColLocation;

    @FXML
    private TableColumn<?, ?> apptColStartTime;

    @FXML
    private TableColumn<?, ?> apptColTitle;

    @FXML
    private TableColumn<?, ?> apptColType;

    @FXML
    private TableColumn<?, ?> apptColUserId;

    @FXML
    private TableView<Appointments> apptTbl;

    @FXML
    private ToggleGroup apptTblView;

    @FXML
    private TableColumn<?, ?> cxColAddr;

    @FXML
    private TableColumn<?, ?> cxColDivisionId;

    @FXML
    private TableColumn<?, ?> cxColId;

    @FXML
    private TableColumn<?, ?> cxColName;

    @FXML
    private TableColumn<?, ?> cxColPhone;

    @FXML
    private TableColumn<?, ?> cxColPostal;

    @FXML
    private TableView<Customers> cxTbl;

    @FXML
    private Button delApptBtn;

    @FXML
    private Button delCxBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button modApptBtn;

    @FXML
    private Button modCxBtn;

    @FXML
    private RadioButton radioBtnAll;

    @FXML
    private RadioButton radioBtnMonth;

    @FXML
    private RadioButton radioBtnWeek;

    @FXML
    private Button reportsBtn;

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

    @FXML
    void onActionAddAppt(ActionEvent event) throws IOException {
        Utilities.loadView("AppointmentsForm.fxml", event);
    }

    @FXML
    void onActionAddCx(ActionEvent event) throws IOException {
        Utilities.loadView("CustomersForm.fxml", event);
    }

    @FXML
    void onActionDelAppt(ActionEvent event) {
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

    @FXML
    void onActionDelCx(ActionEvent event) {
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

    @FXML
    void onActionModAppt(ActionEvent event) throws IOException {
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

    @FXML
    void onActionModCx(ActionEvent event) throws IOException {

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

    @FXML
    void onActionMonthView(ActionEvent event) {
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

    @FXML
    void onActionWeekView(ActionEvent event) {
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

    @FXML
    void onActionAllView(ActionEvent event) {
        apptTbl.setItems(DBAppointments.getAllAppointments());
    }

    @FXML
    void onActionViewReports(ActionEvent event) {

    }

    @FXML
    void onActionLogout(ActionEvent event) throws IOException {
        Utilities.loadView("LoginForm.fxml", event);
    }
}
