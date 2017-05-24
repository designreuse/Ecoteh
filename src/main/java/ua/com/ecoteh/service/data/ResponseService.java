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
 */
public interface ResponseService extends DataService<Response> {

    /**
     * Initializes, updates and returns response with incoming id.
     *
     * @param id       the id of a response to update.
     * @param response the response to update.
     * @return The updating response with incoming id.
     */
    Response update(long id, Response response);

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
     * @param responses  the responses to filter.
     * @param startDate  the initial date.
     * @param finishDate the  date.
     * @return The filtered list of responses.
     */
    List<Response> filterByDate(
            Collection<Response> responses,
            Date startDate,
            Date finishDate
    );

    /**
     * Filters and returns responses by the incoming dates.
     *
     * @param startDate  the initial date.
     * @param finishDate the  date.
     * @return The filtered list of responses.
     */
    List<Response> getAndFilterByDate(Date startDate, Date finishDate);
}
