package ua.com.ecoteh.mocks.service.fabrica;

import ua.com.ecoteh.service.fabrica.CacheMVFabricImpl;
import ua.com.ecoteh.service.fabrica.MainMVFabricImpl;
import ua.com.ecoteh.service.fabrica.CacheMVFabric;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import org.junit.Ignore;

import static ua.com.ecoteh.mocks.service.data.MockServices.*;

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
