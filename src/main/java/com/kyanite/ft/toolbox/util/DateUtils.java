package com.kyanite.ft.toolbox.util;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Alex on 2017/4/14.
 */
public class DateUtils {

    public static Instant stringToInstant(String dateStr, String pattern){
        if(dateStr==null)return null;
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, pattern).toInstant();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ZonedDateTime toDate(String dateStr){
        if(dateStr==null)return null;
        try {
            return ZonedDateTime.ofInstant(org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, "yyyy-MM-dd").toInstant(), ZoneId.systemDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ZonedDateTime toDatetime(String dateStr){
        if(dateStr==null)return null;
        try {
            return ZonedDateTime.ofInstant(org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, "yyyy-MM-dd hh:mm:ss").toInstant(), ZoneId.systemDefault());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ZonedDateTime getZonedDateTimeFromTimestamp(Long timestamp){
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
    }

    public static ZonedDateTime dateToZonedDate(Date date){
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }




    public static String toString(ZonedDateTime date){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String toStringOfPattern(Instant date, String pattern){
        return DateTimeFormatter.ofPattern(pattern).format(ZonedDateTime.ofInstant(date, ZoneId.systemDefault()));
    }

    public static Long toLong(LocalDateTime date){
        return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String toMsgDateStringForEnglish(ZonedDateTime date){
        return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(date);
    }
    public static String toMonthAndDay(ZonedDateTime date){
        return date.getMonthValue()+"."+date.getDayOfMonth();
    }

    public static Instant jodaToInstant(org.joda.time.Instant jodaInstant){
        return Instant.ofEpochMilli( jodaInstant.getMillis() ) ;
    }

    public static org.joda.time.Instant instantToJoda(Instant instant){
        return org.joda.time.Instant.ofEpochMilli(instant.toEpochMilli());
    }
}
