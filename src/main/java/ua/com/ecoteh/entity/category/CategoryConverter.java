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

    private final Category category;
    
    CategoryConverter(final Category category) {
        super(category);
        this.category = category;
    }

    @Override
    public CategoryEntity convert() {
        final CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(this.category.getId());
        categoryEntity.setValidated(this.category.isValidated());
        categoryEntity.setTitle(this.category.getTitle());
        categoryEntity.setUrl(this.category.getUrl());
        categoryEntity.setDescription(this.category.getDescription());
        categoryEntity.setKeywords(this.category.getKeywords());
        categoryEntity.setLogoEntity(this.category.getLogo().convert());
        categoryEntity.setArticleEntities(convertArticles(this.category.getArticles()));
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
