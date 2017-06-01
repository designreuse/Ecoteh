package ua.com.ecoteh.mocks.enity.test;

import ua.com.ecoteh.entity.category.CategoryEntity;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static org.junit.Assert.*;

public final class MockCategoryEntityTest extends MockModelTest<CategoryEntity> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final CategoryEntity categoryEntity = MockEntity.getCategoryEntity();
        assertNotNull(categoryEntity.getTitle());
        assertNotNull(categoryEntity.getDescription());
        assertNotNull(categoryEntity.getKeywords());
        assertNotNull(categoryEntity.getId());
        assertNotNull(categoryEntity.getUrl());
        assertNotNull(categoryEntity.getLogoEntity());
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
}
