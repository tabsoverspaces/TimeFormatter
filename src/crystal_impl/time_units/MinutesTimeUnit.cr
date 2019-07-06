# Time unit representing the *minutes* time period
class MinutesTimeUnit < TimeUnit
  # hidding the constructor
  private def initialize
  end

  # single instance access point
  def self.instance
    @@instance ||= new
  end

  def getFormattingMethodResult(millis : Int64, timeFormat : TimeFormat) : String
    return timeFormat.formatMinutes(self.getConcreteUnitValue(millis))
  end

  def getConcreteUnitValue(millis : Int64) : Int64
    return SecondsTimeUnit.instance.getConcreteUnitValue(millis) / 60.to_i64
  end

  def getUpperLimit : Int64
    return SecondsTimeUnit.instance.getUpperLimit * 60.to_i64
  end

  def nextUnit : TimeUnit
    return HoursTimeUnit.instance
  end

  def previousUnit : TimeUnit
    return SecondsTimeUnit.instance
  end
end
