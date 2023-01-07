package controller;

import DBAccessors.DBUsers;
import Model.Users;
import helper.JDBC;
import helper.Utilities;
import helper.TimeZConversion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    private static boolean isFrench;
    private static Stage stage; //sets the primary stage

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
    public void onActionCancel(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    @FXML
    public void onActionLogin(ActionEvent event) throws IOException {

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
            FileWriter fwVar = new FileWriter("login_activity.txt", true);
            PrintWriter pwVar = new PrintWriter(fwVar);
            ZonedDateTime utcZDT = TimeZConversion.localToUtc(TimeZConversion.getCurrentZDT());

            Users user = DBUsers.getUser(uname);

            if(user != null) isPassword = user.getPassword().equals(passwd); //if one user is returned
            if(isPassword){
                String uid = String.valueOf(user.getId());
                pwVar.println("SUCCESSFUL LOGIN FOR USER: " + uname + " ID: " + uid + " ON: " + utcZDT.toLocalDate() + " AT: " + utcZDT.toLocalTime() + " [UTC]");
                pwVar.close();
                fwVar.close();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/PrimaryForm.fxml"));
                loader.load();

                PrimaryFormController primaryFormController = loader.getController();

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

                primaryFormController.sendUsr(user); //pops up after primaryform comes up

            } else{ //if no user was found or password is incorrect
                pwVar.println("UNSUCCESSFUL LOGIN FOR USER: " + uname + " ON: " + utcZDT.toLocalDate() + " AT: " + utcZDT.toLocalTime() + " [UTC]");

                if(isFrench) Utilities.warningFR.alert(rb.getString("incorrect"));
                else Utilities.warning.alert("Incorrect username or password");
                pwVar.close();
                fwVar.close();
            }
        }
    }

}
