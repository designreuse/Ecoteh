package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.DataDao;
import com.salimov.ecoteh.entity.Model;
import com.salimov.ecoteh.repository.DataRepository;

import java.util.Collection;

/**
 * The class implements a set of standard methods for working models objects
 * of the {@link Model} class or subclasses with the database.
 *
 * @param <T> Entity type, extends {@link Model}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public abstract class DataDaoImpl<T extends Model> implements DataDao<T> {

    /**
     * The interface provides a set of JPA methods
     * for working {@link Model} objects with a database.
     */
    private final DataRepository<T> repository;

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides a set of JPA methods
     *                   for working objects with a database.
     */
    DataDaoImpl(final DataRepository<T> repository) {
        this.repository = repository;
    }

    /**
     * Saves in the database and returns model object
     * of the {@link Model} class or subclasses.
     *
     * @param model a model to add.
     * @return The saving model.
     */
    @Override
    public T add(final T model) {
        return this.repository.save(model);
    }

    /**
     * Saves in the database and returns model objects
     * of the {@link Model} class or subclasses.
     *
     * @param models a model objects to add.
     * @return The saving model objects.
     */
    @Override
    public Collection<T> addAll(final Collection<T> models) {
        return this.repository.save(models);
    }

    /**
     * Updates in the database and returns model object
     * of the {@link Model} class or subclasses.
     *
     * @param model a model object to update.
     * @return The updating model.
     */
    @Override
    public T update(final T model) {
        return this.repository.save(model);
    }

    /**
     * Returns model object of the {@link Model} class
     * or subclasses with parameter id from a database.
     *
     * @param id a id of the model object to return.
     * @return The model object with parameter id.
     */
    @Override
    public T get(final long id) {
        return this.repository.findOne(id);
    }

    /**
     * Returns all model objects of the {@link Model} class
     * or subclasses from a database.
     *
     * @return The all model objects.
     */
    @Override
    public Collection<T> getAll() {
        return this.repository.findAll();
    }

    /**
     * Removes model object of the {@link Model} class
     * or subclasses with parameter id from a database.
     *
     * @param id a id model object to remove.
     */
    @Override
    public void remove(final long id) {
        this.repository.delete(id);
    }

    /**
     * Removes model object of the {@link Model} class
     * or subclasses from a database.
     *
     * @param model The model object to remove.
     */
    @Override
    public void remove(final T model) {
        this.repository.delete(model);
    }

    /**
     * Removes model objects of the {@link Model} class
     * or subclasses from a database.
     *
     * @param models The model objects to remove.
     */
    @Override
    public void remove(final Collection<T> models) {
        this.repository.delete(models);
    }

    /**
     * Removes all model objects of the {@link Model} class
     * or subclasses from a database.
     */
    @Override
    public void removeAll() {
        this.repository.deleteAll();
    }

    /**
     * Checks whether the model object of the {@link Model} class
     * or subclasses with parameter id is exists in a database.
     *
     * @param id a id of model object to exists.
     * @return {@code true} if model object is exists, {@code false} otherwise.
     */
    @Override
    public boolean exists(final long id) {
        return this.repository.exists(id);
    }
}
