package com.salimov.yurii.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Video} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Media
 * @see Model
 */
@Entity
@Table(name = "videos")
public final class Video extends Media<Long> {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Video() {
    }

    /**
     * Constructor.
     * Initializes a main video parameters.
     *
     * @param title a title of the new video.
     * @param url   a URL of the new video.
     */
    public Video(
            final String title,
            final String url
    ) {
        super(title, url);
    }
}
