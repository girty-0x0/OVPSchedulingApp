package helper;

import java.time.LocalDateTime;

public interface BusinessHours {
    boolean workingHoursCheck(LocalDateTime startDT, LocalDateTime endDT);
}
