package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Response;
import ua.com.ecoteh.repository.ResponseRepository;
import ua.com.ecoteh.util.comparator.ResponseComparator;
import ua.com.ecoteh.util.time.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param repository a implementation of the {@link ResponseRepository} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ResponseServiceImpl(final ResponseRepository repository) {
        super(repository);
    }

    /**
     * Initializes, updates and returns response with parameter id.
     *
     * @param id       a id of the response to update.
     * @param response a response to update.
     * @return The updating response with parameter id.
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
     *
     * @param responses the responses to sort.
     * @param revers    is sort in descending or ascending.
     * @return The sorted list of responses.
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
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of responses.
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
     *
     * @param responses  the responses to filter.
     * @param startDate  a initial date.
     * @param finishDate a final date.
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
                                .filter(
                                        response -> compareToDate(response, startDate, finishDate)
                                ).collect(Collectors.toList())
                );
            } else {
                result.addAll(responses);
            }
        }
        return result;
    }

    /**
     * Filters and returns responses by the date.
     *
     * @param startDate  a initial date.
     * @param finishDate a final date.
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
     * Returns empty responses list if responses is empty.
     *
     * @param responses the responses to filter.
     * @return The list of responses.
     */
    @Override
    public List<Response> filteredByValid(Collection<Response> responses) {
        List<Response> result = new ArrayList<>();
        if (isNotEmpty(responses)) {
            result.addAll(
                    responses.stream()
                            .filter(
                                    ResponseServiceImpl::validFilter
                            ).collect(Collectors.toList())
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
     * Dates must be not null
     * and not equals between themselves.
     *
     * @param startDate  a initial date.
     * @param finishDate a final date.
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
     * @param startDate  a initial date.
     * @param finishDate a final date.
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

    /**
     * Filters response by validation.
     *
     * @param response a response to filter.
     * @return true if response is not null and is valid, false otherwise.
     */
    private static boolean validFilter(final Response response) {
        return isNotNull(response) && response.isValidated();
    }
}
