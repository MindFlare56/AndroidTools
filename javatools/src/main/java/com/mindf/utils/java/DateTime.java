package com.mindf.utils.java;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

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

    public final static String ISO8601_DATE_FORMAT = "yyyy-MM-dd";
    public final static String YEAR_FORMAT = "yyyy";
    public final static String MONTH_FORMAT = "MM";
    public final static String DAY_FORMAT = "dd";
    public final static char ISO8601_DATE_SEPARATOR = '-';
    public final static String ISO8601_TIME_FORMAT = "HH:mm:ss";
    public final static String HOUR_FORMAT = "HH";
    public final static String MINUTE_FORMAT = "mm";
    public final static String SECONDS_FORMAT = "ss";
    public final static char ISO8601_TIME_SEPARATOR = ':';
    public final static String WEEK_DAY_FORMAT = "EEEE";
    public final static String YEAR_MONTH_FORMAT = "MMM";

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

    @Getter @Setter private static Locale locale = Locale.ENGLISH;

    //format == iso8601
    public DateTime(Locale locale) {
        DateTime.locale = locale;
    }

    public static String getCurrentDateTime() {
        return getCurrentDate() + " " + getCurrentTime();
    }

    public static SimpleDateFormat getSimpleDateYearsFormat() {
        return new SimpleDateFormat(YEAR_FORMAT, locale);
    }

    public static SimpleDateFormat getSimpleDateMonthsFormat() {
        return new SimpleDateFormat(MONTH_FORMAT, locale);
    }

    public static SimpleDateFormat getSimpleDateDaysFormat() {
        return new SimpleDateFormat(DAY_FORMAT, locale);
    }

    public static SimpleDateFormat getSimpleDateTimeFormat() {
        return new SimpleDateFormat(ISO8601_TIME_FORMAT, locale);
    }

    public static SimpleDateFormat getSimpleDateHoursFormat() {
        return new SimpleDateFormat(HOUR_FORMAT, locale);
    }

    public static SimpleDateFormat getSimpleDateMinutesFormat() {
        return new SimpleDateFormat(MINUTE_FORMAT, locale);
    }

    public static SimpleDateFormat getSimpleDateSecondsFormat() {
        return new SimpleDateFormat(SECONDS_FORMAT, locale);
    }

    public static long getCurrentMilliSecondsValue() {
        return new Date().getTime();
    }

    @SneakyThrows
    public static String getADateMilliSeconds(String dateString) {
        return String.valueOf(new SimpleDateFormat(ISO8601_DATE_FORMAT, locale).parse(dateString).getTime());
    }

    @SneakyThrows
    public static long getStringDateMilliSecondsLong(String dateString) {
        return new SimpleDateFormat(ISO8601_DATE_FORMAT, locale).parse(dateString).getTime();
    }

    @SneakyThrows
    public static double getStringDateMilliSeconds(String dateString) {
        return new SimpleDateFormat(ISO8601_DATE_FORMAT, locale).parse(dateString).getTime();
    }

    public static String changeDateFormatToIso8601(String stringDate, final String OLD_FORMAT) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(OLD_FORMAT, locale);
        simpleDateFormat.applyPattern(ISO8601_DATE_FORMAT);
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

    @SneakyThrows
    public static boolean isDateValid(String inputDate) {
        DateFormat dateFormat = new SimpleDateFormat(ISO8601_DATE_FORMAT, Locale.ENGLISH);
        dateFormat.setLenient(false);
        dateFormat.parse(inputDate);
        return true;
    }

    public static boolean isAfterCurrentDate(String stringDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentStringDate()));
        double milliSecondsStringDate = Double.parseDouble(getADateMilliSeconds(stringDate));
        return milliSecondsStringDate > milliSecondsCurrentDate;
    }

    public static boolean isAfterCurrentDate(long millisecondsDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentStringDate()));
        return millisecondsDate > milliSecondsCurrentDate;
    }

    public static boolean isBeforeCurrentDate(String stringDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentStringDate()));
        double milliSecondsStringDate = Double.parseDouble(getADateMilliSeconds(stringDate));
        return milliSecondsStringDate < milliSecondsCurrentDate;
    }

    public static boolean isBeforeCurrentDate(long millisecondsDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentStringDate()));
        return millisecondsDate < milliSecondsCurrentDate;
    }

    public static boolean isEqualsCurrentDate(String stringDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentStringDate()));
        double milliSecondsStringDate = Double.parseDouble(getADateMilliSeconds(stringDate));
        return milliSecondsStringDate == milliSecondsCurrentDate;
    }

    public static boolean isEqualsCurrentDate(long millisecondsDate) {
        return millisecondsDate == getCurrentMilliSecondsValue();
    }

    public static boolean isAfterOrEqualsCurrentDate(String stringDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentStringDate()));
        double milliSecondsStringDate = Double.parseDouble(getADateMilliSeconds(stringDate));
        return milliSecondsStringDate >= milliSecondsCurrentDate;
    }

    public static boolean isAfterOrEqualsCurrentDate(long millisecondsDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentStringDate()));
        return millisecondsDate >= milliSecondsCurrentDate;
    }

    public static boolean isBeforeOrEqualsCurrentDate(String stringDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentStringDate()));
        double milliSecondsStringDate = Double.parseDouble(getADateMilliSeconds(stringDate));
        return milliSecondsStringDate <= milliSecondsCurrentDate;
    }

    public static boolean isBeforeOrEqualsCurrentDate(long millisecondsDate) {
        double milliSecondsCurrentDate = Double.parseDouble(getADateMilliSeconds(getCurrentStringDate()));
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

    public static double getDoubleDayBetweenTwoDates(String inputDate, String comparedDate) {
        double differenceInMilliSeconds = getMilliSecondsBetweenTwoDates(inputDate, comparedDate);
        return differenceInMilliSeconds / dayMilliseconds;
    }

    public static int getDayBetweenTwoDates(String inputDate, String comparedDate) {
        double differenceInMilliSeconds = getMilliSecondsBetweenTwoDates(inputDate, comparedDate);
        return (int) (differenceInMilliSeconds / dayMilliseconds);
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

    @SneakyThrows
    public static String getWeakDayName() {
        Date date = getSimpleDateFormat().parse(getCurrentStringDate());
        return new SimpleDateFormat(WEEK_DAY_FORMAT, Locale.ENGLISH).format(date);
    }

    @SneakyThrows
    public static String getYearMonthName() {
        Date date = getSimpleDateFormat().parse(getCurrentStringDate());
        return new SimpleDateFormat(YEAR_MONTH_FORMAT, Locale.ENGLISH).format(date);
    }

    public static int getAge(String birthDate) { //must not be born in future
        String birthYear = extractYear(birthDate);
        String birthMonth = extractMonth(birthDate);
        String birthDay = extractDay(birthDate);
        int age = Integer.parseInt(currentYear) - Integer.parseInt(birthYear);
        if (currentMonth.equals(birthMonth) && Integer.parseInt(birthDay) > Integer.parseInt(currentDay) || Integer.parseInt(birthMonth) > Integer.parseInt(currentMonth)) {
            --age;
        }
        return age;
    }

    static boolean isBirthDateValid(String birthDate) {
        return isDateValid(birthDate) && isBeforeCurrentDate(birthDate);
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat(ISO8601_DATE_FORMAT, locale);
    }

    /** Deprecated **/
    public static String dateToStringDate(Date date) {
        long dateValue = date.getDate();
        return milliSecondsToStringDate(dateValue);
    }

    public static String milliSecondsToStringDate(long milliseconds) {
        return getSimpleDateFormat().format(new Date(milliseconds));
    }

    public static double getCurrentMilliSecondsDoubleValue() {
        return (double) new Date().getTime();
    }

    public static Date getCurrentDate() {
        return stringDateToDate(getCurrentStringDate());
    }

    @SneakyThrows
    public static Date stringDateToDate(String stringDate) {
        return getSimpleDateFormat().parse(stringDate);
    }

    @SneakyThrows
    private static Date defineDate(SimpleDateFormat simpleDateFormat, String stringDate) {
        return simpleDateFormat.parse(stringDate);
    }

    public static String getCurrentStringDate() {
        return getSimpleDateFormat().format(new Date());
    }

    public static String getCurrentYear() {
        if (locale == null) locale = Locale.ENGLISH;
        return getSimpleDateYearsFormat().format(new Date());
    }

    public static String getCurrentMonth() {
        if (locale == null) locale = Locale.ENGLISH;
        return getSimpleDateMonthsFormat().format(new Date());
    }

    public static String getCurrentDay() {
        if (locale == null) locale = Locale.ENGLISH;
        return getSimpleDateDaysFormat().format(new Date());
    }

    public static String getCurrentTime() {
        return getSimpleDateTimeFormat().format(new Date());
    }

    public static String getCurrentHours() {
        return getSimpleDateHoursFormat().format(new Date());
    }

    public static String getCurrentMinute() {
        return getSimpleDateMinutesFormat().format(new Date());
    }

    public static String getCurrentSeconds() {
        return getSimpleDateSecondsFormat().format(new Date());
    }

    public static String getCurrentMilliSeconds() {
        return String.valueOf(new Date().getTime());
    }
    /***/
}
