package ua.com.ecoteh.util.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods for working
 * with time string in the format "00:00".
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Time implements ITime {

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
     * Constructor.
     * If an incoming time is null or empty then sets "00:00".
     *
     * @param time the time in string format.
     */
    public Time(final String time) {
        this.time = isNotEmpty(time) ? time : "00:00";
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
        final int hours = getHours();
        final int minutes = getMinutes();
        return getCorrectTime(hours) + ":" + getCorrectTime(minutes);
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
        int hours;
        try {
            final String[] times = this.time.split(":");
            hours = Integer.parseInt(times[0]) + Integer.parseInt(times[1]) / 60;
            hours = (hours >= 24 || hours <= 0) ? 0 : hours;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            hours = 0;
        }
        return hours;
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
        int minutes;
        try {
            minutes = Integer.parseInt(this.time.split(":")[1]);
            minutes = (minutes < 0) ? 0 : minutes % 60;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            minutes = 0;
        }
        return minutes;
    }

    /**
     * Returns true if now time is the working time.
     *
     * @param start  the start work time of a company.
     * @param finish the finish work time of a company.
     * @return true if now time is the working time, false otherwise.
     */
    public static boolean isWorkTime(final String start, final String finish) {
        return isWorkDay() && checkWorkTime(start, finish);
    }

    /**
     * Returns true if today is the working day.
     * Sunday and Saturday are not working day,
     * another are working day.
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
     * <pre>
     *     checkTime(null, ..., ...) = false
     *     checkTime(new Date(), null, null) = false
     *     checkTime(new Date(), new Date(), null) = false
     *     checkTime(new Date(), null, new Date()) = false
     *
     *     Date date1 = new Date();
     *     checkTime(new Date(), date1, date1) = false
     *
     *     Date date2 = new Date();
     *     checkTime(new Date(), date2, date1) = false
     *     checkTime(new Date(), date1, date2) = true
     * </pre>
     *
     * @param current the time to checks.
     * @param start   the initial date.
     * @param finish  the final date.
     * @return true if time is correct, false otherwise.
     */
    public static boolean checkTime(final Date current, final Date start, final Date finish) {
        boolean result = false;
        if (isNotNull(current) && checkDate(start, finish)) {
            final long time = current.getTime();
            result = (time >= start.getTime()) && (time <= finish.getTime());
        }
        return result;
    }

    /**
     * Checks on the correct date.
     * <pre>
     *     checkDate(null, null) = false
     *     checkDate(new Date(), null) = false
     *     checkDate(null, new Date()) = false
     *
     *     Date date1 = new Date();
     *     checkDate(date1, date1) = false
     *
     *     Date date2 = new Date();
     *     checkDate(date2, date1) = false
     *     checkDate(date1, date2) = true
     * </pre>
     *
     * @param start  the initial date.
     * @param finish the final date.
     * @return true if dates are correct, false otherwise.
     */
    public static boolean checkDate(final Date start, final Date finish) {
        return isNotNull(start) && isNotNull(finish) && !start.equals(finish) &&
                (start.getTime() <= finish.getTime());
    }

    /**
     * Returns a date in string format.
     *
     * @return he date in string format.
     */
    public static String getDate() {
        return getDate(new Date());
    }

    /**
     * Returns a date in string format.
     *
     * @param date the date to translate in string.
     * @return The date in string format.
     */
    public static String getDate(final Date date) {
        final TimeZone timeZone = getDefaultTimeZone();
        return getDate(date, timeZone);
    }

    /**
     * Returns a model date in string format with some date format.
     * <pre>
     *     getDate(null, dateFormat, timeZone) = dateFormat.format(new Date())
     *     getDate(date, dateFormat, timeZone) = dateFormat.format(date)
     * </pre>
     *
     * @param date     the date to translate in string.
     * @param timeZone the represents a timezone offset,
     *                 and also figures out daylight savings (newer null).
     * @return The model string-date (newer null).
     */
    public static String getDate(final Date date, final TimeZone timeZone) {
        final DateFormat format = getDefaultDateFormat();
        return getDate(date, format, timeZone);
    }

    /**
     * Returns a model date in string format with some date format.
     * <pre>
     *     getDate(null, dateFormat, timeZone) = dateFormat.format(new Date())
     *     getDate(date, dateFormat, timeZone) = dateFormat.format(date)
     * </pre>
     *
     * @param date     the date to translate in string.
     * @param format   the object for date/time formatting subclasses
     *                 which formats and parses dates or time in
     *                 a language-independent manner (newer null).
     * @param timeZone the represents a timezone offset,
     *                 and also figures out daylight savings (newer null).
     * @return The model string-date (newer null).
     */
    public static String getDate(final Date date, final DateFormat format, final TimeZone timeZone) {
        final Date _date = isNotNull(date) ? date : new Date();
        format.setTimeZone(timeZone);
        return format.format(_date);
    }

    /**
     * Returns correct time (hours / minutes) in format "00".
     * <pre>
     *     getCorrectTime(0) = "00"
     *     getCorrectTime(1) = "01"
     *     getCorrectTime(12) = "12"
     * </pre>
     *
     * @param time the input time (hours / minutes).
     * @return The time (hours / minutes) in format "00" (newer null).
     */
    private static String getCorrectTime(final int time) {
        return (time < 10) ? "0" + time : "" + time;
    }

    /**
     * Checks whether a now time belongs at a given time interval.
     *
     * @param start  the start work time.
     * @param finish the finish work time.
     * @return Returns true if a now time belongs at a given
     * time interval, false otherwise.
     */
    private static boolean checkWorkTime(final String start, final String finish) {
        boolean result;
        try {
            final Time from = new Time(start);
            final Time to = new Time(finish);
            result = checkHours(getHourOfDay(), from.getHours(), to.getHours());
            if (getHourOfDay() == to.getHours()) {
                result = getMinutesOfDay() < to.getMinutes();
            }
        } catch (NumberFormatException ex) {
            result = false;
        }
        return result;
    }

    /**
     * Checks whether a now time belongs at a given time interval.
     * <pre>
     *     checkHours(1, 3, 5) = false
     *     checkHours(1, 5, 3) = false
     *     checkHours(6, 3, 5) = false
     *     checkHours(3, 1, 5) = true
     * </pre>
     *
     * @param now  the now time
     * @param from the start work time of a company.
     * @param to   the finish work time of a company.
     * @return Returns true if a now time belongs at a given
     * time interval, false otherwise.
     */
    private static boolean checkHours(final int now, final int from, final int to) {
        return (to >= from) && (now >= from) && (to > now);
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
     * Returns the value of a minutes of day.
     *
     * @return the value of a minutes of day.
     */
    private static int getMinutesOfDay() {
        return getFromCalendar(Calendar.MINUTE);
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
     * @param calendarFieldNumber the calendar field number.
     * @return The value for the given calendar field.
     */
    private static int getFromCalendar(final int calendarFieldNumber) {
        final TimeZone timeZone = getDefaultTimeZone();
        final Calendar calendar = Calendar.getInstance(timeZone);
        return calendar.get(calendarFieldNumber);
    }

    /**
     * Returns a default time zone.
     *
     * @return The default time zone.
     */
    private static TimeZone getDefaultTimeZone() {
        return TimeZone.getTimeZone(TIME_ZONE);
    }

    /**
     * Returns a default date format.
     *
     * @return The default date format.
     */
    private static DateFormat getDefaultDateFormat() {
        return new SimpleDateFormat(DATE_PATTERN);
    }
}
