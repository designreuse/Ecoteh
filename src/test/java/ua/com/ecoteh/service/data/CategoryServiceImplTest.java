package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.category.CategoryBuilder;
import ua.com.ecoteh.repository.CategoryRepository;

import java.util.Collection;

import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.UNKNOWN_ID;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategories;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategory;
import static ua.com.ecoteh.mocks.repository.MockRepository.getCategoryRepository;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CategoryServiceImplTest extends ContentServiceImplTest<Category> {

    private static CategoryService service;
    private static Category category;
    private static Collection<Category> categories;

    @BeforeClass
    public static void beforeClass() {
        final CategoryRepository repository = getCategoryRepository();
        final FileService fileService = getFileService();
        service = new CategoryServiceImpl(repository, fileService);
        category = getCategory();
        categories = getCategories();
    }

    @Override
    protected CategoryService getService() {
        return service;
    }

    @Override
    protected Category getModel() {
        return category;
    }

    @Override
    protected Collection<Category> getModels() {
        return categories;
    }

    @Override
    protected Category getUnknownModel() {
        final CategoryBuilder builder = Category.getBuilder();
        builder.addId(UNKNOWN_ID).addTitle(ANY_STRING).addUrl(ANY_STRING);
        return builder.build();
    }
}
