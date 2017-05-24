package ua.com.ecoteh.service.data;

import org.apache.log4j.Logger;
import ua.com.ecoteh.entity.Model;
import ua.com.ecoteh.exception.ExceptionMessage;
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
 */
public abstract class DataServiceImpl<T extends Model> implements DataService<T> {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(DataServiceImpl.class);

    /**
     * The object provides a set of standard JPA methods
     * for working {@link Model} objects with the database.
     */
    private final DataRepository<T> repository;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param repository the implementation of the {@link DataRepository} interface.
     */
    DataServiceImpl(final DataRepository<T> repository) {
        this.repository = repository;
    }

    /**
     * Saves and returns object of {@link Model} class or subclasses.
     * Returns input object if model is not valid.
     * If can`t find model then throws NullPointerException.
     *
     * @param model the model to add.
     * @return The saving model or input object (newer null).
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
            throw getNullPointerException(
                    ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return result;
    }

    /**
     * Saves and returns objects of {@link Model} class or subclasses.
     * Returns empty collection if models is empty.
     * <pre>
     *     addAll(null) = empty ArrayList()
     *     addAll(new ArrayList()) = empty ArrayList()
     *
     *     Collection models = new ArrayList();
     *     models.add(new Model());
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
     * Updates and returns object of {@link Model} class or subclasses.
     * If can`t find model then throws NullPointerException.
     *
     * @param model the model to update.
     * @return The updating models (newer null).
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
            throw getNullPointerException(
                    ExceptionMessage.UPDATING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return result;
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
     * Returns object of class {@link Model} or subclasses with incoming id.
     * If can`t find model then throws NullPointerException.
     *
     * @param id the id of a model to return.
     * @return The model with incoming id (newer null).
     * @throws NullPointerException Throw exception when object with parameter id is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public T get(final long id) throws NullPointerException {
        final T result = this.repository.findOne(id);
        if (isNull(result)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_ID_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), id
            );
        }
        return result;
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
        final Collection<T> models = this.repository.findAll();
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
        if (isNotEmpty(models)) {
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
                Collections.sort(result, prepareComparator(comparator, revers));
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
    public List<T> sort(
            final Collection<T> models,
            final Comparator<T> comparator
    ) {
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
     * Returns a list objects of {@link Model} class or subclasses.
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
                            .filter(DataServiceImpl::isValidated)
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
     * Validated a incoming model.
     * Model is valid if it is not null and it validated.
     * <pre>
     *     isValidated(null) = false
     *
     *     Model model = new Model();
     *     model.setValidated(false);
     *     isValidated(model) = false
     *
     *     model.setValidated(true);
     *     isValidated(model) = true
     * </pre>
     *
     * @param model the model to check.
     * @return true if the model is not null and it validated.
     */
    protected static boolean isValidated(final Model model) {
        return isNotNull(model) && model.isValidated();
    }

    /**
     * Creates and returns a instance of the NullPointerException class.
     *
     * @param message    the exception message.
     * @param parameters the message parameters.
     * @return The instance of the NullPointerException class.
     */
    protected static NullPointerException getNullPointerException(
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
    protected static IllegalArgumentException getIllegalArgumentException(
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
    protected static void logException(final Exception ex) {
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
    private static String prepareMessage(
            final String message,
            final Object... parameters
    ) {
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
     * @param <T>        entity type, extends {@link Model}.
     * @param comparator the comparator to sort models.
     * @param revers     is sort in descending or ascending.
     * @return The comparator to sort models (newer null).
     */
    private static <T extends Model> Comparator<T> prepareComparator(
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
    private static boolean checkIndexes(
            final int fromIndex,
            final int toIndex,
            final int size
    ) {
        return (fromIndex < toIndex) && (fromIndex >= 0) && (toIndex < size);
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
