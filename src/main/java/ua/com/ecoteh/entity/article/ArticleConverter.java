package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.content.ContentConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ArticleConverter extends ContentConverter<Article, ArticleEntity> {

    private final Article article;

    /**
     * Constructor.
     * @param article
     */
    ArticleConverter(final Article article) {
        super(article);
        this.article = article;
    }

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
