package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.service.data.MockServices.getArticleService;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

public final class CategoryEntityServiceImplTest extends ContentServiceImplTest<CategoryEntity> {

    private static CategoryService service;

    @BeforeClass
    public static void beforeTest() {
        service = new CategoryServiceImpl(
                MockRepository.getCategoryRepository(),
                getArticleService(),
                getFileService()
        );
    }

    @Test
    public void whenUpdateThenReturnsSomeCategory() {
        assertNotNull(service.update(MockConstants.URL, MockEntity.getCategoryEntity()));
    }

    @Test
    public void whenFilteredByValidWithNullCollectionThenReturnEmptyList() {
        assertTrue(service.filteredByValid(null).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(service.filteredByValid(new ArrayList<>()).isEmpty());
    }

    @Test
    public void whenFilteredByValidThenReturnSomeList() {
        assertFalse(service.filteredByValid(MockEntity.getCategories()).isEmpty());
    }

    @Ignore
    @Override
    protected CategoryService getService() {
        return service;
    }

    @Ignore
    @Override
    protected CategoryEntity getObject() {
        return MockEntity.getCategoryEntity();
    }

    @Ignore
    @Override
    protected Collection<CategoryEntity> getObjects() {
        return MockEntity.getCategories();
    }

    @Ignore
    @Override
    protected CategoryEntity getInvalidObject() {
        return new CategoryEntity();
    }
}
