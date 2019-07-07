package kotlin_impl.concrete_format_types

import kotlin_impl.TimeFormat

/**
 * Example output formats :
 *
 * 1) xxx days, xx hours, xx minutes and xx seconds
 * - 102 days, 22 hours, 33 minutes and 21 seconds
 *
 * 2) xx hours, xx minutes and xx seconds
 * - 15 hours, 21 minutes and 44 seconds
 *
 * 3) xx minutes and xx seconds
 * - 13 minutes and 55 seconds
 *
 * 4) xx seconds
 * - 32 seconds
 */
object VerbalTimeFormat : TimeFormat() {

    override fun formatSeconds(seconds: Long): String {
        // make it pretty
        return if(seconds==1L)
            "$seconds second"
        else
            return "$seconds seconds"

    }

    override fun formatMinutes(minutes: Long): String {

        return "$minutes minutes"
    }

    override fun formatHours(hours: Long): String {

        return "$hours hours"
    }

    override fun formatDays(days: Long): String {

        return "$days days"
    }

    override fun formatMonths(months: Long): String {

        return "$months months"
    }

    override fun formatYears(years: Long): String {

        return "$years years"
    }

    override fun getJoiningString(): String {
        return ", "
    }

    override fun getLastJoiningString(): String {
        return " and "
    }
}