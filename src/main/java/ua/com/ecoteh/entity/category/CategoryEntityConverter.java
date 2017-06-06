package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.content.ContentEntityConverter;
import ua.com.ecoteh.util.validator.ObjectValidator;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class CategoryEntityConverter extends ContentEntityConverter<CategoryEntity, Category> {

    private final CategoryEntity entity;

    /**
     * Constructor.
     * @param entity
     */
    CategoryEntityConverter(final CategoryEntity entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    protected CategoryBuilder prepareBuilder() {
        final CategoryBuilder builder = Category.getBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addTitle(this.entity.getTitle())
                .addUrl(this.entity.getUrl())
                .addDescription(this.entity.getDescription())
                .addKeywords(this.entity.getKeywords())
                .addLogo(this.entity.getLogoEntity().convert())
                .addArticles(getArticles());
        return builder;
    }

    private Collection<Article> getArticles() {
        return this.entity.getArticleEntities().stream()
                .filter(ObjectValidator::isNotNull)
                .map(ArticleEntity::convert)
                .collect(Collectors.toList());
    }
}
