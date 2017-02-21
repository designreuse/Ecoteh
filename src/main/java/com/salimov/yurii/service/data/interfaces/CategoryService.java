package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.File;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Category} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Category
 * @see ContentService
 * @see DataService
 */
public interface CategoryService extends ContentService<Category> {


    /**
     * Initializes, updates and returns category with parameter url.
     *
     * @param url      a url of the category to update.
     * @param category
     * @return The updating category with parameter id.
     * @see Category
     * @see File
     */
    Category update(
            final String url,
            final Category category
    );
}
