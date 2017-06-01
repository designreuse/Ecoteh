package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.response.ResponseEntity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the class {@link ResponseEntity}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface ResponseService extends DataService<ResponseEntity> {

    /**
     * Initializes, updates and returns responseEntity with incoming id.
     *
     * @param id       the id of a responseEntity to update.
     * @param responseEntity the responseEntity to update.
     * @return The updating responseEntity with incoming id.
     */
    ResponseEntity update(long id, ResponseEntity responseEntity);

    /**
     * Sorts and returns responseEntities by date.
     *
     * @param responseEntities the responseEntities to sort.
     * @param revers    Sort in descending or ascending.
     * @return The sorted list of responseEntities.
     */
    List<ResponseEntity> sortByDate(Collection<ResponseEntity> responseEntities, boolean revers);

    /**
     * Sorts and returns responseEntities by date.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of responseEntities.
     */
    List<ResponseEntity> getAndSortByDate(boolean revers);

    /**
     * Filters and returns responseEntities by the incoming dates.
     *
     * @param responseEntities  the responseEntities to filter.
     * @param startDate  the initial date.
     * @param finishDate the  date.
     * @return The filtered list of responseEntities.
     */
    List<ResponseEntity> filterByDate(
            Collection<ResponseEntity> responseEntities,
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
    List<ResponseEntity> getAndFilterByDate(Date startDate, Date finishDate);
}
