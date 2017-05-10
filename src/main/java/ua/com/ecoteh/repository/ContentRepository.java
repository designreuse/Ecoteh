package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.Content;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The interface provides a set of standard JPA methods
 * for working objects which extends {@link Content} with a database.
 *
 * @param <T> Entity type, extends {@link Content}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@NoRepositoryBean
public interface ContentRepository<T extends Content> extends DataRepository<T> {

    /**
     * Returns content object of the {@link Content} class or subclasses
     * with the parameter title from a database.
     *
     * @param title the title of a content object to return.
     * @return The content object with parameter title.
     */
    T findByTitle(String title);

    /**
     * Returns content object of the {@link Content} class or subclasses
     * with the parameter url from a database.
     *
     * @param url the URL of a content object to return.
     * @return The content object with parameter url.
     */
    T findByUrl(String url);

    /**
     * Removes content object of the {@link Content} class or subclasses
     * with the parameter title from a database.
     *
     * @param title the title of a content object to remove.
     */
    void deleteByTitle(String title);

    /**
     * Removes content object with the parameter url from a database.
     *
     * @param url the URL of a content object to remove.
     */
    void deleteByUrl(String url);
}
