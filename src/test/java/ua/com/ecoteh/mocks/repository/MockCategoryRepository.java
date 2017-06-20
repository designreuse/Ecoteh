package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.repository.CategoryRepository;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCategoryEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCategoryEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockCategoryRepository extends MockContentRepository<CategoryEntity> {

    private final CategoryRepository repository;
    private final CategoryEntity entity;
    private final Collection<CategoryEntity> entities;

    MockCategoryRepository() {
        this.repository = mock(CategoryRepository.class);
        this.entity = getCategoryEntity();
        this.entities = getCategoryEntities();
    }

    @Override
    CategoryRepository create() {
        super.create();
        return this.repository;
    }

    @Override
    CategoryRepository getRepository() {
        return this.repository;
    }

    @Override
    CategoryEntity getEntity() {
        return this.entity;
    }

    @Override
    Collection<CategoryEntity> getEntities() {
        return this.entities;
    }
}
