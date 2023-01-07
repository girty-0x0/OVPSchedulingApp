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

/** Controller class for CustomersForm.fxml view. Controls all functions that occur inside the Modify/Add Customers form. */
public class CustomersFormController implements Initializable {

    /** Cancel FXML button. Exits current screen. */
    @FXML
    private Button cancelBtn;

    /** An FXML ComboBox for Countries objects. Drop-down Menu for selecting Countries. */
    @FXML
    private ComboBox<Countries> comboCountry;

    /** An FXML ComboBox  for FirstLvlDivisions objects. Drop-down Menu for selecting FirstLvlDivisions. */
    @FXML
    private ComboBox<FirstLvlDivisions> comboDivision;

    /** Editable field for a Customer's address. FXML TextField. */
    @FXML
    private TextField fieldAddress;

    /** Field for a Customer's ID. Disabled FXML TextField. */
    @FXML
    private TextField fieldId;

    /** Editable field for a Customer's name. FXML TextField. */
    @FXML
    private TextField fieldName;

    /** Editable field for a Customer's phone number. FXML TextField. */
    @FXML
    private TextField fieldPhone;

    /** Editable field for a Customer's postal code. FXML TextField. */
    @FXML
    private TextField fieldPostal;

    /** Save FXML button. Saves data for a customer and exits current screen. */
    @FXML
    private Button saveBtn;

    /** Label for the Form's title. FXML Label that changes dynamically dependent on whether a user is modifying or adding customers. */
    @FXML
    private Label titleLbl;

    /** Initializes the Customer's form and populates required FXML ComboBoxes. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleLbl.setText("Add Customer");
        comboCountry.setItems(DBCountries.getAllCountries());
    }

    /** Sends customer from PrimaryForm.FXML to be modified in CustomersForm.FXML. */
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

    /** Discards all information in TextFields and reloads the PrimaryForm.FXML view. */
    @FXML
    public void onActionCancel(ActionEvent event) throws IOException {
        Utilities.loadView("PrimaryForm.fxml", event);
    }

    /** Populates comboDivision with First Level Administrative Divisions when a Country is specified. */
    @FXML
    public void onActionCountry(ActionEvent event) {
        Countries selectedCountry = comboCountry.getValue();
        comboDivision.setValue(null);

        ObservableList<FirstLvlDivisions> divisions =  DBFirstLvlDivisions.getCountryDivisions(selectedCountry.getId());
        comboDivision.setItems(divisions);
        if(comboDivision.isDisabled()) comboDivision.setDisable(false);
        comboDivision.setPromptText("Select Division");
    }

    /** Validates user input when adding or modifying a customer then updates the database or notifies a user of any errors. If the database is updated successfully, the PrimaryForm.FXML view is loaded.*/
    @FXML
    public void onActionSave(ActionEvent event) throws IOException {
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
