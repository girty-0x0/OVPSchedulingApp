package helper;

import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class BusinessHoursCheck {
    private static final LocalTime businessOpen = LocalTime.of(8,0);
    private static final LocalTime businessClose = LocalTime.of(22,0);

    private static final BusinessHours workingHoursLmbd = (startDT, endDT) -> {
        LocalTime startTime = startDT.toLocalTime();
        LocalTime endTime = endDT.toLocalTime();

        return (startTime.compareTo(businessOpen) >= 0 && endTime.compareTo(businessClose) < 0); //compareTo gives 0 if equal and pos number if greater time than parameter
    };

    public static boolean isBetweenWorkingHours(LocalDateTime startDT, LocalDateTime endDT){
        return workingHoursLmbd.workingHoursCheck(startDT, endDT);
    }

}
