package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Section;

/**
 * The interface provides a set of standard methods
 * for working {@link Section} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.SectionDaoImpl
 * @see ContentDao
 * @see Section
 */
public interface SectionDao extends ContentDao<Section, Long> {
}
