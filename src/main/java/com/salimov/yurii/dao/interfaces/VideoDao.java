package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Video;

/**
 * The interface provides a set of standard methods
 * for working {@link Video} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.VideoDaoImpl
 * @see MediaDao
 * @see Video
 */
public interface VideoDao extends MediaDao<Video, Long> {
}
