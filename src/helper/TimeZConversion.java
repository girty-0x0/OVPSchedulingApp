package helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public abstract class TimeZConversion {
    private static final ZoneId utcZoneId = ZoneId.of("UTC");
    private static final ZoneId estZoneId = ZoneId.of("America/New_York");

    public static ZonedDateTime getCurrentZDT(){
        return ZonedDateTime.now();
    }
    public static LocalDateTime getCurrentLDT(){
        return LocalDateTime.now();
    }

    public static ZoneId getLocalZone(){return ZoneId.systemDefault();}
    public static ZoneId getEstZone(){return estZoneId;}

    public static ZonedDateTime utcToLocal(ZonedDateTime utcZDT){
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(utcZDT.toInstant(), TimeZConversion.getLocalZone());
        return localZDT;
    }
    public static ZonedDateTime localToUtc(ZonedDateTime localZDT){
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(localZDT.toInstant(), utcZoneId);
        return utcZDT;
    }
    public static ZonedDateTime estToLocal(ZonedDateTime estZDT){
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(estZDT.toInstant(), TimeZConversion.getLocalZone());
        return localZDT;
    }
    public static ZonedDateTime localToEst(ZonedDateTime localZDT){
        ZonedDateTime estZDT = ZonedDateTime.ofInstant(localZDT.toInstant(), estZoneId);
        return estZDT;
    }

}
