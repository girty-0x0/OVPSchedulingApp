package Main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale; //left for testing login screen in french

/** Main class. Launches application and handles any starting procedures. */
public class Main extends Application {

    /** Sets the primary stage and starting scene for the Application. */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /** Starts connection to database, launches Application, and closes connection when application is closed. */
    public static void main(String[] args) {
        JDBC.openConnection();

        launch(args);

        JDBC.closeConnection();
    }
}
