package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.mocks.enity.MockModels;

import java.util.Collection;

import static org.junit.Assert.assertFalse;

public final class MockCategoryTest extends MockContentTest<Category> {

    private static Category category;
    private static Collection<Category> categories;

    @BeforeClass
    public static void beforeClass() {
        category = MockModels.getCategory();
        categories = MockModels.getCategories();
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
