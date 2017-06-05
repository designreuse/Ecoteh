package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.repository.ResponseRepository;
import ua.com.ecoteh.util.comparator.ResponseComparator;
import ua.com.ecoteh.util.time.Time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link ResponseEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(basePackages = "ua.com.ecoteh.repository")
public final class ResponseServiceImpl
        extends DataServiceImpl<Response, ResponseEntity>
        implements ResponseService {

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param repository the implementation of the {@link ResponseRepository} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ResponseServiceImpl(final ResponseRepository repository) {
        super(repository);
    }

    /**
     * Sorts and returns responseEntities by date.
     * For sorting used {@link ResponseComparator.ByDate} comparator.
     *
     * @param responses the responseEntities to sort.
     * @param revers    is sort in descending or ascending.
     * @return The sorted list of responseEntities (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Response> sortByDate(final Collection<Response> responses, final boolean revers) {
        return sort(responses, new ResponseComparator.ByDate(), revers);
    }

    /**
     * Sorts and returns responseEntities by date.
     * For sorting used {@link ResponseComparator.ByDate} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of responseEntities (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Response> getAndSortByDate(final boolean revers) {
        return sortByDate(getAll(), revers);
    }

    /**
     * Filters and returns responseEntities by the date.
     * Return empty list if responseEntities is empty.
     * Return back responseEntities list if dates are null
     * or start date is equals end to finish date.
     * <pre>
     *     filterByDate(null, ..., ...) = empty ArrayList()
     *     filterByDate(new ArrayList(), ..., ...) = empty ArrayList()
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new ResponseEntity());
     *     filterByDate(collection, null, null) = collection
     *     filterByDate(collection, new Date(), null) = collection
     *     filterByDate(collection, null, new Date()) = collection
     *
     *     Date startDate = new Date();
     *     Date finishDate = new Date();
     *     if startDate greater than finishDate:
     *     filterByDate(collection, startDate, finishDate) = collection
     *     if finishDate greater than startDate:
     *     filterByDate(collection, startDate, finishDate) = filtered list of responseEntities
     * </pre>
     *
     * @param responses  the responseEntities to filter.
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return The filtered list of responseEntities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Response> filterByDate(
            final Collection<Response> responses,
            final Date startDate,
            final Date finishDate
    ) {
        final List<Response> result = new ArrayList<>();
        if (isNotEmpty(responses)) {
            if (checkDate(startDate, finishDate)) {
                result.addAll(
                        responses.stream()
                                .filter(response -> compareToDate(response, startDate, finishDate))
                                .collect(Collectors.toList())
                );
            } else {
                result.addAll(responses);
            }
        }
        return result;
    }

    /**
     * Filters and returns responseEntities by the date.
     * <pre>
     *     getAndFilterByDate(null, null) = all responseEntities
     *     getAndFilterByDate(new Date(), null) = all articles
     *     getAndFilterByDate(null, new Date()) = all responseEntities
     *
     *     Date startDate = new Date();
     *     Date finishDate = new Date();
     *     if startDate greater than finishDate:
     *     filterByDate(startDate, finishDate) = all responseEntities
     *     if finishDate greater than startDate:
     *     filterByDate(startDate, finishDate) = filtered list of responseEntities
     * </pre>
     *
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return The filtered list of responseEntities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Response> getAndFilterByDate(final Date startDate, final Date finishDate) {
        return filterByDate(getAll(), startDate, finishDate);
    }

    /**
     * Returns a list valid responseEntities.
     * Returns empty list if responseEntities is empty.
     * <pre>
     *     filteredByValid(null) = empty ArrayList()
     *     filteredByValid(new ArrayList()) = empty ArrayList()
     *
     *     Collection responseEntities = new ArrayList();
     *     ResponseEntity responseEntity = new ResponseEntity();
     *     responseEntity.setValidated(false);
     *     responseEntities.add(responseEntity);
     *     filteredByValid(responseEntities) = empty ArrayList()
     *
     *     responseEntity.setValidated(true);
     *     filteredByValid(responseEntities) = filtered list of articles
     * </pre>
     *
     * @param responseEntities the articles to filter.
     * @return The filtered list of responseEntities or empty list (newer null).
     */
    @Override
    public List<Response> filteredByValid(final Collection<Response> responseEntities) {
        final List<Response> result = new ArrayList<>();
        if (isNotEmpty(responseEntities)) {
            result.addAll(
                    responseEntities.stream()
                            .filter(this::isValidated)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Return Class object of {@link ResponseEntity} class.
     *
     * @return The Class object of {@link ResponseEntity} class.
     */
    @Override
    Class<Response> getModelClass() {
        return Response.class;
    }

    /**
     * Checks a dates to correction.
     * Dates must be not null and not equals between themselves.
     * Used the checkDate() method of the {@link Time} class.
     *
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return true if dates are correct, false otherwise.
     */
    private boolean checkDate(final Date startDate, final Date finishDate) {
        return Time.checkDate(startDate, finishDate);
    }

    /**
     * Compares response object date to input dates.
     *
     * @param response   the response to compare.
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return true or false.
     */
    private boolean compareToDate(final Response response, final Date startDate, final Date finishDate) {
        return (response.getDate().compareTo(startDate) == 1) &&
                (response.getDate().compareTo(finishDate) == -1);
    }
}
