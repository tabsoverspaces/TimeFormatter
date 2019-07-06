# Format output examples:
#
# xxxy:xxM:xxd:xxh:xxm:xxs - years:months:days:hours:minutes:seconds
# xxM:xxd:xxh:xxm:xxs - months:days:hours:minutes:seconds
# xxd:xxh:xxm:xxs - days:hours:minutes:seconds
# xxh:xxm:xxs - hours:minutes:seconds
# xxm:xxs - minutes:seconds
# xxs - seconds
class SingleSuffixTimeFormat < TimeFormat
  private def initialize
  end

  def self.instance
    @@instance ||= new
  end

  def formatSeconds(seconds : Int64) : String
    return "0#{seconds}s" if seconds < 10
    return "#{seconds}s"
  end

  def formatMinutes(minutes : Int64) : String
    return "0#{minutes}m" if minutes < 10
    return "#{minutes}m"
  end

  def formatHours(hours : Int64) : String
    return "0#{hours}h" if hours < 10
    return "#{hours}h"
  end

  def formatDays(days : Int64) : String
    return "0#{days}d" if days < 10
    return "00#{days}d" if days < 100
    return "#{days}d"
  end

  def formatMonths(months : Int64) : String
    return "0#{months}M" if months < 10
    return "#{months}M"
  end

  def formatYears(years : Int64) : String
    return "#{years}y"
  end

  def getJoiningCharacter : String
    return ":"
  end

  def getLastJoiningCharacter : String
    return self.getJoiningCharacter
  end
end
