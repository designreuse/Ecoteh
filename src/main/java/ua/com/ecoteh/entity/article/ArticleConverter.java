package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.content.ContentConverter;

/**
 * The class implements a set of methods
 * for converting articles to article entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Article
 * @see ArticleEntity
 */
final class ArticleConverter extends ContentConverter<Article, ArticleEntity> {

    /**
     * The article for converting to article entity.
     */
    private final Article article;

    /**
     * Constructor.
     *
     * @param article the article for converting to article entity.
     */
    ArticleConverter(final Article article) {
        super(article);
        this.article = article;
    }

    /**
     * Converts the article and returns a new article entity.
     *
     * @return The converted article entity (newer null).
     */
    @Override
    public ArticleEntity convert() {
        final ArticleEntity entity = new ArticleEntity();
        entity.setId(this.article.getId());
        entity.setValidated(this.article.isValidated());
        entity.setTitle(this.article.getTitle());
        entity.setUrl(this.article.getUrl());
        entity.setDescription(this.article.getDescription());
        entity.setKeywords(this.article.getKeywords());
        entity.setNumber(this.article.getNumber());
        entity.setText(this.article.getText());
        entity.setDate(this.article.getDate());
        entity.setPrice(this.article.getPrice());
        entity.setCurrency(this.article.getCurrency());
        entity.setNovelty(this.article.isNovelty());
        entity.setLogoEntity(this.article.getLogo().convert());
        entity.setCategoryEntity(this.article.getCategory().convert());
        return entity;
    }
}
