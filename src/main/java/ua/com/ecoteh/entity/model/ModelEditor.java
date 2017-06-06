package ua.com.ecoteh.entity.model;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelEditor<T extends Model, R extends ModelEditor<T, R>> implements Editor<T> {

    private final T model;

    /**
     * The unique identifier for each object.
     */
    private long id;

    /**
     * The value of validations of the model.
     */
    private int validated;

    /**
     * Constructor.
     * @param model
     */
    protected ModelEditor(final T model) {
        this.model = model;
    }

    @Override
    public R copy(final T model) {
        return addId(model.getId())
                .addValidated(model.isValidated());
    }

    public R addId(final long id) {
        this.id = id;
        return (R) this;
    }

    public R addValidated(final boolean validated) {
        this.validated = validated ? 1 : 2;
        return (R) this;
    }

    public R isValid() {
        return addValidated(true);
    }

    public R isNotValid() {
        return addValidated(false);
    }

    protected long getId() {
        return (this.id > 0) ? this.id : this.model.getId();
    }

    protected boolean isValidated() {
        boolean result;
        if (this.validated > 0) {
            result = (this.validated == 1);
        } else {
            result = this.model.isValidated();
        }
        return result;
    }
}
