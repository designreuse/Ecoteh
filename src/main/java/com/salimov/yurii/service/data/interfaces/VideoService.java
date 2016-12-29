package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Video;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the class {@link Video}.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Video
 * @see MediaService
 * @see DataService
 */
public interface VideoService extends MediaService<Video, Long> {
}
