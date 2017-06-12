package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.service.data.CategoryService;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategories;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategory;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockCategoryService extends MockContentService<Category> {

    private final CategoryService service;
    private final Category category;
    private final Collection<Category> categories;

    MockCategoryService() {
        this.service = mock(CategoryService.class);
        this.category = getCategory();
        this.categories = getCategories();
    }

    @Override
    CategoryService create() {
        super.create();
        return this.service;
    }

    @Override
    CategoryService getService() {
        return this.service;
    }

    @Override
    Category getModel() {
        return this.category;
    }

    @Override
    Collection<Category> getModels() {
        return this.categories;
    }
}
