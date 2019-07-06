# Time unit representing the *years* time period
class YearsTimeUnit < TimeUnit
  # hidding the constructor
  private def initialize
  end

  # single instance access point
  def self.instance
    @@instance ||= new
  end

  def getFormattingMethodResult(millis : Int64, timeFormat : TimeFormat) : String
    return timeFormat.formatYears(self.getConcreteUnitValue(millis))
  end

  def getConcreteUnitValue(millis : Int64) : Int64
    return MonthsTimeUnit.instance.getConcreteUnitValue(millis) / 12.to_i64
  end

  def getUpperLimit : Int64
    return MonthsTimeUnit.instance.getUpperLimit * 100.to_i64 # 100 years max, but this can be optimized to maximum length supported by Crystal
  end

  def nextUnit : TimeUnit
    return YearsTimeUnit.instance
  end

  def previousUnit : TimeUnit
    return MonthsTimeUnit.instance
  end
end
