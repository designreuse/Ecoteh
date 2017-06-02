package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.content.ContentConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ArticleConverter extends ContentConverter<Article, ArticleEntity> {

    ArticleConverter(final Article model) {
        super(model);
    }

    @Override
    public ArticleEntity convert() {
        final ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(this.model.getId());
        articleEntity.setValidated(this.model.isValidated());
        articleEntity.setTitle(this.model.getTitle());
        articleEntity.setUrl(this.model.getUrl());
        articleEntity.setDescription(this.model.getDescription());
        articleEntity.setKeywords(this.model.getKeywords());
        articleEntity.setNumber(this.model.getNumber());
        articleEntity.setText(this.model.getText());
        articleEntity.setDate(this.model.getDate());
        articleEntity.setPrice(this.model.getPrice());
        articleEntity.setLogoEntity(this.model.getLogo().convert());
        articleEntity.setCategoryEntity(this.model.getCategory().convert());
        return articleEntity;
    }
}
