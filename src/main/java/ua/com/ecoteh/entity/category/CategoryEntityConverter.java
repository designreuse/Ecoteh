package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.content.ContentEntityConverter;
import ua.com.ecoteh.util.validator.ObjectValidator;

import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class CategoryEntityConverter extends ContentEntityConverter<Category> {

    private final CategoryEntity entity;

    CategoryEntityConverter(final CategoryEntity entity) {
        this.entity = entity;
    }

    @Override
    protected CategoryBuilder prepareBuilder() {
        final CategoryBuilder builder = new CategoryBuilder();
        if (isNotNull(this.entity)) {
            builder.addId(this.entity.getId())
                    .addValidated(this.entity.isValidated())
                    .addTitle(this.entity.getTitle())
                    .addUrl(this.entity.getUrl())
                    .addDescription(this.entity.getDescription())
                    .addKeywords(this.entity.getKeywords())
                    .addLogo(this.entity.getLogoEntity().convert())
                    .addArticles(getArticles());
        }
        return builder;
    }

    private List<Article> getArticles() {
        return this.entity.getArticleEntities().stream()
                .filter(ObjectValidator::isNotNull)
                .map(ArticleEntity::convert)
                .collect(Collectors.toList());
    }
}
