package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.CategoryDao;
import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.ArticleService;
import com.salimov.yurii.service.data.interfaces.CategoryService;
import com.salimov.yurii.service.data.interfaces.FileService;
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
public final class CategoryServiceImpl extends ContentServiceImpl<Category> implements CategoryService {

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
     * @param fileService    a implementation of the {@link FileService} interface.
     * @see CategoryDao
     * @see ArticleService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryServiceImpl(
            final CategoryDao dao,
            final ArticleService articleService,
            final FileService fileService
    ) {
        super(dao, fileService);
        this.articleService = articleService;
    }

    /**
     * Initializes, updates and returns category with parameter url.
     *
     * @param url      a url of the category to update.
     * @param category a category to update.
     * @return The updating category with parameter id.
     * @see Category
     * @see File
     */
    /*@Override
    @Transactional
    public Category update(
            final String url,
            final Category category
    ) {
        final Category categoryToUpdate = getByUrl(url, false);
        final File newLogo = category.getLogo();
        final File oldLogo = categoryToUpdate.getLogo();
        if (!newLogo.equals(oldLogo) && isNotBlank(newLogo.getUrl())) {
            this.fileService.deleteFile(oldLogo.getUrl());
        }
        categoryToUpdate.initialize(category);
        return update(categoryToUpdate);
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
    public void remove(final long id) {
        remove(get(id));
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
                                    category -> (category != null) && (category.isValidated())
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
