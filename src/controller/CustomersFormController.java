package controller;

import DBAccessors.DBCountries;
import DBAccessors.DBCustomers;
import DBAccessors.DBFirstLvlDivisions;
import Model.Countries;
import Model.Customers;
import Model.FirstLvlDivisions;
import helper.Utilities;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomersFormController implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<Countries> comboCountry;

    @FXML
    private ComboBox<FirstLvlDivisions> comboDivision;

    @FXML
    private TextField fieldAddress;

    @FXML
    private TextField fieldId;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldPhone;

    @FXML
    private TextField fieldPostal;

    @FXML
    private Button saveBtn;

    @FXML
    private Label titleLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleLbl.setText("Add Customer");
        comboCountry.setItems(DBCountries.getAllCountries());
    }

    public void sendCustomer(Customers customer){

        titleLbl.setText("Modify Customer");
        Countries cxCountry = DBCountries.getCountry(customer.getFirstLvlDivisionId());
        ObservableList<FirstLvlDivisions> divisions = DBFirstLvlDivisions.getCountryDivisions(cxCountry.getId());

        comboCountry.setValue(cxCountry);
        comboDivision.setItems(divisions);

        for(FirstLvlDivisions div : divisions){
            if(div.getId() == customer.getFirstLvlDivisionId()){
                comboDivision.setValue(div);
                comboDivision.setDisable(false);
            }
        }
        fieldId.setText(String.valueOf(customer.getId()));
        fieldAddress.setText(customer.getAddress());
        fieldName.setText(customer.getName());
        fieldPhone.setText(customer.getPhone());
        fieldPostal.setText(customer.getPostalCode());
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Utilities.loadView("PrimaryForm.fxml", event);
    }

    @FXML
    void onActionCountry(ActionEvent event) {
        Countries selectedCountry = comboCountry.getValue();
        comboDivision.setValue(null);

        ObservableList<FirstLvlDivisions> divisions =  DBFirstLvlDivisions.getCountryDivisions(selectedCountry.getId());
        comboDivision.setItems(divisions);
        if(comboDivision.isDisabled()) comboDivision.setDisable(false);
        comboDivision.setPromptText("Select Division");
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        boolean isMod = false;
        int firstLvlDivisionId;
        int id = -1;
        //
        String name, address, postalCode, phone;

        if (titleLbl.getText().contains("Modify")) {
            isMod = true;
            id = Integer.parseInt(fieldId.getText());
        }

        for (int i = 0; i < 1; i++) {

            if (comboCountry.getValue() == null) {
                Utilities.warning.alert("Please choose a Country option.");
                break;
            } else if (comboDivision.getValue() == null) {
                Utilities.warning.alert("Please choose an Administrative Division option.");
                break;
            } else if (fieldName.getText().isEmpty()) {
                Utilities.warning.alert("Please type a Name for the Customer");
                break;
            } else if (fieldAddress.getText().isEmpty()) {
                Utilities.warning.alert("Please type an Address for the Customer");
                break;
            } else if (fieldPostal.getText().isEmpty()) {
                Utilities.warning.alert("Please type an Postal Code for the Customer");
                break;
            } else if (fieldPhone.getText().isEmpty()) {
                Utilities.warning.alert("Please type an Phone Number for the Customer");
                break;
            }
            name = fieldName.getText();
            address = fieldAddress.getText();
            postalCode = fieldPostal.getText();
            phone = fieldPhone.getText();
            firstLvlDivisionId = comboDivision.getValue().getId();

            Customers customer = new Customers(id, name, address, postalCode, phone, firstLvlDivisionId);
            if(isMod){
                if(DBCustomers.updateCustomer(customer) < 1){
                    Utilities.error.alert("There was an error updating this Customer.");
                    break;
                }
            } else{
                if(DBCustomers.addCustomer(customer) < 1){
                    Utilities.error.alert("There was an error adding this Customer.");
                    break;
                }
            }
            Utilities.loadView("PrimaryForm.fxml", event);
        }
    }
}
