# Format output examples:
#
# xx years, xx months, xx days, xx hours, xx minutes and xx seconds
# xx months, xx days, xx hours, xx minutes and xx seconds
# xx days, xx hours, xx minutes and xx seconds
# xx hours, xx minutes and xx seconds
# xx minutes and xx seconds
# xx seconds
class VerbalTimeFormat < TimeFormat
  private def initialize
  end

  def self.instance
    @@instance ||= new
  end

  def formatSeconds(seconds : Int64) : String
    return "1 second" if seconds == 1
    return "#{seconds} seconds"
  end

  def formatMinutes(minutes : Int64) : String
    return "1 minute" if minutes == 1
    return "#{minutes} minutes"
  end

  def formatHours(hours : Int64) : String
    return "1 hour" if hours == 1
    return "#{hours} hours"
  end

  def formatDays(days : Int64) : String
    return "1 day" if days == 1
    return "#{days} days"
  end

  def formatMonths(months : Int64) : String
    return "1 month" if months == 1
    return "#{months} months"
  end

  def formatYears(years : Int64) : String
    return "1 year" if years == 1
    return "#{years} years"
  end

  def getJoiningCharacter : String
    return ", "
  end

  def getLastJoiningCharacter : String
    return " and "
  end
end
