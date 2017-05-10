package ua.com.ecoteh.service.data;

/**
 * The interface of the service layer, describes a set of methods
 * for working with CSS styles.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
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
     */
    void save(String styles);

    /**
     * Rollbacks a CSS styles.
     */
    void rollback();
}
