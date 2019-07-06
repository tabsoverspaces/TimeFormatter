# Base class for all the time formats available to use.
#
# These classes need to implement the Singleton design pattern so that only one instance of the format type can be
# created and used.
#
abstract class TimeFormat
  # Returns the formatted string of the provided *millis* and *significantUnits*
  #
  # Method works by finding the largest time unit present in the provided *millis* argument
  # and goes down through the time units from there.
  def format(millis, significantUnits) : String
    biggestUnit = TimeUnit.getBiggestTimeUnit(millis)

    return biggestUnit.format(millis, self, significantUnits)
  end

  # Returns a formatted string with the provided number of *seconds*
  abstract def formatSeconds(seconds : Int64) : String
  # Returns a formatted string with the provided number of *minutes*
  abstract def formatMinutes(minutes : Int64) : String
  # Returns a formatted string with the provided number of *hours*
  abstract def formatHours(hours : Int64) : String
  # Returns a formatted string with the provided number of *days*
  abstract def formatDays(days : Int64) : String
  # Returns a formatted string with the provided number of *months*
  abstract def formatMonths(months : Int64) : String
  # Returns a formatted string with the provided number of *years*
  abstract def formatYears(years : Int64) : String
  # Returns the string that is inserted between two time units in the final string
  abstract def getJoiningCharacter : String
  # Returns the string that is inserted between the last and second to last time unit in the final string
  abstract def getLastJoiningCharacter : String
end
