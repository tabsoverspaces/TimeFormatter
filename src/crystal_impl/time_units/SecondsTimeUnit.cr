# Time unit representing the *seconds* time period
class SecondsTimeUnit < TimeUnit
  # hidding the constructor
  private def initialize
  end

  # single instance access point
  def self.instance
    @@instance ||= new
  end

  def getFormattingMethodResult(millis : Int64, timeFormat : TimeFormat) : String
    return timeFormat.formatSeconds(self.getConcreteUnitValue(millis))
  end

  def getConcreteUnitValue(millis : Int64) : Int64
    return millis/1000.to_i64
  end

  def getUpperLimit : Int64
    return 60.to_i64 * 1000.to_i64
  end

  def nextUnit : TimeUnit
    return MinutesTimeUnit.instance
  end

  def previousUnit : TimeUnit
    return SecondsTimeUnit.instance
  end
end
