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
 */
public interface ResponseService extends DataService<Response> {

    /**
     * Sorts and returns responseEntities by date.
     *
     * @param responseEntities the responseEntities to sort.
     * @param revers    Sort in descending or ascending.
     * @return The sorted list of responseEntities.
     */
    List<Response> sortByDate(Collection<Response> responseEntities, boolean revers);

    /**
     * Sorts and returns responseEntities by date.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of responseEntities.
     */
    List<Response> getAndSortByDate(boolean revers);

    /**
     * Filters and returns responseEntities by the incoming dates.
     *
     * @param responseEntities  the responseEntities to filter.
     * @param startDate  the initial date.
     * @param finishDate the  date.
     * @return The filtered list of responseEntities.
     */
    List<Response> filterByDate(
            Collection<Response> responseEntities,
            Date startDate,
            Date finishDate
    );

    /**
     * Filters and returns responseEntities by the incoming dates.
     *
     * @param startDate  the initial date.
     * @param finishDate the  date.
     * @return The filtered list of responseEntities.
     */
    List<Response> getAndFilterByDate(Date startDate, Date finishDate);
}
