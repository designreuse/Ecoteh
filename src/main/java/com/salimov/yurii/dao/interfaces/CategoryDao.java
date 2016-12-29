package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.Section;

import java.util.List;

/**
 * The interface provides a set of standard methods for working {@link Category}
 * objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.CategoryDaoImpl
 * @see ContentDao
 * @see Category
 */
public interface CategoryDao extends ContentDao<Category, Long> {

    /**
     * Returns categories with the parameter section id from a database.
     *
     * @param id a section id of the categories to return.
     * @return The categories with parameter section id.
     * @see Category
     * @see Section
     */
    List<Category> getBySectionId(final Long id);
}
