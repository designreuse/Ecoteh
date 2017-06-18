package ua.com.ecoteh.util.cache;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class KeyComparatorTest {

    private static KeyComparator comparator;

    @BeforeClass
    public static void beforeClass() {
        comparator = new KeyComparator();
    }

    @Test
    public void whenCompareWithNullKeysThenReturnZero() {
        final Key first = null;
        final Key second = null;
        final int result = comparator.compare(first, second);
        assertTrue(result == 0);
    }

    @Test
    public void whenCompareWithNullFirstKeyThenReturnNegativeOne() {
        final Key first = null;
        final Key second = new Key<>(ANY_STRING);
        final int result = comparator.compare(first, second);
        assertTrue(result == -1);
    }

    @Test
    public void whenCompareWithNullSecondKeyThenReturnOne() {
        final Key first = new Key<>(ANY_STRING);
        final Key second = null;
        final int result = comparator.compare(first, second);
        assertTrue(result == 1);
    }

    @Test
    public void whenCompareThenReturnSomeValue() {
        final Key first = new Key<>(ANY_STRING);
        final Key second = new Key<>(ANY_STRING);
        final int result = comparator.compare(first, second);
    }
}