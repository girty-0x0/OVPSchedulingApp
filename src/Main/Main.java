package Main;

import DBAccessors.DBUsers;
import helper.JDBC;
import helper.TimeZConversion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        JDBC.openConnection();
        //Locale.setDefault(new Locale("fr"));
        launch(args);
        JDBC.closeConnection(); //closed after app is closed out
    }
}
