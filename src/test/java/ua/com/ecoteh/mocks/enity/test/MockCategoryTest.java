package ua.com.ecoteh.mocks.enity.test;

import ua.com.ecoteh.entity.Category;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static org.junit.Assert.*;

public final class MockCategoryTest extends MockModelTest<Category> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final Category category = MockEntity.getCategory();
        assertNotNull(category.getTitle());
        assertNotNull(category.getDescription());
        assertNotNull(category.getKeywords());
        assertNotNull(category.getId());
        assertNotNull(category.getUrl());
        assertNotNull(category.getLogo());
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
