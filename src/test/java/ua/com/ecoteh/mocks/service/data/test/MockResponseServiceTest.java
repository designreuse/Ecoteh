package ua.com.ecoteh.mocks.service.data.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.service.data.ResponseService;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.DATE;
import static ua.com.ecoteh.mocks.enity.MockModels.getResponse;
import static ua.com.ecoteh.mocks.enity.MockModels.getResponses;
import static ua.com.ecoteh.mocks.service.data.MockServices.getResponseService;

public final class MockResponseServiceTest extends MockDataServiceTest<Response> {

    private static ResponseService service;
    private static Response response;
    private static Collection<Response> responses;

    @BeforeClass
    public static void beforeClass() {
        service = getResponseService();
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
    public void whenSortEmptyCollectionByDateThenReturnEmptyCollection() {
        Collection<Response> sortedResponses = service.sortByDate(new ArrayList<>(), true);
        assertTrue(sortedResponses.isEmpty());
        sortedResponses = service.sortByDate(new ArrayList<>(), false);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenSortNullCollectionByDateThenReturnEmptyCollection() {
        Collection<Response> sortedResponses = service.sortByDate(null, true);
        assertTrue(sortedResponses.isEmpty());
        sortedResponses = service.sortByDate(null, false);
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
        responses = service.getAndSortByDate(false);
        sortedResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByDateThenReturnNotEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(responses, DATE, DATE);
        assertFalse(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterByDateThenReturnCollectionWithNotNullObjects() {
        final Collection<Response> sortedResponses = service.filterByDate(responses, DATE, DATE);
        sortedResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterEmptyCollectionByDateThenReturnEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(new ArrayList<>(), DATE, DATE);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateThenReturnEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(null, DATE, DATE);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnNotEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(responses, null, DATE);
        assertFalse(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnCollectionWithNotNullObjects() {
        final Collection<Response> sortedResponses = service.filterByDate(responses, null, DATE);
        sortedResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByDateWithNullEndDateThenReturnNotEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(responses, DATE, null);
        assertFalse(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullEndDateThenReturnCollectionWithNotNullObjects() {
        final Collection<Response> sortedResponses = service.filterByDate(responses, DATE, null);
        sortedResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByDateWithNullDatesThenReturnNotEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(responses, null, null);
        assertFalse(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullDatesThenReturnCollectionWithNotNullObjects() {
        final Collection<Response> sortedResponses = service.filterByDate(responses, null, null);
        sortedResponses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterEmptyCollectionByDateWithNullStartDateThenReturnEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(new ArrayList<>(), null, DATE);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateWithNullStartDateThenReturnEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(null, null, DATE);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateWithNullEndDateThenReturnEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(new ArrayList<>(), DATE, null);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateWithNullEndDateThenReturnEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(null, DATE, null);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateWithNullDatesThenReturnEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(new ArrayList<>(), null, null);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateWithNullDatesThenReturnEmptyCollection() {
        final Collection<Response> sortedResponses = service.filterByDate(null, null, null);
        assertTrue(sortedResponses.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateTheReturnNotEmptyCollection() {
        final Collection<Response> responses = service.getAndFilterByDate(DATE, DATE);
        assertFalse(responses.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateTheReturnCollectionWithNotNullObjects() {
        final Collection<Response> responses = service.getAndFilterByDate(DATE, DATE);
       responses.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByDateWithNullStartDateTheReturnEmptyCollection() {
        final Collection<Response> responses = service.getAndFilterByDate(null, DATE);
        assertTrue(responses.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullEndDateTheReturnEmptyCollection() {
        final Collection<Response> responses = service.getAndFilterByDate(DATE, null);
        assertTrue(responses.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullDatesTheReturnEmptyCollection() {
        final Collection<Response> responses = service.getAndFilterByDate(null, null);
        assertTrue(responses.isEmpty());
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
    public void whenFilterEmptyCollectionByValidThenReturnEmptyCollection() {
        final Collection<Response> filteredResponses = service.filterByValid(new ArrayList<>());
        assertTrue(filteredResponses.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByValidThenReturnEmptyCollection() {
        final Collection<Response> filteredResponses = service.filterByValid(null);
        assertTrue(filteredResponses.isEmpty());
    }

    @Ignore
    @Override
    protected ResponseService getService() {
        return service;
    }

    @Ignore
    @Override
    protected Response getObject() {
        return response;
    }

    @Ignore
    @Override
    protected Collection<Response> getObjects() {
        return responses;
    }
}
