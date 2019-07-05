package testing.java_impl;

import java_impl.TimeFormatter;
import java_impl.concreteTimeFormats.SingleSuffixTimeFormat;
import java_impl.concreteTimeFormats.VerbalTimeFormat;

import java.util.Calendar;

public class Launcher {

    public static void main(String[] args)
    {
        TimeFormatter timeFormatter = TimeFormatter.getInstance();

        long time1 = 5_000_000;
        long time2 = 2_500;
        long time3 = 1_000_000_000;
        long time4 = 15_250;

        System.out.println("Time 1 : " + timeFormatter.format(time1, VerbalTimeFormat.getInstance()));
        System.out.println("Time 2 : " + timeFormatter.format(time2, VerbalTimeFormat.getInstance()));
        System.out.println("Time 3 : " + timeFormatter.format(time3, SingleSuffixTimeFormat.getInstance()));
        System.out.println("Time 4 : " + timeFormatter.format(time4, SingleSuffixTimeFormat.getInstance()));
    }
}
