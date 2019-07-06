# Time unit representing the *hours* time period
class HoursTimeUnit < TimeUnit
  # hidding the constructor
  private def initialize
  end

  # single instance access point
  def self.instance
    @@instance ||= new
  end

  def getFormattingMethodResult(millis : Int64, timeFormat : TimeFormat) : String
    return timeFormat.formatHours(self.getConcreteUnitValue(millis))
  end

  def getConcreteUnitValue(millis : Int64) : Int64
    return MinutesTimeUnit.instance.getConcreteUnitValue(millis) / 60.to_i64
  end

  def getUpperLimit : Int64
    return MinutesTimeUnit.instance.getUpperLimit * 24.to_i64
  end

  def nextUnit : TimeUnit
    return DaysTimeUnit.instance
  end

  def previousUnit : TimeUnit
    return MinutesTimeUnit.instance
  end
end
