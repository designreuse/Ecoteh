package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.Category;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.service.data.MockServices;
import ua.com.ecoteh.service.data.CategoryService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.enity.MockEntity.getCategory;
import static org.junit.Assert.assertNotNull;

public final class MockCategoryServiceTest extends MockContentServiceTest<Category> {

    private CategoryService service;

    @Before
    public void initCategoryService() {
        this.service = MockServices.getCategoryService();
    }

    @Test
    public void whenInitAndUpdateCategoryThenReturnThisCategory() {
        assertNotNull(this.service.update(URL, getCategory()));
    }

    @Ignore
    @Override
    protected CategoryService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected Category getObject() {
        return getCategory();
    }

    @Ignore
    @Override
    protected Collection<Category> getObjects() {
        return MockEntity.getCategories();
    }
}
