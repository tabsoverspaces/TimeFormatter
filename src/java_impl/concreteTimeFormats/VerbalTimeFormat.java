package java_impl.concreteTimeFormats;

import java_impl.TimeFormat;

/**
 * Example format output:
 *
 * 1) 2 minutes and 43 seconds
 * 2) 2 hours, 52 minutes and 37 seconds
 * 3) 5 months, 32 minutes and 2 seconds
 * 4) 1 year, 2 months, 12 days, 4 hours, 29 minutes and 55 seconds
 *
 */
public class VerbalTimeFormat extends TimeFormat {

    private static VerbalTimeFormat instance;

    /**
     * @return object instance of the class
     */
    public static VerbalTimeFormat getInstance()
    {
        if(instance == null)
        {
            instance = new VerbalTimeFormat();
        }

        return instance;
    }

    /**
     * Primary method made private in order to enable the Singleton design pattern.
     */
    private VerbalTimeFormat(){}

    @Override
    public String formatSeconds(long seconds) {
        // making it pretty
        if(seconds==1)
            return "1 second";

        return seconds + " seconds";
    }

    @Override
    public String formatMinutes(long minutes) {
        // making it pretty
        if(minutes==1)
            return "1 minute";

        return minutes + " minutes";
    }

    @Override
    public String formatHours(long hours) {
        // making it pretty
        if(hours==1)
            return "1 hour";

        return hours + " hours";
    }

    @Override
    public String formatDays(long days) {
        // making it pretty
        if(days==1)
            return "1 day";

        return days + " days";
    }

    @Override
    public String formatMonths(long months) {
        // making it pretty
        if(months==1)
            return "1 month";

        return months + " months";
    }

    @Override
    public String formatYears(long years) {
        // making it pretty
        if(years==1)
            return "1 year";

        return years + " years";
    }

    @Override
    public String getJoiningCharacter() {
        return ", ";
    }

    @Override
    public String getLastJoiningCharacter()
    {
        return " and ";
    }
}
