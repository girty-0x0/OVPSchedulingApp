package controller;

import helper.TimeZConversion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordLbl;

    @FXML
    private Label timezoneLbl;

    @FXML
    private Label titleLbl;

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timezoneLbl.setText(TimeZConversion.getLocalZone().toString());
    }

    @FXML
    void onActionCancel(ActionEvent event) {

    }

    @FXML
    void onActionLogin(ActionEvent event) {

    }

}
