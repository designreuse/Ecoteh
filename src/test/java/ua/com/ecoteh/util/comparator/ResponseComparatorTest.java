package ua.com.ecoteh.util.comparator;

import org.junit.Test;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.entity.response.ResponseBuilder;

import java.util.Comparator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ResponseComparatorTest {

    @Test
    public void whenGetByDateComparatorThenReturnNotNull() {
        final Comparator<Response> byDateComparator = new ResponseComparator.ByDate();
        assertNotNull(byDateComparator);
    }

    @Test
    public void compareByDateTest() {
        final Comparator<Response> byDateComparator = new ResponseComparator.ByDate();
        final ResponseBuilder builder = Response.getBuilder();
        final Response first = builder.build();
        final Response second = builder.build();
        int compareValue;
        int temp = byDateComparator.compare(first, second);
        for (int i = 0; i < 10; i++) {
            compareValue = byDateComparator.compare(first, second);
            assertTrue(compareValue == temp);
            temp = compareValue;
        }
    }
}