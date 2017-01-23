package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Model;

import java.util.Collection;

/**
 * The interface provides a set of standard methods for working models objects
 * of the {@link Model} class or subclasses with the database.
 *
 * @param <T>  Entity type, extends {@link Model}.
 * @param <E> Entity id type, extends Number.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see ArticleDao
 * @see CategoryDao
 * @see CompanyDao
 * @see FileDao
 * @see ResponseDao
 * @see UserDao
 * @see MessageDao
 * @see Model
 */
public interface DataDao<T extends Model<E>, E extends Number> {

    /**
     * Saves in the database and returns model object
     * of the {@link Model} class or subclasses.
     *
     * @param model a model to add.
     * @return The saving model.
     * @see Model
     */
    T add(final T model);

    /**
     * Saves in the database and returns model objects
     * of the {@link Model} class or subclasses.
     *
     * @param models a model objects to add.
     * @return The saving model objects.
     * @see Model
     */
    Collection<T> addAll(final Collection<T> models);

    /**
     * Updates in the database and returns model object
     * of the {@link Model} class or subclasses.
     *
     * @param model a model object to update.
     * @return The updating model.
     * @see Model
     */
    T update(final T model);

    /**
     * Returns model object of the {@link Model} class
     * or subclasses with parameter id from the database.
     *
     * @param id a id of the model object to return.
     * @return The model object with parameter id.
     * @see Model
     */
    T get(final E id);

    /**
     * Returns all model objects of the {@link Model} class
     * or subclasses from the database.
     *
     * @return The all model objects.
     * @see Model
     */
    Collection<T> getAll();

    /**
     * Removes model object of the {@link Model} class
     * or subclasses with parameter id in the database.
     *
     * @param id a id model object to remove.
     * @see Model
     */
    void remove(final E id);

    /**
     * Removes model object of the {@link Model} class
     * or subclasses in the database.
     *
     * @param model The model object to remove.
     * @see Model
     */
    void remove(final T model);

    /**
     * Removes model objects of the {@link Model} class
     * or subclasses in the database.
     *
     * @param models The model objects to remove.
     * @see Model
     */
    void remove(final Collection<T> models);

    /**
     * Removes all model objects of the {@link Model} class
     * or subclasses in the database.
     *
     * @see Model
     */
    void removeAll();

    /**
     * Checks whether the model object of the {@link Model} class
     * or subclasses with parameter id is exists in the database.
     *
     * @param id a id model object to exists.
     * @return {@code true} if model object is exists, {@code false} otherwise.
     * @see Model
     */
    boolean exists(final E id);
}
