package ua.com.ecoteh.service.data;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.model.Model;
import ua.com.ecoteh.entity.model.ModelEntity;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.DataRepository;
import ua.com.ecoteh.util.validator.ObjectValidator;

import java.util.*;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, describes a set of methods
 * for working with objects of {@link ModelEntity} class or subclasses.
 *
 * @param <T> entity type, extends {@link ModelEntity}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class DataServiceImpl<T extends Model, E extends ModelEntity> implements DataService<T> {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(DataServiceImpl.class);

    /**
     * The object provides a set of standard JPA methods
     * for working {@link ModelEntity} objects with the database.
     */
    private final DataRepository<E> repository;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param repository the implementation of the {@link DataRepository} interface.
     */
    DataServiceImpl(final DataRepository<E> repository) {
        this.repository = repository;
    }

    /**
     * Saves and returns object of {@link ModelEntity} class or subclasses.
     * Returns input object if modelEntity is not valid.
     * If can`t find modelEntity then throws NullPointerException.
     *
     * @param model the modelEntity to add.
     * @return The saving modelEntity or input object (newer null).
     * @throws IllegalArgumentException
     * @throws NullPointerException     Throw exception when saving modelEntity is null.
     */
    @Override
    @Transactional
    public T add(final T model) throws IllegalArgumentException, NullPointerException {
        if (isNull(model)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final E incomingEntity = model.convert();
        final E savingEntity = this.repository.save(incomingEntity);
        if (isNull(savingEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return savingEntity.convert();
    }

    /**
     * Saves and returns objects of {@link ModelEntity} class or subclasses.
     * Returns empty collection if models is empty.
     * <pre>
     *     addAll(null) = empty ArrayList()
     *     addAll(new ArrayList()) = empty ArrayList()
     *
     *     Collection models = new ArrayList();
     *     models.add(new ModelEntity());
     *     addAll(models) = saving models
     * </pre>
     *
     * @param models the models to add.
     * @return The saving models or empty collection.
     */
    @Override
    @Transactional
    public Collection<T> add(final Collection<T> models) {
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
     * Updates and returns object of {@link ModelEntity} class or subclasses.
     * If can`t find modelEntity then throws NullPointerException.
     *
     * @param model the modelEntity to update.
     * @return The updating models (newer null).
     */
    @Override
    @Transactional
    public T update(final T model) {
        return add(model);
    }

    /**
     * Updates and returns objects of {@link ModelEntity} class or subclasses.
     * Return empty collection if models is empty.
     * <pre>
     *     update(null) = empty ArrayList()
     *     update(new ArrayList()) = empty ArrayList()
     *
     *     Collection models = new ArrayList();
     *     models.add(new ModelEntity());
     *     update(models) = saving models
     * </pre>
     *
     * @param models the models to update.
     * @return The updating models or empty collection
     * if models is empty (newer null).
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
     * Returns object of class {@link ModelEntity} or subclasses with incoming id.
     * If can`t find modelEntity then throws NullPointerException.
     *
     * @param id the id of a modelEntity to return.
     * @return The modelEntity with incoming id (newer null).
     * @throws NullPointerException Throw exception when object with parameter id is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public T get(final long id) throws NullPointerException {
        final E entity = this.repository.findOne(id);
        if (isNull(entity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_ID_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), id
            );
        }
        return entity.convert();
    }

    /**
     * Returns all valid objects of {@link ModelEntity} class or subclasses.
     *
     * @return The all models (newer null).
     */
    @Override
    @Transactional
    public Collection<T> getAll() {
        return getAll(true);
    }

    /**
     * Returns all or valid objects of {@link ModelEntity} class or subclasses
     * depending on the parameter value.
     * <pre>
     *     getAll(true) = all valid models
     *     getAll(false) = all models
     * </pre>
     *
     * @param valid is returns all or valid models.
     * @return The all models (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<T> getAll(final boolean valid) {
        final Collection<E> entities = this.repository.findAll();
        final Collection<T> models = convertToModels(entities);
        return valid ? filteredByValid(models) : models;
    }

    /**
     * Removes object of {@link ModelEntity} class or subclasses
     * with parameter id.
     *
     * @param id the id of a modelEntity to remove.
     */
    @Override
    @Transactional
    public void remove(final long id) {
        this.repository.delete(id);
    }

    /**
     * Removes object of {@link ModelEntity} class or subclasses.
     * Removes modelEntity if it is not null.
     *
     * @param model the modelEntity to remove.
     */
    @Override
    @Transactional
    public void remove(final T model) {
        if (isNotNull(model)) {
            final E entity = model.convert();
            this.repository.delete(entity);
        }
    }

    /**
     * Removes objects of {@link ModelEntity} class or subclasses.
     * Removes models if are not null.
     *
     * @param models the models to remove.
     */
    @Override
    @Transactional
    public void remove(final Collection<T> models) {
        if (isNotEmpty(models)) {
            final Collection<E> entities = convertToEntities(models);
            this.repository.delete(entities);
        }
    }

    /**
     * Removes all objects of {@link ModelEntity} class or subclasses.
     */
    @Override
    @Transactional
    public void removeAll() {
        this.repository.deleteAll();
    }

    /**
     * Checks whether the object of {@link ModelEntity} class or subclasses
     * with parameter id is exists.
     *
     * @param id the id of a modelEntity to exists.
     * @return Returns true if the modelEntity is exists,
     * otherwise returns false.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exists(final long id) {
        return this.repository.exists(id);
    }

    /**
     * Checks whether the object of {@link ModelEntity} class
     * or subclasses is exists. If modelEntity is null then return false.
     *
     * @param model the models to exists.
     * @return Returns true if the modelEntity is exists,
     * otherwise returns false.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exists(final T model) {
        return isNotNull(model) && exists(model.getId());
    }

    /**
     * Sorts and returns objects of {@link ModelEntity} class or subclasses
     * by the comparator. If models is empty then return empty collection.
     *
     * @param models     the models to sort.
     * @param comparator the comparator to sort models.
     * @param revers     is sort in descending or ascending.
     * @return The sorted list of models (newer null).
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
                Collections.sort(result, prepareComparator(comparator, revers));
            }
        }
        return result;
    }

    /**
     * Sorts and returns objects of {@link ModelEntity} class or subclasses
     * by the comparator.
     *
     * @param models     the models to sort.
     * @param comparator the comparator to sort models.
     * @return The sorted list of models (newer null).
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
     * Returns a list with the incoming list objects of {@link ModelEntity}
     * class or subclasses.
     * <pre>
     *     subList(null, ..., ...) = empty ArrayList
     *     subList(new ArrayList(), ..., ...) = empty ArrayList
     *
     *     For example, listSize = 10
     *     subList(modelsList, 1, 11) = modelsList
     *     subList(modelsList, 11, 1) = modelsList
     *     subList(modelsList, 11, 11) = modelsList
     *
     *     subList(modelsList, 1, 5) = substring list of models.
     * </pre>
     *
     * @param models    the list of models to be processed.
     * @param fromIndex the initial index.
     * @param toIndex   the final index.
     * @return The substring list of models (newer null).
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
     * Returns a list objects of {@link ModelEntity} class or subclasses.
     *
     * @param fromIndex the initial index.
     * @param toIndex   the final index.
     * @return The substring list of models (newer null).
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
     * Returns a list valid objects of {@link ModelEntity} class or subclasses.
     *
     * @param models the models to filter.
     * @return The filter list of models (newer null).
     */
    @Override
    public List<T> filteredByValid(final Collection<T> models) {
        final List<T> result = new ArrayList<>();
        if (isNotEmpty(models)) {
            result.addAll(
                    models.stream()
                            .filter(this::isValidated)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Shuffles the incoming models and returns it.
     *
     * @param models the models to shuffle.
     * @return The shuffling models (newer null).
     */
    @Override
    public List<T> shuffle(final Collection<T> models) {
        final List<T> list = new ArrayList<>(models);
        Collections.shuffle(list);
        return list;
    }

    Collection<T> convertToModels(final Collection<E> entities) {
        final Collection<T> models = new ArrayList<>();
        if (isNotEmpty(entities)) {
            entities.stream()
                    .filter(ObjectValidator::isNotNull)
                    .forEach(model -> models.add(model.convert()));
        }
        return models;
    }

    Collection<E> convertToEntities(final Collection<T> models) {
        final Collection<E> entities = new ArrayList<>();
        if (isNotEmpty(models)) {
            models.stream()
                    .filter(ObjectValidator::isNotNull)
                    .forEach(model -> entities.add(model.convert()));
        }
        return entities;
    }

    /**
     * Return name of {@link ModelEntity} class or subclasses.
     *
     * @return The name of {@link ModelEntity} class or subclasses.
     */
    String getClassName() {
        return getModelClass().getName();
    }

    /**
     * Return simple name of {@link ModelEntity} class or subclasses.
     *
     * @return The simple name of {@link ModelEntity} class or subclasses.
     */
    String getClassSimpleName() {
        return getModelClass().getSimpleName();
    }

    /**
     * Validated a incoming modelEntity.
     * ModelEntity is valid if it is not null and it validated.
     * <pre>
     *     isValidated(null) = false
     *
     *     ModelEntity modelEntity = new ModelEntity();
     *     modelEntity.setValidated(false);
     *     isValidated(modelEntity) = false
     *
     *     modelEntity.setValidated(true);
     *     isValidated(modelEntity) = true
     * </pre>
     *
     * @param model the modelEntity to check.
     * @return true if the modelEntity is not null and it validated.
     */
    boolean isValidated(final T model) {
        return isNotNull(model) && model.isValidated();
    }

    E convertToEntity(final T model) {
        return model.convert();
    }

    T convertToModel(final E model) {
        return model.convert();
    }

    /**
     * Creates and returns a instance of the NullPointerException class.
     *
     * @param message    the exception message.
     * @param parameters the message parameters.
     * @return The instance of the NullPointerException class.
     */
    NullPointerException getNullPointerException(
            final String message,
            final Object... parameters
    ) {
        return new NullPointerException(
                prepareMessage(message, (Object[]) parameters)
        );
    }

    /**
     * Creates and returns a instance of the IllegalArgumentException class.
     *
     * @param message    the exception message.
     * @param parameters the message parameters.
     * @return The instance of the IllegalArgumentException class.
     */
    IllegalArgumentException getIllegalArgumentException(
            final String message,
            final Object... parameters
    ) {
        return new IllegalArgumentException(
                prepareMessage(message, (Object[]) parameters)
        );
    }

    /**
     * Error logging.
     *
     * @param ex the intercepted exception.
     */
    void logException(final Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    /**
     * Creates and returns the incoming message.
     *
     * @param message    the message to prepare.
     * @param parameters the message parameters.
     * @return the prepared message.
     */
    private String prepareMessage(final String message, final Object... parameters) {
        return isNotEmpty(parameters) ? String.format(message, parameters) : message;
    }

    /**
     * Gets revers input comparator if revers is true.
     * Incoming comparator must be not null.
     * <pre>
     *     Comparator comparator = new SomeComparator();
     *     getReversComparator(comparator, true) = revers comparator
     *     getReversComparator(comparator, false) = comparator
     * </pre>
     *
     * @param comparator the comparator to sort models.
     * @param revers     is sort in descending or ascending.
     * @return The comparator to sort models (newer null).
     */
    private Comparator<T> prepareComparator(
            final Comparator<T> comparator,
            final boolean revers
    ) {
        return revers ? Collections.reverseOrder(comparator) : comparator;
    }

    /**
     * Checks a incoming indexes.
     * <pre>
     *     checkIndexes(0, 2, 3) = true
     *     checkIndexes(1, 2, 3) = true
     *     checkIndexes(2, 1, 3) = false
     *     checkIndexes(1, 4, 3) = false
     * </pre>
     *
     * @param fromIndex the initial index.
     * @param toIndex   the final index.
     * @param size      the models size.
     * @return true if incoming indexes is correct, false otherwise.
     */
    private boolean checkIndexes(final int fromIndex, final int toIndex, final int size) {
        return (fromIndex < toIndex) && (fromIndex >= 0) && (toIndex < size);
    }

    /**
     * Return Class object of {@link ModelEntity} or subclasses.
     *
     * @return The Class object of {@link ModelEntity} or subclasses.
     */
    abstract Class<T> getModelClass();
}
