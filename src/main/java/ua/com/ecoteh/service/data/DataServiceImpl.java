package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Model;
import ua.com.ecoteh.repository.DataRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, describes a set of methods
 * for working with objects of {@link Model} class or subclasses.
 *
 * @param <T> entity type, extends {@link Model}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public abstract class DataServiceImpl<T extends Model> implements DataService<T> {

    /**
     *
     */
    private final static String SAVING_OBJECT_IS_NULL_MESSAGE =
            "Saving object of the %s class is null!";

    /**
     *
     */
    private final static String UPDATING_OBJECT_IS_NULL_MESSAGE =
            "Updating object of the %s class is null!";

    /**
     *
     */
    private final static String FINDING_BY_ID_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class class by incoming id %d!";

    /**
     * The object provides a set of standard JPA methods
     * for working {@link Model} objects with the database.
     */
    private final DataRepository<T> repository;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param repository a implementation of the {@link DataRepository} interface.
     */
    DataServiceImpl(final DataRepository<T> repository) {
        this.repository = repository;
    }

    /**
     * Saves and returns object of {@link Model} class or subclasses.
     * Returns input object if model is not valid.
     *
     * @param model the model to add.
     * @return The saving model or input object.
     * @throws NullPointerException Throw exception when saving model is null.
     */
    @Override
    @Transactional
    public T add(final T model) throws NullPointerException {
        T result = model;
        if (validated(model, false, true)) {
            result = this.repository.save(model);
        }
        if (isNull(result)) {
            throw new NullPointerException(
                    String.format(
                            SAVING_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName()
                    )
            );
        }
        return result;
    }

    /**
     * Saves and returns objects of {@link Model} class or subclasses.
     * Returns empty collection if models is empty.
     *
     * @param models the models to add.
     * @return The saving models or empty collection.
     */
    @Override
    @Transactional
    public Collection<T> addAll(final Collection<T> models) {
        final List<T> result = new ArrayList<>();
        if (isNotEmpty(models)) {
            result.addAll(
                    models.stream()
                            .map(this::add)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Updates and returns object of {@link Model} class or subclasses.
     * Return null if model is not valid.
     *
     * @param model the model to update.
     * @return The updating models or null.
     * @throws NullPointerException Throw exception when saving model is null.
     */
    @Override
    @Transactional
    public T update(final T model) throws NullPointerException {
        T result = model;
        if (validated(model, true, false)) {
            result = this.repository.save(model);
        }
        if (isNull(result)) {
            throw new NullPointerException(
                    String.format(
                            UPDATING_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName()
                    )
            );
        }
        return result;
    }

    /**
     * Updates and returns objects of {@link Model} class or subclasses.
     * Return empty collection if models is empty.
     *
     * @param models the models to update.
     * @return The updating models or empty collection if models is empty.
     */
    @Override
    @Transactional
    public Collection<T> update(final Collection<T> models) {
        final List<T> result = new ArrayList<>();
        if (isNotEmpty(models)) {
            result.addAll(
                    models.stream()
                            .map(this::update)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Returns object of class {@link Model} or subclasses with parameter id.
     *
     * @param id is id of object to return.
     * @return The model with parameter id.
     * @throws NullPointerException Throw exception when object with parameter id is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public T get(final long id) throws NullPointerException {
        final T result = this.repository.findOne(id);
        if (isNull(result)) {
            throw new NullPointerException(
                    String.format(
                            FINDING_BY_ID_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName(), id
                    )
            );
        }
        return result;
    }

    /**
     * Returns all valid objects of {@link Model} class or subclasses.
     *
     * @return The all models.
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
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<T> getAll(final boolean valid) {
        final Collection<T> models = this.repository.findAll();
        return valid ? filteredByValid(models) : models;
    }

    /**
     * Removes object of {@link Model} class or subclasses
     * with parameter id.
     *
     * @param id a id of model to remove.
     */
    @Override
    @Transactional
    public void remove(final long id) {
        this.repository.delete(id);
    }

    /**
     * Removes object of {@link Model} class or subclasses.
     * Removes model if it is not null.
     *
     * @param model the model to remove.
     */
    @Override
    @Transactional
    public void remove(final T model) {
        if (isNotNull(model)) {
            this.repository.delete(model);
        }
    }

    /**
     * Removes objects of {@link Model} class or subclasses.
     * Removes models if are not null.
     *
     * @param models the models to remove.
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
     */
    @Override
    @Transactional
    public void removeAll() {
        this.repository.deleteAll();
    }

    /**
     * Checks whether the object of {@link Model} class or subclasses
     * with parameter id is exists.
     *
     * @param id a id of the model to exists.
     * @return Returns true if the model is exists,
     * otherwise returns false.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exists(final long id) {
        return this.repository.exists(id);
    }

    /**
     * Checks whether the object of {@link Model} class
     * or subclasses is exists. If model is null then return false.
     *
     * @param model the models to exists.
     * @return Returns true if the model is exists,
     * otherwise returns false.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exists(final T model) {
        return isNotNull(model) && exists(model.getId());
    }

    /**
     * Sorts and returns objects of {@link Model} class or subclasses
     * by the comparator. If models is empty then return empty collection.
     *
     * @param models     the models to sort.
     * @param comparator a comparator to sort models.
     * @param revers     is sort in descending or ascending.
     * @return The sorted list of models.
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sort(
            final Collection<T> models,
            final Comparator<T> comparator,
            final boolean revers
    ) {
        final List<T> result = new ArrayList<>();
        if (isNotEmpty(models)) {
            result.addAll(models);
            if (isNotNull(comparator)) {
                Collections.sort(
                        result,
                        getReversComparator(comparator, revers)
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
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sort(
            final Collection<T> models,
            final Comparator<T> comparator
    ) {
        return sort(models, comparator, false);
    }

    /**
     * Returns a list with the incoming list objects of {@link Model}
     * class or subclasses.
     *
     * @param models    the list of models to be processed.
     * @param fromIndex a initial index.
     * @param toIndex   a final index.
     * @return The substring list of models.
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> subList(
            final Collection<T> models,
            final int fromIndex,
            final int toIndex
    ) {
        List<T> result;
        if (isEmpty(models)) {
            result = new ArrayList<>();
        } else if (checkIndexes(fromIndex, toIndex, models.size())) {
            result = new ArrayList<>(models).subList(fromIndex, toIndex);
        } else {
            result = new ArrayList<>(models);
        }
        return result;
    }

    /**
     * Checks a incoming indexes.
     *
     * @param fromIndex a initial index.
     * @param toIndex   a final index.
     * @param size      a models size.
     * @return true if incoming indexes is correct, false otherwise.
     */
    private static boolean checkIndexes(final int fromIndex, final int toIndex, final int size) {
        return (fromIndex < toIndex) && (fromIndex >= 0) && (toIndex < size);
    }

    /**
     * Returns a list objects of {@link Model} class or subclasses.
     *
     * @param fromIndex a initial index.
     * @param toIndex   a final index.
     * @return The substring list of models.
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAndSubList(
            final int fromIndex,
            final int toIndex
    ) {
        return subList(getAll(), fromIndex, toIndex);
    }

    /**
     * Returns a list valid objects of {@link Model} class or subclasses.
     *
     * @param models the models to filter.
     * @return The filter list of models.
     */
    @Override
    public List<T> filteredByValid(final Collection<T> models) {
        final List<T> result = new ArrayList<>();
        if (isNotEmpty(models)) {
            result.addAll(
                    models.stream()
                            .filter(
                                    DataServiceImpl::validFilter
                            ).collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Shuffles the models and returns it.
     *
     * @param models a models to shuffle.
     * @return The shuffling models.
     */
    @Override
    public List<T> shuffle(final Collection<T> models) {
        final List<T> list = new ArrayList<>(models);
        Collections.shuffle(list);
        return list;
    }

    /**
     * Gets revers input comparator if revers is true.
     *
     * @param comparator a comparator to sort models.
     * @param revers     is sort in descending or ascending.
     * @return The comparator to sort models.
     */
    private Comparator<T> getReversComparator(
            final Comparator<T> comparator,
            final boolean revers
    ) {
        return revers ? Collections.reverseOrder(comparator) : comparator;
    }

    /**
     * Filters model by validation.
     *
     * @param model a model to filter.
     * @return true if model is not null and is valid, false otherwise.
     */
    private static boolean validFilter(final Model model) {
        return (model != null) && model.isValidated();
    }

    /**
     * Return name of {@link Model} class or subclasses.
     *
     * @return The name of {@link Model} class or subclasses.
     */
    protected String getClassName() {
        return getModelClass().getName();
    }

    /**
     * Return simple name of {@link Model} class or subclasses.
     *
     * @return The simple name of {@link Model} class or subclasses.
     */
    String getClassSimpleName() {
        return getModelClass().getSimpleName();
    }

    /**
     * Return Class object of {@link Model} or subclasses.
     *
     * @return The Class object of {@link Model} or subclasses.
     */
    protected abstract Class<T> getModelClass();

    /**
     * Validates input object of class {@link Model} or subclasses.
     *
     * @param model     the model to valid.
     * @param exist     is validate input model by exists.
     * @param duplicate is validate input model by duplicate.
     * @return Returns true if the model is valid, otherwise returns false.
     */
    protected abstract boolean validated(
            final T model,
            final boolean exist,
            final boolean duplicate
    );
}
