package ua.com.ecoteh.mocks.service.fabrica;

import org.junit.Ignore;
import ua.com.ecoteh.service.data.*;
import ua.com.ecoteh.service.fabrica.CacheMVFabric;
import ua.com.ecoteh.service.fabrica.CacheMVFabricImpl;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.fabrica.MainMVFabricImpl;

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
        final ArticleService articleService = getArticleService();
        final CategoryService categoryService = getCategoryService();
        final CompanyService companyService = getCompanyService();
        final FileService fileService = getFileService();
        final ResponseService responseService = getResponseService();
        final UserService userService = getUserService();
        return new MainMVFabricImpl(
                articleService, categoryService,
                companyService, fileService,
                responseService, userService
        );
    }

    private static CacheMVFabric initCacheMVFabric() {
        final MainMVFabric mainMVFabric = getMainMVFabric();
        return new CacheMVFabricImpl(mainMVFabric);
    }
}
