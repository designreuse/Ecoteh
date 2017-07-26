package ua.com.ecoteh.entity.post;

import ua.com.ecoteh.entity.content.ContentConverter;

/**
 * The class implements a set of methods
 * for converting posts to post entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Post
 * @see PostEntity
 */
final class PostConverter extends ContentConverter<Post, PostEntity> {

    /**
     * The post for converting to post entity.
     */
    private final Post post;

    /**
     * Constructor.
     *
     * @param post the post for converting to post entity.
     */
    PostConverter(final Post post) {
        super(post);
        this.post = post;
    }

    /**
     * Converts the post and returns a new post entity.
     *
     * @return The converted post entity (newer null).
     */
    @Override
    public PostEntity convert() {
        final PostEntity entity = new PostEntity();
        entity.setId(this.post.getId());
        entity.setValidated(this.post.isValidated());
        entity.setTitle(this.post.getTitle());
        entity.setUrl(this.post.getUrl());
        entity.setDescription(this.post.getDescription());
        entity.setKeywords(this.post.getKeywords());
        entity.setText(this.post.getText());
        entity.setDate(this.post.getDate());
        entity.setLogoEntity(this.post.getLogo().convert());
        return entity;
    }
}
