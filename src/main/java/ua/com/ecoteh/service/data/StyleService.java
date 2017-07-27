package ua.com.ecoteh.service.data;

/**
 * The interface of the service layer, describes a set of methods
 * for working with CSS styles.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface StyleService {

    /**
     * Returns a CSS styles of the site.
     *
     * @return The styles.
     */
    String get();

    /**
     * Saves a new CSS styles of the site.
     *
     * @param styles the new CSS styles to save.
     * @return true if styles is saved, false otherwise.
     */
    boolean save(String styles);

    /**
     * Rollbacks a CSS styles.
     *
     * @return true if styles is rollback, false otherwise.
     */
    boolean rollback();
}
