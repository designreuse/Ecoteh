package com.salimov.yurii.mocks.enity.test;

import com.salimov.yurii.entity.Category;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getCategories;
import static com.salimov.yurii.mocks.enity.MockEntity.getCategory;

public class MockCategoryTest extends MockModelTest<Category> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final Category category = getCategory();
        assertFalse(Category.isValidated(category));
        assertNotNull(category.getTitle());
        assertNotNull(category.getDescription());
        assertNotNull(category.getKeywords());
        assertNotNull(category.getId());
        assertNotNull(category.getUrl());
        assertNull(category.getPhoto());
        assertNull(category.getSection());
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
    protected Collection<Category> getObjects(int size) {
        return getCategories(size);

    }
}
