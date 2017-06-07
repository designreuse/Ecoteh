package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.category.CategoryEntity;

/**
 * The interface provides a set of JPA methods
 * for working {@link CategoryEntity} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see CategoryEntity
 */
public interface CategoryRepository extends ContentRepository<CategoryEntity> {
}
