require "./TimeFormatter"

# EXAMPLE USAGE FILE

time1 : Int64 = 1_000_000
time2 : Int64 = 5_000
time3 : Int64 = 500_000_000

timeFormatter = TimeFormatter::TimeFormatter.instance # needed to access the TimeFormatter class from the TimeFormatter module
verbalFormat = VerbalTimeFormat.instance
singleSuffix = SingleSuffixTimeFormat.instance

puts "Time 1 : " + timeFormatter.format(time1, verbalFormat)
puts "Time 2 : " + timeFormatter.format(time2, verbalFormat)
puts "Time 3 : " + timeFormatter.format(time3, singleSuffix)
