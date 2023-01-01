package controller;

import DBAccessors.DBUsers;
import Model.Users;
import helper.JDBC;
import helper.Utilities;
import helper.TimeZConversion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    private static boolean isFrench;

    private static ResourceBundle rb = ResourceBundle.getBundle("helper/loginForm", Locale.FRENCH);

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

        if(Locale.getDefault().getLanguage().equals("fr")){
            usernameLbl.setText(rb.getString("username"));
            passwordLbl.setText(rb.getString("password"));
            cancelBtn.setText(rb.getString("Cancel"));
            loginBtn.setText(rb.getString("identify"));
            titleLbl.setText(rb.getString("Login"));
            isFrench = true;
        }
    }

    @FXML
    void onActionCancel(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {

        String uname = usernameField.getText();
        String passwd = passwordField.getText();
        boolean isPassword = false;

        if((passwd.isEmpty() || uname.isEmpty()) && isFrench){

            if(uname.isEmpty() && passwd.isEmpty()) Utilities.warningFR.alert(rb.getString("plsUP"));
            else if(uname.isEmpty()) Utilities.warningFR.alert(rb.getString("plsUname"));
            else Utilities.warningFR.alert(rb.getString("plsPasswd"));

        } else if(passwd.isEmpty() || uname.isEmpty()){

            if(uname.isEmpty() && passwd.isEmpty()) Utilities.warning.alert("Please enter a username and password");
            else if(uname.isEmpty()) Utilities.warning.alert("Please enter a username");
            else Utilities.warning.alert("Please enter a password");

        } else{
            Users user = DBUsers.getUser(uname);

            if(user != null) isPassword = user.getPassword().equals(passwd); //if one user is returned
            if(isPassword){
                Utilities.loadView("PrimaryForm.fxml", event);
            } else{ //if no user was found or password is incorrect

                if(isFrench) Utilities.warningFR.alert(rb.getString("incorrect"));
                else Utilities.warning.alert("Incorrect username or password");
            }
        }
    }

}
