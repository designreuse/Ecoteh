package ua.com.ecoteh.service.fabrica;

import org.junit.BeforeClass;

import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MainMVFabricImplTest extends AbstractMainMVFabricImplTest {

    private static MainMVFabric fabric;

    @BeforeClass
    public static void beforeClass() {
        fabric = getMainMVFabric();
    }

    @Override
    protected MainMVFabric getFabric() {
        return fabric;
    }
}