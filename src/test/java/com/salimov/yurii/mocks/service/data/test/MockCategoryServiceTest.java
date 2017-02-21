package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Category;
import com.salimov.yurii.mocks.enity.MockEntity;
import com.salimov.yurii.mocks.service.data.MockServices;
import com.salimov.yurii.service.data.interfaces.CategoryService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getCategory;
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
