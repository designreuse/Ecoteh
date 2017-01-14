package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.DataDao;
import com.salimov.yurii.entity.Model;
import com.salimov.yurii.service.data.interfaces.DataService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The class of the service layer, describes a set of methods
 * for working with objects of {@link Model} class or subclasses.
 *
 * @param <T> entity type, extends {@link Model}.
 * @param <E> entity id type, extends Number.
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Model
 * @see DataService
 * @see ContentServiceImpl
 * @see MediaServiceImpl
 * @see com.salimov.yurii.service.data.impl.ArticleServiceImpl
 * @see com.salimov.yurii.service.data.impl.CategoryServiceImpl
 * @see com.salimov.yurii.service.data.impl.CompanyServiceImpl
 * @see com.salimov.yurii.service.data.impl.FileServiceImpl
 * @see com.salimov.yurii.service.data.impl.ResponseServiceImpl
 * @see com.salimov.yurii.service.data.impl.UserServiceImpl
 * @see DataDao
 */
public abstract class DataServiceImpl<T extends Model<E>, E extends Number>
        implements DataService<T, E> {

    /**
     * The object provides a set of standard JPA methods
     * for working {@link Model} objects with the database.
     *
     * @see DataDao
     */
    private final DataDao<T, E> dao;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao a implementation of the {@link DataDao} interface.
     * @see DataDao
     */
    DataServiceImpl(final DataDao<T, E> dao) {
        this.dao = dao;
    }

    /**
     * Saves and returns object of {@link Model} class or subclasses.
     * Returns {@code null} if model is not valid.
     *
     * @param model the model to add.
     * @return The saving model or {@code null}.
     * @see Model
     */
    @Override
    @Transactional
    public T add(final T model) {
        T result = model;
        if (validated(model, true, false, true)) {
            result = this.dao.add(model);
        }
        return result;
    }

    /**
     * Saves and returns objects of {@link Model} class or subclasses.
     * Returns empty collection if models is empty.
     *
     * @param models the models to add.
     * @return The saving models or empty collection.
     * @see Model
     */
    @Override
    @Transactional
    public Collection<T> add(final Collection<T> models) {
        final List<T> result = new ArrayList<>();
        if (models != null && !models.isEmpty()) {
            result.addAll(
                    models.stream()
                            .map(
                                    this::add
                            )
                            .collect(
                                    Collectors.toList()
                            )
            );
        }
        return result;
    }

    /**
     * Updates and returns object of {@link Model} class or subclasses.
     * Return {@code null} if model is not valid.
     *
     * @param model the model to update.
     * @return The updating models or {@code null}.
     * @see Model
     */
    @Override
    @Transactional
    public T update(final T model) {
        T result = model;
        if (validated(model, true, true, false)) {
            result = this.dao.update(model);
        }
        return result;
    }

    /**
     * Updates and returns objects of {@link Model} class or subclasses.
     * Return empty collection if models is empty.
     *
     * @param models the models to update.
     * @return The updating models or empty collection if models is empty.
     * @see Model
     */
    @Override
    @Transactional
    public Collection<T> update(final Collection<T> models) {
        final List<T> result = new ArrayList<>();
        if (models != null && !models.isEmpty()) {
            result.addAll(
                    models.stream()
                            .map(
                                    this::update
                            )
                            .collect(
                                    Collectors.toList()
                            )
            );
        }
        return result;
    }

    /**
     * Returns object of class {@link Model} or subclasses with parameter id.
     * If id is {@code null} then throws IllegalArgumentException.
     *
     * @param id is id of object to return.
     * @return The model with parameter id.
     * @throws IllegalArgumentException Throw exception when object
     *                                  parameter id is {@code null}.
     * @throws NullPointerException     Throw exception when object with
     *                                  parameter id is not exist.
     * @see Model
     */
    @Override
    @Transactional(readOnly = true)
    public T get(E id) throws IllegalArgumentException, NullPointerException {
        if (id == null) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " id is null!"
            );
        }
        final T model = this.dao.get(id);
        if (model == null) {
            throw new NullPointerException(
                    "Can`t find " + getClassSimpleName()
                            + " by id " + id + "!"
            );
        }
        return model;

    }

    /**
     * Returns all valid objects of {@link Model} class or subclasses.
     *
     * @return The all models.
     * @see Model
     */
    @Override
    @Transactional
    public Collection<T> getAll() {
        return getAll(true);
    }

    /**
     * Returns all or valid objects of {@link Model} class or subclasses
     * depending on the parameter value.
     *
     * @param valid is returns all or valid models.
     * @return The all models.
     * @see Model
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<T> getAll(final boolean valid) {
        final Collection<T> models = this.dao.getAll();
        return valid ? filteredByValid(models) : models;
    }

    /**
     * Removes object of {@link Model} class or subclasses
     * with parameter id.
     *
     * @param id a id of model to remove.
     * @see Model
     */
    @Override
    @Transactional
    public void remove(final E id) {
        remove(
                get(id)
        );
    }

    /**
     * Removes object of {@link Model} class or subclasses.
     * Removes model if it is not {@code null}.
     *
     * @param model the model to remove.
     * @see Model
     */
    @Override
    @Transactional
    public void remove(final T model) {
        if (model != null) {
            this.dao.remove(model);
        }
    }

    /**
     * Removes objects of {@link Model} class or subclasses.
     * Removes models if are not {@code null}.
     *
     * @param models the models to remove.
     * @see Model
     */
    @Override
    @Transactional
    public void remove(final Collection<T> models) {
        if (models != null && !models.isEmpty()) {
            models.forEach(this::remove);
        }
    }

    /**
     * Removes all objects of {@link Model} class or subclasses.
     *
     * @see Model
     */
    @Override
    @Transactional
    public void removeAll() {
        remove(
                getAll(false)
        );
    }

    /**
     * Checks whether the object of {@link Model} class or subclasses
     * with parameter id is exists. If id is {@code null} then
     * return {@code false}.
     *
     * @param id a id of the model to exists.
     * @return Returns {@code true} if the model is exists,
     * otherwise returns {@code false}.
     * @see Model
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exists(final E id) {
        return (id != null) && (this.dao.exists(id));
    }

    /**
     * Checks whether the object of {@link Model} class or subclasses is exists.
     * If model is {@code null} or model id is {@code null} then
     * return {@code false}.
     *
     * @param model the models to exists.
     * @return Returns {@code true} if the model is exists,
     * otherwise returns {@code false}.
     * @see Model
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exists(final T model) {
        boolean result = false;
        if ((model != null) && (model.getId() != null)) {
            result = exists(
                    model.getId()
            );
        }
        return result;
    }

    /**
     * Sorts and returns objects of {@link Model} class or subclasses
     * by the comparator. If models is empty then return empty collection.
     *
     * @param models     the models to sort.
     * @param comparator a comparator to sort models.
     * @param revers     is sort in descending or ascending.
     * @return The sorted list of models.
     * @see Model
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sort(
            final Collection<T> models,
            final Comparator<T> comparator,
            final boolean revers
    ) {
        final List<T> result = new ArrayList<>();
        if (models != null && !models.isEmpty()) {
            result.addAll(models);
            if (comparator != null) {
                Collections.sort(
                        result,
                        revers ?
                                Collections.reverseOrder(comparator) :
                                comparator
                );
            }
        }
        return result;
    }

    /**
     * Sorts and returns objects of {@link Model} class or subclasses
     * by the comparator.
     *
     * @param models     the models to sort.
     * @param comparator a comparator to sort models.
     * @return The sorted list of models.
     * @see Model
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sort(
            final Collection<T> models,
            final Comparator<T> comparator
    ) {
        return sort(
                models,
                comparator,
                false
        );
    }

    /**
     * Returns a list with the incoming list objects of {@link Model}
     * class or subclasses.
     *
     * @param models    the list of models to be processed.
     * @param fromIndex a initial index.
     * @param toIndex   a final index.
     * @return The substring list of models.
     * @see Model
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> subList(
            final Collection<T> models,
            final int fromIndex,
            final int toIndex
    ) {
        List<T> result = new ArrayList<>(models);
        return result.subList(
                fromIndex,
                toIndex
        );
    }

    /**
     * Returns a list objects of {@link Model} class or subclasses.
     *
     * @param fromIndex a initial index.
     * @param toIndex   a final index.
     * @return The substring list of models.
     * @see Model
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAndSubList(
            final int fromIndex,
            final int toIndex
    ) {
        return subList(
                new ArrayList<>(
                        getAll()
                ),
                fromIndex,
                toIndex
        );
    }

    /**
     * Returns a list valid objects of {@link Model} class or subclasses.
     * Return {@code null} if models is empty.
     *
     * @param models the models to filter.
     * @return The filter list of models.
     * @see Model
     */
    @Override
    @Transactional
    public List<T> filteredByValid(final Collection<T> models) {
        final List<T> result = new ArrayList<>();
        if (models != null && !models.isEmpty()) {
            result.addAll(
                    models.stream()
                            .filter(
                                    model -> (
                                            model != null
                                    ) && (
                                            model.isValidated()
                                    )
                            )
                            .collect(
                                    Collectors.toList()
                            )
            );
        }
        return result;
    }

    /**
     * Return name of {@link Model} class or subclasses.
     *
     * @return The name of {@link Model} class or subclasses.
     * @see Model
     */
    protected String getClassName() {
        return getModelClass().getName();
    }

    /**
     * Return simple name of {@link Model} class or subclasses.
     *
     * @return The simple name of {@link Model} class or subclasses.
     * @see Model
     */
    String getClassSimpleName() {
        return getModelClass().getSimpleName();
    }

    /**
     * Creates new instance of the type.
     *
     * @param type Class object.
     * @return The Class object.
     */
    T getInstance(Class<T> type) {
        T instance = null;
        try {
            instance = type.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return instance;
    }

    /**
     * Return Class object of {@link Model} or subclasses.
     *
     * @return The Class object of {@link Model} or subclasses.
     * @see Model
     */
    protected abstract Class<T> getModelClass();

    /**
     * Validates input object of class {@link Model} or subclasses.
     *
     * @param model              the model to valid.
     * @param requiredParameters is validate input model by required parameters.
     * @param exist              is validate input model by exists.
     * @param duplicate          is validate input model by duplicate.
     * @return Returns {@code true} if the model is valid,
     * otherwise returns {@code false}.
     * @see Model
     */
    protected abstract boolean validated(
            final T model,
            final boolean requiredParameters,
            final boolean exist,
            final boolean duplicate
    );
}
