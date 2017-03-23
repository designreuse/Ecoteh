package com.salimov.ecoteh.service.fabrica.impl;

import org.junit.Before;

import static com.salimov.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class CacheMVFabricImplTest extends MainMVFabricImplTest {

    @Before
    public void beforeTests() {
        setFabric(
                new CacheMVFabricImpl(
                        getMainMVFabric()
                )
        );
    }
}
