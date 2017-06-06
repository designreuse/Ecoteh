package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.content.ContentEditor;

import java.util.Collection;
import java.util.HashSet;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class CategoryEditor extends ContentEditor<Category, CategoryEditor> {

    /**
     * The set of a articleEntities.
     */
    private final Collection<Article> articles;

    /**
     * Constructor.
     * @param category
     */
    CategoryEditor(final Category category) {
        super(category);
        this.articles = new HashSet<>(category.getArticles());
    }

    @Override
    public Category update() {
        final CategoryBuilder builder = Category.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addTitle(getTitle())
                .addUrl(getUrl())
                .addDescription(getDescription())
                .addKeywords(getKeywords())
                .addLogo(getLogo())
                .addArticles(getArticles());
        return builder.build();
    }

    @Override
    public CategoryEditor copy(final Category category) {
        if (isNotNull(category)) {
            super.copy(category)
                    .addArticles(getArticles());
        }
        return this;
    }

    public CategoryEditor addArticle(final Article article) {
        this.articles.add(article);
        return this;
    }

    public CategoryEditor addArticles(final Collection<Article> articles) {
        this.articles.addAll(articles);
        return this;
    }

    public CategoryEditor removeArticle(final Article article) {
        this.articles.remove(article);
        return this;
    }

    public CategoryEditor removeArticles(final Collection<Article> articles) {
        this.articles.removeAll(articles);
        return this;
    }

    public CategoryEditor clearArticles() {
        this.articles.clear();
        return this;
    }

    private Collection<Article> getArticles() {
        return this.articles;
    }
}
