package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.IFile;
import com.salimov.yurii.util.translator.Translator;

import javax.persistence.Entity;
import javax.persistence.Table;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link File} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Media
 * @see Model
 */
@Entity
@Table(name = "files")
public final class File
        extends Media<Long>
        implements IFile<Long> {

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
    public File() {
    }

    /**
     * Constructor.
     *
     * @param title a title of the new photo.
     * @param url   a url of the new photo.
     */
    public File(
            final String title,
            final String url
    ) {
        super(title, url);
    }

    /**
     * Translates and sets a new url to the photo.
     * If parameter url is blank, then sets {@code null}.
     *
     * @param url a url to translate and set.
     */
    @Override
    public void translateAndSetUrl(final String url) {
        String newUrl = null;
        if (isNotBlank(url)) {
            final char oldChar = '.';
            final char newChar = '!';
            final String temp = url.replace(oldChar, newChar);
            newUrl = Translator.fromCyrillicToLatin(temp)
                    .replace(newChar, oldChar);
        }
        setUrl(newUrl);
    }
}
