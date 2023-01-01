package helper;

import java.time.LocalDateTime;

public interface BusinessHoursInterface {
    boolean workingHoursCheck(LocalDateTime startDT, LocalDateTime endDT);
}
