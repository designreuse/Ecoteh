package com.salimov.ecoteh.service.data.impl;

import com.salimov.ecoteh.entity.Article;
import com.salimov.ecoteh.entity.Category;
import com.salimov.ecoteh.repository.CategoryRepository;
import com.salimov.ecoteh.service.data.interfaces.ArticleService;
import com.salimov.ecoteh.service.data.interfaces.CategoryService;
import com.salimov.ecoteh.service.data.interfaces.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the class {@link Category}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Service
@ComponentScan(
        basePackages = {
                "com.salimov.ecoteh.dao",
                "com.salimov.ecoteh.service.data"
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
     * @param repository     a implementation  of the {@link CategoryRepository} interface.
     * @param articleService a implementation of the {@link ArticleService} interface.
     * @param fileService    a implementation of the {@link FileService} interface.
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
     * Returns category with the parameter url.
     *
     * @param url     a URL of the category to return.
     * @param isValid is get valid category or not.
     * @return The category with the parameter url.
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
     * Removes category with parameter id.
     *
     * @param id a id of category to remove.
     */
    @Override
    @Transactional
    public void remove(final long id) {
        remove(get(id));
    }

    /**
     * Removes category with the parameter title.
     * Removes content if title is not blank.
     *
     * @param title a title of the category to remove.
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotBlank(title)) {
            remove(getByTitle(title, false));
        }
    }

    /**
     * Removes category with the parameter url.
     *
     * @param url a URL of the category to remove.
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotBlank(url)) {
            remove(getByUrl(url, false));
        }
    }

    /**
     * Removes the category. Removes category if it is not null.
     * Also deletes file file if category file is not null
     *
     * @param category the category to remove.
     */
    @Override
    @Transactional
    public void remove(final Category category) {
        if (category != null) {
            clearArticles(category);
            super.remove(category);
        }
    }

    /**
     * Returns a list valid categories.
     * Returns empty list if categories collection is empty.
     *
     * @param categories a categories to filter.
     * @return The list of categories.
     */
    @Override
    @Transactional
    public List<Category> filteredByValid(final Collection<Category> categories) {
        final List<Category> result = new ArrayList<>();
        if (categories != null && !categories.isEmpty()) {
            result.addAll(
                    categories.stream()
                            .filter(
                                    CategoryServiceImpl::validFilter
                            ).collect(Collectors.toList())
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
    protected Class<Category> getModelClass() {
        return Category.class;
    }

    /**
     * Copies the object "from" to object "to".
     *
     * @param from a copied object
     * @param to   a object to copy
     */
    @Override
    protected void copy(final Category from, final Category to) {
        to.initialize(from);
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

    /**
     * Filters category by validation.
     *
     * @param category a category to filter.
     * @return true if article is not null and is valid, false otherwise.
     */
    private static boolean validFilter(final Category category) {
        return (category != null) && (category.isValidated());
    }
}
