package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.category.Category;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategories;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategory;

public final class MockCategoryTest extends MockContentTest<Category> {

    private static Category category;
    private static Collection<Category> categories;

    @BeforeClass
    public static void beforeClass() {
        category = getCategory();
        categories = getCategories();
    }

    @Test
    public void whenGetArticlesThenReturnNotEmpty() {
        assertFalse(category.getArticles().isEmpty());
    }

    @Ignore
    @Override
    protected Category getObject() {
        return category;
    }

    @Ignore
    @Override
    protected Collection<Category> getObjects() {
        return categories;
    }
}
