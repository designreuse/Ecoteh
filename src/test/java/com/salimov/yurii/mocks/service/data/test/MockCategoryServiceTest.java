package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.Section;
import com.salimov.yurii.mocks.enity.MockEntity;
import com.salimov.yurii.mocks.service.data.MockServices;
import com.salimov.yurii.service.data.interfaces.CategoryService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotNull;

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

    @Test
    public void whenFilterCategoriesBySectionThenReturnThisFilterCollection() {
        final Section section = MockEntity.getSection();
        final Collection<Category> categories1 = new ArrayList<>();
        categories1.add(MockEntity.getCategory());
        Collection<Category> categories2 = this.service.filterBySection(categories1, section);
        assertNotNull(categories2);
    }

    @Test
    public void whenFilterCategoriesBySectionsThenReturnThisFilterCollection() {
        final Section section = MockEntity.getSection();
        final Collection<Section> sections = new ArrayList<>();
        sections.add(section);
        final Collection<Category> categories1 = new ArrayList<>();
        categories1.add(MockEntity.getCategory());
        Collection<Category> categories2 = this.service.filterBySections(categories1, sections);
        assertNotNull(categories2);
    }

    @Test
    public void whenGetAndFilterCategoriesBySectionThenReturnThisFilterCollection() {
        final Section section = MockEntity.getSection();
        final Collection<Category> categories = this.service.getAndFilterBySection(section);
        assertNotNull(categories);
    }

    @Test
    public void whenGetAndFilterCategoriesBySectionsThenReturnThisFilterCollection() {
        final Section section = MockEntity.getSection();
        final List<Section> sections = new ArrayList<>();
        sections.add(section);
        Collection<Category> categories = this.service.getAndFilterBySections(sections);
        assertNotNull(categories);
    }

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
