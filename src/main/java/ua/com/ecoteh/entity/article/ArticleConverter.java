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
        final ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(this.article.getId());
        articleEntity.setValidated(this.article.isValidated());
        articleEntity.setTitle(this.article.getTitle());
        articleEntity.setUrl(this.article.getUrl());
        articleEntity.setDescription(this.article.getDescription());
        articleEntity.setKeywords(this.article.getKeywords());
        articleEntity.setNumber(this.article.getNumber());
        articleEntity.setText(this.article.getText());
        articleEntity.setDate(this.article.getDate());
        articleEntity.setPrice(this.article.getPrice());
        articleEntity.setLogoEntity(this.article.getLogo().convert());
        articleEntity.setCategoryEntity(this.article.getCategory().convert());
        return articleEntity;
    }
}
