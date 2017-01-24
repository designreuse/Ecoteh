package com.salimov.yurii.mocks.service.fabrica;

import com.salimov.yurii.service.fabrica.impl.AdminMVFabricImpl;
import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.yurii.service.fabrica.impl.ClientMVFabricImpl;
import com.salimov.yurii.service.fabrica.interfaces.AdminMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.CacheMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.ClientMVFabric;
import org.junit.Ignore;

import static com.salimov.yurii.mocks.service.data.MockServices.*;

@Ignore
public final class MockMVFabric {

    private static ClientMVFabric clientMVFabric;
    private static AdminMVFabric adminMVFabric;
    private static CacheMVFabric cacheMVFabric;

    public static ClientMVFabric getClientMVFabric() {
        if (clientMVFabric == null) {
            clientMVFabric = initClientMVFabric();
        }
        return clientMVFabric;
    }

    public static AdminMVFabric getAdminMVFabric() {
        if (adminMVFabric == null) {
            adminMVFabric = initAdminMVFabric();
        }
        return adminMVFabric;
    }

    public static CacheMVFabric getCacheMVFabric() {
        if (cacheMVFabric == null) {
            cacheMVFabric = initCacheMVFabric();
        }
        return cacheMVFabric;
    }

    private static ClientMVFabric initClientMVFabric() {
        return new ClientMVFabricImpl(
                getArticleService(),
                getCategoryService(),
                getCompanyService(),
                getUserService(),
                getResponseService());
    }

    private static AdminMVFabric initAdminMVFabric() {
        return new AdminMVFabricImpl(
                getArticleService(),
                getCategoryService(),
                getCompanyService(),
                getUserService(),
                getResponseService()
        );
    }

    private static CacheMVFabric initCacheMVFabric() {
        return new CacheMVFabricImpl(
                getAdminMVFabric()
        );
    }
}
