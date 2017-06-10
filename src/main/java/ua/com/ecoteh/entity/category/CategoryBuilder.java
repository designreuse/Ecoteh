package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.content.ContentBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods
 * for building an objects of the {@link Category} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Category
 */
public final class CategoryBuilder extends ContentBuilder<Category, CategoryBuilder> {

    /**
     * The article collection.
     */
    private final Collection<Article> articles;

    /**
     * Constructor.
     */
    CategoryBuilder() {
        this.articles = new HashSet<>();
    }

    /**
     * Builds and returns a new category.
     *
     * @return The new category.
     * @see Category
     */
    @Override
    public Category build() {
        return new Category(
                getId(), isValidated(), getTitle(),
                getUrl(), getDescription(), getKeywords(),
                getLogo(), getArticles()
        );
    }

    /**
     * Adds the incoming article.
     *
     * @param article the new article to add.
     * @return the category builder.
     */
    public CategoryBuilder addArticle(final Article article) {
        this.articles.add(article);
        return this;
    }

    /**
     * Adds the incoming articles.
     *
     * @param articles the new articles to add.
     * @return the category builder.
     */
    public CategoryBuilder addArticles(final Collection<Article> articles) {
        this.articles.addAll(articles);
        return this;
    }

    /**
     * Removes the incoming article.
     *
     * @param article the article to remove.
     * @return the category builder.
     */
    public CategoryBuilder removeArticle(final Article article) {
        this.articles.remove(article);
        return this;
    }

    /**
     * Removes the incoming articles.
     *
     * @param articles the articles to remove.
     * @return the category builder.
     */
    public CategoryBuilder removeArticles(final Collection<Article> articles) {
        this.articles.removeAll(articles);
        return this;
    }

    /**
     * Clears the article collection.
     *
     * @return the category builder.
     */
    public CategoryBuilder clearArticles() {
        this.articles.clear();
        return this;
    }

    /**
     * Returns the collection of articles.
     * Collection can be empty.
     *
     * @return The collection of articles (newer null).
     */
    private Collection<Article> getArticles() {
        return isNotEmpty(this.articles) ? this.articles : Collections.emptyList();
    }
}
