package ua.com.ecoteh.entity.post;

import ua.com.ecoteh.entity.content.ContentBuilder;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for build an objects of the {@link Post} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Post
 */
public final class PostBuilder extends ContentBuilder<Post, PostBuilder> {

    /**
     * The date of a new post.
     */
    private Date date;

    /**
     * Constructor.
     */
    PostBuilder() {
    }

    /**
     * Builds and returns a new post.
     *
     * @return The new post.
     * @see Post
     */
    @Override
    public Post build() {
        return new Post(
                getId(), isValidated(), getTitle(), getUrl(),
                getText(), getDescription(), getKeywords(),
                getLogo(), getDate()
        );
    }

    /**
     * Adds a new date to a new post.
     *
     * @param date the new date to a new post.
     * @return the article builder.
     */
    public PostBuilder addDate(final Date date) {
        this.date = date;
        return this;
    }

    /**
     * Returns a text of a new post.
     * Returns an empty string if the text is null or empty.
     *
     * @return The text or empty string (newer null).
     */
    private Date getDate() {
        return isNotNull(this.date) ? this.date : new Date();
    }
}
