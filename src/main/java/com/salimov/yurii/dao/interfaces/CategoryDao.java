package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Category;

/**
 * The interface provides a set of standard methods for working {@link Category}
 * objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.CategoryDaoImpl
 * @see ContentDao
 * @see Category
 */
public interface CategoryDao
        extends ContentDao<Category, Long> {
}
