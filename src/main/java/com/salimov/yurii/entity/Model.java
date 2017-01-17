package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.IModel;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/**
 * The abstract superclass class implements a set of standard methods
 * for working with entity of the {@link Model} class or subclasses.
 *
 * @param <E> Model id type, extends Number.
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Media
 * @see Content
 * @see Article
 * @see Category
 * @see Company
 * @see File
 * @see Response
 * @see User
 * @see Message
 */
@MappedSuperclass
public abstract class Model<E extends Number>
        implements IModel<E>, Serializable, Cloneable {

    /**
     * Default length of new generating string,
     */
    public static final int CODE_LENGTH = 6;

    /**
     * Default pattern to generated new string.
     */
    public static final char[] CODE_PATTERN =
            "ECOTEHANDTERMODRUCK1234567890".toCharArray();

    /**
     * It is s an object for date/time formatting subclasses which formats
     * and parses dates or time in a language-independent manner.
     */
    public static final String DATE_PATTERN = "d MMM yyyy, HH:mm";

    /**
     * Represents a timezone offset, and also figures out daylight savings.
     */
    public static final String TIME_ZONE = "GMT+3";

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for each object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false
    )
    private E id;

    /**
     * The value of validations of the model.
     */
    @Column(name = "validated")
    private boolean validated;

    /**
     * Default constructor.
     */
    public Model() {
        this.validated = true;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Model clone() {
        Model model = null;
        try {
            model = (Model) super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return model;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this object is the same as the obj
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(final Object object) {
        return (
                object != null
        ) && ((
                super.equals(object)
        ) || (
                getClass() == object.getClass()
        ));
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public abstract int hashCode();

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public abstract String toString();

    /**
     * Returns a unique identifier of the model.
     *
     * @return The unique identifier.
     */
    @Override
    public E getId() {
        return this.id;
    }

    /**
     * Sets new identifier to the model.
     *
     * @param id a new identifier to the model.
     */
    @Override
    public void setId(final E id) {
        this.id = id;
    }

    /**
     * Sets validations of the model.
     * Sets {@code true} if the model is valid,
     * {@code false} is invalid.
     *
     * @param validated a validations of the model.
     */
    @Override
    public void setValidated(final boolean validated) {
        this.validated = validated;
    }

    /**
     * Validates the model.
     *
     * @return {@code true} if the model is valid,
     * {@code false} otherwise.
     */
    @Override
    public boolean isValidated() {
        return this.validated;
    }

    /**
     * Statically validates the model.
     * Model is valid if it is not {@code null}.
     *
     * @param model a model to validate.
     * @return {@code true} if the model is valid,
     * {@code false} otherwise.
     */
    public static boolean isValidated(final Model model) {
        return model != null;
    }

    /**
     * Creates a random string of predetermined pattern and length.
     *
     * @param pattern a pattern of the random string.
     * @param length  a length of the random string.
     * @return The random string.
     */
    public static String createRandomString(
            final char[] pattern,
            final int length
    ) {
        final StringBuilder sb = new StringBuilder();
        if ((
                pattern != null
        ) && (
                pattern.length != 0
        ) && (
                length > 0
        )) {
            final Random random = new Random(System.nanoTime());
            for (int i = 0; i < length; i++) {
                sb.append(
                        pattern[random.nextInt(pattern.length)]
                );
            }
        }
        return sb.toString();
    }

    /**
     * Creates a random number of predetermined length.
     *
     * @param length a length of the random number.
     * @return The random number.
     */
    public static int createRandomNumber(int length) {
        int number = 0;
        if (length > 0) {
            final Random random = new Random(System.nanoTime());
            for (int i = 0; i < length; i++) {
                number += (random.nextInt(8) + 1) * (int) Math.pow(10, i);
            }
        }
        return number;
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
        return dateFormat.format(
                date != null ? date : new Date()
        );
    }
}
