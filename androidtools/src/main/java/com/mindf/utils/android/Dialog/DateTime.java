package com.mindf.utils.android.Dialog;

import android.os.Build;
import android.support.annotation.RequiresApi;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import lombok.SneakyThrows;
import static com.mindf.utils.java.DateTime.dateToStringDate;
import static com.mindf.utils.java.DateTime.getSimpleDateFormat;
import static com.mindf.utils.java.DateTime.milliSecondsToStringDate;
import static com.mindf.utils.java.DateTime.stringDateToDate;

public class DateTime {

    //every Date method are here to support Date untill it is removed (replaced by Calendar)

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    }

    public static String calendarToStringDate(Calendar calendarDate) {
        long calendarMilliSeconds = calendarDate.getTimeInMillis();
        return milliSecondsToStringDate(calendarMilliSeconds);
    }

    @SneakyThrows
    public static Calendar stringDateToCalendar(String stringDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getSimpleDateFormat().parse(stringDate));
        return calendar;
    }

    public static Calendar dateToCalendar(Date date) {
        return stringDateToCalendar(dateToStringDate(date));
    }

    public static Date calendarToDate(Calendar calendar) {
        return stringDateToDate(milliSecondsToStringDate(calendar.getTimeInMillis()));
    }
}
