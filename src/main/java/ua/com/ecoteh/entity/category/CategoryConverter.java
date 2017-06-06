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
 * The class implements a set of methods
 * for converting categories to category entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Category
 * @see CategoryEntity
 */
final class CategoryConverter extends ContentConverter<Category, CategoryEntity> {

    /**
     * The category for converting to category entity.
     */
    private final Category category;

    /**
     * Constructor.
     *
     * @param category the category for converting to category entity.
     */
    CategoryConverter(final Category category) {
        super(category);
        this.category = category;
    }

    /**
     * Converts the category and returns a new category entity.
     *
     * @return The converted category entity (newer null).
     */
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

    /**
     * Converts the incoming articles collection to an article entities collection.
     * <pre>
     *     convertArticles(null) -> empty collection of an article entities
     *
     *     articleCollection - empty collection of an articles
     *     convertArticles(articleCollection) -> empty collection of an article entities
     *
     *     articleCollection - not empty collection of an articles and all article are null
     *     convertArticles(articleCollection) -> empty collection of an article entities
     *
     *     articleCollection - not empty collection of an articles
     *     convertArticles(articleCollection) -> not empty collection of an article entities
     * </pre>
     *
     * @param articles the articles collection to convert.
     * @return The converted article entities collection (newer null).
     * @see ArticleEntity
     * @see Article
     */
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
