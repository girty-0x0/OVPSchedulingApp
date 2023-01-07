package helper;

/** Functional interface for alerts. All alerts except confirmation alerts will use this interface. */
public interface AlertsInterface {

    /** Method for interfacing with related lambda inside Utilities which allowed for a single method to call after the type of alert I wanted to call upon.
     * This kept the code more compact and presented a standardized way to alert a user.
     * @param contentText the message that will be displayed to a user */
    void alert(String contentText);
}
