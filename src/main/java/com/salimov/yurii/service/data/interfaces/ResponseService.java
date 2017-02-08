package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Response;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the class {@link Response}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Response
 * @see DataService
 */
public interface ResponseService extends DataService<Response, Long> {

    /**
     * Initializes, saves and returns a new response.
     *
     * @param username a username of the new response.
     * @param text     a text of the new response.
     * @return The new saving response.
     * @see Response
     */
    Response initAndAdd(
            final String username,
            final String text
    );

    /**
     * Initializes, updates and returns response with parameter id.
     *
     * @param id       a id of the response to update.
     * @param username a new username of the response.
     * @param text     a new text of the response.
     * @return The updating response with parameter id.
     * @see Response
     */
    Response initAndUpdate(
            final Long id,
            final String username,
            final String text
    );

    /**
     * Sorts and returns responses by date.
     *
     * @param responses the responses to sort.
     * @param revers    Sort in descending or ascending.
     * @return The sorted list of responses.
     * @see Response
     */
    List<Response> sortByDate(
            final Collection<Response> responses,
            final boolean revers
    );

    /**
     * Sorts and returns responses by date.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of responses.
     * @see Response
     */
    List<Response> getAndSortByDate(final boolean revers);

    /**
     * Filters and returns responses by the date.
     *
     * @param responses  the responses to filter.
     * @param startDate  a initial date.
     * @param finishDate a final date.
     * @return The filtered list of responses.
     * @see Response
     */
    List<Response> filterByDate(
            final Collection<Response> responses,
            final Date startDate,
            final Date finishDate
    );

    /**
     * Filters and returns responses by the date.
     *
     * @param startDate  a initial date.
     * @param finishDate a final date.
     * @return The filtered list of responses.
     * @see Response
     */
    List<Response> getAndFilterByDate(
            final Date startDate,
            final Date finishDate
    );
}
