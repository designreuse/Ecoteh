package ua.com.ecoteh.entity.model;

import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of methods
 * for converting model entities to models.
 *
 * @param <E> model entity type, extends {@link ModelEntity}.
 * @param <T> model type, extends {@link Model}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ModelEntity
 * @see Model
 */
public abstract class ModelEntityConverter<E extends ModelEntity, T extends Model> implements Converter<T> {

    /**
     * The model entity for converting to model.
     */
    private final E entity;

    /**
     * The instance of the interface for data decryption.
     */
    private Encryptor decryptor;

    /**
     * Constructor.
     *
     * @param entity the model entity for converting to model.
     */
    protected ModelEntityConverter(final E entity) {
        this.entity = entity;
    }

    /**
     * Converts the model entity and returns a new model.
     *
     * @return The converted model (newer null).
     */
    @Override
    public T convert() {
        return prepareBuilder().build();
    }

    /**
     * Converts the model entity and returns a new model.
     * Synchronized method, locks by the model entity.
     *
     * @return The converted model (newer null).
     */
    @Override
    public T syncConvert() {
        synchronized (this.entity) {
            return convert();
        }
    }

    /**
     * Decrypts the incoming value and returns it.
     * <pre>
     *     decrypt(null) - empty string
     *     decrypt("") - empty string
     *     decrypt(" ") - empty string
     *     decrypt("   ") - empty string
     *     decrypt("value") - some decrypted value
     * </pre>
     *
     * @param value the value to decrypt.
     * @return the decrypted value or empty string (newer null).
     * @see Base64Encryptor
     */
    protected String decrypt(final String value) {
        final String decryptedValue;
        if (isNotEmpty(value)) {
            final Encryptor decryptor = getDecryptor();
            decryptedValue = decryptor.decrypt(value);
        } else {
            decryptedValue = "";
        }
        return decryptedValue;
    }

    /**
     * Prepares and returns a model builder for creating
     * a new converted model.
     *
     * @return the prepared model builder.
     */
    protected abstract ModelBuilder<T, ?> prepareBuilder();

    /**
     * Creates and returns the object for data decryption.
     *
     * @return The object for data decryption.
     * @see Base64Encryptor
     */
    private Encryptor getDecryptor() {
        if (isNull(this.decryptor)) {
            this.decryptor = new Base64Encryptor();
        }
        return this.decryptor;
    }
}
