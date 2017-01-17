package com.salimov.yurii.util.worktime;

import java.util.Calendar;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods for working
 * with time string in the format "00:00".
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see ITime
 */
public class Time implements ITime {
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
     * @return {@code true} if the input time is not blank,
     * {@code false} otherwise.
     */
    public boolean isNotBlankTime() {
        return this.isNotBlankTime;
    }

    /**
     * Initializes a correct time value from the time.
     */
    private void initCorrectTime() {
        if (isNotBlankTime()) {
            try {
                this.correctTime = createTime();
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                this.correctTime = "00:00";
            }
        } else {
            this.correctTime = "00:00";
        }
    }

    /**
     * Initializes a hours value from the time.
     */
    private void initHours() {
        if (isNotBlankTime()) {
            final String[] times = this.time.split(":");
            final int hours = Integer.parseInt(times[0]) + Integer.parseInt(times[1]) / 60;
            this.hours = (hours >= 24 || hours <= 0) ? 0 : hours;
        }
    }

    /**
     * Initializes a minutes value from the time.
     */
    private void initMinutes() {
        if (isNotBlankTime()) {
            final int minutes = Integer.parseInt(
                    time.split(":")[1]
            );
            this.minutes = minutes < 0 ? 0 : minutes % 60;
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
        return (hours < 10 ? "0" + hours : hours) +
                ":" + (minutes < 10 ? "0" + minutes : minutes);
    }

    /**
     * Returns {@code true} if now time is the working time.
     *
     * @param start  a start work time of a company.
     * @param finish a finish work time of a company.
     * @return {@code true} if now time is the working time,
     * {@code false} otherwise.
     */
    public static boolean isWorkTime(
            final String start,
            final String finish
    ) {
        return isWorkDay() && isWorkHour(start, finish);
    }

    /**
     * Checks whether a now time belongs at a given time interval.
     *
     * @param start  a start work time of a company.
     * @param finish a finish work time of a company.
     * @return Returns {@code true} if a now time belongs at a given
     * time interval, {@code false} otherwise.
     */
    private static boolean isWorkHour(
            final String start,
            final String finish
    ) {
        boolean result;
        try {
            final int from = new Time(start).getHours();
            final int to = new Time(finish).getHours();
            final int hourNow = getHourOfDay();
            result = hourNow >= from && hourNow < to;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * Returns {@code true} if today is the working day.
     *
     * @return {@code true} if today is the working day,
     * {@code false} otherwise.
     */
    private static boolean isWorkDay() {
        boolean result = true;
        switch (getDayOfWeek()) {
            case Calendar.SUNDAY:
            case Calendar.SATURDAY:
                result = false;
        }
        return result;
    }

    /**
     * Returns the value of a hour of day.
     *
     * @return the value of a hour of day.
     */
    private static int getHourOfDay() {
        return getFromCalendar(
                Calendar.HOUR_OF_DAY
        );
    }

    /**
     * Returns the value of a day of week.
     *
     * @return the value of a day of week.
     */
    private static int getDayOfWeek() {
        return getFromCalendar(
                Calendar.DAY_OF_WEEK
        );
    }

    /**
     * Returns the value of the given calendar field.
     *
     * @return The value for the given calendar field.
     */
    private static int getFromCalendar(final int calendarFieldNumber) {
        return Calendar.getInstance().get(calendarFieldNumber);
    }
}
