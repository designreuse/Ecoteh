package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.category.CategoryEditor;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.CategoryRepository;
import ua.com.ecoteh.util.validator.ObjectValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the class {@link Category}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Category
 * @see CategoryEntity
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.service.data"
        }
)
public final class CategoryServiceImpl
        extends ContentServiceImpl<Category, CategoryEntity> implements CategoryService {

    /**
     * The interface provides a set of standard methods
     * for working {@link CategoryEntity} objects a the database.
     */
    private final CategoryRepository repository;

    /**
     * Constructor.
     *
     * @param repository  the implementation  of the {@link CategoryRepository} interface.
     * @param fileService the implementation of the {@link FileService} interface.
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
     * @throws IllegalArgumentException Throw exception when the incoming url is null or empty.
     * @throws NullPointerException     Throw exception when object with parameter url is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public Category getByUrl(final String url, final boolean isValid)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw getIllegalArgumentException(ExceptionMessage.BLANK_URL_MESSAGE);
        }
        final CategoryEntity entity = this.repository.findByUrl(url);
        if (isNotValidated(entity, isValid)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), url
            );
        }
        final Category category = convertToModel(entity);
        final CategoryEditor categoryEditor = category.getEditor();
        final Collection<Article> articles = convertArticles(entity.getArticleEntities());
        categoryEditor.addArticles(articles);
        return categoryEditor.update();
    }

    /**
     * Converts the category article entities to an articles collection.
     *
     * @param entities the model entities to convert.
     * @return The converted articles collection (newer null).
     * @see Article
     * @see ArticleEntity
     */
    private Collection<Article> convertArticles(final Collection<ArticleEntity> entities) {
        return entities.stream()
                .filter(ObjectValidator::isNotNull)
                .map(ArticleEntity::convert)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list valid categories.
     * Returns empty list if categories collection is empty.
     * <pre>
     *     filteredByValid(null) = empty ArrayList()
     *     filteredByValid(new ArrayList()) = empty ArrayList()
     *
     *     if the incoming collection has a not validated categories
     *     filteredByValid(categories) = empty ArrayList()
     *
     *     if the incoming collection has a validated categories
     *     filteredByValid(categories) = filtered list of articles
     * </pre>
     *
     * @param categories the categories to filter.
     * @return The filtered list of articles or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Category> filterByValid(final Collection<Category> categories) {
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
     * Return Class object of {@link Category} class.
     *
     * @return The Class object of {@link Category} class.
     */
    @Override
    Class<Category> getModelClass() {
        return Category.class;
    }
}
