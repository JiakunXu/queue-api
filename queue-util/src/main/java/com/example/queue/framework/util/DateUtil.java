package com.example.queue.framework.util;

import java.util.Date;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

/**
 * @author JiakunXu
 */
public class DateUtil {

    private static final String DEFAULT_DATE_FORMAT     = "yyyy-MM-dd";

    private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获得"yyyy-MM-dd"格式的当前日期字符串.
     *
     * @return
     */
    public static String getNowDate() {
        return getNowDateTime(DEFAULT_DATE_FORMAT);
    }

    /**
     * 获得"yyyy-MM-dd HH:mm:ss"格式的当前日期时间字符串.
     *
     * @return
     */
    public static String getNowDateTime() {
        return getNowDateTime(DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 获得当前日期时间字符串.
     *
     * @param pattern 日期格式
     * @return 日期时间字符串
     */
    public static String getNowDateTime(String pattern) {
        return DateTime.now().toString(pattern);
    }

    /**
     * 获得"yyyy-MM-dd"格式的日期字符串.
     *
     * @param date 日期时间
     * @return
     */
    public static String getDate(Date date) {
        return getDateTime(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 获得"yyyy-MM-dd HH:mm:ss"格式的日期时间字符串.
     *
     * @param date 日期时间
     * @return
     */
    public static String getDateTime(Date date) {
        return getDateTime(date, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 获得日期时间字符串.
     *
     * @param date 日期时间
     * @param pattern 日期格式
     * @return 日期时间字符串
     */
    public static String getDateTime(Date date, String pattern) {
        return new DateTime(date).toString(pattern);
    }

    /**
     * 获得"yyyy-MM-dd"格式的日期.
     *
     * @param date 日期时间
     * @return
     */
    public static Date getDate(String date) {
        return getDateTime(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 获得"yyyy-MM-dd HH:mm:ss"格式的日期时间.
     *
     * @param date 日期时间
     * @return
     */
    public static Date getDateTime(String date) {
        return getDateTime(date, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 获得日期时间.
     *
     * @param date 日期时间
     * @param pattern 日期格式
     * @return 日期时间字符串
     */
    public static Date getDateTime(String date, String pattern) {
        return DateTime.parse(date, DateTimeFormat.forPattern(pattern)).toDate();
    }

    /**
     * 获取当前年.
     *
     * @return
     */
    public static int getYear() {
        return DateTime.now().getYear();
    }

    /**
     * 获取当前月.
     *
     * @return
     */
    public static int getMonth() {
        return DateTime.now().getMonthOfYear();
    }

    /**
     * 获取当前周.
     *
     * @return
     */
    public static int getWeek() {
        return DateTime.now().getWeekOfWeekyear();
    }

    /**
     * 获取当前日.
     *
     * @return
     */
    public static int getDayOfMonth() {
        return DateTime.now().getDayOfMonth();
    }

    /**
     * 获取当前日.
     *
     * @return
     */
    public static int getDayOfWeek() {
        return DateTime.now().getDayOfWeek();
    }

    /**
     * 获取两个日期相差的天数.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDaysBetween(Date startDate, Date endDate) {
        return Days.daysBetween(new DateTime(startDate), new DateTime(endDate)).getDays();
    }

    /**
     * 获取两个日期相差的小时.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getHoursBetween(Date startDate, Date endDate) {
        return Hours.hoursBetween(new DateTime(startDate), new DateTime(endDate)).getHours();
    }

    /**
     * 获取两个日期相差的分钟.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getMinutesBetween(Date startDate, Date endDate) {
        return Minutes.minutesBetween(new DateTime(startDate), new DateTime(endDate)).getMinutes();
    }

    /**
     * 获取两个日期相差的秒.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getSecondsBetween(Date startDate, Date endDate) {
        return Seconds.secondsBetween(new DateTime(startDate), new DateTime(endDate)).getSeconds();
    }

    /**
     * 获取日期时间加上days天.
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        return new DateTime(date).dayOfMonth().addToCopy(days).toDate();
    }

    /**
     * 获取日期时间加上hours小时.
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, int hours) {
        return new DateTime(date).hourOfDay().addToCopy(hours).toDate();
    }

    /**
     * 获取日期时间加上minutes分钟.
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, int minutes) {
        return new DateTime(date).minuteOfHour().addToCopy(minutes).toDate();
    }

    /**
     * 获取日期时间加上seconds秒.
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date addSeconds(Date date, int seconds) {
        return new DateTime(date).secondOfMinute().addToCopy(seconds).toDate();
    }

}
