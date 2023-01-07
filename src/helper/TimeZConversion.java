package helper;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/** Class with various timezone conversions and other related public variables for ease of access relating to timezones.. */
public abstract class TimeZConversion {
    /** Class variable containing the ZoneId object for the UTC timezone. */
    private static final ZoneId utcZoneId = ZoneId.of("UTC");
    /** Class variable containing the ZoneId object for the America/New_York timezone. */
    private static final ZoneId estZoneId = ZoneId.of("America/New_York");

    /** Accessor to retrieve the current ZonedDateTime for a given instant. */
    public static ZonedDateTime getCurrentZDT(){
        return ZonedDateTime.now();
    }

    /** Accessor to retrieve the ZoneId for a user's system. */
    public static ZoneId getLocalZone(){return ZoneId.systemDefault();}

    /** Accessor to publicly retrieve the ZoneId for the America/New_York timezone. */
    public static ZoneId getEstZone(){return estZoneId;}

    /** Converts from UTC timezone to a user's system timezone.
     * @param utcZDT the ZonedDateTime object in UTC timezone
     * @return the translated ZonedDateTime object in a user's system timezone */
    public static ZonedDateTime utcToLocal(ZonedDateTime utcZDT){
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(utcZDT.toInstant(), TimeZConversion.getLocalZone());
        return localZDT;
    }

    /** Converts from a user's system timezone to the UTC timezone.
     * @param localZDT the ZonedDateTime object in a user's system timezone
     * @return the translated ZonedDateTime object in UTC timezone */
    public static ZonedDateTime localToUtc(ZonedDateTime localZDT){
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(localZDT.toInstant(), utcZoneId);
        return utcZDT;
    }

    /** Converts from America/New_York time zone to a user's system timezone.
     * @param estZDT the ZonedDateTime object in America/New_York timezone
     * @return the translated ZonedDateTime object in a user's system timezone */
    public static ZonedDateTime estToLocal(ZonedDateTime estZDT){
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(estZDT.toInstant(), TimeZConversion.getLocalZone());
        return localZDT;
    }

    /** Converts from a user's system timezone to the America/New_York timezone.
     * @param localZDT the ZonedDateTime object in a user's system timezone
     * @return the translated ZonedDateTime object in America/New_York timezone */
    public static ZonedDateTime localToEst(ZonedDateTime localZDT){
        ZonedDateTime estZDT = ZonedDateTime.ofInstant(localZDT.toInstant(), estZoneId);
        return estZDT;
    }

}
