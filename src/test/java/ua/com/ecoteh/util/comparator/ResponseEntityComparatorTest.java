package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Comparator;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class ResponseEntityComparatorTest {

    @Test
    public void responseComparator() {
        ResponseComparator comparator = new ResponseComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getResponseComparatorByDate() throws InterruptedException {
        Comparator<ResponseEntity> comparator = new ResponseComparator.ByDate();
        assertNotNull(comparator);

        ResponseEntity responseEntity1 = MockEntity.getResponseEntity();
        ResponseEntity responseEntity2 = MockEntity.getResponseEntity();
        int value = comparator.compare(responseEntity1, responseEntity2);
        assertNotNull(value);
        assertEquals(value, 0);

        final Date date1 = new Date();
        Thread.sleep(100);
        final Date date2 = new Date();

        responseEntity1.setDate(date1);
        responseEntity2.setDate(date2);
        value = comparator.compare(responseEntity1, responseEntity2);
        assertNotNull(value);
        assertEquals(value, -1);

        responseEntity1.setDate(date2);
        responseEntity2.setDate(date1);
        value = comparator.compare(responseEntity1, responseEntity2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getResponseComparatorByDateWithInvalidResponses() {
        Comparator<ResponseEntity> comparator = new ResponseComparator.ByDate();
        getResponseComparatorWithInvalidResponses(comparator);
    }

    @Ignore
    private static void getResponseComparatorWithInvalidResponses(Comparator<ResponseEntity> comparator) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final ResponseEntity responseEntity = MockEntity.getResponseEntity();
        value = comparator.compare(responseEntity, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, responseEntity);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
