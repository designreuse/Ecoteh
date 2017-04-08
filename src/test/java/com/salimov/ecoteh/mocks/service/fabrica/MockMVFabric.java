package com.salimov.ecoteh.mocks.service.fabrica;

import com.salimov.ecoteh.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.ecoteh.service.fabrica.impl.MainMVFabricImpl;
import com.salimov.ecoteh.service.fabrica.interfaces.CacheMVFabric;
import com.salimov.ecoteh.service.fabrica.interfaces.MainMVFabric;
import org.junit.Ignore;

import static com.salimov.ecoteh.mocks.service.data.MockServices.*;

@Ignore
public final class MockMVFabric {

    private static MainMVFabric clientMVFabric;
    private static CacheMVFabric cacheMVFabric;

    public static MainMVFabric getMainMVFabric() {
        if (clientMVFabric == null) {
            clientMVFabric = initClientMVFabric();
        }
        return clientMVFabric;
    }

    public static CacheMVFabric getCacheMVFabric() {
        if (cacheMVFabric == null) {
            cacheMVFabric = initCacheMVFabric();
        }
        return cacheMVFabric;
    }

    private static MainMVFabric initClientMVFabric() {
        return new MainMVFabricImpl(
                getArticleService(),
                getCategoryService(),
                getCompanyService(),
                getFileService(),
                getResponseService(),
                getUserService()
        );
    }

    private static CacheMVFabric initCacheMVFabric() {
        return new CacheMVFabricImpl(
                getMainMVFabric()
        );
    }
}