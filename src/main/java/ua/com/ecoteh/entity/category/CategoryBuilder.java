package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.content.ContentBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class CategoryBuilder extends ContentBuilder<Category, CategoryBuilder> {

    /**
     * The set of a articleEntities.
     */
    private final Collection<Article> articles;

    CategoryBuilder() {
        this.articles = new HashSet<>();
    }

    @Override
    public Category build() {
        return new Category(
                getId(), isValidated(), getTitle(),
                getUrl(), getDescription(), getKeywords(),
                getLogo(), getArticles()
        );
    }

    public CategoryBuilder addArticle(final Article article) {
        this.articles.add(article);
        return this;
    }

    public CategoryBuilder addArticles(final Collection<Article> articles) {
        this.articles.addAll(articles);
        return this;
    }

    public CategoryBuilder removeArticle(final Article article) {
        this.articles.remove(article);
        return this;
    }

    public CategoryBuilder removeArticles(final Collection<Article> articles) {
        this.articles.removeAll(articles);
        return this;
    }

    public CategoryBuilder clearArticles() {
        this.articles.clear();
        return this;
    }

    private Collection<Article> getArticles() {
        return isNotEmpty(this.articles) ? this.articles : Collections.emptyList();
    }

    private Collection<Article> prepareArticles() {
        final Collection<Article> result = new HashSet<>();
        for (Article article : this.articles) {
            if (isNotNull(article)) {
                result.add(article);    // TODO: add this category to the articles
            }
        }
        return result;
    }
}
