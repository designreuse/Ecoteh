package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Category;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.ArrayList;
import java.util.Collection;

import static ua.com.ecoteh.mocks.service.data.MockServices.getArticleService;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;
import static org.junit.Assert.*;

public final class CategoryServiceImplTest extends ContentServiceImplTest<Category> {

    private CategoryService service;

    @Before
    public void beforeTest() {
        this.service = new CategoryServiceImpl(
                MockRepository.getCategoryRepository(),
                getArticleService(),
                getFileService()
        );
    }

    @Test
    public void whenUpdateThenReturnsSomeCategory() {
        assertNotNull(this.service.update(MockConstants.URL, MockEntity.getCategory()));
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
        assertFalse(this.service.filteredByValid(MockEntity.getCategories()).isEmpty());
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

    @Ignore
    @Override
    protected Category getInvalidObject() {
        return new Category();
    }
}
