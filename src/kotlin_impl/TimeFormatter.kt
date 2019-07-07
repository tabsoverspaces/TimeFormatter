package kotlin_impl

/**
 * Class provides methods for formatting time periods
 *
 * Several formats available:
 *
 * 1) Verbal
 * Ex. 102 days, 22 hours, 33 minutes and 21 seconds
 *
 * 2) Single suffix
 * Ex. 22h:33m:21s
 *
 ************************
 * More to be implemented
 */
object TimeFormatter {

    /**
     * By default, this method will format the given time duration and will include all possible time units
     *
     * @param millis time duration given in milliseconds
     * @param format the format type according to which the time duration is to be formatted
     *
     * @return formatted string of the given duration in milliseconds
     */
    fun format(millis:Long, format : TimeFormat) : String
    {
        return format.format(millis, 99)
    }

    /**
     * @param millis time duration given in milliseconds
     * @param format the format type according to which the time duration is to be formatted
     * @param significantUnits the number of units to be included in the string
     *
     * @return formatted string of the given duration in milliseconds
     */
    fun format(millis:Long, format: TimeFormat, significantUnits:Int):String{

        return format.format(millis, significantUnits)
    }
}