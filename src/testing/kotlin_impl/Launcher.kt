package testing.kotlin_impl

import kotlin_impl.concrete_format_types.SingleSuffixTimeFormat
import kotlin_impl.concrete_format_types.VerbalTimeFormat
import kotlin_impl.TimeFormatter

class Launcher {

    companion object{

        @JvmStatic
        fun main(args : Array<String>)
        {
            val timePeriod = 5_000_000L

            val timeFormatter = TimeFormatter
            val verbalFormat = VerbalTimeFormat
            val singleSuffix = SingleSuffixTimeFormat

            /*
            Formatting time period without explicitly stating number of time units to be shown
             */
            val formattedVerbal1 = timeFormatter.format(timePeriod, verbalFormat)
            val formattedSingleSuffix1 = timeFormatter.format(timePeriod, singleSuffix)

            /*
            Formatting with significant units argument.
             */
            val formattedVerbal2 = timeFormatter.format(timePeriod, verbalFormat, 2)
            val formattedSingleSuffix2 = timeFormatter.format(timePeriod, singleSuffix, 2)

            println("Verbal format - all time units : $formattedVerbal1")
            println("Verbal format - 2 time units : $formattedVerbal2")
            println()
            println("Single suffix - all time units : $formattedSingleSuffix1")
            println("Single suffix - 2 time units : $formattedSingleSuffix2")
        }

    }
}