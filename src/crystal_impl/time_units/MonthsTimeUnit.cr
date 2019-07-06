# Time unit representing the *months* time period
class MonthsTimeUnit < TimeUnit
  # hidding the constructor
  private def initialize
  end

  # single instance access point
  def self.instance
    @@instance ||= new
  end

  def getFormattingMethodResult(millis : Int64, timeFormat : TimeFormat) : String
    return timeFormat.formatMonths(self.getConcreteUnitValue(millis))
  end

  def getConcreteUnitValue(millis : Int64) : Int64
    return DaysTimeUnit.instance.getConcreteUnitValue(millis) / 30.to_i64
  end

  def getUpperLimit : Int64
    return DaysTimeUnit.instance.getUpperLimit * 12.to_i64
  end

  def nextUnit : TimeUnit
    return YearsTimeUnit.instance
  end

  def previousUnit : TimeUnit
    return DaysTimeUnit.instance
  end
end
