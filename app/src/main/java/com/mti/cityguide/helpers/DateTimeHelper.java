package com.mti.cityguide.helpers;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Eslam on 03/22/2019.
 */
public class DateTimeHelper {
    //2018-02-22 02:33:23
    public static final String FORMAT_MY_ACCOUNT = "dd MMM yyyy";
    public static final String FORMAT_LOGS_DATE = "MM/dd/yyyy";
    public static final String FORMAT_LOGS_TIME = "hh:mm:ss a";
    public static final String MM_DD_YYYY = "mm/dd/yyyy";
    public static final String FORMAT_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DD_MM = "dd MMM";
    //2018-04-06 08:06:10
    public static final String SERVER_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String H_MM = "h:mm";
    public static final String A = "a";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String DD = "dd";
    public static final String MMM = "MMM";
    public static final String HH_MM_A = "hh:mm a";

    /**
     * parses date from old format, and returns a date formatted with the new format
     * uses default locale
     *
     * @param oldFormat
     * @param newFormat
     * @param dateStr
     * @return
     */
    public static String formatDate(String oldFormat, String newFormat, String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return "";
        }
        SimpleDateFormat fmt = new SimpleDateFormat(oldFormat, Locale.ENGLISH);
        Date date;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr;
        }

        SimpleDateFormat fmtOut = new SimpleDateFormat(newFormat, Locale.ENGLISH);
        return fmtOut.format(date);
    }

    public static String formatDate(String oldFormat, String newFormat, String dateStr, Locale oldLocale, Locale newLocale) {
        if (oldLocale == null) {
            return formatDate(oldFormat, newFormat, dateStr);
        }

        if (TextUtils.isEmpty(dateStr)) {
            return "";
        }
        SimpleDateFormat fmt = new SimpleDateFormat(oldFormat, oldLocale);
        Date date;
        try {
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr;
        }

        SimpleDateFormat fmtOut = new SimpleDateFormat(newFormat, newLocale);
        return fmtOut.format(date);
    }

    /**
     * Converts The date object to String
     *
     * @param date   The date object
     * @param format The format to display the date String
     */
    public static String convertDateToString(Date date, String format) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format, Locale.ENGLISH);
        return dateFormatter.format(date);
    }

    public static String convertDateToStringForServer(Date date, String format) {
        Locale localeObject = Locale.ENGLISH;
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format, localeObject);
        return dateFormatter.format(date);
    }

    /**
     * Converts the date String returned from server to Date object
     *
     * @param msJsonDateTime date String
     * @return Date Object
     */
    public static Date parseMsTimestampToDateWithFormat(String msJsonDateTime) {
        if (msJsonDateTime == null)
            return null;
        int startIndex = msJsonDateTime.indexOf("Date(") + 5;
        int endIndex = msJsonDateTime.indexOf(")");
        long ts;
        int timeIndex = 0;
        // int sign = 0;
        if (msJsonDateTime.indexOf("+") > 0) {
            // sign = 1;
            timeIndex = msJsonDateTime.indexOf("+");
        } else if (msJsonDateTime.indexOf("-") > 0) {
            // sign = -1;
            timeIndex = msJsonDateTime.indexOf("-");
        }
        if (timeIndex > 0) {

            ts = Long
                    .parseLong(msJsonDateTime.substring(startIndex, timeIndex));
        } else {
            ts = Long.parseLong(msJsonDateTime.substring(startIndex, endIndex));
        }
        return new Date(ts);
    }

    public static String serializeDateToMsTimestamp(final Date date) {
        long ticks = date.getTime();
        return "/Date(" + Long.toString(ticks) + ")/";
    }

    public static String timestampToDateString(long milliSeconds, String dateFormat) {
        try {
            // Create a DateFormatter object for displaying date in specified format.
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            return formatter.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Date convertStringToDate(String oldFormat, String dateStr, Locale oldLocale) {
        if (oldLocale == null) {
            oldLocale = Locale.ENGLISH;
        }

        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(oldFormat, oldLocale);
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isToday(Date date) {
        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.setTime(date);
        return Calendar.getInstance().get(Calendar.DATE) == otherCalendar.get(Calendar.DATE);
    }

    public static boolean isSameYear(Date date) {
        Calendar otherCalendar = Calendar.getInstance();
        otherCalendar.setTime(date);
        return Calendar.getInstance().get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR);
    }

}
