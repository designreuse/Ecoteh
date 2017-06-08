package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the class {@link CategoryEntity}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.service.data"
        }
)
public final class CategoryServiceImpl
        extends ContentServiceImpl<Category, CategoryEntity>
        implements CategoryService {

    private final CategoryRepository repository;

    /**
     * Constructor.
     *
     * @param repository     the implementation  of the {@link CategoryRepository} interface.
     * @param fileService    the implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryServiceImpl(
            final CategoryRepository repository,
            final FileService fileService
    ) {
        super(repository, fileService);
        this.repository = repository;
    }

    /**
     * Returns categoryEntity with the incoming URL.
     *
     * @param url     the URL of the categoryEntity to return.
     * @param isValid is get valid categoryEntity or not.
     * @return The categoryEntity with the incoming URL (newer null).
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    @Override
    @Transactional(readOnly = true)
    public Category getByUrl(final String url, final boolean isValid)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw getIllegalArgumentException(ExceptionMessage.BLANK_URL_MESSAGE);
        }
        final CategoryEntity categoryEntity = this.repository.findByUrl(url);
        if (isNotValidated(categoryEntity, isValid)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), url
            );
        }
        categoryEntity.getArticleEntities().size();
        return convertToModel(categoryEntity);
    }

    /**
     * Returns a list valid categories.
     * Returns empty list if categories collection is empty.
     * <pre>
     *     filteredByValid(null) = empty ArrayList()
     *     filteredByValid(new ArrayList()) = empty ArrayList()
     *
     *     Collection categories = new ArrayList();
     *     CategoryEntity categoryEntity = new CategoryEntity();
     *     categoryEntity.setValidated(false);
     *     categories.add(categoryEntity);
     *     filteredByValid(categories) = empty ArrayList()
     *
     *     categoryEntity.setValidated(true);
     *     filteredByValid(categories) = filtered list of articles
     * </pre>
     *
     * @param categories the categories to filter.
     * @return The filtered list of articles or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Category> filteredByValid(final Collection<Category> categories) {
        final List<Category> result = new ArrayList<>();
        if (isNotEmpty(categories)) {
            result.addAll(
                    categories.stream()
                            .filter(this::isValidated)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Return Class object of {@link CategoryEntity} class.
     *
     * @return The Class object of {@link CategoryEntity} class.
     */
    @Override
    protected Class<Category> getModelClass() {
        return Category.class;
    }
}
