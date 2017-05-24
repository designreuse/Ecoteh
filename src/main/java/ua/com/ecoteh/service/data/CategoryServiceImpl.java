package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.Article;
import ua.com.ecoteh.entity.Category;
import ua.com.ecoteh.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the class {@link Category}.
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
public final class CategoryServiceImpl extends ContentServiceImpl<Category> implements CategoryService {

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link Article}.
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
     * Returns category with the incoming URL.
     *
     * @param url     the URL of the category to return.
     * @param isValid is get valid category or not.
     * @return The category with the incoming URL (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public Category getByUrl(
            final String url,
            final boolean isValid
    ) {
        final Category category = super.getByUrl(url, isValid);
        category.getArticles().size();
        return category;
    }

    /**
     * Removes category with incoming id.
     *
     * @param id the id of a category to remove.
     */
    @Override
    @Transactional
    public void remove(final long id) {
        remove(get(id));
    }

    /**
     * Removes category with the incoming title.
     * Removes content if title is not blank.
     *
     * @param title the title of a category to remove.
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotEmpty(title)) {
            remove(getByTitle(title, false));
        }
    }

    /**
     * Removes category with the incoming URL.
     *
     * @param url the URL of a category to remove.
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotEmpty(url)) {
            remove(getByUrl(url, false));
        }
    }

    /**
     * Removes the category.
     * Removes category if it is not null.
     *
     * @param category the category to remove.
     */
    @Override
    @Transactional
    public void remove(final Category category) {
        if (isNotNull(category)) {
            clearArticles(category);
            super.remove(category);
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
     *     Category category = new Category();
     *     category.setValidated(false);
     *     categories.add(category);
     *     filteredByValid(categories) = empty ArrayList()
     *
     *     category.setValidated(true);
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
    protected void copy(final Category from, final Category to) {
        to.initialize(from);
    }

    /**
     * Return Class object of {@link Category} class.
     *
     * @return The Class object of {@link Category} class.
     */
    @Override
    protected Class<Category> getModelClass() {
        return Category.class;
    }

    /**
     * Remove articles in selected category.
     *
     * @param category a selected category.
     */
    private void clearArticles(final Category category) {
        final Collection<Article> articles = category.getArticles();
        category.clearArticles();
        this.articleService.update(articles);
    }
}
