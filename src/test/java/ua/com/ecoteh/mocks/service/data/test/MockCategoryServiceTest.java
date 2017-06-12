package ua.com.ecoteh.mocks.service.data.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.service.data.CategoryService;

import java.util.Collection;

import static ua.com.ecoteh.mocks.enity.MockModels.getCategories;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategory;
import static ua.com.ecoteh.mocks.service.data.MockServices.getCategoryService;

public final class MockCategoryServiceTest extends MockContentServiceTest<Category> {

    private static CategoryService service;
    private static Category category;
    private static Collection<Category> categories;

    @BeforeClass
    public static void beforeClass() {
        service = getCategoryService();
        category = getCategory();
        categories = getCategories();
    }

    @Ignore
    @Override
    protected CategoryService getService() {
        return service;
    }

    @Ignore
    @Override
    protected Category getObject() {
        return category;
    }

    @Ignore
    @Override
    protected Collection<Category> getObjects() {
        return categories;
    }
}
