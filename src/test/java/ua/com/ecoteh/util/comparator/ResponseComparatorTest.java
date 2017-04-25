package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.Response;
import ua.com.ecoteh.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Comparator;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class ResponseComparatorTest {

    @Test
    public void responseComparator() {
        ResponseComparator comparator = new ResponseComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getResponseComparatorByDate() throws InterruptedException {
        Comparator<Response> comparator = new ResponseComparator.ByDate();
        assertNotNull(comparator);

        Response response1 = MockEntity.getResponse();
        Response response2 = MockEntity.getResponse();
        int value = comparator.compare(response1, response2);
        assertNotNull(value);
        assertEquals(value, 0);

        final Date date1 = new Date();
        Thread.sleep(100);
        final Date date2 = new Date();

        response1.setDate(date1);
        response2.setDate(date2);
        value = comparator.compare(response1, response2);
        assertNotNull(value);
        assertEquals(value, -1);

        response1.setDate(date2);
        response2.setDate(date1);
        value = comparator.compare(response1, response2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getResponseComparatorByDateWithInvalidResponses() {
        Comparator<Response> comparator = new ResponseComparator.ByDate();
        getResponseComparatorWithInvalidResponses(comparator);
    }

    @Ignore
    private static void getResponseComparatorWithInvalidResponses(Comparator<Response> comparator) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final Response response = MockEntity.getResponse();
        value = comparator.compare(response, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, response);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
