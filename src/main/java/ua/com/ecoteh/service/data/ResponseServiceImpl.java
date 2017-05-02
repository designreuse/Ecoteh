package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.Response;
import ua.com.ecoteh.repository.ResponseRepository;
import ua.com.ecoteh.util.comparator.ResponseComparator;
import ua.com.ecoteh.util.time.Time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Response} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Service
@ComponentScan(basePackages = "ua.com.ecoteh.repository")
public final class ResponseServiceImpl extends DataServiceImpl<Response> implements ResponseService {

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
     * Updates and returns response with incoming id.
     *
     * @param id       the id of the response to update.
     * @param response the response to update.
     * @return The updating response with parameter id (newer null).
     */
    @Override
    @Transactional
    public Response update(
            final long id,
            final Response response
    ) {
        return update(get(id).initialize(response));
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
    public List<Response> sortByDate(
            final Collection<Response> responses,
            final boolean revers
    ) {
        return sort(responses, new ResponseComparator.ByDate(), revers);
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
        return sortByDate(getAll(), revers);
    }

    /**
     * Filters and returns responses by the date.
     * Return empty list if responses is empty.
     * Return back responses list if dates are null
     * or start date is equals end to finish date.
     *<pre>
     *     filterByDate(null, ..., ...) = empty ArrayList()
     *     filterByDate(new ArrayList(), ..., ...) = empty ArrayList()
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new Response());
     *     filterByDate(collection, null, null) = collection
     *     filterByDate(collection, new Date(), null) = collection
     *     filterByDate(collection, null, new Date()) = collection
     *
     *     Date startDate = new Date();
     *     Date finishDate = new Date();
     *     if startDate greater than finishDate:
     *     filterByDate(collection, startDate, finishDate) = collection
     *     if finishDate greater than startDate:
     *     filterByDate(collection, startDate, finishDate) = filtered list of responses
     * </pre>
     *
     * @param responses  the responses to filter.
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return The filtered list of responses.
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
     * Filters and returns responses by the date.
     * <pre>
     *     getAndFilterByDate(null, null) = all responses
     *     getAndFilterByDate(new Date(), null) = all articles
     *     getAndFilterByDate(null, new Date()) = all responses
     *
     *     Date startDate = new Date();
     *     Date finishDate = new Date();
     *     if startDate greater than finishDate:
     *     filterByDate(startDate, finishDate) = all responses
     *     if finishDate greater than startDate:
     *     filterByDate(startDate, finishDate) = filtered list of responses
     * </pre>
     *
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return The filtered list of responses.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Response> getAndFilterByDate(
            final Date startDate,
            final Date finishDate
    ) {
        return filterByDate(getAll(), startDate, finishDate);
    }

    /**
     * Returns a list valid responses.
     * Returns empty list if responses is empty.
     * <pre>
     *     filteredByValid(null) = empty ArrayList()
     *     filteredByValid(new ArrayList()) = empty ArrayList()
     *
     *     Collection responses = new ArrayList();
     *     Response response = new Response();
     *     response.setValidated(false);
     *     responses.add(response);
     *     filteredByValid(responses) = empty ArrayList()
     *
     *     response.setValidated(true);
     *     filteredByValid(responses) = filtered list of articles
     * </pre>
     *
     * @param responses the articles to filter.
     * @return The filtered list of responses or empty list (newer null).
     */
    @Override
    public List<Response> filteredByValid(Collection<Response> responses) {
        List<Response> result = new ArrayList<>();
        if (isNotEmpty(responses)) {
            result.addAll(
                    responses.stream()
                            .filter(ResponseServiceImpl::isValidated)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Return Class object of {@link Response} class.
     *
     * @return The Class object of {@link Response} class.
     */
    @Override
    protected Class<Response> getModelClass() {
        return Response.class;
    }

    /**
     * Validates input response.
     *
     * @param response  the response to valid.
     * @param exist     is validate input object by exists.
     * @param duplicate is validate input object by duplicate.
     * @return Returns true if response is valid, otherwise returns false.
     */
    @Override
    protected boolean validated(
            final Response response,
            final boolean exist,
            final boolean duplicate
    ) {
        boolean result = false;
        if (isNotNull(response)) {
            result = true;
            if (exist) {
                result = exists(response);
            }
        }
        return result;
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
    private static boolean checkDate(
            final Date startDate,
            final Date finishDate
    ) {
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
    private static boolean compareToDate(
            final Response response,
            final Date startDate,
            final Date finishDate
    ) {
        return (response.getDate().compareTo(startDate) == 1) &&
                (response.getDate().compareTo(finishDate) == -1);
    }
}
