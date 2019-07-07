package kotlin_impl.concrete_format_types

import kotlin_impl.TimeFormat

/**
 * Possible output formats :
 *
 * xxxy:xxM:xxd:xxh:xxm:xxs - years:months:days:hours:minutes:seconds
 * xxM:xxd:xxh:xxm:xxs - months:days:hours:minutes:seconds
 * xxd:xxh:xxm:xxs - days:hours:minutes:seconds
 * xxh:xxm:xxs - hours:minutes:seconds
 * xxm:xxs - minutes:seconds
 * xxs - seconds *
 */
object SingleSuffixTimeFormat : TimeFormat() {

    override fun formatSeconds(seconds: Long): String {
        if(seconds < 10)
            return "0"
        return "$seconds" + "s"
    }

    override fun formatMinutes(minutes: Long): String {
        return "$minutes" + "m"
    }

    override fun formatHours(hours: Long): String {
        return "$hours" + "h"
    }

    override fun formatDays(days: Long): String {
        return "$days" + "d"
    }

    override fun formatMonths(months: Long): String {
        return "$months" + "m"
    }

    override fun formatYears(years: Long): String {
        return "$years" + "y"
    }

    override fun getJoiningString(): String {
        return ":"
    }

    override fun getLastJoiningString(): String {
        return getJoiningString()
    }
}