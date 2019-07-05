# TimeFormatter
TimeFormatter is a utility class which provides various different ways of formatting (pretty-printing) a given time period. It is (will be) available for multiple programming languages.
As of the initial commit of version 0.1, only implementation for Java is available.

## Formats available
So far, there are two possible formats available, but more are planned to be added in the future, to allow for more formatting functionality.

### Verbal time format
This format can return a string of the given time period in the following shape:

- xx seconds
- xx minutes and xx seconds
- xx hours, xx minutes and xx seconds
- xx days, xx hours, xx minutes and xx seconds
- xx months, xx days, xx hours, xx minutes and xx seconds



## Time units
One important flexibility feature of the formatter is that it allows you to give a value for the number of time units(days, hours, minutes...) that you want your final formatted string to contain.
For example, if you wanted to format and print a time period with the verbal time format on the scale of a couple of years, but anything less than a day is irrelevant to you, then you can simple state that you only want to see 3 significant units, i.e. years, months and days.

Ex.
1) 4 significant units
- 3 years, 6 months, 2 days and 7 hours
- 1 month, 22 hours, 32 minutes and 14 seconds

2) 3 significant units
- 5 years, 8 months and 25 days
- 11 days, 45 hours and 2 minutes
- 8 hours, 12 minutes and 24 seconds
etc.

Currently, there are 6 time units available in total:
1) seconds ( each containing 1.000 milliseconds; time periods less than a 1.000 milliseconds are formatted as zero seconds)
2) minutes ( each containing 60 seconds )
3) hours ( each containing 60 minutes )
4) days ( each containing 24 hours )
5) months ( containing 30 days )
6) years ( each containing 12 months )

## Usage
### Java
1. Get the 'java_util' package which contains all necessary class files and add it to your project.
2. Use one of the two available overloading methods named 'format'.
Ex.

long timePeriod = 200_000; // 200.000 milliseconds, or 200 seconds, or three minutes and 20 seconds.
String formattedTimePeriod = TimeFormatter.getInstance().format(timePeriod, VerbalTimeFormat.getInstance()));
