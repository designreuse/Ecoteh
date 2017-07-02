package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.repository.ResponseRepository;
import ua.com.ecoteh.service.data.comparator.ResponseComparator;
import ua.com.ecoteh.util.time.Time;

import java.util.*;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Response} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Response
 * @see ResponseEntity
 */
@Service
@ComponentScan(basePackages = "ua.com.ecoteh.repository")
public final class ResponseServiceImpl extends DataServiceImpl<Response, ResponseEntity> implements ResponseService {

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
     * Sorts and returns responses by date.
     * For sorting used {@link ResponseComparator.ByDate} comparator.
     *
     * @param responses the responses to sort.
     * @param revers    is sort in descending or ascending.
     * @return The sorted list of responses (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Response> sortByDate(final Collection<Response> responses, final boolean revers) {
        final Comparator<Response> comparator = new ResponseComparator.ByDate();
        return sort(responses, comparator, revers);
    }

    /**
     * Sorts and returns responses by date.
     * For sorting used {@link ResponseComparator.ByDate} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of responses (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Response> getAndSortByDate(final boolean revers) {
        final Collection<Response> responses = getAll();
        return sortByDate(responses, revers);
    }

    /**
     * Filters and returns responses by the date.
     * Return empty list if responses is empty.
     * Return back responses list if dates are null
     * or start date is equals end to finish date.
     * <pre>
     *     filterByDate(null, ..., ...) = empty ArrayList()
     *     filterByDate(new ArrayList(), ..., ...) = empty ArrayList()
     *
     *     if the incoming collection is not empty
     *     filterByDate(collection, null, null) = collection
     *     filterByDate(collection, new Date(), null) = collection
     *     filterByDate(collection, null, new Date()) = collection
     *
     *     Date start = new Date();
     *     Date finish = new Date();
     *     if start date greater than finishDate:
     *     filterByDate(collection, start, finish) = collection
     *     if finish date greater than start date:
     *     filterByDate(collection, start, finish) = filtered list of responses
     * </pre>
     *
     * @param responses the responses to filter.
     * @param start     the initial date.
     * @param finish    the final date.
     * @return The filtered list of responses.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Response> filterByDate(
            final Collection<Response> responses,
            final Date start, final Date finish
    ) {
        final List<Response> result = new ArrayList<>();
        if (isNotEmpty(responses)) {
            if (checkDate(start, finish)) {
                result.addAll(
                        responses.stream()
                                .filter(response -> compareToDate(response, start, finish))
                                .collect(Collectors.toList())
                );
            } else {
                result.addAll(responses);
            }
        }
        return result;
    }

    /**
     * Filters and returns responses by the date.
     * <pre>
     *     getAndFilterByDate(null, null) = all responses
     *     getAndFilterByDate(new Date(), null) = all articles
     *     getAndFilterByDate(null, new Date()) = all responses
     *
     *     Date start = new Date();
     *     Date finish = new Date();
     *     if start date greater than finish date:
     *     filterByDate(start, finish) = all responses
     *     if finish date greater than start date:
     *     filterByDate(start, finish) = filtered list of responses
     * </pre>
     *
     * @param start  the initial date.
     * @param finish the final date.
     * @return The filtered list of responses.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Response> getAndFilterByDate(final Date start, final Date finish) {
        final Collection<Response> responses = getAll();
        return filterByDate(responses, start, finish);
    }

    /**
     * Returns a list valid responses.
     * Returns empty list if responses is empty.
     * <pre>
     *     filteredByValid(null) = empty ArrayList()
     *     filteredByValid(new ArrayList()) = empty ArrayList()
     *
     *     Collection responses = new ArrayList();
     *     ResponseEntity responseEntity = new ResponseEntity();
     *     responseEntity.setValidated(false);
     *     responses.add(responseEntity);
     *     filteredByValid(responses) = empty ArrayList()
     *
     *     responseEntity.setValidated(true);
     *     filteredByValid(responses) = filtered list of articles
     * </pre>
     *
     * @param responses the articles to filter.
     * @return The filtered list of responses or empty list (newer null).
     */
    @Override
    public List<Response> filterByValid(final Collection<Response> responses) {
        final List<Response> result = new ArrayList<>();
        if (isNotEmpty(responses)) {
            result.addAll(
                    responses.stream()
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
     * @param start  the initial date.
     * @param finish the final date.
     * @return true if dates are correct, false otherwise.
     */
    private boolean checkDate(final Date start, final Date finish) {
        return Time.checkDate(start, finish);
    }

    /**
     * Compares response object date to input dates.
     *
     * @param response the response to compare.
     * @param start    the initial date.
     * @param finish   the final date.
     * @return true or false.
     */
    private boolean compareToDate(final Response response, final Date start, final Date finish) {
        final Date responseDate = response.getDate();
        return (responseDate.compareTo(start) == 1) && (responseDate.compareTo(finish) == -1);
    }
}
