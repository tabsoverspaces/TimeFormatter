package java_impl.concreteTimeFormats;

import java_impl.TimeFormat;

public class SingleSuffixTimeFormat extends TimeFormat {

    private static SingleSuffixTimeFormat instance;

    /**
     * @return object instance of the class
     */
    public static SingleSuffixTimeFormat getInstance()
    {
        if(instance == null)
        {
            instance = new SingleSuffixTimeFormat();
        }

        return instance;
    }

    /**
     * Primary method made private in order to enable the Singleton design pattern.
     */
    private SingleSuffixTimeFormat(){}

    @Override
    public String formatSeconds(long seconds) {
        return seconds + "s";
    }

    @Override
    public String formatMinutes(long minutes) {
        return minutes + "m";
    }

    @Override
    public String formatHours(long hours) {
        return hours + "h";
    }

    @Override
    public String formatDays(long days) {
        return days + "d";
    }

    @Override
    public String formatMonths(long months) {
        return months + "M";
    }

    @Override
    public String formatYears(long years) {
        return years + "y";
    }

    @Override
    public String getJoiningCharacter() {
        return ":";
    }

    @Override
    public String getLastJoiningCharacter() {
        return this.getJoiningCharacter(); // there's no special last joining character here
    }
}
