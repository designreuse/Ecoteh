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
 */
public interface ResponseService extends DataService<Response> {

    /**
     * Initializes, updates and returns response with parameter id.
     *
     * @param id       a id of the response to update.
     * @param response a response to update.
     * @return The updating response with parameter id.
     */
    Response update(
            final long id,
            final Response response
    );

    /**
     * Sorts and returns responses by date.
     *
     * @param responses the responses to sort.
     * @param revers    Sort in descending or ascending.
     * @return The sorted list of responses.
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
     */
    List<Response> getAndSortByDate(final boolean revers);

    /**
     * Filters and returns responses by the date.
     *
     * @param responses  the responses to filter.
     * @param startDate  a initial date.
     * @param finishDate a final date.
     * @return The filtered list of responses.
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
     */
    List<Response> getAndFilterByDate(
            final Date startDate,
            final Date finishDate
    );
}
