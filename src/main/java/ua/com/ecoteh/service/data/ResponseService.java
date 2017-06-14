package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.response.Response;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the class {@link Response}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Response
 */
public interface ResponseService extends DataService<Response> {

    /**
     * Sorts and returns responses by date.
     *
     * @param responses the responses to sort.
     * @param revers    Sort in descending or ascending.
     * @return The sorted list of responses.
     */
    List<Response> sortByDate(Collection<Response> responses, boolean revers);

    /**
     * Sorts and returns responses by date.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of responses.
     */
    List<Response> getAndSortByDate(boolean revers);

    /**
     * Filters and returns responses by the incoming dates.
     *
     * @param responses the responses to filter.
     * @param start     the initial date.
     * @param finish    the final date.
     * @return The filtered list of responses.
     */
    List<Response> filterByDate(Collection<Response> responses, Date start, Date finish);

    /**
     * Filters and returns responses by the incoming dates.
     *
     * @param start  the initial date.
     * @param finish the final date.
     * @return The filtered list of responses.
     */
    List<Response> getAndFilterByDate(Date start, Date finish);
}
