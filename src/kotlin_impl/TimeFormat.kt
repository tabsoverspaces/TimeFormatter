package kotlin_impl

/**
 * Base class of all the time formats available to use when formatting time periods.
 *
 * These classes need to implement the Singleton design pattern so that only one instance of the format type can be
 * created and used. Thus, all concrete format types are actually 'object' file types, instead of 'class'.
 */
abstract class TimeFormat {

    /**
     * @param millis time period in milliseconds
     * @param significantUnits number of TimeUnits to be shown in the final string
     *
     * @return the formatted String object
     */
    fun format(millis:Long, significantUnits : Int):String
    {
        val biggestUnit = TimeUnit.getBiggestUnit(millis)

        return biggestUnit.format(millis,this, significantUnits)
    }

    /**
     * @return formatted string containing the number of seconds and the respective suffix
     */
    abstract fun formatSeconds(seconds: Long):String

    /**
     * @return formatted string containing the number of minutes and the respective suffix
     */
    abstract fun formatMinutes(minutes:Long):String

    /**
     * @return formatted string containing the number of hours and the respective suffix
     */
    abstract fun formatHours(hours:Long):String

    /**
     * @return formatted string containing the number of days and the respective suffix
     */
    abstract fun formatDays(days:Long):String

    /**
     * @return formatted string containing the number of months and the respective suffix
     */
    abstract fun formatMonths(months:Long):String

    /**
     * @return formatted string containing the number of years and the respective suffix
     */
    abstract fun formatYears(years:Long):String

    /**
     * @return string that is inserted between time units
     */
    abstract fun getJoiningString():String

    /**
     * @return string that is inserted between the last and second to last time unit
     */
    abstract fun getLastJoiningString():String
}