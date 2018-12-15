package com.mindf.utils.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTime {

    public final static String MONDAY = "monday";
    public final static String TUESDAY = "tuesday";
    public final static String WEDNESSDAY = "wednessday";
    public final static String THURSDAY = "thursday";
    public final static String FRIDAY = "friday";
    public final static String SATURDAY = "saturday";
    public final static String SUNDAY = "sunday";
    public final static String LUNDI = "lundi";
    public final static String MARDI = "mardi";
    public final static String MERCREDI = "mercredi";
    public final static String JEUDI = "jeudi";
    public final static String VENDREDI = "vendredi";
    public final static String SAMEDI = "samedi";
    public final static String DIMANCHE = "dimanche";

    public final static double secondMilliseconds = 1000;
    public final static double minuteMilliseconds = 60000;
    public final static double hourMilliseconds = 3.6e+6;
    public final static double dayMilliseconds = 8.64e+7;
    public final static double weekMilliseconds = 6.048e+8;
    public final static double monthMilliseconds = 2.628e+9;
    public final static double yearMilliseconds = 3.154e+10;

    private final static String currentYear = getCurrentYear();
    private final static String currentMonth = getCurrentMonth();
    private final static String currentDay = getCurrentDay();

    //format == iso8601

    public static String getCurrentDateTime() {
        return getCurrentDate() + " " + getCurrentTime();
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getCurrentYear() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }

    public static String getCurrentMonth() {
        return new SimpleDateFormat("MM").format(new Date());
    }

    public static String getCurrentDay() {
        return new SimpleDateFormat("dd").format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static String getCurrentHours() {
        return new SimpleDateFormat("HH").format(new Date());
    }

    public static String getCurrentMinute() {
        return new SimpleDateFormat("mm").format(new Date());
    }

    public static String getCurrentSeconds() {
        return new SimpleDateFormat("ss").format(new Date());
    }

    public static String getCurrentMilliSeconds() {
        return String.valueOf(new Date().getTime());
    }

    public static double getCurrentMilliSecondsValue() {
        return (double) new Date().getTime();
    }

    public static String getADateMilliSeconds(String dateString) {
        try {
            return String.valueOf(new SimpleDateFormat("yyyy-MM-dd").parse(dateString).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Parse Exception");
        }
    }
    
    public static double getADateMilliSecondsValue(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString).getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Parse Exception");
        }
    }

/*
    public static String getAYearMilliSeconds(String yearString) {
        //todo
    }

    public static String getAMonthMilliSeconds(String monthString) {
        //todo
    }

    public static String getADayMilliSeconds(String dayString) {
        //todo
    }
*/

    public static String changeDateFormatToIso8601(String stringDate, final String OLD_FORMAT) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(OLD_FORMAT);
        simpleDateFormat.applyPattern("yyyy-MM-dd");
        return simpleDateFormat.format(defineDate(simpleDateFormat, stringDate));
    }

    public static String extractYear(String dateString) {
        return dateString.substring(0, 4);
    }

    public static String extractMonth(String dateString) {
        return dateString.substring(5, 7);
    }

    public static String extractDay(String dateString) {
        return dateString.substring(8, 10);
    }

    public static boolean isDateBetween(String inputDate, String startingDate, String endingDate) {
        double milliSecondsInputDate = Double.parseDouble(getADateMilliSeconds(inputDate));
        double milliSecondsStartingDate = Double.parseDouble(getADateMilliSeconds(startingDate));
        double milliSecondsEndingDate = Double.parseDouble(getADateMilliSeconds(endingDate));
        return milliSecondsInputDate >= milliSecondsStartingDate && milliSecondsInputDate <= milliSecondsEndingDate;
    }

    public static boolean isDateSmallerOrEquals(String inputDate, String comparedDate) {
        double milliSecondsInputDate = Double.parseDouble(getADateMilliSeconds(inputDate));
        double milliSecondsComparedDate = Double.parseDouble(getADateMilliSeconds(comparedDate));
        return milliSecondsInputDate <= milliSecondsComparedDate;
    }

    public static boolean isDateAfter(String inputDate, String comparedDate) {
        double milliSecondsInputDate = Double.parseDouble(getADateMilliSeconds(inputDate));
        double milliSecondsComparedDate = Double.parseDouble(getADateMilliSeconds(comparedDate));
        return milliSecondsInputDate > milliSecondsComparedDate;
    }

    public static boolean isDateHigherOrEquals(String inputDate, String comparedDate) {
        double milliSecondsInputDate = Double.parseDouble(getADateMilliSeconds(inputDate));
        double milliSecondsComparedDate = Double.parseDouble(getADateMilliSeconds(comparedDate));
        return milliSecondsInputDate >= milliSecondsComparedDate;
    }

    public static boolean isDateBefore(String inputDate, String comparedDate) {
        double milliSecondsInputDate = Double.parseDouble(getADateMilliSeconds(inputDate));
        double milliSecondsComparedDate = Double.parseDouble(getADateMilliSeconds(comparedDate));
        return milliSecondsInputDate < milliSecondsComparedDate;
    }

    public static boolean isDateEquals(String inputDate, String comparedDates) {
        double milliSecondsInputDate = Double.parseDouble(getADateMilliSeconds(inputDate));
        double milliSecondsComparedDate = Double.parseDouble(getADateMilliSeconds(comparedDates));
        return milliSecondsInputDate == milliSecondsComparedDate;
    }

    public static boolean isDateValid(String inputDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(inputDate);
            return true;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Parse Exception");
        }
    }

    public static boolean isAfterCurrentDate(String stringDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentDate()));
        double milliSecondsStringDate = Double.parseDouble(getADateMilliSeconds(stringDate));
        return milliSecondsStringDate > milliSecondsCurrentDate;
    }

    public static boolean isAfterCurrentDate(long millisecondsDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentDate()));
        return millisecondsDate > milliSecondsCurrentDate;
    }

    public static boolean isBeforeCurrentDate(String stringDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentDate()));
        double milliSecondsStringDate = Double.parseDouble(getADateMilliSeconds(stringDate));
        return milliSecondsStringDate < milliSecondsCurrentDate;
    }

    public static boolean isBeforeCurrentDate(long millisecondsDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentDate()));
        return millisecondsDate < milliSecondsCurrentDate;
    }

    public static boolean isEqualsCurrentDate(String stringDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentDate()));
        double milliSecondsStringDate = Double.parseDouble(getADateMilliSeconds(stringDate));
        return milliSecondsStringDate == milliSecondsCurrentDate;
    }

    public static boolean isEqualsCurrentDate(long millisecondsDate) {
        return millisecondsDate == getCurrentMilliSecondsValue();
    }

    public static boolean isAfterOrEqualsCurrentDate(String stringDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentDate()));
        double milliSecondsStringDate = Double.parseDouble(getADateMilliSeconds(stringDate));
        return milliSecondsStringDate >= milliSecondsCurrentDate;
    }

    public static boolean isAfterOrEqualsCurrentDate(long millisecondsDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentDate()));
        return millisecondsDate >= milliSecondsCurrentDate;
    }

    public static boolean isBeforeOrEqualsCurrentDate(String stringDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentDate()));
        double milliSecondsStringDate = Double.parseDouble(getADateMilliSeconds(stringDate));
        return milliSecondsStringDate <= milliSecondsCurrentDate;
    }

    public static boolean isBeforeOrEqualsCurrentDate(long millisecondsDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentDate()));
        return millisecondsDate <= milliSecondsCurrentDate;
    }

    public static boolean isMinor(String age) {
        return getAge(age) <= 18;
    }

    public static boolean isMinor(int age) {
        return age <= 18;
    }

    public static boolean isYearValid(String stringYear) {
        double yearBetweenCurentDate = getYearsBetweenTwoDates(getCurrentYear(), stringYear);
        return yearBetweenCurentDate > 150;
    }

    public static double getMilliSecondsBetweenTwoDates(String inputDate, String comparedDate) {
        double milliSecondsInputDate = Double.parseDouble(getADateMilliSeconds(inputDate));
        double milliSecondsComparedDate = Double.parseDouble(getADateMilliSeconds(comparedDate));
        return milliSecondsInputDate - milliSecondsComparedDate;
    }

    public static double getDayBetweenTwoDates(String inputDate, String comparedDate) {
        double differenceInMilliSeconds = getMilliSecondsBetweenTwoDates(inputDate, comparedDate);
        return differenceInMilliSeconds / dayMilliseconds;
    }

    public static double getMonthBetweenTwoDates(String inputDate, String comparedDate) {
        double differenceInMilliSeconds = getMilliSecondsBetweenTwoDates(inputDate, comparedDate);
        return Math.round(differenceInMilliSeconds / monthMilliseconds);
    }

    public static double getYearsBetweenTwoDates(String inputDate, String comparedDate) {
        double differenceInMilliSeconds = getMilliSecondsBetweenTwoDates(inputDate, comparedDate);
        return Math.round(differenceInMilliSeconds / yearMilliseconds);
    }

    public static double getHoursBetweenTwoDates(String inputDate, String comparedDate) {
        double differenceInMilliSeconds = getMilliSecondsBetweenTwoDates(inputDate, comparedDate);
        return differenceInMilliSeconds / hourMilliseconds;
    }

    public static double getMinutesBetweenTwoDates(String inputDate, String comparedDate) {
        double deferenceInMilliSeconds = getMilliSecondsBetweenTwoDates(inputDate, comparedDate);
        return deferenceInMilliSeconds / minuteMilliseconds;
    }

    public static double getSecondsBetweenTwoDates(String inputDate, String comparedDate) {
        double differenceInMilliSeconds = getMilliSecondsBetweenTwoDates(inputDate, comparedDate);
        return differenceInMilliSeconds / secondMilliseconds;
    }

    public static String getWeakDayName(final Locale LANGUAGE) {
        try {
            Date date = new SimpleDateFormat("yyyy-M-d").parse(getCurrentDate());
            return new SimpleDateFormat("EEEE", LANGUAGE).format(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Parse Exception");
        }
    }

    public static String getYearMonthName(final Locale LANGUAGE) {
        try {
            Date date = new SimpleDateFormat("yyyy-M-d").parse(getCurrentDate());
            return new SimpleDateFormat("MMM", LANGUAGE).format(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Parse Exception");
        }
    }

    public static int getAge(String birthDate) {      //must not be born in future
        String birthYear = extractYear(birthDate);
        String birthMonth = extractMonth(birthDate);
        String birthDay = extractDay(birthDate);
        int age = Integer.parseInt(currentYear) - Integer.parseInt(birthYear);
        if (currentMonth.equals(birthMonth) && Integer.parseInt(birthDay) > Integer.parseInt(currentDay) || Integer.parseInt(birthMonth) > Integer.parseInt(currentMonth)) {
            --age;
        }
        return age;
    }

    private static Date defineDate(SimpleDateFormat simpleDateFormat, String stringDate) {
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Parse Exception");
        }
    }

    static boolean isBirthDateValid(String birthDate) {
        return isDateValid(birthDate) && isBeforeCurrentDate(birthDate);
    }
}
