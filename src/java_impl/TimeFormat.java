package java_impl;

/**
 * Base class of all the time formats available to use when formatting time periods.
 *
 * These classes need to implement the Singleton design pattern so that only one instance of the format type can be
 * created and used.
 */
public abstract class TimeFormat {

    /**
     * @param millis the time period to be formatted
     * @param significantUnits how many time units need to be displayed, starting from the largest
     *
     * @return the formatted string representing the time period
     */
    public String format(Long millis, int significantUnits)
    {
        TimeUnit biggestUnit = TimeUnit.getBiggestTimeUnit(millis);

        return biggestUnit.format(millis, this, significantUnits);
    }

    /**
     * @param seconds number of seconds to be formatted
     *
     * @return formatted string representing seconds
     */
    public abstract String formatSeconds(long seconds);

    /**
     * @param minutes number of minutes to be formatted
     *
     * @return formatted string representing minutes
     */
    public abstract String formatMinutes(long minutes);

    /**
     * @param hours number of hours to be formatted
     *
     * @return formatted string representing hours
     */
    public abstract String formatHours(long hours);

    /**
     * @param days number of days to be formatted
     *
     * @return formatted string representing days
     */
    public abstract String formatDays(long days);

    /**
     * @param months number of months to be formatted
     *
     * @return formatted string representing months
     */
    public abstract String formatMonths(long months);

    /**
     * @param years number of years to be formatted
     *
     * @return formatted string representing years
     */
    public abstract String formatYears(long years);

    /**
     * @return string that is used in between formatted time unit strings
     *
     * Ex. 5 hours, 33 minutes and 23 seconds -> Here the joining character is the comma that is inserted between all
     * time units except between the last and the second to last, where a different string is inserted(in this case 'and')
     */
    public abstract String getJoiningCharacter();

    /**
     * For some format types(ex. verbal), the last time unit is separated from the second to last time unit by a different
     * joining character rather than the default one. (i.e. joining xx minutes AND xx seconds, instead of xx minutes, xx seconds).
     *
     * @return string to be added between the last and second to last time unit strings
     */
    public abstract String getLastJoiningCharacter();
}
