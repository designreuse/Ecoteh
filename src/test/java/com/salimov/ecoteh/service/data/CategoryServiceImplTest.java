package com.salimov.ecoteh.service.data;

import com.salimov.ecoteh.entity.Category;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static com.salimov.ecoteh.mocks.MockConstants.URL;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCategories;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCategory;
import static com.salimov.ecoteh.mocks.repository.MockRepository.getCategoryRepository;
import static com.salimov.ecoteh.mocks.service.data.MockServices.getArticleService;
import static com.salimov.ecoteh.mocks.service.data.MockServices.getFileService;
import static org.junit.Assert.*;

public final class CategoryServiceImplTest extends ContentServiceImplTest<Category> {

    private CategoryService service;

    @Before
    public void beforeTest() {
        this.service = new CategoryServiceImpl(
                getCategoryRepository(),
                getArticleService(),
                getFileService()
        );
    }

    @Test
    public void whenUpdateThenReturnsSomeCategory() {
        assertNotNull(this.service.update(URL, getCategory()));
    }

    @Test
    public void whenFilteredByValidWithNullCollectionThenReturnEmptyList() {
        assertTrue(this.service.filteredByValid(null).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(this.service.filteredByValid(new ArrayList<>()).isEmpty());
    }

    @Test
    public void whenFilteredByValidThenReturnSomeList() {
        assertFalse(this.service.filteredByValid(getCategories()).isEmpty());
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
        return getCategories();
    }

    @Ignore
    @Override
    protected Category getInvalidObject() {
        return new Category();
    }
}
