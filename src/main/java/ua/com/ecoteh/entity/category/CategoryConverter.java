package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.content.ContentConverter;
import ua.com.ecoteh.util.validator.ObjectValidator;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class CategoryConverter extends ContentConverter<Category, CategoryEntity> {

    CategoryConverter(final Category model) {
        super(model);
    }

    @Override
    public CategoryEntity convert() {
        final CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(this.model.getId());
        categoryEntity.setValidated(this.model.isValidated());
        categoryEntity.setTitle(this.model.getTitle());
        categoryEntity.setUrl(this.model.getUrl());
        categoryEntity.setDescription(this.model.getDescription());
        categoryEntity.setKeywords(this.model.getKeywords());
        categoryEntity.setLogoEntity(this.model.getLogo().convert());
        categoryEntity.setArticleEntities(convertArticles(this.model.getArticles()));
        return categoryEntity;
    }

    private Collection<ArticleEntity> convertArticles(final Collection<Article> articles) {
        Collection<ArticleEntity> result = new HashSet<>();
        if (isNotEmpty(articles)) {
            result.addAll(
                    articles.stream()
                            .filter(ObjectValidator::isNotNull)
                            .map(Article::convert)
                            .collect(Collectors.toList())
            );
        }
        return result;
    }
}
