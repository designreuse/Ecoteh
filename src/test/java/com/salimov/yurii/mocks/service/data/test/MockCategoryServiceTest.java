package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Category;
import com.salimov.yurii.mocks.enity.MockEntity;
import com.salimov.yurii.mocks.service.data.MockServices;
import com.salimov.yurii.service.data.interfaces.CategoryService;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

public class MockCategoryServiceTest extends MockContentServiceTest<Category> {

    private CategoryService service;

    @Before
    public void initCategoryService() {
        this.service = MockServices.getCategoryService();
    }

    /*@Test
    public void whenInitAndAddCategoryThenReturnThisCategory() {
        final Photo photo = MockEntity.getPhoto();
        final Section section = MockEntity.getSection();
        Category category = this.service.initAndAdd(MockConstants.TITLE, MockConstants.DESCRIPTION, MockConstants.KEYWORDS, photo, section);
        assertNotNull(category);
    }*/

    /*@Test
    public void whenInitAndUpdateCategoryThenReturnThisCategory() {
        final Photo photo = MockEntity.getPhoto();
        final Section section = MockEntity.getSection();
        Category category = this.service.initAndUpdate(MockConstants.ID, MockConstants.TITLE, MockConstants.DESCRIPTION, MockConstants.KEYWORDS, photo, section);
        assertNotNull(category);
    }*/

    @Ignore
    @Override
    protected CategoryService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected Category getObject() {
        return MockEntity.getCategory();
    }

    @Ignore
    @Override
    protected Collection<Category> getObjects() {
        return MockEntity.getCategories();
    }
}
