package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Model;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of {@link Model} class or subclasses.
 *
 * @param <T> Entity type, extends {@link Model}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Model
 * @see ArticleService
 * @see CategoryService
 * @see ContentService
 * @see FileService
 * @see ResponseService
 * @see UserService
 */
public interface DataService<T extends Model> {

    /**
     * Saves and returns model object of {@link Model} class or subclasses.
     *
     * @param model the model to add.
     * @return The saving model.
     * @see Model
     */
    T add(final T model);

    /**
     * Saves and returns objects of {@link Model} class or subclasses.
     *
     * @param models the models to add.
     * @return The saving models.
     * @see Model
     */
    Collection<T> addAll(
            final Collection<T> models
    );

    /**
     * Updates and returns object of {@link Model} class or subclasses.
     *
     * @param model a model to update.
     * @return The updating models.
     * @see Model
     */
    T update(final T model);

    /**
     * Updates and returns objects of {@link Model} class or subclasses.
     *
     * @param models the models to update.
     * @return The updating models.
     * @see Model
     */
    Collection<T> update(final Collection<T> models);

    /**
     * Returns object of {@link Model} class or subclasses with parameter id.
     *
     * @param id is id of object to return.
     * @return The object models with parameter id.
     * @see Model
     */
    T get(final long id);

    /**
     * Returns all valid objects of {@link Model} class or subclasses.
     *
     * @return The all valid models.
     * @see Model
     */
    Collection<T> getAll();

    /**
     * Returns all or valid objects of {@link Model} class or subclasses
     * depending on the parameter value.
     *
     * @param valid It is returns all or valid objects.
     * @return The all models.
     * @see Model
     */
    Collection<T> getAll(final boolean valid);

    /**
     * Removes object of {@link Model} class or subclasses with parameter id.
     *
     * @param id a id of the model to remove.
     * @see Model
     */
    void remove(final long id);

    /**
     * Removes object of {@link Model} class or subclasses.
     *
     * @param model the model to remove.
     * @see Model
     */
    void remove(final T model);

    /**
     * Removes objects of {@link Model} class or subclasses.
     *
     * @param models the models to remove.
     * @see Model
     */
    void remove(final Collection<T> models);

    /**
     * Removes all objects of {@link Model} class or subclasses.
     *
     * @see Model
     */
    void removeAll();

    /**
     * Checks whether the object of {@link Model} class or subclasses
     * is exists with parameter id.
     *
     * @param id a id of the model to exist.
     * @return Returns true if model is exists, otherwise returns false.
     * @see Model
     */
    boolean exists(final long id);

    /**
     * Checks whether the object of {@link Model} class or subclasses is exists.
     *
     * @param model the model to exists.
     * @return Returns <code>true</code> if model is exists,
     * otherwise returns <code>false</code>.
     * @see Model
     */
    boolean exists(final T model);

    /**
     * Sorts and returns objects of {@link Model} class or subclasses
     * by the comparator.
     *
     * @param models     the models to sort.
     * @param comparator a comparator to sort models.
     * @param revers     Sort in descending or ascending.
     * @return The sorted list of models.
     * @see Model
     */
    List<T> sort(
            final Collection<T> models,
            final Comparator<T> comparator,
            final boolean revers
    );

    /**
     * Sorts and returns objects of {@link Model} class or subclasses
     * by the comparator.
     *
     * @param models     the objects to sort.
     * @param comparator a comparator to sort models.
     * @return The sorted list of models.
     * @see Model
     */
    List<T> sort(
            final Collection<T> models,
            final Comparator<T> comparator
    );

    /**
     * Returns a list with the incoming list objects of {@link Model} class
     * or subclasses.
     *
     * @param models    the models to be processed.
     * @param fromIndex a initial index.
     * @param toIndex   a final index.
     * @return The models from initial index to final index.
     * @see Model
     */
    List<T> subList(
            final Collection<T> models,
            final int fromIndex,
            final int toIndex
    );

    /**
     * Returns a list objects of {@link Model} class or subclasses.
     *
     * @param fromIndex a initial index.
     * @param toIndex   a final index.
     * @return The models from initial index to final index.
     * @see Model
     */
    List<T> getAndSubList(final int fromIndex, final int toIndex);

    /**
     * Returns a list valid objects of {@link Model} class or subclasses.
     *
     * @param models the models to filter.
     * @return The filtered models.
     * @see Model
     */
    List<T> filteredByValid(final Collection<T> models);

    List<T> shuffle(final Collection<T> models);
}
