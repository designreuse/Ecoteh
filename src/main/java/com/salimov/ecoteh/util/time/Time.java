package com.salimov.ecoteh.util.time;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods for working
 * with time string in the format "00:00".
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class Time implements ITime {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(Time.class);

    /**
     * It is s an object for date/time formatting subclasses which formats
     * and parses dates or time in a language-independent manner.
     */
    private static final String DATE_PATTERN = "d MMM yyyy, HH:mm";

    /**
     * Represents a timezone offset, and also figures out daylight savings.
     */
    private static final String TIME_ZONE = "GMT+3";

    /**
     * The input time;
     */
    private final String time;

    /**
     * The value of input time is not blank.
     */
    private final boolean isNotBlankTime;

    /**
     * The correct time.
     */
    private String correctTime;

    /**
     * The number of hours in the input time.
     */
    private int hours;

    /**
     * The number of minutes in the input time.
     */
    private int minutes;

    /**
     * Constructor.
     *
     * @param time an input time.
     */
    public Time(final String time) {
        this.time = time;
        this.isNotBlankTime = isNotBlank(this.time);
    }

    /**
     * Returns an input time.
     *
     * @return The input time.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Returns correct time.
     * Returns time in format "00:00".
     * Returns "00:00" if method throws some exception.
     *
     * @return The correct time.
     */
    public String getCorrectTime() {
        if (isBlank(this.correctTime)) {
            initCorrectTime();
        }
        return this.correctTime;
    }

    /**
     * Returns a correct hours of the time.
     * If input time has than 0 hours,
     * then returns 0 hours. If hours greater
     * or equal 24 or hours less or equal then
     * sets hours = 0. If input time has more
     * than 60 minutes, then adds one hours.
     *
     * @return The value of a correct minutes.
     */
    public int getHours() {
        if (this.hours == 0) {
            initHours();
        }
        return this.hours;
    }

    /**
     * Returns a correct minutes of the time.
     * If input time has than 0 minutes,
     * then returns 0 minutes.
     * If minutes greater 60 then returns
     * minutes % 60.
     *
     * @return The value of a correct minutes.
     */
    public int getMinutes() {
        if (this.minutes == 0) {
            initMinutes();
        }
        return this.minutes;
    }

    /**
     * Returns the value of input time is not blank.
     *
     * @return true if the input time is not blank, false otherwise.
     */
    public boolean isNotBlankTime() {
        return this.isNotBlankTime;
    }

    /**
     * Initializes a correct time value from the time.
     */
    private void initCorrectTime() {
        if (isNotBlankTime()) {
            this.correctTime = createTime();
        } else {
            this.correctTime = "00:00";
        }
    }

    /**
     * Initializes a hours value from the time.
     */
    private void initHours() {
        if (isNotBlankTime()) {
            try {
                final String[] times = this.time.split(":");
                final int hours = Integer.parseInt(times[0]) + Integer.parseInt(times[1]) / 60;
                this.hours = (hours >= 24 || hours <= 0) ? 0 : hours;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Initializes a minutes value from the time.
     */
    private void initMinutes() {
        if (isNotBlankTime()) {
            try {
                final int minutes = Integer.parseInt(time.split(":")[1]);
                this.minutes = (minutes < 0) ? 0 : minutes % 60;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }

    /**
     * Creates and returns time in format "00:00".
     *
     * @return The time in format "00:00".
     */
    private String createTime() {
        final int hours = getHours();
        final int minutes = getMinutes();
        return getCorrectTime(hours) + ":" + getCorrectTime(minutes);
    }

    /**
     * Returns true if now time is the working time.
     *
     * @param startHour  a start work time of a company.
     * @param finishHour a finish work time of a company.
     * @return true if now time is the working time, false otherwise.
     */
    public static boolean isWorkTime(
            final String startHour,
            final String finishHour
    ) {
        return isWorkDay() && isWorkHour(startHour, finishHour);
    }

    /**
     * Returns true if today is the working day.
     *
     * @return true if today is the working day, false otherwise.
     */
    public static boolean isWorkDay() {
        boolean result = true;
        switch (getDayOfWeek()) {
        case Calendar.SUNDAY:
        case Calendar.SATURDAY:
            result = false;
        }
        return result;
    }

    /**
     * Checks whether the current time
     * is included in the interval.
     *
     * @param currentTime a time to checks.
     * @param startDate   a initial date.
     * @param finishDate  a final date.
     * @return true if time is correct, false otherwise.
     */
    public static boolean checkTime(
            final Date currentTime,
            final Date startDate,
            final Date finishDate
    ) {
        boolean result = false;
        if ((currentTime != null) && checkDate(startDate, finishDate)) {
            final long time = currentTime.getTime();
            result = (time >= startDate.getTime()) && (time <= finishDate.getTime());
        }
        return result;
    }

    /**
     * Checks on the correct date.
     *
     * @param startDate  a initial date.
     * @param finishDate a final date.
     * @return true if dates are correct, false otherwise.
     */
    public static boolean checkDate(
            final Date startDate,
            final Date finishDate
    ) {
        return (startDate != null) && (finishDate != null)
                && !startDate.equals(finishDate)
                && (startDate.getTime() <= finishDate.getTime());
    }

    /**
     * Returns a model date in string format.
     *
     * @param date a date to translate in string.
     * @return The model string-date.
     */
    public static String getDateToString(final Date date) {
        return getDateToStringWithFormat(
                date,
                new SimpleDateFormat(DATE_PATTERN),
                TimeZone.getTimeZone(TIME_ZONE)
        );
    }

    /**
     * Returns correct time (hours / minutes) in format "00".
     *
     * @param time a input time (hours / minutes).
     * @return The time (hours / minutes) in format "00:00".
     */
    private static String getCorrectTime(int time) {
        return "" + ((time < 10) ? "0" + time : time);
    }

    /**
     * Checks whether a now time belongs at a given time interval.
     *
     * @param start  a start work time.
     * @param finish a finish work time.
     * @return Returns true if a now time belongs at a given
     * time interval, false otherwise.
     */
    private static boolean isWorkHour(
            final String start,
            final String finish
    ) {
        boolean result;
        try {
            final int from = getHours(start);
            final int to = getHours(finish);
            final int now = getHourOfDay();
            result = checkHours(now, from, to);
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage(), ex);
            result = false;
        }
        return result;
    }

    /**
     * Returns a time hours.
     *
     * @param time a string time.
     * @return a hours.
     */
    private static int getHours(String time) {
        return new Time(time).getHours();
    }

    /**
     * Checks whether a now time belongs at a given time interval.
     *
     * @param now  a now time
     * @param from a start work time of a company.
     * @param to   a finish work time of a company.
     * @return Returns true if a now time belongs at a given
     * time interval, false otherwise.
     */
    private static boolean checkHours(final int now, final int from, final int to) {
        return (now >= from) && (now <= to);
    }

    /**
     * Returns a model date in string format with some date format.
     *
     * @param date       a date to translate in string.
     * @param dateFormat is an object for date/time formatting subclasses
     *                   which formats and parses dates or time in
     *                   a language-independent manner.
     * @param timeZone   represents a timezone offset,
     *                   and also figures out daylight savings.
     * @return The model string-date.
     */
    private static String getDateToStringWithFormat(
            final Date date,
            final DateFormat dateFormat,
            final TimeZone timeZone
    ) {
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date != null ? date : new Date());
    }

    /**
     * Returns the value of a hour of day.
     *
     * @return the value of a hour of day.
     */
    private static int getHourOfDay() {
        return getFromCalendar(Calendar.HOUR_OF_DAY);
    }

    /**
     * Returns the value of a day of week.
     *
     * @return the value of a day of week.
     */
    private static int getDayOfWeek() {
        return getFromCalendar(Calendar.DAY_OF_WEEK);
    }

    /**
     * Returns the value of the given calendar field.
     *
     * @param calendarFieldNumber a calendar field number.
     * @return The value for the given calendar field.
     */
    private static int getFromCalendar(final int calendarFieldNumber) {
        return Calendar.getInstance().get(calendarFieldNumber);
    }
}
