package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.File;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Category} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Category
 * @see ContentService
 * @see DataService
 */
public interface CategoryService
        extends ContentService<Category, Long> {

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
    Category initAndAdd(
            final String title,
            final String description,
            final String keywords,
            final String photoUrl,
            final boolean isValid
    );

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
    Category initAndUpdate(
            final String url,
            final String title,
            final String description,
            final String keywords,
            final String photoUrl,
            final boolean isValid
    );
}
