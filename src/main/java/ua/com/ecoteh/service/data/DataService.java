package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Model;

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
 */
public interface DataService<T extends Model> {

    /**
     * Saves and returns model object of {@link Model} class or subclasses.
     *
     * @param model the model to add.
     * @return The saving model.
     */
    T add(final T model);

    /**
     * Saves and returns objects of {@link Model} class or subclasses.
     *
     * @param models the models to add.
     * @return The saving models.
     */
    Collection<T> addAll(final Collection<T> models);

    /**
     * Updates and returns object of {@link Model} class or subclasses.
     *
     * @param model a model to update.
     * @return The updating models.
     */
    T update(final T model);

    /**
     * Updates and returns objects of {@link Model} class or subclasses.
     *
     * @param models the models to update.
     * @return The updating models.
     */
    Collection<T> update(final Collection<T> models);

    /**
     * Returns object of {@link Model} class or subclasses with parameter id.
     *
     * @param id is id of object to return.
     * @return The object models with parameter id.
     */
    T get(final long id);

    /**
     * Returns all valid objects of {@link Model} class or subclasses.
     *
     * @return The all valid models.
     */
    Collection<T> getAll();

    /**
     * Returns all or valid objects of {@link Model} class or subclasses
     * depending on the parameter value.
     *
     * @param valid It is returns all or valid objects.
     * @return The all models.
     */
    Collection<T> getAll(final boolean valid);

    /**
     * Removes object of {@link Model} class or subclasses with parameter id.
     *
     * @param id a id of the model to remove.
     */
    void remove(final long id);

    /**
     * Removes object of {@link Model} class or subclasses.
     *
     * @param model the model to remove.
     */
    void remove(final T model);

    /**
     * Removes objects of {@link Model} class or subclasses.
     *
     * @param models the models to remove.
     */
    void remove(final Collection<T> models);

    /**
     * Removes all objects of {@link Model} class or subclasses.
     */
    void removeAll();

    /**
     * Checks whether the object of {@link Model} class or subclasses
     * is exists with parameter id.
     *
     * @param id a id of the model to exist.
     * @return Returns true if model is exists, otherwise returns false.
     */
    boolean exists(final long id);

    /**
     * Checks whether the object of {@link Model} class
     * or subclasses is exists.
     *
     * @param model the model to exists.
     * @return Returns true if model is exists, otherwise returns false.
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
     */
    List<T> getAndSubList(final int fromIndex, final int toIndex);

    /**
     * Returns a list valid objects of {@link Model} class or subclasses.
     *
     * @param models the models to filter.
     * @return The filtered models.
     */
    List<T> filteredByValid(final Collection<T> models);

    /**
     * Shuffles the models and returns it.
     *
     * @param models a models to shuffle.
     * @return The shuffling models.
     */
    List<T> shuffle(final Collection<T> models);
}
