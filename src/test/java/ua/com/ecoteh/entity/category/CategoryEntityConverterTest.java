package ua.com.ecoteh.entity.category;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.content.ContentEntityConverterTest;

import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCategoryEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CategoryEntityConverterTest extends ContentEntityConverterTest<CategoryEntity, Category> {

    private static CategoryEntityConverter converter;
    private static CategoryEntity entity;

    @BeforeClass
    public static void beforeClass() {
        entity = getCategoryEntity();
        converter = new CategoryEntityConverter(entity);
    }

    @Override
    protected void checkEntity(final Category category) {
        super.checkEntity(category);
        assertNotNull(category.getArticles());
    }

    @Override
    protected CategoryEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected CategoryEntity getEntity() {
        return entity;
    }
}