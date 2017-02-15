package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.CategoryDao;
import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.ArticleService;
import com.salimov.yurii.service.data.interfaces.CategoryService;
import com.salimov.yurii.util.compressor.HtmlPress;
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
 * @see Category
 * @see CategoryService
 * @see ContentServiceImpl
 * @see DataServiceImpl
 * @see CategoryDao
 */
@Service
@ComponentScan(
        basePackages = {
                "com.salimov.yurii.dao",
                "com.salimov.yurii.service.data"
        }
)
public final class CategoryServiceImpl extends ContentServiceImpl<Category, Long> implements CategoryService {

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link Article}.
     *
     * @see ArticleService
     * @see Article
     */
    private final ArticleService articleService;

    /**
     * Constructor.
     *
     * @param dao            a implementation  of the {@link CategoryDao} interface.
     * @param articleService a implementation of the {@link ArticleService} interface.
     * @see CategoryDao
     * @see ArticleService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryServiceImpl(
            final CategoryDao dao,
            final ArticleService articleService
    ) {
        super(dao);
        this.articleService = articleService;
    }

    /**
     * Initializes, saves and returns a new category.
     *
     * @param title       a title of the new category.
     * @param description a description of the new category.
     * @param keywords    a keywords of the new category.
     * @param photoUrl    a main photo URL of the new category.
     * @param isValid     validated of the new category.
     * @return The new saving category.
     * @see Category
     * @see File
     */
    @Override
    @Transactional
    public Category initAndAdd(
            final String title,
            final String description,
            final String keywords,
            final String photoUrl,
            final boolean isValid
    ) {
        final Category category = new Category();
        category.initialize(
                title,
                new HtmlPress().compress(description),
                keywords, photoUrl
        );
        category.setValidated(isValid);
        return add(category);
    }

    /**
     * Initializes, updates and returns category with parameter url.
     *
     * @param url         a url of the category to update.
     * @param title       a new title to the category.
     * @param description a new description to the category.
     * @param keywords    a new keywords to the category.
     * @param photoUrl    a new main photo URL to the category.
     * @param isValid     a validated of the category.
     * @return The updating category with parameter id.
     * @see Category
     * @see File
     */
    @Override
    @Transactional
    public Category initAndUpdate(
            final String url,
            final String title,
            final String description,
            final String keywords,
            final String photoUrl,
            final boolean isValid
    ) {
        final Category category = getByUrl(url, false);
        category.initialize(
                title,
                new HtmlPress().compress(description),
                keywords, photoUrl);
        category.setValidated(isValid);
        return update(category);
    }

    /**
     * Returns category with the parameter url.
     *
     * @param url     a url of the category to return.
     * @param isValid is get valid category or not.
     * @return The category with the parameter url.
     * @see Category
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
     * @see Article
     */
    @Override
    @Transactional
    public void remove(final Long id) {
        if (id != null) {
            remove(get(id));
        }
    }

    /**
     * Removes category with the parameter title.
     * Removes content if title is not blank.
     *
     * @param title a title of the category to remove.
     * @see Category
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
     * @param url a url of the category to remove.
     * @see Category
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotBlank(url)) {
            remove(getByUrl(url, false));
        }
    }

    /**
     * Removes the category. Removes category if it is not {@code null}.
     * Also deletes file file if category file is not {@code null}
     *
     * @param category the category to remove.
     * @see Category
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
     * @see Category
     */
    @Override
    @Transactional
    public List<Category> filteredByValid(final Collection<Category> categories) {
        final List<Category> result = new ArrayList<>();
        if (categories != null && !categories.isEmpty()) {
            result.addAll(
                    categories.stream()
                            .filter(
                                    category -> (category != null)
                                            && (category.isValidated())
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
