require "./TimeFormat"
require "./TimeUnit"
require "./time_units/*"
require "./time_formats/*"

# TimeFormatter provides various different ways of formatting a given time period and representing it
# as a more readable, user-friendly strings, instead of just showing milliseconds.
#
# A couple of formats are currently available.
#
# 1) Verbal
# Ex. 21 hours, 15 minutes and 32 seconds
#
# 2) Single suffix
# Ex. 22h:33m:21s
#
# For more information, please visit https://github.com/tabsoverspaces/TimeFormatter
#
module TimeFormatter
  VERSION = "0.1"

  class TimeFormatter
    # Constructor made private to comply to the Singleton design pattern
    private def initialize
    end

    # single access point to an instance of this class to comply to the Singleton design pattern
    def self.instance
      @@instance ||= new
    end

    # Formats the provided *millis* with the *timeFormat*.
    # *millis* is the number of milliseconds in the time period that is to be formatted.
    def format(millis : Int64, timeFormat : TimeFormat) : String
      return timeFormat.format(millis, 99)
    end

    # Returns a formatted string from the provided *millis* which are formatted with the *timeFormat*,
    # while considering the number of *significantUnits* that the final string can contain.
    #
    # *millis* is the number of milliseconds in the time period that is to be formatted.
    # *significantUnits* is the maximum number of time units that can be found in the final formatted string
    def format(millis : Int64, timeFormat : TimeFormat, significantUnits : Int32) : String
      return timeFormat.format(millis, significantUnits)
    end
  end
end
