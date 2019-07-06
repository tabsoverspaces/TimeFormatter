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
        if(seconds < 10) // basically turn 3s to 03s
            return "0" + seconds + "s";

        return seconds + "s";
    }

    @Override
    public String formatMinutes(long minutes) {
        if(minutes < 10)
            return "0" + minutes + "m";

        return minutes + "m";
    }

    @Override
    public String formatHours(long hours) {
        if(hours < 10)
            return "0" + hours + "h";

        return hours + "h";
    }

    @Override
    public String formatDays(long days) {
        if(days < 10)
            return "0" + days + "d";

        return days + "d";
    }

    @Override
    public String formatMonths(long months) {
        if(months < 10)
            return "0" + months + "M";

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
