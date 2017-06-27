package ua.com.ecoteh.entity.model;

import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of methods
 * for converting models to model entities.
 *
 * @param <T> model type, extends {@link Model}.
 * @param <E> model entity type, extends {@link ModelEntity}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Model
 * @see ModelEntity
 */
public abstract class ModelConverter<T extends Model, E extends ModelEntity> implements Converter<E> {

    /**
     * The model for converting to model entity.
     */
    private final T model;

    /**
     * The instance of the interface for data encryption.
     */
    private Encryptor encryptor;

    /**
     * Constructor.
     *
     * @param model the model for converting to model entity.
     */
    protected ModelConverter(final T model) {
        this.model = model;
    }

    /**
     * Converts the model and returns a new model entity.
     * Synchronized method, locks by the model.
     *
     * @return The converted model entity (newer null).
     */
    @Override
    public E syncConvert() {
        synchronized (this.model) {
            return convert();
        }
    }

    /**
     * Encrypts the incoming value and returns it.
     * <pre>
     *     encrypt(null) - empty string
     *     encrypt("") - empty string
     *     encrypt(" ") - empty string
     *     encrypt("   ") - empty string
     *     encrypt("value") - some encrypted value
     * </pre>
     *
     * @param value the value to encrypt.
     * @return the encrypted value or empty string (newer null).
     * @see Base64Encryptor
     */
    protected String encrypt(final String value) {
        final String encryptedValue;
        if (isNotEmpty(value)) {
            final Encryptor encryptor = getEncryptor();
            encryptedValue = encryptor.encrypt(value);
        } else {
            encryptedValue = "";
        }
        return encryptedValue;
    }

    /**
     * Creates and returns the object for data encryption.
     *
     * @return The object for data encryption.
     * @see Base64Encryptor
     */
    private Encryptor getEncryptor() {
        if (isNull(this.encryptor)) {
            this.encryptor = new Base64Encryptor();
        }
        return this.encryptor;
    }
}
