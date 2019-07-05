package java_impl;

/**
 * Class provides methods to format a given time period of milliseconds
 * into a better-looking and more user-friendly string.
 */
public class TimeFormatter {

    /**
     * Static object reference to the main instance that is returned by using the getInstance method.
     * Made private to enable the Singleton design pattern.
     */
    private static TimeFormatter instance;

    /**
     * @return instance of the TimeFormatter class
     */
    public static TimeFormatter getInstance()
    {
        if(instance == null)
            instance = new TimeFormatter();

        return instance;
    }

    /**
     * Primary constructor made private to enable the Singleton design pattern.
     */
    private TimeFormatter(){}

    /**
     * By default, this method will format the provided time period by including all the time units.
     *
     * @param millis time period to be formatted
     * @param timeFormat formatting type to be used while formatting the string
     *
     * @return formatted string of the provided time period
     */
    public String format(long millis, TimeFormat timeFormat)
    {
        return timeFormat.format(millis, 99);
    }

    /**
     * Method can limit the number of time units that appear in the final string, by counting down from the largest time unit.
     *
     * @param millis time period to be formatted
     * @param timeFormat formatting type to be used while formatting the string
     * @param significantUnits number of time units to be shown in the formatted string
     *
     * @return formatted string of the provided time period
     */
    public String format(long millis, TimeFormat timeFormat, int significantUnits)
    {
        return timeFormat.format(millis, significantUnits);
    }
}
