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

/** Class with various miscellaneous utilities used throughout the program (Lambdas would be found here). */
public abstract class Utilities {

    /** Stage instance variable to hold the primary stage when transferring data across forms. */
    private static Stage stage; //sets the primary stage

    /** Scene instance variable to set the current scene when loading a new view. */
    private static Parent scene; //sets the current scene

    /** Instance variable which holds the opening time for the given business. LocalTime variable used for time comparisons with various timezones. */
    private static final LocalTime businessOpen = LocalTime.of(8,0);

    /** Instance variable which holds the closing time for the given business. LocalTime variable used for time comparisons with various timezones. */
    private static final LocalTime businessClose = LocalTime.of(22,0);

    /** Lambda implementation for the BusinessHoursInterface which allowed for easier modifications when developing an efficient way to compare the given starting and ending times.
     * The structure for this lambda allowed for quick changes in parameter and return type changes. Also, it allowed for swifter implementation in related AppointmentsFormController class. */
    private static final BusinessHoursInterface workingHoursLmbd = (startZDT, endZDT) -> {
        ZonedDateTime estStart = TimeZConversion.localToEst(startZDT); //converts normal times to est for return comparison
        ZonedDateTime estEnd = TimeZConversion.localToEst(endZDT);
        LocalTime startTime = estStart.toLocalTime();
        LocalTime endTime = estEnd.toLocalTime();

        return (startTime.compareTo(businessOpen) >= 0 && endTime.compareTo(businessClose) <= 0); //compareTo gives 0 if equal and pos number if greater time than parameter
    };

    /** Lambda implementation for the AlertsInterface allowing for a standardized and compact way to alert a User with warnings.
     * The structure for this lambda allowed me to specify when an alert has to be a warning and dynamically change the contents of the warning. */
    public static AlertsInterface warning = contentText ->{

        Alert errAlert = new Alert(Alert.AlertType.WARNING);
        errAlert.setTitle("Warning Box");
        errAlert.setContentText(contentText);
        errAlert.showAndWait();

    };

    /** Lambda implementation for the AlertsInterface allowing for a standardized and compact way to alert a User with warnings in French.
     * The structure for this lambda allowed me to specify when an alert has to be a warning with a french title and dynamically change the contents of the warning with a resource bundle. */
    public static AlertsInterface warningFR = contentText ->{

        Alert errAlert = new Alert(Alert.AlertType.WARNING);
        errAlert.setTitle("Boîte d'avertissement");
        errAlert.setContentText(contentText);
        errAlert.showAndWait();

    };

    /** Lambda implementation for the AlertsInterface allowing for a standardized and compact way to alert a User with errors.
     * The structure for this lambda allowed me to specify when an alert has to be an error and dynamically change the contents of the error. */
    public static AlertsInterface error = contentText ->{

        Alert errAlert = new Alert(Alert.AlertType.ERROR);
        errAlert.setTitle("Error Box");
        errAlert.setContentText(contentText);
        errAlert.showAndWait();

    };

    /** Lambda implementation for the AlertsInterface allowing for a standardized and compact way to alert a User with informative messages.
     * The structure for this lambda allowed me to specify when an alert has to be a notification and dynamically change the contents of that notification. */
    public static AlertsInterface inform = contentText ->{

        Alert errAlert = new Alert(Alert.AlertType.INFORMATION);
        errAlert.setTitle("Notification Box");
        errAlert.setContentText(contentText);
        errAlert.showAndWait();
    };

    /** Method only for alerts that require confirmation. Only used when a user needs to confirm an important action.
     * @param confirmationMsg message informing a user what they need to confirm
     * @return true if a user confirmed action by clicking "OK" */
    public static boolean confirmPopUp(String confirmationMsg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, confirmationMsg);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /** Method to wrap lambda workingHoursLmbd for a more compact implementation when called upon. This keeps the code clean and allows for changes to lambda to be done separately without affecting its implementation.
     * @param startZDT a potential appointment's starting ZonedDateTime
     * @param endZDT a potential appointment's ending ZonedDateTime
     * @return true if it is within working hours */
    public static boolean isBetweenWorkingHours(ZonedDateTime startZDT, ZonedDateTime endZDT){
        return workingHoursLmbd.workingHoursCheck(startZDT, endZDT);
    }

    /** Gets working hours in a user's timezone using their chosen date.
     * @param comparingDay the chosen day for to translate the working hours for a business between a user's current time zone and the EST timezone
     * @return an array with two ZonedDateTime objects. Index 0 is for the starting time and index 1 is for the closing time */
    public static ZonedDateTime[] getWorkingLZDT(LocalDate comparingDay){

        LocalDateTime openDT = LocalDateTime.of(comparingDay, businessOpen);
        LocalDateTime closeDT = LocalDateTime.of(comparingDay, businessClose);
        ZonedDateTime estOpenTime = ZonedDateTime.of(openDT, TimeZConversion.getEstZone());
        ZonedDateTime estCloseTime = ZonedDateTime.of(closeDT, TimeZConversion.getEstZone());
        ZonedDateTime usrOpen = TimeZConversion.estToLocal(estOpenTime);
        ZonedDateTime usrClose = TimeZConversion.estToLocal(estCloseTime);

        return new ZonedDateTime[]{usrOpen, usrClose};
    }

    /** Loads an FXML form view.
     * @param event the control form's ActionEvent object from a user's action before a new form is loaded */
    public static void loadView(String form, ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Utilities.class.getResource("../view/" + form));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Checks to see if a certain day is within a certain timeframe.
     * @param apptDay the LocalDate object representing the day that is checked within a certain time frame
     * @param frame the time frame. 1 for same month, 2 for same week
     * @return true if the given date is within the chosen timeframe */
    public static boolean compareDates(int frame, LocalDate apptDay){
        LocalDate today = LocalDate.now();
        LocalDate upperBound = switch (frame) {
            case 1 -> LocalDate.now().plusMonths(1);
            case 2 -> LocalDate.now().plusWeeks(1);
            default -> null;
        };

        return (apptDay.isBefore(upperBound) || apptDay.isEqual(upperBound)) && (apptDay.isAfter(today) || apptDay.isEqual(today)); //makes sure only dates within 1 month or one week are returned
    }

    /** Checks to make sure if a potential appointment conflicts with any other appointment in the database.
     * @param endZDT the potential appointment's ending ZonedDateTime
     * @param startZDT the potential appointment's ending ZonedDateTime
     * @param apptID the Appointments object id
     * @param customerID the potential appointment's related customerId
     * @return an Appointments object if it conflicts with another appointment; null if it is not conflicting */
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

    /** Method to return all existing appointment types in the database.
     * @return unique list of all types in the related database */
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
