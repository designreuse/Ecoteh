package ua.com.ecoteh.service.data;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.entity.response.ResponseBuilder;
import ua.com.ecoteh.repository.ResponseRepository;
import ua.com.ecoteh.service.data.comparator.ResponseComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.UNKNOWN_ID;
import static ua.com.ecoteh.mocks.enity.MockModels.getResponse;
import static ua.com.ecoteh.mocks.enity.MockModels.getResponses;
import static ua.com.ecoteh.mocks.repository.MockRepository.getResponseRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ResponseServiceImplTest extends DataServiceImplTest<Response> {

    private static ResponseService service;
    private static Response response;
    private static Collection<Response> responses;

    @BeforeClass
    public static void beforeClass() {
        final ResponseRepository repository = getResponseRepository();
        service = new ResponseServiceImpl(repository);
        response = getResponse();
        responses = getResponses();
    }

    @Test
    public void whenSortByDateThenReturnNotEmptyCollection() {
        Collection<Response> sortedResponses = service.sortByDate(responses, true);
        assertFalse(sortedResponses.isEmpty());
        sortedResponses = service.sortByDate(responses, false);
        assertFalse(sortedResponses.isEmpty());
    }

    @Test
    public void whenSortByDateThenReturnCollectionWithNotNullObjects() {
        Collection<Response> sortedResponses = service.sortByDate(responses, true);
        sortedResponses.forEach(Assert::assertNotNull);
        sortedResponses = service.sortByDate(responses, false);
        sortedResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortNullCollectionByDateThenReturnEmptyCollection() {
        Collection<Response> sortedResponses = service.sortByDate(null, true);
        assertTrue(sortedResponses.isEmpty());
        sortedResponses = service.sortByDate(null, false);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionByDateThenReturnEmptyCollection() {
        Collection<Response> sortedResponses = service.sortByDate(new ArrayList<>(), true);
        assertTrue(sortedResponses.isEmpty());
        sortedResponses = service.sortByDate(new ArrayList<>(), false);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenGetAndSortByDateThenReturnNotEmptyCollection() {
        Collection<Response> sortedResponses = service.getAndSortByDate(true);
        assertFalse(sortedResponses.isEmpty());
        sortedResponses = service.getAndSortByDate(false);
        assertFalse(sortedResponses.isEmpty());
    }

    @Test
    public void whenGetAndSortByDateThenReturnCollectionWithNotNullObjects() {
        Collection<Response> sortedResponses = service.getAndSortByDate(true);
        sortedResponses.forEach(Assert::assertNotNull);
        sortedResponses = service.getAndSortByDate(false);
        sortedResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByDateThenReturnNotEmptyCollection() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Response> filteredResponses = service.filterByDate(responses, start, finish);
        assertFalse(filteredResponses.isEmpty());
    }

    @Test
    public void whenFilterByDateThenReturnCollectionWithNotNullObjects() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Response> filteredResponses = service.filterByDate(responses, start, finish);
        filteredResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterNullCollectionByDateThenReturnEmptyCollection() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Response> filteredResponses = service.filterByDate(null, start, finish);
        assertTrue(filteredResponses.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateThenReturnEmptyCollection() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Response> filteredResponses = service.filterByDate(new ArrayList<>(), start, finish);
        assertTrue(filteredResponses.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnNotEmptyCollection() {
        final Date start = null;
        final Date finish = new Date();
        final Collection<Response> filteredResponses = service.filterByDate(responses, start, finish);
        assertFalse(filteredResponses.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullFinishDateThenReturnNotEmptyCollection() {
        final Date start = new Date();
        final Date finish = null;
        final Collection<Response> filteredResponses = service.filterByDate(responses, start, finish);
        assertFalse(filteredResponses.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullDatesThenReturnNotEmptyCollection() {
        final Date start = null;
        final Date finish = null;
        final Collection<Response> filteredResponses = service.filterByDate(responses, start, finish);
        assertFalse(filteredResponses.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateThenReturnNotEmptyCollection() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Response> filteredResponses = service.getAndFilterByDate(start, finish);
        assertFalse(filteredResponses.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateThenReturnCollectionWithNotNullObjects() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Response> filteredResponses = service.getAndFilterByDate(start, finish);
        filteredResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByDateWithNullStartDateThenReturnNotEmptyCollection() {
        final Date start = null;
        final Date finish = new Date();
        final Collection<Response> filteredResponses = service.getAndFilterByDate(start, finish);
        assertFalse(filteredResponses.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullStartDateThenReturnCollectionWithNotNullObjects() {
        final Date start = null;
        final Date finish = new Date();
        final Collection<Response> filteredResponses = service.getAndFilterByDate(start, finish);
        filteredResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByDateWithNullFinishDateThenReturnNotEmptyCollection() {
        final Date start = new Date();
        final Date finish = null;
        final Collection<Response> filteredResponses = service.getAndFilterByDate(start, finish);
        assertFalse(filteredResponses.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullFinishDateThenReturnCollectionWithNotNullObjects() {
        final Date start = new Date();
        final Date finish = null;
        final Collection<Response> filteredResponses = service.getAndFilterByDate(start, finish);
        filteredResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByDateWithNullDatesThenReturnNotEmptyCollection() {
        final Date start = null;
        final Date finish = null;
        final Collection<Response> filteredResponses = service.getAndFilterByDate(start, finish);
        assertFalse(filteredResponses.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullDatesThenReturnCollectionWithNotNullObjects() {
        final Date start = null;
        final Date finish = null;
        final Collection<Response> filteredResponses = service.getAndFilterByDate(start, finish);
        filteredResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByValidThenReturnNotEmptyCollection() {
        final Collection<Response> filteredResponses = service.filterByValid(responses);
        assertFalse(filteredResponses.isEmpty());
    }

    @Test
    public void whenFilterByValidThenReturnCollectionWithNotNullObjects() {
        final Collection<Response> filteredResponses = service.filterByValid(responses);
        filteredResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterNullCollectionByValidThenReturnEmptyCollection() {
        final Collection<Response> filteredResponses = service.filterByValid(null);
        assertTrue(filteredResponses.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByValidThenReturnEmptyCollection() {
        final Collection<Response> filteredResponses = service.filterByValid(new ArrayList<>());
        assertTrue(filteredResponses.isEmpty());
    }

    @Override
    protected ResponseService getService() {
        return service;
    }

    @Override
    protected Response getModel() {
        return response;
    }

    @Override
    protected Collection<Response> getModels() {
        return responses;
    }

    @Override
    protected Response getUnknownModel() {
        final ResponseBuilder builder = Response.getBuilder();
        builder.addId(UNKNOWN_ID);
        return builder.build();
    }

    @Override
    protected Comparator<Response> getComparator() {
        return new ResponseComparator.ByDate();
    }
}
