package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.util.properties.ContentProperties;

import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.properties.MockContentProperties.getContentProperties;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class StyleServiceImplTest {

    private static StyleService service;

    @BeforeClass
    public static void beforeClass() {
        final ContentProperties properties = getContentProperties();
        service = new StyleServiceImpl(properties);
    }

    @Test
    public void whenGetThenReturnDoIt() {
       service.get();
    }

    @Test
    public void whenSaveNullThenDoNothing() {
        service.save(null);
    }

    @Test
    public void whenSaveEmptyThenDoNothing() {
        service.save("");
    }

    @Test
    public void whenSaveThenDoIt() {
        service.save(ANY_STRING);
    }

    @Test
    public void whenRollbackThenDoIt() {
        service.rollback();
    }
}
