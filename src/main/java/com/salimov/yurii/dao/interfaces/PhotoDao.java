package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Photo;

/**
 * The interface provides a set of standard methods
 * for working {@link Photo} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.PhotoDaoImpl
 * @see MediaDao
 * @see Photo
 */
public interface PhotoDao extends MediaDao<Photo, Long> {
}
