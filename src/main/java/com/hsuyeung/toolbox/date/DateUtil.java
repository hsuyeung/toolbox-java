package com.hsuyeung.toolbox.date;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期相关工具类
 *
 * @author hsuyeung
 * @date 2022/02/24
 */
public final class DateUtil {
    public static final String FORMAT_CONT_TO_SECOND = "yyyyMMddHHmmss";
    public static final String FORMAT_MINUTE_FORMAT = "yyyyMMddHHmm";
    public static final String FORMAT_HOUR_FORMAT = "yyyyMMddHH";
    public static final String FORMAT_DATE_FORMAT = "yyyyMMdd";
    public static final String FORMAT_MONTH_FORMAT = "yyyyMM";
    public static final String FORMAT_YEAR_TO_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_MM_DD = "MM-dd";

    private DateUtil() {
    }

    /**
     * 获取指定日期当天的开始时间，比如：2018-01-01 10:00:00 -> 2018-01-01 00:00:00
     *
     * @param date 指定日期
     * @return 指定日期当天的开始时间，类型为 {@link Date}
     */
    public static Date getStartOfDayDate(Date date) {
        LocalDateTime time = fromDateToJava8LocalDate(date);
        LocalDateTime startOfDay = LocalDateTime.of(time.toLocalDate(), LocalTime.MIN);
        return fromJava8LocalDateToDate(startOfDay);
    }

    /**
     * 获取指定日期当前整点时间，比如：2018-01-01 10:20:00 -> 2018-01-01 10:00:00
     *
     * @param date 指定日期
     * @return 指定日期当前整点时间，类型为 {@link Date}
     */
    public static Date getStartOfDayHour(Date date) {
        LocalDateTime time = fromDateToJava8LocalDate(date);
        LocalDateTime todayStart = LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), time.getHour(), 0);
        return fromJava8LocalDateToDate(todayStart);
    }

    /**
     * 获取指定日期当天的开始时间，比如：2018-01-01 10:00:00 -> 2018-01-01 00:00:00
     *
     * @param date 指定日期
     * @return 指定日期当天的开始时间，类型为 {@link LocalDateTime}
     */
    public static LocalDateTime getStartOfDayJava8LocalDateTime(Date date) {
        LocalDateTime time = fromDateToJava8LocalDate(date);
        return LocalDateTime.of(time.toLocalDate(), LocalTime.MIN);
    }

    /**
     * 获取指定日期当年的开始时间，比如：2018-12-25 10:00:00 -> 2018-01-01 00:00:00
     *
     * @param date 指定日期
     * @return 指定日期当年的开始时间，类型为 {@link LocalDateTime}
     */
    public static LocalDateTime getYearStartOfDayJava8LocalDateTime(Date date) {
        LocalDateTime time = fromDateToJava8LocalDate(date);
        return LocalDateTime.of(time.getYear(), 1, 1, 0, 0, 0);
    }

    /**
     * 将指定日期字符串按照指定格式转换为 {@link LocalDateTime} 对象
     *
     * @param content 指定日期字符串
     * @param pattern 指定格式
     * @return 对应的 {@link LocalDateTime} 对象
     */
    public static LocalDateTime parseToGetLocalDateTime(String content, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(content, formatter);
    }

    /**
     * 获取指定日期向后偏移 offset 天后的日期
     *
     * @param date   指定日期
     * @param offset 往后的偏移量
     * @return 偏移后的日期，类型为 {@link Date}
     */
    public static Date getDayOffset(Date date, int offset) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        return calendar.getTime();
    }

    /**
     * 获取指定日期当天的结束时间，比如：2018-01-01 10:00:00 -> 2018-01-01 23:59:59
     *
     * @param date 指定日期
     * @return 指定日期当天的结束时间，类型为 {@link Date}
     */
    public static Date getEndOfDayDate(Date date) {
        LocalDateTime time = fromDateToJava8LocalDate(date);
        LocalDateTime todayEnd = LocalDateTime.of(time.toLocalDate(), LocalTime.MAX);
        return fromJava8LocalDateToDate(todayEnd);
    }

    /**
     * 获取指定日期当天的结束时间，比如：2018-01-01 10:00:00 -> 2018-01-01 23:59:59
     *
     * @param date 指定日期
     * @return 指定日期当天的结束时间，类型为 {@link LocalDateTime}
     */
    public static LocalDateTime getEndOfDayDateJava8LocalDateTime(Date date) {
        LocalDateTime time = fromDateToJava8LocalDate(date);
        return LocalDateTime.of(time.toLocalDate(), LocalTime.MAX);
    }

    /**
     * 将指定 {@link LocalDateTime} 类型日期格式化为指定格式的字符串
     *
     * @param localDateTime 指定日期
     * @param pattern       格式
     * @return 格式化后的日期字符串
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * 将系统本地时区的指定日期转换为 UTC 时区日期
     *
     * @param systemDate 系统本地时区的指定日期
     * @return UTC 时区的日期，类型为 {@link Date}
     */
    public static Date systemDateToUtc(Date systemDate) {
        return fromJava8LocalDateToDate(systemLocalDateTimeToUtc(fromDateToJava8LocalDate(systemDate)));
    }

    /**
     * 将 UTC 时区时间转为系统本地时区时间
     *
     * @param utc UTC 时区时间
     * @return 系统本地时区时间，类型为 {@link Date}
     */
    public static Date utcToSystemDate(Date utc) {
        return fromJava8LocalDateToDate(utcToSystemLocalDateTime(fromDateToJava8LocalDate(utc)));
    }

    /**
     * 将系统本地时区的指定日期转换为 UTC 时区日期
     *
     * @param systemLocalDateTime 系统本地时区的指定日期
     * @return UTC 时区的日期，类型为 {@link LocalDateTime}
     */
    public static LocalDateTime systemLocalDateTimeToUtc(LocalDateTime systemLocalDateTime) {
        return systemLocalDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }

    /**
     * 将 UTC 时区时间转为系统本地时区时间
     *
     * @param utcLocalDateTime UTC 时区时间
     * @return 系统本地时区时间，类型为 {@link LocalDateTime}
     */
    public static LocalDateTime utcToSystemLocalDateTime(LocalDateTime utcLocalDateTime) {
        return utcLocalDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 将 {@link Date} 类型日期按照指定格式格式化为日期字符串
     *
     * @param date   指定日期
     * @param format 格式化字符串
     * @return 格式化后的日期字符串
     */
    public static String getFormattedDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 将 {@link LocalDateTime} 类型转为 {@link Date} 类型对象
     *
     * @param localDateTime {@link LocalDateTime}
     * @return {@link Date}
     */
    public static Date fromJava8LocalDateToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将毫秒级时间戳转为 {@link LocalDateTime} 对象
     *
     * @param time 毫秒级时间戳
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime fromLongToJava8LocalDate(long time) {
        return Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 将 {@link Date} 对象转为 {@link LocalDateTime} 对象
     *
     * @param date {@link Date}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime fromDateToJava8LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 获取指定 {@link LocalDateTime} 时间的毫秒级时间戳
     *
     * @param localDateTime {@link LocalDateTime}
     * @return 毫秒级时间戳
     */
    public static long getTimeMills(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 计算两个 {@link Date} 相差的天数
     *
     * @param from 开始时间
     * @param to   结束时间
     * @return 相差天数
     */
    public static long getDiffDays(Date from, Date to) {
        LocalDateTime fromTime = fromDateToJava8LocalDate(from);
        LocalDateTime toTime = fromDateToJava8LocalDate(to);
        return fromTime.until(toTime, ChronoUnit.DAYS);
    }

    /**
     * 计算两个 {@link LocalDateTime} 相差的天数
     *
     * @param from 开始时间
     * @param to   结束时间
     * @return 相差天数
     */
    public static long getDiffDays(LocalDateTime from, LocalDateTime to) {
        return from.until(to, ChronoUnit.DAYS);
    }
}
