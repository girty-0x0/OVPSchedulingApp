package helper;

import java.time.ZonedDateTime;

public interface BusinessHoursInterface {
    boolean workingHoursCheck(ZonedDateTime startZDT, ZonedDateTime endZDT);
}
