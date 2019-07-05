package testing.java_impl;

import java_impl.TimeFormatter;
import java_impl.concreteTimeFormats.SingleSuffixTimeFormat;
import java_impl.concreteTimeFormats.VerbalTimeFormat;

import java.util.Calendar;

public class Launcher {

    public static void main(String[] args)
    {
        long timePeriod = 200_000_000;
        TimeFormatter timeFormatter = TimeFormatter.getInstance();
        VerbalTimeFormat verbalTimeFormat = VerbalTimeFormat.getInstance();
        SingleSuffixTimeFormat singleSuffixTimeFormat = SingleSuffixTimeFormat.getInstance();

        /*
         * Formatting without explicitly providing a number of significant units.
         * By default, this will show all time units present in the provided time period.
         */

        // verbal format
        String formattedTimePeriodVerbal1 = timeFormatter.format(timePeriod, verbalTimeFormat);

        // single suffix format
        String formattedTimePeriodSingleSuffix1 = timeFormatter.format(timePeriod, singleSuffixTimeFormat);

        /*
         * Formatting with significant units argument.
         */

        // verbal format with 2 significant units
        String formattedTimePeriodVerbal2 = timeFormatter.format(timePeriod, verbalTimeFormat, 2);

        // single suffix format with 2 significant units
        String formattedTimePeriodSingleSuffix2 = timeFormatter.format(timePeriod, singleSuffixTimeFormat, 2);

        System.out.println("Verbal format - all time units : " + formattedTimePeriodVerbal1);
        System.out.println("Single suffix - all time units : " + formattedTimePeriodSingleSuffix1);
        System.out.println("Verbal format - two time units : " + formattedTimePeriodVerbal2);
        System.out.println("Single suffix - two time units : " + formattedTimePeriodSingleSuffix2);
    }
}
