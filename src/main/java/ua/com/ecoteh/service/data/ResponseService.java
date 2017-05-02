package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Response;

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
     * Initializes, updates and returns response with incoming id.
     *
     * @param id       the id of a response to update.
     * @param response the response to update.
     * @return The updating response with incoming id.
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
     * Filters and returns responses by the incoming dates.
     *
     * @param responses  the responses to filter.
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return The filtered list of responses.
     */
    List<Response> filterByDate(
            final Collection<Response> responses,
            final Date startDate,
            final Date finishDate
    );

    /**
     * Filters and returns responses by the incoming dates.
     *
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return The filtered list of responses.
     */
    List<Response> getAndFilterByDate(
            final Date startDate,
            final Date finishDate
    );
}
