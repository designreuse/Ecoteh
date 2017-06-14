package ua.com.ecoteh.service.fabrica;

import org.junit.BeforeClass;

import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CacheMVFabricImplTest extends AbstractMainMVFabricImplTest {

    private static CacheMVFabric fabric;

    @BeforeClass
    public static void beforeClass() {
        fabric = getCacheMVFabric();
    }

    @Override
    protected CacheMVFabric getFabric() {
        return fabric;
    }
}
