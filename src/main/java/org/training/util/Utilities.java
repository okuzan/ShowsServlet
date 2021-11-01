package org.training.util;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class Utilities {
    public static final int RECORDS_PER_PAGE = 5;
    private final static String FORMAT = "EEE MMM d yyyy HH:mm:ss z";
    public static LocalDateTime timestampToLocalDateTime(Timestamp stamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(stamp.getTime()), TimeZone.getDefault().toZoneId());
    }

    public static ResourceBundle getBundle(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        return lang == null
                ? ResourceBundle.getBundle("messages")
                : ResourceBundle.getBundle("messages", Locale.forLanguageTag(lang));
    }

    public static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        input = input.substring(0, input.length() - 2) + ":" + input.substring(input.length() - 2);
        return LocalDateTime.parse(input, formatter);
    }

    public static Timestamp stringToStamp(String input) {
        return Timestamp.valueOf(parseDateTime(input));
    }
    public static Timestamp ldtToStamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

}
