package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.File;

/**
 * The interface provides a set of standard methods
 * for working {@link File} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.FileDaoImpl
 * @see MediaDao
 * @see File
 */
public interface FileDao extends MediaDao<File, Long> {
}
