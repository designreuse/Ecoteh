package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.Response;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.enity.MockEntity.getResponse;
import static ua.com.ecoteh.mocks.enity.MockEntity.getResponses;

public final class ResponseServiceImplTest extends DataServiceImplTest<Response> {

    private static ResponseService service;

    @BeforeClass
    public static void beforeTest() {
        service = new ResponseServiceImpl(MockRepository.getResponseRepository());
    }

    @Test
    public void whenAddTheReturnSomeResponse() {
        assertNotNull(service.add(getResponse()));
    }

    @Test
    public void whenUpdateTheReturnSomeResponse() {
        assertNotNull(service.update(ID, getResponse()));
    }

    @Test
    public void whenSortByDateWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(service.sortByDate(null, true).isEmpty());
    }

    @Test
    public void whenSortByDateWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(service.sortByDate(null, false).isEmpty());
    }

    @Test
    public void whenSortByDateWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                service.sortByDate(
                        new ArrayList<>(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByDateWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                service.sortByDate(
                        new ArrayList<>(), false
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByDateWithTrueReversThenReturnSomeList() {
        assertFalse(service.getAndSortByDate(true).isEmpty());
    }

    @Test
    public void whenGetAndSortByDateWithFalseReversThenReturnSomeList() {
        assertFalse(service.getAndSortByDate(false).isEmpty());
    }

    @Test
    public void whenFilteredByDateWithNullCollectionThenReturnEmptyList() {
        assertTrue(service.filterByDate(null, new Date(), new Date()).isEmpty());
    }

    @Test
    public void whenFilteredByDateWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(service.filterByDate(null, new Date(), new Date()).isEmpty());
    }

    @Test
    public void whenFilteredByDateWithNullStartDateThenReturnNotSortList() {
        assertFalse(service.filterByDate(getResponses(), null, new Date()).isEmpty());
    }

    @Test
    public void whenFilteredByDateWithNullFinishDateThenReturnNotFilterList() {
        assertFalse(service.filterByDate(getResponses(), new Date(), null).isEmpty());
    }

    @Test
    public void whenFilteredByDateWithEqualsDatesThenReturnNotFilterList() {
        final Date date = new Date();
        assertFalse(service.filterByDate(getResponses(), date, date).isEmpty());
    }

    @Test
    public void whenFilteredByDateWithStartDateGreatFinishDateThenReturnNotFilterList() {
        final Date finishDate = new Date();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date startDate = new Date();
        assertFalse(service.filterByDate(getResponses(), startDate, finishDate).isEmpty());
    }

    @Test
    public void whenFilteredByDateWithFinishDateGreatStartDateThenReturnNotFilterList() {
        List<Response> responses = getResponses();
        final Date startDate = new Date();
        try {
            for (Response response : responses) {
                response.setDate(new Date());
                Thread.sleep(100);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date finishDate = new Date();
        assertFalse(service.filterByDate(responses, startDate, finishDate).isEmpty());
    }

    @Test
    public void whenFilterByDateThenReturnSomeList() {
        assertFalse(service.filterByDate(getResponses(), new Date(), new Date()).isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullStartDateThenReturnNotFilterList() {
        assertFalse(service.getAndFilterByDate(null, new Date()).isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullFinishDateThenReturnNotFilterList() {
        assertFalse(service.getAndFilterByDate(new Date(), null).isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithEqualsDatesThenReturnNotFilterList() {
        final Date date = new Date();
        assertFalse(service.getAndFilterByDate(date, date).isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithStartDateGreatFinishDateThenReturnNotFilterList() {
        final Date finishDate = new Date();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date startDate = new Date();
        assertFalse(service.getAndFilterByDate(startDate, finishDate).isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateThenReturnSomeList() {
        assertFalse(service.getAndFilterByDate(new Date(), new Date()).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithNullCollectionThenReturnEmptyList() {
        assertTrue(service.filteredByValid(null).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(service.filteredByValid(new ArrayList<>()).isEmpty());
    }

    @Test
    public void whenFilteredByValidThenReturnSomeList() {
        assertFalse(service.filteredByValid(getResponses()).isEmpty());
    }

    @Ignore
    @Override
    protected ResponseService getService() {
        return service;
    }

    @Ignore
    @Override
    protected Response getObject() {
        return getResponse();
    }

    @Ignore
    @Override
    protected Collection<Response> getObjects() {
        return getResponses();
    }

    @Ignore
    @Override
    protected Response getInvalidObject() {
        return new Response();
    }
}
