# Time unit representing the *days* time period
class DaysTimeUnit < TimeUnit
  # hiding the constructor
  private def initialize
  end

  # single instance access point
  def self.instance
    @@instance ||= new
  end

  def getFormattingMethodResult(millis : Int64, timeFormat : TimeFormat) : String
    return timeFormat.formatDays(self.getConcreteUnitValue(millis))
  end

  def getConcreteUnitValue(millis : Int64) : Int64
    return HoursTimeUnit.instance.getConcreteUnitValue(millis) / 24.to_i64
  end

  def getUpperLimit : Int64
    return HoursTimeUnit.instance.getUpperLimit * 30.to_i64
  end

  def nextUnit : TimeUnit
    return MonthsTimeUnit.instance
  end

  def previousUnit : TimeUnit
    return HoursTimeUnit.instance
  end
end
