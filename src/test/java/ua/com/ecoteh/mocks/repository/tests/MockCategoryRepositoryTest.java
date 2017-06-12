package ua.com.ecoteh.mocks.repository.tests;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.repository.CategoryRepository;

import java.util.Collection;

import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCategoryEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCategoryEntity;
import static ua.com.ecoteh.mocks.repository.MockRepository.getCategoryRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockCategoryRepositoryTest extends MockContentRepositoryTest<CategoryEntity> {

    private static CategoryEntity category;
    private static Collection<CategoryEntity> categories;
    private static CategoryRepository repository;

    @BeforeClass
    public static void beforeClass() {
        category = getCategoryEntity();
        categories = getCategoryEntities();
        repository = getCategoryRepository();
    }

    @Override
    protected CategoryRepository getRepository() {
        return repository;
    }

    @Override
    protected CategoryEntity getObject() {
        return category;
    }

    @Override
    protected Collection<CategoryEntity> getObjects() {
        return categories;
    }
}
