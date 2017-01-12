package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.CategoryDao;
import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.service.data.interfaces.ArticleService;
import com.salimov.yurii.service.data.interfaces.CategoryService;
import com.salimov.yurii.service.data.interfaces.PhotoService;
import com.salimov.yurii.util.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the class {@link Category}.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
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
public final class CategoryServiceImpl
        extends ContentServiceImpl<Category, Long>
        implements CategoryService {

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link Article}.
     *
     * @see ArticleService
     */
    private final ArticleService articleService;

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link Photo}.
     *
     * @see PhotoService
     */
    private final PhotoService photoService;

    /**
     * Constructor.
     *
     * @param dao            a implementation
     *                       of the {@link CategoryDao} interface.
     * @param articleService a implementation
     *                       of the {@link ArticleService} interface.
     * @param photoService   a implementation
     *                       of the {@link PhotoService} interface.
     * @see CategoryDao
     * @see ArticleService
     * @see PhotoService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryServiceImpl(
            final CategoryDao dao,
            final ArticleService articleService,
            final PhotoService photoService
    ) {
        super(dao);
        this.articleService = articleService;
        this.photoService = photoService;
    }

    /**
     * Initializes, saves and returns a new category.
     *
     * @param title       a title of the new category.
     * @param description a description of the new category.
     * @param keywords    a keywords of the new category.
     * @param photoFile   a photo of the new category.
     * @param isValid     validated of the new category.
     * @return The new saving category.
     * @see Category
     * @see Photo
     */
    @Override
    @Transactional
    public Category initAndAdd(
            final String title,
            final String description,
            final String keywords,
            final MultipartFile photoFile,
            final boolean isValid
    ) {
        final Category category = new Category();
        category.initialize(
                title,
                description,
                keywords,
                updatePhoto(
                        new Photo(),
                        photoFile,
                        title
                )
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
     * @param photoFile   a new photo to the category.
     * @param photoAction a action on the main photo.
     * @param isValid     a validated of the category.
     * @return The updating category with parameter id.
     * @see Category
     * @see Photo
     */
    @Override
    @Transactional
    public Category initAndUpdate(
            final String url,
            final String title,
            final String description,
            final String keywords,
            final MultipartFile photoFile,
            final String photoAction,
            final boolean isValid
    ) {
        final Category category = getByUrl(url, false);
        category.initialize(
                title,
                description,
                keywords
        );
        category.setValidated(isValid);
        final Photo photo = category.getPhoto();
        updatePhoto(
                category,
                photoFile,
                title,
                photoAction
        );
        final Category _category = update(category);
        removePhoto(photo, photoAction);
        return _category;
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
    public Category getByUrl(final String url, final boolean isValid) {
        final Category category = super.getByUrl(url, isValid);
        category.getArticles().size();
        return category;
    }

    /**
     * Removes the category. Removes category if it is not {@code null}.
     * Also deletes photo file if category photo is not {@code null}
     *
     * @param category the category to remove.
     * @see Category
     */
    @Override
    @Transactional
    public void remove(final Category category) {
        if (category != null) {
            removePhoto(category);
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
    public List<Category> filteredByValid(
            final Collection<Category> categories
    ) {
        final List<Category> result = new ArrayList<>();
        if (categories != null && !categories.isEmpty()) {
            result.addAll(
                    categories.stream()
                            .filter(
                                    category -> (category != null) &&
                                            (category.isValidated())
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

    /**
     * Updates category photo.
     *
     * @param category the category to update.
     * @param file     a photo file.
     * @param title    a photo title.
     * @param action   a action on the photo.
     */
    private void updatePhoto(
            final Category category,
            final MultipartFile file,
            final String title,
            final String action
    ) {
        switch (action) {
            case "replace":
                category.setPhoto(
                        updatePhoto(
                                category.getPhoto(),
                                file,
                                title
                        )
                );
                break;
            case "delete":
                category.setPhoto(null);
                break;
        }
    }

    /**
     * Updates photo.
     *
     * @param photo the photo to updates.
     * @param file  a photo file.
     * @param title a photo title.
     * @return The updating photo.
     */
    private Photo updatePhoto(
            final Photo photo,
            final MultipartFile file,
            final String title
    ) {
        return this.photoService.updatePhoto(
                photo != null ? photo : new Photo(),
                file,
                Translator.fromCyrillicToLatin(title) + " photo",
                "categories"
        );
    }

    /**
     * Removes photo if action equals "delete".
     *
     * @param photo  the photo to remove.
     * @param action a action on the photo.
     */
    private void removePhoto(
            final Photo photo,
            final String action
    ) {
        if (action.equals("delete")) {
            this.photoService.remove(photo);
        }
    }

    /**
     * Remove photo in selected category.
     *
     * @param category a selected category.
     */
    private void removePhoto(final Category category) {
        this.photoService.remove(
                category.getPhoto()
        );
    }
}
