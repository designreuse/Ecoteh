package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.content.ContentEditor;

import java.util.Collection;
import java.util.HashSet;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for editing an objects of the {@link Category} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Category
 */
public final class CategoryEditor extends ContentEditor<Category, CategoryEditor> {

    /**
     * The category to edit.
     */
    private final Category category;

    /**
     * The article collection.
     */
    private final Collection<Article> articles;

    /**
     * True if articles is modified.
     */
    private boolean isNewArticles;

    /**
     * Constructor.
     *
     * @param category the category to edit.
     */
    CategoryEditor(final Category category) {
        super(category);
        this.category = category;
        this.articles = new HashSet<>(this.category.getArticles());
    }

    /**
     * Updates and returns a new category.
     *
     * @return The updated category.
     */
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

    /**
     * Copies the incoming category.
     *
     * @param category the category to copy.
     * @return the category editor.
     */
    @Override
    public CategoryEditor copy(final Category category) {
        if (isNotNull(category)) {
            super.copy(category)
                    .addArticles(getArticles());
        }
        return this;
    }

    /**
     * Adds new article to the category.
     *
     * @param article a new article to the category.
     * @return the category editor.
     */
    public CategoryEditor addArticle(final Article article) {
        this.articles.add(article);
        this.isNewArticles = true;
        return this;
    }

    /**
     * Adds new articles to the category.
     *
     * @param articles a new articles to the category.
     * @return the category editor.
     */
    public CategoryEditor addArticles(final Collection<Article> articles) {
        this.articles.addAll(articles);
        this.isNewArticles = true;
        return this;
    }

    /**
     * Removes the incoming article.
     *
     * @param article the article to remove.
     * @return the category editor.
     */
    public CategoryEditor removeArticle(final Article article) {
        this.articles.remove(article);
        this.isNewArticles = true;
        return this;
    }

    /**
     * Removes the incoming articles.
     *
     * @param articles the articles to remove.
     * @return the category editor.
     */
    public CategoryEditor removeArticles(final Collection<Article> articles) {
        this.articles.removeAll(articles);
        this.isNewArticles = true;
        return this;
    }

    /**
     * Clears the article collection.
     *
     * @return the category editor.
     */
    public CategoryEditor clearArticles() {
        this.articles.clear();
        this.isNewArticles = true;
        return this;
    }

    /**
     * Returns the collection of articles.
     * Collection can be empty.
     *
     * @return The collection of articles (newer null).
     */
    private Collection<Article> getArticles() {
        return this.isNewArticles ? this.articles : this.category.getArticles();
    }
}
