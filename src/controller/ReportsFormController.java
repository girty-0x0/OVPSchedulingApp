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

public class ReportsFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> apptColContact;

    @FXML
    private TableColumn<?, ?> apptColContact1;

    @FXML
    private TableColumn<?, ?> apptColContact2;

    @FXML
    private TableColumn<?, ?> apptColCustomerId;

    @FXML
    private TableColumn<?, ?> apptColCustomerId1;

    @FXML
    private TableColumn<?, ?> apptColCustomerId2;

    @FXML
    private TableColumn<?, ?> apptColDate;

    @FXML
    private TableColumn<?, ?> apptColDate1;

    @FXML
    private TableColumn<?, ?> apptColDate2;

    @FXML
    private TableColumn<?, ?> apptColDesc;

    @FXML
    private TableColumn<?, ?> apptColDesc1;

    @FXML
    private TableColumn<?, ?> apptColDesc2;

    @FXML
    private TableColumn<?, ?> apptColEndTime;

    @FXML
    private TableColumn<?, ?> apptColEndTime1;

    @FXML
    private TableColumn<?, ?> apptColEndTime2;

    @FXML
    private TableColumn<?, ?> apptColId;

    @FXML
    private TableColumn<?, ?> apptColId1;

    @FXML
    private TableColumn<?, ?> apptColId2;

    @FXML
    private TableColumn<?, ?> apptColLocation;

    @FXML
    private TableColumn<?, ?> apptColLocation1;

    @FXML
    private TableColumn<?, ?> apptColLocation2;

    @FXML
    private TableColumn<?, ?> apptColStartTime;

    @FXML
    private TableColumn<?, ?> apptColStartTime1;

    @FXML
    private TableColumn<?, ?> apptColStartTime2;

    @FXML
    private TableColumn<?, ?> apptColTitle;

    @FXML
    private TableColumn<?, ?> apptColTitle1;

    @FXML
    private TableColumn<?, ?> apptColTitle2;

    @FXML
    private TableColumn<?, ?> apptColType;

    @FXML
    private TableColumn<?, ?> apptColType1;

    @FXML
    private TableColumn<?, ?> apptColType2;

    @FXML
    private TableColumn<?, ?> apptColUserId;

    @FXML
    private TableColumn<?, ?> apptColUserId1;

    @FXML
    private TableColumn<?, ?> apptColUserId2;

    @FXML
    private TableView<Appointments> apptTbl;

    @FXML
    private TableView<Appointments> apptTbl1;

    @FXML
    private TableView<Appointments> apptTbl2;

    @FXML
    private ComboBox<Contacts> comboTbl1Contact;

    @FXML
    private ComboBox<Customers> comboTbl2Cx;

    @FXML
    private ComboBox<String> comboTblMonth;

    @FXML
    private ComboBox<String> comboTblType;

    @FXML
    private Button exitBtn;

    @FXML
    private Button totalBtn;

    @FXML
    private Label totalLbl;

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

    @FXML
    void onActionExit(ActionEvent event) throws IOException {
        Utilities.loadView("PrimaryForm.fxml", event);
    }

    @FXML
    void onActionGetTotal(ActionEvent event) {
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

    @FXML
    void onActionTbl1(ActionEvent event) {
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

    @FXML
    void onActionTbl2(ActionEvent event) {
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
