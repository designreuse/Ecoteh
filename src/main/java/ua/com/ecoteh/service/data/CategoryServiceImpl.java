package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

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
public final class CategoryServiceImpl extends ContentServiceImpl<CategoryEntity> implements CategoryService {

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link ArticleEntity}.
     */
    private final ArticleService articleService;

    /**
     * Constructor.
     *
     * @param repository     the implementation  of the {@link CategoryRepository} interface.
     * @param articleService the implementation of the {@link ArticleService} interface.
     * @param fileService    the implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryServiceImpl(
            final CategoryRepository repository,
            final ArticleService articleService,
            final FileService fileService
    ) {
        super(repository, fileService);
        this.articleService = articleService;
    }

    /**
     * Returns categoryEntity with the incoming URL.
     *
     * @param url     the URL of the categoryEntity to return.
     * @param isValid is get valid categoryEntity or not.
     * @return The categoryEntity with the incoming URL (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public CategoryEntity getByUrl(
            final String url,
            final boolean isValid
    ) {
        final CategoryEntity categoryEntity = super.getByUrl(url, isValid);
        categoryEntity.getArticleEntities().size();
        return categoryEntity;
    }

    /**
     * Removes categoryEntity with incoming id.
     *
     * @param id the id of a categoryEntity to remove.
     */
    @Override
    @Transactional
    public void remove(final long id) {
        remove(get(id));
    }

    /**
     * Removes categoryEntity with the incoming title.
     * Removes content if title is not blank.
     *
     * @param title the title of a categoryEntity to remove.
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotEmpty(title)) {
            remove(getByTitle(title, false));
        }
    }

    /**
     * Removes categoryEntity with the incoming URL.
     *
     * @param url the URL of a categoryEntity to remove.
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotEmpty(url)) {
            remove(getByUrl(url, false));
        }
    }

    /**
     * Removes the categoryEntity.
     * Removes categoryEntity if it is not null.
     *
     * @param categoryEntity the categoryEntity to remove.
     */
    @Override
    @Transactional
    public void remove(final CategoryEntity categoryEntity) {
        if (isNotNull(categoryEntity)) {
            clearArticles(categoryEntity);
            super.remove(categoryEntity);
        }
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
    public List<CategoryEntity> filteredByValid(final Collection<CategoryEntity> categories) {
        final List<CategoryEntity> result = new ArrayList<>();
        if (isNotEmpty(categories)) {
            result.addAll(
                    categories.stream()
                            .filter(CategoryServiceImpl::isValidated)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }

    /**
     * Copies the object "from" to object "to".
     * Incoming objects must be not null.
     *
     * @param from the copied object
     * @param to   the object to copy
     */
    @Override
    protected void copy(final CategoryEntity from, final CategoryEntity to) {
        to.initialize(from);
    }

    /**
     * Return Class object of {@link CategoryEntity} class.
     *
     * @return The Class object of {@link CategoryEntity} class.
     */
    @Override
    protected Class<CategoryEntity> getModelClass() {
        return CategoryEntity.class;
    }

    /**
     * Remove articles in selected categoryEntity.
     *
     * @param categoryEntity a selected categoryEntity.
     */
    private void clearArticles(final CategoryEntity categoryEntity) {
        final Collection<ArticleEntity> articleEntities = categoryEntity.getArticleEntities();
        categoryEntity.clearArticles();
        this.articleService.update(articleEntities);
    }
}
