package ua.com.ecoteh.entity.post;

import ua.com.ecoteh.entity.content.ContentEntityConverter;

/**
 * The class implements a set of methods
 * for converting post entities to posts.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see PostEntity
 * @see Post
 */
final class PostEntityConverter extends ContentEntityConverter<PostEntity, Post> {

    /**
     * The post entity for converting to post.
     */
    private final PostEntity entity;

    /**
     * Constructor.
     *
     * @param entity the post entity for converting to post.
     */
    PostEntityConverter(final PostEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Prepares and returns a post builder for creating
     * a new converted post.
     *
     * @return the prepared post builder.
     */
    @Override
    protected PostBuilder prepareBuilder() {
        final PostBuilder builder = Post.getBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addTitle(this.entity.getTitle())
                .addUrl(this.entity.getUrl())
                .addDescription(this.entity.getDescription())
                .addKeywords(this.entity.getKeywords())
                .addText(this.entity.getText())
                .addDate(this.entity.getDate())
                .addLogo(this.entity.getLogoEntity().convert());
        return builder;
    }
}
