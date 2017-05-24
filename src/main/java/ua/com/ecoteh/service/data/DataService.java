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
    Collection<T> add(final Collection<T> models);

    /**
     * Updates and returns object of {@link Model} class or subclasses.
     *
     * @param model the model to update.
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
     * Returns object of {@link Model} class or subclasses
     * with incoming id.
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
     * Removes object of {@link Model} class or subclasses with incoming id.
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
     * is exists with incoming id.
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
     * @param comparator the comparator to sort models.
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
     * @param comparator the comparator to sort models.
     * @return The sorted list of models.
     */
    List<T> sort(
            final Collection<T> models,
            final Comparator<T> comparator
    );

    /**
     * Returns a list with the incoming list objects
     * of {@link Model} class or subclasses.
     *
     * @param models    the models to be processed.
     * @param fromIndex the initial index.
     * @param toIndex   the final index.
     * @return The models from initial index to final index.
     */
    List<T> subList(Collection<T> models, int fromIndex, int toIndex);

    /**
     * Returns a list objects of {@link Model} class or subclasses.
     *
     * @param fromIndex the initial index.
     * @param toIndex   the final index.
     * @return The models from initial index to final index.
     */
    List<T> getAndSubList(int fromIndex, int toIndex);

    /**
     * Returns a list valid objects of {@link Model} class or subclasses.
     *
     * @param models the models to filter.
     * @return The filtered models.
     */
    List<T> filteredByValid(Collection<T> models);

    /**
     * Shuffles the models and returns it.
     *
     * @param models the models to shuffle.
     * @return The shuffling models.
     */
    List<T> shuffle(Collection<T> models);
}
