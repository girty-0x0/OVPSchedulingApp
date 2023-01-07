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

/** Controller class for LoginForm.fxml view. Controls all functions that occur inside the Login form after application is loaded. */
public class LoginFormController implements Initializable {

    /** Boolean instance variable to represent if a user's language settings are set to french. */
    private static boolean isFrench;
    /** Stage instance variable to hold the primary stage when transferring data across forms. */
    private static Stage stage; //sets the primary stage
    /** Resource bundle to handle translations to french. */
    private static ResourceBundle rb = ResourceBundle.getBundle("helper/loginForm", Locale.FRENCH);

    /** Cancel FXML button. Exits application when pressed. */
    @FXML
    private Button cancelBtn;

    /** Login FXML button. Starts authentication and authorization process to determine if a user's credential are valid. */
    @FXML
    private Button loginBtn;

    /** Editable field for a user's password. FXML PasswordField. */
    @FXML
    private PasswordField passwordField;

    /** Label to show where a user must input their password. FXML Label that changes dynamically dependent the user's system language. */
    @FXML
    private Label passwordLbl;

    /** Label to show a user's time zone. FXML Label that changes dynamically dependent the user's time zone. */
    @FXML
    private Label timezoneLbl;

    /** Label with the login screen's title. FXML Label that changes dynamically dependent the user's system language. */
    @FXML
    private Label titleLbl;

    /** Editable field for a user's username. FXML TextField. */
    @FXML
    private TextField usernameField;

    /** Label to show where a user must input their username. FXML Label that changes dynamically dependent the user's system language. */
    @FXML
    private Label usernameLbl;

    /** Initializes the login form and changes any labels and text depending on a user's system language and time zone. */
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

    /** Exits application and closes the database connection. */
    @FXML
    public void onActionCancel(ActionEvent event) {
        JDBC.closeConnection();
        System.exit(0);
    }

    /** Authenticates user credentials by making queries to a database. User is notified if their credentials are invalid or are missing a username/password. Writes successful and unsuccessful logins to login_activity.txt inside the root folder. */
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
