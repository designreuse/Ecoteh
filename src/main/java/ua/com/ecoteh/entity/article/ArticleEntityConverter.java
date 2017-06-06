package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.content.ContentEntityConverter;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class ArticleEntityConverter extends ContentEntityConverter<ArticleEntity, Article> {

    private final ArticleEntity entity;

    /**
     * Constructor.
     * @param entity
     */
    ArticleEntityConverter(final ArticleEntity entity) {
        super(entity);
        this.entity = entity;
    }

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
                .addLogo(this.entity.getLogoEntity().convert())
                .addCategory(this.entity.getCategoryEntity().convert());
        return builder;
    }
}
