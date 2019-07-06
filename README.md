# TimeFormatter
TimeFormatter is a utility class which provides various different ways of formatting (pretty-printing) a given time period. It is (will be) available for multiple programming languages.

Currently there are implementations available for Java and Crystal.

The formatter works by identifying the largest time unit present in the provided time period of milliseconds, and then formatting all smaller time units, one by one. Time units are better explained [here].

## Formats available
So far, there are two possible formats available, but more are planned to be added in the future, to allow for more formatting functionality.

### Verbal time format
This format can return a string of the given time period in the following shape:

- xx years, xx months, xx days, xx hours, xx minutes and xx seconds
- xx months, xx days, xx hours, xx minutes and xx seconds
- xx days, xx hours, xx minutes and xx seconds
- xx hours, xx minutes and xx seconds
- xx minutes and xx seconds
- xx seconds

### Single suffix time format
This format returns the string with values for each time units being followed by a single-letter suffix.
That can look like this:

- xxy:xxM:xxd:xxh:xxm:xxs
- xxM:xxd:xxh:xxm:xxs
- xxd:xxh:xxm:xxs
- xxh:xxm:xxs
- xxm:xxs
- xxs

Note: capital M stands for months; this is to prevent ambiguity between the months and minutes

## Time units
One interesting feature is the abstraction of larger time periods (called time units here). Basically (almost) every time period is represented by a separate entity and in the implementation they are organized in a hierarchy, which allows for easy access and management.

Currently, there are 6 time units available:
1) seconds (each containing 1.000 milliseconds; time periods less than a 1.000 milliseconds are formatted as zero seconds)
2) minutes (each containing 60 seconds)
3) hours (each containing 60 minutes)
4) days (each containing 24 hours)
5) months (containing 30 days)
6) years (each containing 12 months)

An important flexibility feature that is thus implemented is the possibility to give a value for the number of time units that you want your final formatted string to contain.
For example, if you wanted to format and print a time period with the verbal time format on the scale of a couple of years, but anything less than a day is irrelevant to you, then you can simple state that you only want to see 3 significant units, i.e. years, months and days.

Here are a couple of examples to illustrate this:
Ex.
#### - 4 significant units
- 3 years, 6 months, 2 days and 7 hours
- 1 month, 22 hours, 32 minutes and 14 seconds

#### - 3 significant units
- 5 years, 8 months and 25 days
- 11 days, 45 hours and 2 minutes
- 8 hours, 12 minutes and 24 seconds
etc.

If the user-provided number of time units is bigger than the number of time units found in the provided time period, then the smaller value will be used.

### DISCLAIMER
Due to the way time units are implemented and represented in the software, this formatter does in no way handle (extreme) precision(i.e taking leap years into consideration, 30/31 day months). Thus, its use is in no way suitable for time-critical systems, but rather more loosely based software solutions. 

### DISCLAIMER 2
Considering the previous disclaimer, this software might be used in systems where the minimal time period granularity is one second and whose time periods are not greater than 30 days. 
This way, there is no imprecision happening when formatting months(30/31 days) and there is no issue with milliseconds.

## Usage

### Java
1. Get the 'java_impl' package which contains all necessary class files and add it to your project.
2. Use one of the two available overloading methods named 'format'.

Ex.
```java
long timePeriod = 200_000_000;

TimeFormatter timeFormatter = TimeFormatter.getInstance();
VerbalTimeFormat verbalTimeFormat = VerbalTimeFormat.getInstance();
SingleSuffixTimeFormat singleSuffixTimeFormat = SingleSuffixTimeFormat.getInstance();

/*
* Formatting without explicitly providing a number of significant units.
* By default, this will show all time units present in the provided time period.
*/

// verbal format
String formattedTimePeriodVerbal1 = timeFormatter.format(timePeriod, verbalTimeFormat);

// single suffix format
String formattedTimePeriodSingleSuffix1 = timeFormatter.format(timePeriod, singleSuffixTimeFormat);

/*
* Formatting with significant units argument.
*/

// verbal format with 2 significant units
String formattedTimePeriodVerbal2 = timeFormatter.format(timePeriod, verbalTimeFormat, 2);

// single suffix format with 2 significant units
String formattedTimePeriodSingleSuffix2 = timeFormatter.format(timePeriod, singleSuffixTimeFormat, 2);

System.out.println("Verbal format - all time units : " + formattedTimePeriodVerbal1);
System.out.println("Single suffix - all time units : " + formattedTimePeriodSingleSuffix1);

System.out.println("Verbal format - two time units : " + formattedTimePeriodVerbal2);
System.out.println("Single suffix - two time units : " + formattedTimePeriodSingleSuffix2);
```

### Crystal
1. Get the 'crystal_impl' package which contains the TimeFormatter module and all the other necessary files and add it to your project.
2. Use one of the two methods of the TimeFormatter class.

Example usage:

```crystal
require "./TimeFormatter"

timePeriod : Int64 = 1_000_000

timeFormatter = TimeFormatter::TimeFormatter.instance
verbalFormat = VerbalTimeFormat.instance
singleSuffix = SingleSuffixTimeFormat.instance

# Formatting without explictly specifying the number of significant units to be displayed
formattedVerbal1 = timeFormatter.format(timePeriod, verbalFormat) # verbal format with no significant units argument
formattedSuffix1 = timeFormatter.format(timePeriod, singleSuffix) # single suffix format with no significant units argument

# Formatting with significant units argument
formattedVerbal2 = timeFormatter.format(timePeriod, verbalFormat, 2) # verbal format with two significant units
formattedSuffix2 = timeFormatter.format(timePeriod, singleSuffix, 2) # single suffix format with two significant units


puts "Verbal format - all time units : " + formattedVerbal1
puts "Single suffix - all time units : " + formattedSuffix1

puts "Verbal format - two time units : " + formattedVerbal2
puts "Single suffix - two time units : " + formattedSuffix2
```

[here]: https://github.com/tabsoverspaces/TimeFormatter#time-units
