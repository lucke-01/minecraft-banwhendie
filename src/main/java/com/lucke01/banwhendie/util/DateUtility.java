package com.lucke01.banwhendie.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtility {
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static String formatDate(Date date) {
        return formatDate(date,DEFAULT_DATETIME_FORMAT);
    }
    public static String formatDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
    
    public static String formatLocalDateTime(LocalDateTime dateTime) {
        return formatLocalDateTime(dateTime, DEFAULT_DATETIME_FORMAT);
    }
    public static String formatLocalDateTime(LocalDateTime dateTime, String format) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }
    public static LocalDateTime getLocalDateTimeFromString(String value) {
        return getLocalDateTimeFromString(value,DEFAULT_DATETIME_FORMAT);
    }
    public static LocalDateTime getLocalDateTimeFromString(String value, String format) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(value, dtf);
    }
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        LocalDateTime localDateTime = Instant
            .ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
        return localDateTime;
    }
}