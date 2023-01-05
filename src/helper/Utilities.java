package helper;

import DBAccessors.DBAppointments;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Optional;

public abstract class Utilities {
    private static Stage stage; //sets the primary stage
    private static Parent scene; //sets the current scene

    private static final LocalTime businessOpen = LocalTime.of(8,0);
    private static final LocalTime businessClose = LocalTime.of(22,0);


    private static final BusinessHoursInterface workingHoursLmbd = (startZDT, endZDT) -> {
        ZonedDateTime estStart = TimeZConversion.localToEst(startZDT); //converts normal times to est for return comparison
        ZonedDateTime estEnd = TimeZConversion.localToEst(endZDT);
        LocalTime startTime = estStart.toLocalTime();
        LocalTime endTime = estEnd.toLocalTime();

        return (startTime.compareTo(businessOpen) >= 0 && endTime.compareTo(businessClose) <= 0); //compareTo gives 0 if equal and pos number if greater time than parameter
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

    public static AlertsInterface inform = contentText ->{

        Alert errAlert = new Alert(Alert.AlertType.INFORMATION);
        errAlert.setTitle("Notification Box");
        errAlert.setContentText(contentText);
        errAlert.showAndWait();
    };

    public static boolean confirmPopUp(String confirmationMsg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, confirmationMsg);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }


    public static boolean isBetweenWorkingHours(ZonedDateTime startZDT, ZonedDateTime endZDT){
        return workingHoursLmbd.workingHoursCheck(startZDT, endZDT);
    }

    public static ZonedDateTime[] getWorkingLZDT(LocalDate comparingDay){

        LocalDateTime openDT = LocalDateTime.of(comparingDay, businessOpen);
        LocalDateTime closeDT = LocalDateTime.of(comparingDay, businessClose);
        ZonedDateTime estOpenTime = ZonedDateTime.of(openDT, TimeZConversion.getEstZone());
        ZonedDateTime estCloseTime = ZonedDateTime.of(closeDT, TimeZConversion.getEstZone());
        ZonedDateTime usrOpen = TimeZConversion.estToLocal(estOpenTime);
        ZonedDateTime usrClose = TimeZConversion.estToLocal(estCloseTime);

        return new ZonedDateTime[]{usrOpen, usrClose};
    }

    public static void loadView(String form, ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Utilities.class.getResource("../view/"+form));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public static boolean compareDates(int frame, LocalDate apptDay){
        LocalDate today = LocalDate.now();
        LocalDate upperBound = switch (frame) {
            case 1 -> LocalDate.now().plusMonths(1);
            case 2 -> LocalDate.now().plusWeeks(1);
            default -> null;
        };

        return (apptDay.isBefore(upperBound) || apptDay.isEqual(upperBound)) && (apptDay.isAfter(today) || apptDay.isEqual(today)); //makes sure only dates within 1 month or one week are returned
    }

    public static Appointments isConflicting(ZonedDateTime startZDT, ZonedDateTime endZDT, int customerID, int apptID){
        ZonedDateTime tmpStart, tmpEnd;
        for(Appointments appt : DBAppointments.getAllAppointments()){
            if(appt.getCustomerId() == customerID && appt.getId() != apptID){
                tmpStart = ZonedDateTime.of(LocalDateTime.of(appt.getDay(), appt.getStart()), TimeZConversion.getLocalZone());
                tmpEnd = ZonedDateTime.of(LocalDateTime.of(appt.getDay(), appt.getEnd()), TimeZConversion.getLocalZone());

                if(!((startZDT.isAfter(tmpStart) && endZDT.isAfter(tmpEnd) && startZDT.isAfter(tmpEnd) && endZDT.isAfter(tmpStart)) || (startZDT.isBefore(tmpStart) && endZDT.isBefore(tmpEnd) && startZDT.isBefore(tmpEnd) && endZDT.isBefore(tmpStart)))) return appt; //appt is returned when at least one appointment conflicts
            }
        }
        return null; //null is returned when no conflicting appointments are made
    }

    public static ObservableList<String> getTypes(){
        ObservableList<String> types = FXCollections.observableArrayList();

        for(Appointments appt : DBAppointments.getAllAppointments()){
            if(!(types.contains(appt.getType()))){
                types.add(appt.getType());
            }
        }
        return types;
    }

}
