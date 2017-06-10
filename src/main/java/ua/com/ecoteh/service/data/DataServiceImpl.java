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
 * for working with objects of {@link Model} class or subclasses.
 *
 * @param <T> model type, extends {@link Model}.
 * @param <E> entity type, extends {@link ModelEntity}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Model
 * @see ModelEntity
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
     *
     * @param repository the implementation of the {@link DataRepository} interface.
     */
    DataServiceImpl(final DataRepository<E> repository) {
        this.repository = repository;
    }

    /**
     * Saves and returns object of {@link Model} class or subclasses.
     * Returns input object if model is not valid.
     * If can`t find model then throws NullPointerException.
     *
     * @param model the model to add.
     * @return The saving model (newer null).
     * @throws IllegalArgumentException Throw exception when incoming model is null.
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
        final E incomingEntity = convertToEntity(model);
        final E savingEntity = this.repository.save(incomingEntity);
        if (isNull(savingEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return convertToModel(savingEntity);
    }

    /**
     * Saves and returns objects of {@link Model} class or subclasses.
     * Returns empty collection if models is empty.
     * <pre>
     *     add(null) = empty ArrayList()
     *     add(new ArrayList()) = empty ArrayList()
     *
     *     Collection models = new ArrayList();
     *     models.add(new Model());
     *     add(models) = saving models
     * </pre>
     *
     * @param models the models to add.
     * @return The saving models or empty collection.
     */
    @Override
    @Transactional
    public Collection<T> add(final Collection<T> models) {
        final Collection<T> result;
        if (isNotEmpty(models)) {
            final Collection<E> entities = convertToEntities(models);
            final Collection<E> savingEntities = this.repository.save(entities);
            result = convertToModels(savingEntities);
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

    /**
     * Updates and returns object of {@link Model} class or subclasses.
     *
     * @param model the model to update.
     * @return The updating models (newer null).
     */
    @Override
    @Transactional
    public T update(final T model) {
        return add(model);
    }

    /**
     * Updates and returns objects of {@link Model} class or subclasses.
     * Return empty collection if models is empty.
     * <pre>
     *     update(null) = empty ArrayList()
     *     update(new ArrayList()) = empty ArrayList()
     *
     *     Collection models = new ArrayList();
     *     models.add(new Model());
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
        return add(models);
    }

    /**
     * Returns object of class {@link Model} or subclasses with incoming id.
     * If can`t find model then throws NullPointerException.
     *
     * @param id the id of a model to return.
     * @return The model with incoming id (newer null).
     * @throws NullPointerException Throw exception when model entity
     *                              with parameter id is not exist.
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
        return convertToModel(entity);
    }

    /**
     * Returns all valid objects of {@link Model} class or subclasses.
     *
     * @return The all models (newer null).
     */
    @Override
    @Transactional
    public Collection<T> getAll() {
        return getAll(true);
    }

    /**
     * Returns all or valid objects of {@link Model} class or subclasses
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
     * Removes object of {@link Model} class or subclasses
     * with parameter id.
     *
     * @param id the id of a model to remove.
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
            final E entity = convertToEntity(model);
            this.repository.delete(entity);
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
        if (isNotEmpty(models)) {
            final Collection<E> entities = convertToEntities(models);
            this.repository.delete(entities);
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
     * @param id the id of a model to exists.
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
     * <pre>
     *     exists(null) - false
     *
     *     if the incoming model is not null but it`s not exist
     *     exists(not null) - false
     *
     *     if the incoming model is not null but it`s exist
     *     exists(not null) - true
     * </pre>
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
                final Comparator<T> preparedComparator = prepareComparator(comparator, revers);
                Collections.sort(result, preparedComparator);
            }
        }
        return result;
    }

    /**
     * Sorts and returns objects of {@link Model} class or subclasses
     * by the comparator.
     *
     * @param models     the models to sort.
     * @param comparator the comparator to sort models.
     * @return The sorted list of models (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sort(final Collection<T> models, final Comparator<T> comparator) {
        return sort(models, comparator, false);
    }

    /**
     * Returns a list with the incoming list objects of {@link Model}
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
    public List<T> subList(final Collection<T> models, final int fromIndex, final int toIndex) {
        final List<T> result;
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
     * Returns a list objects of {@link Model} class or subclasses.
     *
     * @param from the initial index.
     * @param to   the final index.
     * @return The substring list of models (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAndSubList(final int from, final int to) {
        return subList(getAll(), from, to);
    }

    /**
     * Returns a list valid objects of {@link Model} class or subclasses.
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

    /**
     * Converts the incoming model to model entity.
     *
     * @param model the model to convert (newer null).
     * @return The converted model entity.
     */
    E convertToEntity(final T model) {
        return (E) model.convert();
    }

    /**
     * Converts the incoming models to model entities.
     *
     * @param models the models to convert.
     * @return The converted model entities (newer null).
     */
    Collection<E> convertToEntities(final Collection<T> models) {
        final Collection<E> entities = new ArrayList<>();
        if (isNotEmpty(models)) {
            models.stream()
                    .filter(ObjectValidator::isNotNull)
                    .forEach(model -> entities.add(convertToEntity(model)));
        }
        return entities;
    }

    /**
     * Converts the incoming model entity to model.
     *
     * @param entity the model entity to convert (newer null).
     * @return The converted model.
     */
    T convertToModel(final E entity) {
        return (T) entity.convert();
    }

    /**
     * Converts the incoming model entities to models.
     *
     * @param entities the model entities to convert.
     * @return The converted models (newer null).
     */
    Collection<T> convertToModels(final Collection<E> entities) {
        final Collection<T> models = new ArrayList<>();
        if (isNotEmpty(entities)) {
            entities.stream()
                    .filter(ObjectValidator::isNotNull)
                    .forEach(entity -> models.add(convertToModel(entity)));
        }
        return models;
    }

    /**
     * Return name of {@link Model} class or subclasses.
     *
     * @return The name of {@link Model} class or subclasses.
     */
    String getClassName() {
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
     * Validated a incoming model.
     * Model is valid if it is not null and it validated.
     * <pre>
     *     isValidated(null) = false
     *
     *     if incoming model is not null and not validated
     *     isValidated(model) = false
     *
     *     if incoming model is not null and validated
     *     isValidated(model) = true
     * </pre>
     *
     * @param model the model to check.
     * @return true if the model is not null and it validated.
     */
    boolean isValidated(final T model) {
        return isNotNull(model) && model.isValidated();
    }

    /**
     * Check the incoming content to not valid.
     * Content is not valid if it is null or not valid.
     * <pre>
     *     isNotValidated(null, false) = true
     *     isNotValidated(null, true) = true
     *
     *     if incoming model is not null and not validated
     *     isNotValidated(model, false) = true
     *     isNotValidated(model, true) = false
     *
     *     if incoming model is not null and validated
     *     isNotValidated(model, false) = true
     *     isNotValidated(model, true) = true
     * </pre>
     *
     * @param entity  the model to check.
     * @param isValid checks the incoming model to valid or not.
     * @return true if the model is null or it is not valid.
     */
    boolean isNotValidated(final E entity, final boolean isValid) {
        return isNull(entity) || (isValid && !entity.isValidated());
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
        final String preparedMessage = prepareMessage(message, (Object[]) parameters);
        return new NullPointerException(preparedMessage);
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
        final String preparedMessage = prepareMessage(message, (Object[]) parameters);
        return new IllegalArgumentException(preparedMessage);
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
    private Comparator<T> prepareComparator(final Comparator<T> comparator, final boolean revers) {
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
     * @param from the initial index.
     * @param to   the final index.
     * @param size the models size.
     * @return true if incoming indexes is correct, false otherwise.
     */
    private boolean checkIndexes(final int from, final int to, final int size) {
        return (from < to) && (from >= 0) && (to < size);
    }

    /**
     * Return Class object of {@link Model} or subclasses.
     *
     * @return The Class object of {@link Model} or subclasses.
     */
    abstract Class<T> getModelClass();
}
