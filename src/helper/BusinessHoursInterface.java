package helper;

import java.time.ZonedDateTime;

/** Functional interface to check if an appointment is within business hours. */
public interface BusinessHoursInterface {

    /** Method for interfacing with related lambda inside Utilities which allowed for easier modifications when developing an efficient way to compare the given starting and ending times.
     * The structure for this lambda allowed for quick changes in parameter and return type changes. Also, it allowed for swifter implementation in related AppointmentsFormController class.
     * @param startZDT a potential appointment's starting ZonedDateTime
     * @param endZDT a potential appointment's ending ZonedDateTime
     * @return true if it is within working hours */
    boolean workingHoursCheck(ZonedDateTime startZDT, ZonedDateTime endZDT);
}
