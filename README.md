# TimeFormatter
TimeFormatter is a utility class which provides various different ways of formatting (pretty-printing) a given time period. It is (will be) available for multiple programming languages.
As of the initial commit of version 0.1, only implementation for Java is available.

The formatter works by identifying the largest time unit present in the provided time period of milliseconds, and then formatting all smaller time units, one by one. Time units are better explained below.

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

Note: capital M stands for months;  this is to prevent ambiguity between the months and minutes

## Time units
One important flexibility feature of the formatter is that it allows you to give a value for the number of time units(days, hours, minutes...) that you want your final formatted string to contain.
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

Currently, there are 6 time units available in total:
1) seconds ( each containing 1.000 milliseconds; time periods less than a 1.000 milliseconds are formatted as zero seconds)
2) minutes ( each containing 60 seconds )
3) hours ( each containing 60 minutes )
4) days ( each containing 24 hours )
5) months ( containing 30 days )
6) years ( each containing 12 months )

If the user-provided number of time units is bigger than the number of time units found in the provided time period, then the smaller value will be used.

## Usage
### Java
1. Get the 'java_util' package which contains all necessary class files and add it to your project.
2. Use one of the two available overloading methods named 'format'.

Ex.
```java
long timePeriod = 200_000; // 200.000 milliseconds, or 200 seconds, or three minutes and 20 seconds.
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

// verbal format with 3 significant units
String formattedTimePeriodVerbal2 = timeFormatter.format(timePeriod, verbalTimeFormat, 3);

// single suffix format with 4 significant units
String formattedTimePeriodSingleSuffix2 = timeFormatter.format(timePeriod, singleSuffixTimeFormat, 4);

System.out.println("Verbal format - all time units : " + formattedTimePeriodVerbal1);
System.out.println("Single suffix - all time units : " + formattedTimePeriodSingleSuffix1);
System.out.println("Verbal format - three time units : " + formattedTimePeriodVerbal2);
System.out.println("Single suffix - four time units : " + formattedTimePeriodSingleSuffix2);
```

