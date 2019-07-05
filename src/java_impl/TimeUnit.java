package java_impl;

import java.lang.String;
import java.time.Instant;

/**
 * This class represents all possible time units(seconds, minutes, hours...) as enums.
 * This allows for more flexible management of the time units when formatting time periods into strings.
 */
public enum TimeUnit{

    SECONDS
    {
        @Override
        public String getFormattingMethodResult(Long millis, TimeFormat timeFormat) {
            return timeFormat.formatSeconds(this.getConcreteUnitValue(millis));
        }

        @Override
        public Long getConcreteUnitValue(Long millis) {
            return (millis/1000);
        }

        @Override
        public Long getUpperLimit() {
            return 60 * 1000L;
        }

        @Override
        public TimeUnit nextUnit() {
            return MINUTES;
        }

        @Override
        public TimeUnit previousUnit() {
            return SECONDS; // cannot go any lower down in this hierarchy
        }


    },
    MINUTES{
        @Override
        public String getFormattingMethodResult(Long millis, TimeFormat timeFormat) {
            return timeFormat.formatMinutes(this.getConcreteUnitValue(millis));
        }

        @Override
        public Long getConcreteUnitValue(Long millis) {
            return SECONDS.getConcreteUnitValue(millis) / 60;
        }

        @Override
        public Long getUpperLimit() {
            return SECONDS.getUpperLimit() * 60;
        }

        @Override
        public TimeUnit nextUnit() {
            return HOURS;
        }

        @Override
        public TimeUnit previousUnit() {
            return SECONDS;
        }
    },
    HOURS{
        @Override
        public String getFormattingMethodResult(Long millis, TimeFormat timeFormat) {
            return timeFormat.formatHours(this.getConcreteUnitValue(millis));
        }

        @Override
        public Long getConcreteUnitValue(Long millis) {
            return MINUTES.getConcreteUnitValue(millis)/60;
        }

        @Override
        public Long getUpperLimit() {
            return MINUTES.getUpperLimit() * 24;
        }

        @Override
        public TimeUnit nextUnit() {
            return DAYS;
        }

        @Override
        public TimeUnit previousUnit() {
            return MINUTES;
        }
    },
    DAYS{
        @Override
        public String getFormattingMethodResult(Long millis, TimeFormat timeFormat) {
            return timeFormat.formatDays(this.getConcreteUnitValue(millis));
        }

        @Override
        public Long getConcreteUnitValue(Long millis) {
            return HOURS.getConcreteUnitValue(millis)/24;
        }

        @Override
        public Long getUpperLimit() {
            return HOURS.getUpperLimit() * 30;
        }

        @Override
        public TimeUnit nextUnit() {
            return MONTHS;
        }

        @Override
        public TimeUnit previousUnit() {
            return HOURS;
        }
    },
    MONTHS{
        @Override
        public String getFormattingMethodResult(Long millis, TimeFormat timeFormat) {
            return timeFormat.formatMonths(this.getConcreteUnitValue(millis));
        }

        @Override
        public Long getConcreteUnitValue(Long millis) {
            return DAYS.getConcreteUnitValue(millis)/24;
        }

        @Override
        public Long getUpperLimit() {
            return DAYS.getUpperLimit() * 12;
        }

        @Override
        public TimeUnit nextUnit() {
            return YEARS;
        }

        @Override
        public TimeUnit previousUnit() {
            return DAYS;
        }
    },
    YEARS{
        @Override
        public String getFormattingMethodResult(Long millis, TimeFormat timeFormat) {
            return timeFormat.formatYears(this.getConcreteUnitValue(millis));
        }

        @Override
        public Long getConcreteUnitValue(Long millis) {
            return MONTHS.getConcreteUnitValue(millis)/12;
        }

        @Override
        public Long getUpperLimit() {
            return Instant.MAX.getEpochSecond(); // this is the maximum time period supported by Java
        }

        @Override
        public TimeUnit nextUnit() {
            return YEARS; // cannot go any higher up in this hierarachy
        }

        @Override
        public TimeUnit previousUnit() {
            return MONTHS;
        }
    };

    /**
     * Formats the input milliseconds with the given time format type
     * while considering the provided number of significant units.
     *
     * The method starts with the largest time unit that is found in the provided milliseconds and then formats
     * all the other time units below it, step by step.
     *
     * @param millis time period in milliseconds which is to be formatted
     * @param timeFormat formatting type to be used
     * @param significantUnits number of time units to be included in the final formatted string
     *
     * @return formatted string of the time period
     */
    public String format(long millis, TimeFormat timeFormat, int significantUnits)
    {
        /*
        Safety check is done in order to limit the maximum of value of significant units
        according to the maximum number of distinct time units present in the provided milliseconds value.
         */
        significantUnits = Math.min(significantUnits, getTotalTimeUnitsPresent(millis));

        /*
        This string starts empty and on each time unit iteration and respective invocation of the format method,
        the value of the time units' format strings are appended to it.
         */
        String formattedString = this.getFormattingMethodResult(millis, timeFormat);

        /*
        Number of remaining significant units that will be passed on to the next time unit in line.
         */
        int nextSignificantUnits = significantUnits - 1;

        /*
        Concrete value of the current time unit that is being processed.
        At this point this is just a number and it will be passed to the formatter method of the time unit.
         */
        long currentUnitValue = this.getConcreteUnitValue(millis);

        /*
        These are basically remainder milliseconds that will be passed on for the next time unit in line.
         */
        long nextSmallerUnitMillis = (millis - (currentUnitValue * this.previousUnit().getUpperLimit()));

        if(significantUnits == 2)
            formattedString += timeFormat.getLastJoiningCharacter();
        else
            if(nextSignificantUnits>0)
                formattedString += timeFormat.getJoiningCharacter();

        if(nextSignificantUnits > 0 && !(this==SECONDS))
        {
            formattedString += this.previousUnit().format(nextSmallerUnitMillis, timeFormat, nextSignificantUnits);
        }

        return formattedString;
    }

    /**
     * @return the method which the current time unit primarily needs to use
     */
    public abstract String getFormattingMethodResult(Long millis, TimeFormat timeFormat);

    /**
     * @return the value of the unit(15 months, 2 years, 34 minutes etc...)
     */
    public abstract Long getConcreteUnitValue(Long millis);

    /**
     * @return maximum units that exist of this type
     */
    public abstract Long getUpperLimit();

    /**
     * @return next time unit in the hierarchy
     */
    public abstract TimeUnit nextUnit();

    /**
     * @return previous time unit in the hierarchy
     */
    public abstract TimeUnit previousUnit();

    /**
     * @param millis time period
     *
     * @return the biggest time unit found in the provided time period
     */
    public static TimeUnit getBiggestTimeUnit(long millis)
    {
        if(millis > MONTHS.getUpperLimit())
            return YEARS;

        if(millis>DAYS.getUpperLimit())
            return MONTHS;

        if(millis>HOURS.getUpperLimit())
            return DAYS;

        if(millis>MINUTES.getUpperLimit())
            return HOURS;

        if(millis>SECONDS.getUpperLimit())
            return MINUTES;

        return SECONDS;
    }

    /**
     * @param millis length of time period in milliseconds
     *
     * @return number of distinct time units found in the provided time period
     */
    public static int getTotalTimeUnitsPresent(long millis)
    {
        if(millis > MONTHS.getUpperLimit())
            return 6;

        if(millis>DAYS.getUpperLimit())
            return 5;

        if(millis>HOURS.getUpperLimit())
            return 4;

        if(millis>MINUTES.getUpperLimit())
            return 3;

        if(millis>SECONDS.getUpperLimit())
            return 2;

        return 1;
    }
}
