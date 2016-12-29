package com.salimov.yurii.repository;

import com.salimov.yurii.entity.Section;

/**
 * The interface provides a set of JPA methods
 * for working {@link Section} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataRepository
 * @see Section
 */
public interface SectionRepository extends DataRepository<Section, Long> {

    /**
     * Returns section from a database,
     * which matches the parameter title.
     *
     * @param title a title of the section to return.
     * @return The object of class {@link Section}.
     * @see Section
     */
    Section findByTitle(final String title);

    /**
     * Returns section from a database,
     * which matches the parameter url.
     *
     * @param url a url of the section to return.
     * @return The object of class {@link Section}.
     * @see Section
     */
    Section findByUrl(final String url);

    /**
     * Removes section from a database,
     * which matches the parameter title.
     *
     * @param title a title of the section to remove.
     * @see Section
     */
    void deleteByTitle(final String title);

    /**
     * Removes section from a database,
     * which matches the parameter url.
     *
     * @param url a url of the section to remove.
     * @see Section
     */
    void deleteByUrl(final String url);
}
