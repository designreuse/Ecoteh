package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.category.CategoryEntity;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCategoryEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCategoryEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockCategoryEntityTest extends MockContentEntityTest<CategoryEntity> {

    private static CategoryEntity category;
    private static Collection<CategoryEntity> categories;

    @BeforeClass
    public static void beforeClass() {
        category = getCategoryEntity();
        categories = getCategoryEntities();
    }

    @Test
    public void whenGetArticlesThenReturnNotEmpty() {
        assertFalse(category.getArticleEntities().isEmpty());
    }

    @Ignore
    @Override
    protected CategoryEntity getObject() {
        return category;
    }

    @Ignore
    @Override
    protected Collection<CategoryEntity> getObjects() {
        return categories;
    }
}
