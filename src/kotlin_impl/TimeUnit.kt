package kotlin_impl

/**
 *  This class represents all possible time units(seconds, minutes, hours...) as enums.
 *  This allows for more flexible management of the time units when formatting time periods into strings.
 */
enum class TimeUnit{

    SECONDS{
        override fun getFormattingMethodResult(millis: Long, format: TimeFormat): String {
            return format.formatSeconds(this.getConcreteUnitValue(millis))
        }

        override fun getConcreteUnitValue(millis: Long): Long {
            return millis/1000L
        }

        override fun getUpperLimit(): Long {
            return 60*1000
        }
        override fun previousUnit(): TimeUnit {
            return SECONDS
        }

        override fun nextUnit(): TimeUnit {
            return MINUTES
        }
    },
    MINUTES{
        override fun getFormattingMethodResult(millis: Long, format: TimeFormat): String {
            return format.formatMinutes(this.getConcreteUnitValue(millis))
        }

        override fun getConcreteUnitValue(millis: Long): Long {
            return SECONDS.getConcreteUnitValue(millis) / 60
        }

        override fun getUpperLimit(): Long {
            return SECONDS.getUpperLimit() * 60
        }

        override fun previousUnit(): TimeUnit {
            return SECONDS
        }

        override fun nextUnit(): TimeUnit {
            return HOURS
        }
    },
    HOURS{
        override fun getFormattingMethodResult(millis: Long, format: TimeFormat): String {
            return format.formatHours(this.getConcreteUnitValue(millis))
        }

        override fun getConcreteUnitValue(millis: Long): Long {
            return MINUTES.getConcreteUnitValue(millis) / 60
        }

        override fun getUpperLimit(): Long {
            return MINUTES.getUpperLimit() * 24
        }
        override fun previousUnit(): TimeUnit {
            return MINUTES
        }

        override fun nextUnit(): TimeUnit {
            return DAYS
        }
    },
    DAYS{
        override fun getFormattingMethodResult(millis: Long, format: TimeFormat): String {
            return format.formatDays(this.getConcreteUnitValue(millis))
        }

        override fun getConcreteUnitValue(millis: Long): Long {
            return HOURS.getConcreteUnitValue(millis) / 24
        }

        override fun getUpperLimit(): Long {
            return HOURS.getUpperLimit() * 30
        }
        override fun previousUnit(): TimeUnit {
            return HOURS
        }

        override fun nextUnit(): TimeUnit {
            return MONTHS
        }
    },
    MONTHS{
        override fun getFormattingMethodResult(millis: Long, format: TimeFormat): String {
            return format.formatMonths(this.getConcreteUnitValue(millis))
        }

        override fun getConcreteUnitValue(millis: Long): Long {
            return DAYS.getConcreteUnitValue(millis) / 30
        }

        override fun getUpperLimit(): Long {
            return DAYS.getUpperLimit() * 12
        }

        override fun previousUnit(): TimeUnit {
            return DAYS
        }

        override fun nextUnit(): TimeUnit {
            return YEARS
        }
    },
    YEARS{
        override fun getFormattingMethodResult(millis:Long, format: TimeFormat) : String {
            return format.formatYears(this.getConcreteUnitValue(millis))
        }

        override fun getConcreteUnitValue(millis:Long): Long {
            return MONTHS.getConcreteUnitValue(millis) / 12
        }

        override fun getUpperLimit(): Long {
            return MONTHS.getUpperLimit() * 100 // 100 years limit, for a century, duh
        }

        override fun previousUnit(): TimeUnit {
            return MONTHS
        }

        override fun nextUnit(): TimeUnit {
            return this
        }
    };

    /**
     * @param millis time period to be formatted
     * @param timeFormat TimeFormat object that will be used to timeFormat the input milliseconds
     * @param significantUnits number of TimeUnits to be shown in the final string
     *
     * @return formatted time string
     */
    fun format(millis:Long, timeFormat: TimeFormat, significantUnits : Int) : String
    {
        /*
        Safety check is done in order to limit the maximum of value of significant units
        according to the maximum number of distinct time units present in the provided milliseconds value.
         */
        val actualSignificantUnits = Math.min(significantUnits, getTotalTimeUnitsPresent(millis))

        /*
        This string starts empty and on each time unit iteration and respective invocation of the timeFormat method,
        the value of the time units' timeFormat strings are appended to it.
         */
        var formattedString = this.getFormattingMethodResult(millis, timeFormat)

        /*
        Number of remaining significant units that the next time unit's method will be invoked with
         */
        val nextSignificantUnits = actualSignificantUnits - 1

        /*
        Concrete value of the current time unit that is being processed.
        At this point this is just a number and it will be passed to the formatter method of the time unit.
         */
        val currentUnitValue = this.getConcreteUnitValue(millis)

        /*
        These are basically remainder milliseconds that will be passed on to the next time unit's method invocation.
         */
        val nextSmallerUnitMillis = (millis - (currentUnitValue * this.previousUnit().getUpperLimit()))

        if(actualSignificantUnits == 2)
            formattedString += timeFormat.getLastJoiningString()
        else
            if(nextSignificantUnits > 0)
                formattedString += timeFormat.getJoiningString()

        if(nextSignificantUnits>0 && this != SECONDS) {

            formattedString += this.previousUnit().format(nextSmallerUnitMillis, timeFormat, nextSignificantUnits)
        }

        return formattedString
    }

    /**
     * @return the method which the current time unit primarily needs to use
     */
    abstract fun getFormattingMethodResult(millis:Long, format : TimeFormat):String

    /**
     * @return the value of the unit(15 months, 2 years, 34 minutes etc...)
     */
    abstract fun getConcreteUnitValue(millis:Long) : Long

    /**
     * @return maximum units that can exist of this type
     */
    abstract fun getUpperLimit():Long

    /**
     * @return next time unit in the hierarchy
     */
    abstract fun nextUnit(): TimeUnit

    /**
     * @return previous time unit in the hierarchy
     */
    abstract fun previousUnit(): TimeUnit

    companion object {
        /**
         * @param millis time period in milliseconds
         *
         * @return biggest TimeUnit found in the provided millis argument
         */
        fun getBiggestUnit(millis:Long): TimeUnit {
            if(millis> MONTHS.getUpperLimit())
                return YEARS

            if(millis> DAYS.getUpperLimit())
                return MONTHS

            if(millis> HOURS.getUpperLimit())
                return DAYS

            if(millis> MINUTES.getUpperLimit())
                return HOURS

            if(millis> SECONDS.getUpperLimit())
                return MINUTES

            return SECONDS
        }

        /**
         * @param millis time period in milliseconds
         *
         * @return number of distinct time units found in the provided time period
         */
        fun getTotalTimeUnitsPresent(millis : Long) : Int
        {
            if(millis > MONTHS.getUpperLimit())
                return 6

            if(millis>DAYS.getUpperLimit())
                return 5

            if(millis>HOURS.getUpperLimit())
                return 4

            if(millis>MINUTES.getUpperLimit())
                return 3

            if(millis>SECONDS.getUpperLimit())
                return 2

            return 1
        }
    }
}