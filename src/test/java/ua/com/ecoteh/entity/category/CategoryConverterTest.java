package ua.com.ecoteh.entity.category;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.content.ContentConverterTest;

import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategory;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CategoryConverterTest extends ContentConverterTest<Category, CategoryEntity> {

    private static CategoryConverter converter;
    private static Category category;

    @BeforeClass
    public static void beforeClass() {
        category = getCategory();
        converter = new CategoryConverter(category);
    }

    @Override
    protected void checkEntity(final CategoryEntity entity) {
        super.checkEntity(entity);
       assertNotNull(entity.getArticleEntities());
    }

    @Override
    protected CategoryConverter getConverter() {
        return converter;
    }

    @Override
    protected Category getModel() {
        return category;
    }
}
