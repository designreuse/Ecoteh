package ua.com.ecoteh.entity.post;

import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.util.time.Time;

import java.util.Date;

/**
 * The class implements a set of methods for working
 * with objects of the {@link Post} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see PostEntity
 */
public final class Post extends Content {

    /**
     * The date of this post.
     */
    private final Date date;

    /**
     * Constructor.
     *
     * @param id          the unique identifier for each post.
     * @param validated   the validations of a new post.
     * @param title       the title of a new post.
     * @param url         the URL of a new post.
     * @param text        the text of a new post.
     * @param description the description of a new post.
     * @param keywords    the keywords of a new post.
     * @param logo        the logo of a new post.
     * @param date        the date of a new post.
     */
    Post(
            final long id, final boolean validated,
            final String title, final String url, final String text,
            final String description, final String keywords,
            final File logo, Date date
    ) {
        super(id, validated, title, url, text, description, keywords, logo);
        this.date = date;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "Post{" + super.toString() +
                ", date=" + this.date +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this object is the same as the obj
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Post other = (Post) object;
            result = this.date.equals(other.date);
        }
        return result;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return super.hashCode() + this.date.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public Post clone() {
        return (Post) super.clone();
    }

    /**
     * Returns a date of the post.
     *
     * @return The post date (newer null).
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Returns an post date in string format.
     *
     * @return The post string-date (newer null).
     */
    public String getDateToString() {
        return Time.getDate(getDate());
    }

    /**
     * Converts this object and returns an entity
     * of the {@link PostEntity} class.
     *
     * @return The entity of the {@link PostEntity} class (newer null).
     * @see PostConverter
     */
    @Override
    public PostEntity convert() {
        return new PostConverter(this).convert();
    }

    /**
     * Returns a editor for updating this post.
     *
     * @return the post editor (newer null).
     */
    @Override
    public PostEditor getEditor() {
        return new PostEditor(this);
    }

    /**
     * Returns a builder for creating a new post.
     *
     * @return the post builder (newer null).
     */
    public static PostBuilder getBuilder() {
        return new PostBuilder();
    }
}
