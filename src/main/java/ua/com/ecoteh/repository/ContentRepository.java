package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.content.ContentEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The interface provides a set of standard JPA methods
 * for working objects which extends {@link ContentEntity} with a database.
 *
 * @param <E> Entity type, extends {@link ContentEntity}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@NoRepositoryBean
public interface ContentRepository<E extends ContentEntity> extends DataRepository<E> {

    /**
     * Returns content object of the {@link ContentEntity} class or subclasses
     * with the parameter title from a database.
     *
     * @param title the title of a content object to return.
     * @return The content object with parameter title.
     */
    E findByTitle(String title);

    /**
     * Returns content object of the {@link ContentEntity} class or subclasses
     * with the parameter url from a database.
     *
     * @param url the URL of a content object to return.
     * @return The content object with parameter url.
     */
    E findByUrl(String url);

    /**
     * Removes content object of the {@link ContentEntity} class or subclasses
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
