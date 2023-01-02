package controller;

import DBAccessors.DBAppointments;
import DBAccessors.DBCustomers;
import Model.Appointments;
import helper.Utilities;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryFormController implements Initializable {

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
    private TableView<?> apptTbl;

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
    private TableView<?> cxTbl;

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

    @FXML
    void onActionAddAppt(ActionEvent event) throws IOException {
        Utilities.loadView("AppointmentsForm.fxml", event);
    }

    @FXML
    void onActionAddCx(ActionEvent event) {

    }

    @FXML
    void onActionDelAppt(ActionEvent event) {

    }

    @FXML
    void onActionDelCx(ActionEvent event) {

    }

    @FXML
    void onActionModAppt(ActionEvent event) {

    }

    @FXML
    void onActionModCx(ActionEvent event) {

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
        apptTbl.setItems((ObservableList) apptView);
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
        apptTbl.setItems((ObservableList) apptView);
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
