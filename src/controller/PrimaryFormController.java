package controller;

import DBAccessors.DBAppointments;
import DBAccessors.DBCustomers;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
        modApptBtn.disableProperty().bind(Bindings.isEmpty(apptTbl.getSelectionModel().getSelectedItems())); //disables modify button by apptTbl until an appt is selected
        delApptBtn.disableProperty().bind(Bindings.isEmpty(apptTbl.getSelectionModel().getSelectedItems()));
        modCxBtn.disableProperty().bind(Bindings.isEmpty(cxTbl.getSelectionModel().getSelectedItems()));
        delCxBtn.disableProperty().bind(Bindings.isEmpty(cxTbl.getSelectionModel().getSelectedItems()));

        cxTbl.setItems(DBCustomers.getAllCustomers());
        cxColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cxColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cxColAddr.setCellValueFactory(new PropertyValueFactory<>("address"));
        cxColPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        cxColPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cxColDivisionId.setCellValueFactory(new PropertyValueFactory<>("firstLvlDivisionId"));

        apptTbl.setItems(DBAppointments.getAllAppointments());
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

    }

    @FXML
    void onActionAddAppt(ActionEvent event) {

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

    }

    @FXML
    void onActionWeekView(ActionEvent event) {

    }

    @FXML
    void onActionAllView(ActionEvent event) {

    }

    @FXML
    void onActionViewReports(ActionEvent event) {

    }

    @FXML
    void onActionLogout(ActionEvent event) {

    }

}
