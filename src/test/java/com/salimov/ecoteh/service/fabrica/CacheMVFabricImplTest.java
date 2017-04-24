package com.salimov.ecoteh.service.fabrica;

import org.junit.Before;

import static com.salimov.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class CacheMVFabricImplTest extends MainMVFabricImplTest {

    @Before
    public void beforeTests() {
        setFabric(getCacheMVFabric());
    }
}
