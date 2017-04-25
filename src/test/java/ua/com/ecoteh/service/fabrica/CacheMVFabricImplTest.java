package ua.com.ecoteh.service.fabrica;

import org.junit.Before;
import ua.com.ecoteh.mocks.service.fabrica.MockMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class CacheMVFabricImplTest extends MainMVFabricImplTest {

    @Before
    public void beforeTests() {
        setFabric(MockMVFabric.getCacheMVFabric());
    }
}
