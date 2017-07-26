package ua.com.ecoteh.entity.post;

import ua.com.ecoteh.entity.content.ContentEditor;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for editing an objects of the {@link Post} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Post
 */
public final class PostEditor extends ContentEditor<Post, PostEditor> {

    /**
     * The post to edit.
     */
    private final Post post;

    /**
     * The new date of the post.
     */
    private Date date;

    /**
     * Constructor.
     *
     * @param post the post to edit.
     */
    PostEditor(final Post post) {
        super(post);
        this.post = post;
    }

    /**
     * Updates and returns a new post.
     *
     * @return The updated post.
     */
    @Override
    public Post update() {
        final PostBuilder builder = Post.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addTitle(getTitle())
                .addUrl(getUrl())
                .addDescription(getDescription())
                .addKeywords(getKeywords())
                .addText(getText())
                .addDate(getDate())
                .addLogo(getLogo());
        return builder.build();
    }

    /**
     * Copies the incoming post.
     *
     * @param post the post to copy.
     * @return the post editor.
     */
    @Override
    public PostEditor copy(final Post post) {
        if (isNotNull(post)) {
            super.copy(post).addDate(post.getDate());
        }
        return this;
    }

    /**
     * Adds new date to the post.
     *
     * @param date a new date to the post.
     * @return the post editor.
     */
    public PostEditor addDate(final Date date) {
        this.date = date;
        return this;
    }

    /**
     * Returns a new date of the post.
     * Returns the post date if the date is null.
     *
     * @return The new post date.
     */
    private Date getDate() {
        return isNotNull(this.date) ? this.date : this.post.getDate();
    }
}
