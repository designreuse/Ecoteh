package ua.com.ecoteh.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Category} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "categories")
public class Category extends Content implements ICategory {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The set of a articles.
     */
    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }
    )
    private Set<Article> articles = new HashSet<>();

    /**
     * Default constructor.
     */
    public Category() {

    }

    /**
     * Constructor.
     *
     * @param title       the title of the new category.
     * @param description the description of the new category.
     * @param keywords    the keywords of the new category.
     */
    public Category(
            final String title,
            final String description,
            final String keywords
    ) {
        super(title, description, keywords);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "Category{" + super.toString() + '}';
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public Category clone() {
        return (Category) super.clone();
    }

    /**
     * Adds new article to the list of articles.
     * Adds a new article, if it is not null.
     * <pre>
     *     addArticle(null) - does nothing
     *     addArticle(new Article()) - Adds the article
     *     to the category article list.
     *     Also, to the article sets this category.
     * </pre>
     *
     * @param article an article to add.
     */
    @Override
    public void addArticle(final Article article) {
        if (isNotNull(article)) {
            this.articles.add(article);
            if (!this.equals(article.getCategory())) {
                article.setCategory(this);
            }
        }
    }

    /**
     * Adds new articles to the list of articles.
     * Adds a new articles, if they are not null and not empty.
     * <pre>
     *     addArticles(null) - does nothing
     *     addArticles(new ArrayList()) - does nothing
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new Object);
     *     addArticles(collection) - Adds the collection.
     *     Also, for each article sets this category.
     * </pre>
     *
     * @param articles the articles to add.
     */
    @Override
    public void addArticles(final Collection<Article> articles) {
        if (isNotEmpty(articles)) {
            articles.forEach(this::addArticle);
        }
    }

    /**
     * Removes an article from the list of articles.
     * Removes an incoming article if it contains in the article list.
     * <pre>
     *     removeArticle(null) - does nothing
     *     removeArticle(new Article()) - does nothing
     *
     *     Article article = new Article();
     *     article.setCategory(this);
     *     removeArticle(article) - Removes the articles
     *     from the category article list.
     *     Also, to the article sets null category.
     * </pre>
     *
     * @param article the article to remove.
     */
    @Override
    public void removeArticle(final Article article) {
        if (isNotNull(article) && containsArticle(article)) {
            this.articles.remove(article);
            if (this.equals(article.getCategory())) {
                article.setCategory(null);
            }
        }
    }

    /**
     * Removes articles from the list of articles.
     * Removes a articles, if they are not null and not empty.
     * <pre>
     *     removeArticles(null) - does nothing
     *     removeArticles(new ArrayList()) - does nothing
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new Object);
     *     removeArticles(collection) - Removes the collection.
     *     Also, for each article sets null category.
     * </pre>
     *
     * @param articles the articles to remove.
     */
    @Override
    public void removeArticles(final Collection<Article> articles) {
        if (isNotEmpty(articles)) {
            articles.forEach(this::removeArticle);
        }
    }

    /**
     * Returns an collection of articles.
     * Collection can be empty.
     *
     * @return The collection of articles (newer null).
     */
    @Override
    public Collection<Article> getArticles() {
        return this.articles;
    }

    /**
     * Sets a new articles to list of articles.
     * Clears the list of articles and adds new articles.
     *
     * @param articles the articles to add.
     */
    @Override
    public void setArticles(final Collection<Article> articles) {
        clearArticles();
        addArticles(articles);
    }

    /**
     * Contains an article in the list of articles.
     *
     * @param article the article to contain.
     * @return true if article is contains, false otherwise.
     */
    @Override
    public boolean containsArticle(final Article article) {
        return this.articles.contains(article);
    }

    /**
     * Contains articles in the list of articles.
     *
     * @param articles an articles to contain.
     * @return true if articles are contains, false otherwise.
     */
    @Override
    public boolean containsArticles(final Collection<Article> articles) {
        return this.articles.containsAll(articles);
    }

    /**
     * Clears the list of articles.
     */
    @Override
    public void clearArticles() {
        removeArticles(new ArrayList<>(this.articles));
        this.articles = new HashSet<>();
    }

    /**
     * Initializes the category.
     * Returns this category with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this category
     *     initialize(new Category()) - does nothing, returns this
     *     category with a new copied fields
     * </pre>
     *
     * @param category the category to copy.
     * @return This category with new fields (newer null).
     */
    @Override
    public Category initialize(final Category category) {
        super.initialize(category);
        return this;
    }
}
