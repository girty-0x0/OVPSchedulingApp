package helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Utilities {
    static Stage stage; //sets the primary stage
    static Parent scene; //sets the current scene

    private static final LocalTime businessOpen = LocalTime.of(8,0);
    private static final LocalTime businessClose = LocalTime.of(22,0);

    private static final BusinessHoursInterface workingHoursLmbd = (startDT, endDT) -> {
        LocalTime startTime = startDT.toLocalTime();
        LocalTime endTime = endDT.toLocalTime();

        return (startTime.compareTo(businessOpen) >= 0 && endTime.compareTo(businessClose) < 0); //compareTo gives 0 if equal and pos number if greater time than parameter
    };

    public static AlertsInterface warning = contentText ->{

        Alert errAlert = new Alert(Alert.AlertType.WARNING);
        errAlert.setTitle("Warning Box");
        errAlert.setContentText(contentText);
        errAlert.showAndWait();

    };

    public static AlertsInterface warningFR = contentText ->{

        Alert errAlert = new Alert(Alert.AlertType.WARNING);
        errAlert.setTitle("Boîte d'avertissement");
        errAlert.setContentText(contentText);
        errAlert.showAndWait();

    };

    public static AlertsInterface error = contentText ->{

        Alert errAlert = new Alert(Alert.AlertType.ERROR);
        errAlert.setTitle("Error Box");
        errAlert.setContentText(contentText);
        errAlert.showAndWait();

    };

    public static boolean isBetweenWorkingHours(LocalDateTime startDT, LocalDateTime endDT){
        return workingHoursLmbd.workingHoursCheck(startDT, endDT);
    }

    public static void loadView(String form, ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Utilities.class.getResource("../view/"+form));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public static boolean compareDates(int frame, LocalDate apptDay){
        LocalDate today = LocalDate.now();
        LocalDate upperBound = null;

        switch (frame){
            case 1:
                upperBound = LocalDate.now().plusMonths(1);
                break;

            case 2:
                upperBound = LocalDate.now().plusWeeks(1);
                break;
        }

        if((apptDay.isBefore(upperBound) || apptDay.isEqual(upperBound)) && (apptDay.isAfter(today) || apptDay.isEqual(today))) return true; //makes sure only dates within 1 month or one week are returned
        return false;
    }

}
