package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.content.ContentEntityConverter;

/**
 * The class implements a set of methods
 * for converting article entities to articles.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ArticleEntity
 * @see Article
 */
final class ArticleEntityConverter extends ContentEntityConverter<ArticleEntity, Article> {

    /**
     * The article entity for converting to article.
     */
    private final ArticleEntity entity;

    /**
     * Constructor.
     *
     * @param entity the article entity for converting to article.
     */
    ArticleEntityConverter(final ArticleEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Prepares and returns a article builder for creating
     * a new converted article.
     *
     * @return the prepared article builder.
     */
    @Override
    protected ArticleBuilder prepareBuilder() {
        final ArticleBuilder builder = Article.getBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addTitle(this.entity.getTitle())
                .addUrl(this.entity.getUrl())
                .addDescription(this.entity.getDescription())
                .addKeywords(this.entity.getKeywords())
                .addNumber(this.entity.getNumber())
                .addText(this.entity.getText())
                .addDate(this.entity.getDate())
                .addPrice(this.entity.getPrice())
                .addCurrency(this.entity.getCurrency())
                .addNovelty(this.entity.isNovelty())
                .addLogo(this.entity.getLogoEntity().convert())
                .addCategory(this.entity.getCategoryEntity().convert());
        return builder;
    }
}
