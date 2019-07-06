#
# Base abstraction class for all the concrete time units that will be implemented.
# These time units are seconds, minutes, hours, days, months and years.
#
# Time units in Crystal are implemented as classes, instead of enums, which is the case in some other languages.

# Each of these concrete time unit class implementations must implement the Singleton design pattern, so that only one
# instance is created and used at any given moment.
#
abstract class TimeUnit
  # Returns a formatted string for only this time unit
  # *millis* is the time period in milliseconds
  # *timeFormat* is the format which the *millis* are to be formatted with
  abstract def getFormattingMethodResult(millis : Int64, timeFormat : TimeFormat) : String

  # Returns the value of the current time unit found in the provided *millis*
  # *millis* is the time period in milliseconds
  abstract def getConcreteUnitValue(millis : Int64) : Int64

  # Returns the maximum value that this time unit can contain in a single time period

  # Ex.
  # max seconds = 60
  # max minutes = 60
  # max hours = 24
  # max days = 30
  # max months = 12
  #
  # Excess values are passed on to the next time unit

  # Ex.
  # 80 seconds => 1 minute and 20 seconds
  # 98 days    => 3 months and 8 days
  abstract def getUpperLimit : Int64

  # Returns the next time unit in the hierarchy
  abstract def nextUnit : TimeUnit

  # Returns the previous time unit in the hierarchy
  abstract def previousUnit : TimeUnit

  # Formats *millis* with *timeFormat* while considering the provided *significantUnits*.
  #
  # Method starts calculating from the largest time unit found in *millis*, and goes down through the time units in the hierarchy
  # one by one.
  def format(millis, timeFormat, significantUnits)
    # Safety check is done in order to limit the maximum of value of significant units
    # according to the maximum number of distinct time units present in the provided milliseconds value.
    significantUnits = Math.min(significantUnits, TimeUnit.getTotalTimeUnitsPresent(millis))
    # String starts empty and on each time unit iteration and invocation of the format method,
    # the value of the time units' format strings are appended to it.
    formattedString : String = self.getFormattingMethodResult(millis, timeFormat)

    # number of significant units that will be passed on to the next time unit's format method
    nextSignificantUnits = significantUnits - 1

    # concrete value of the current time unit
    # at this point, this is just a number and it will be passed to the formatter method to produce the string
    currentUnitValue : Int64 = self.getConcreteUnitValue(millis)

    # this are the remainder milliseconds that will be passed to the next time unit's format method
    nextSmallerUnitMillis : Int64 = (millis - (currentUnitValue * self.previousUnit.getUpperLimit))

    if (significantUnits == 2)
      formattedString += timeFormat.getLastJoiningCharacter
    else
      if (nextSignificantUnits > 0)
        formattedString += timeFormat.getJoiningCharacter
      end
    end

    if (nextSignificantUnits > 0 && !(self == SecondsTimeUnit.instance))
      formattedString += self.previousUnit.format(nextSmallerUnitMillis, timeFormat, nextSignificantUnits)
    end

    return formattedString
  end

  # Returns the biggest time unit that was found in the provided *millis* argument
  def TimeUnit.getBiggestTimeUnit(millis : Int64) : TimeUnit
    if (millis > MonthsTimeUnit.instance.getUpperLimit)
      return YearsTimeUnit.instance
    end

    if (millis > DaysTimeUnit.instance.getUpperLimit)
      return MonthsTimeUnit.instance
    end

    if (millis > HoursTimeUnit.instance.getUpperLimit)
      return DaysTimeUnit.instance
    end

    if (millis > MinutesTimeUnit.instance.getUpperLimit)
      return HoursTimeUnit.instance
    end

    if (millis > SecondsTimeUnit.instance.getUpperLimit)
      return MinutesTimeUnit.instance
    end

    return SecondsTimeUnit.instance
  end

  # Returns the number of distinct time units found in the provided *millis* argument
  def TimeUnit.getTotalTimeUnitsPresent(millis : Int64) : Int32
    return 6 if millis > MonthsTimeUnit.instance.getUpperLimit
    return 5 if millis > DaysTimeUnit.instance.getUpperLimit
    return 4 if millis > HoursTimeUnit.instance.getUpperLimit
    return 3 if millis > MinutesTimeUnit.instance.getUpperLimit
    return 2 if millis > SecondsTimeUnit.instance.getUpperLimit
    return 1
  end
end
