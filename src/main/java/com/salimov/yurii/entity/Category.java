package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.ICategory;

import javax.persistence.*;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Category} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "categories")
public final class Category extends Content implements ICategory {

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
     * @param title       a title of the new category.
     * @param description a description of the new category.
     * @param keywords    a keywords of the new category.
     */
    public Category(
            final String title,
            final String description,
            final String keywords
    ) {
        super(title, description, keywords);
    }

    @Override
    public String toString() {
        return "Category{" + super.toString() + '}';
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Category clone() {
        return (Category) super.clone();
    }

    /**
     * Adds new article to the list of articles.
     * Adds a new article, if it is valid.
     *
     * @param article an article to add.
     */
    @Override
    public void addArticle(final Article article) {
        if (article != null) {
            this.articles.add(article);
            final Category category = article.getCategory();
            if ((category == null) || (!category.equals(this))) {
                article.setCategory(this);
            }
        }
    }

    /**
     * Adds new articles to the list of articles.
     * Adds a new articles, if they are valid.
     *
     * @param articles an articles to add.
     */
    @Override
    public void addArticles(final Collection<Article> articles) {
        if ((articles != null) && (!articles.isEmpty())) {
            articles.forEach(this::addArticle);
        }
    }

    /**
     * Removes article from the list of articles.
     *
     * @param article an article to remove.
     */
    @Override
    public void removeArticle(final Article article) {
        if ((article != null) && containsArticle(article)) {
            this.articles.remove(article);
            final Category category = article.getCategory();
            if ((category != null) && category.equals(this)) {
                article.setCategory(null);
            }
        }
    }

    /**
     * Removes articles from the list of articles.
     *
     * @param articles an articles to remove.
     */
    @Override
    public void removeArticles(final Collection<Article> articles) {
        if ((articles != null) && !articles.isEmpty()) {
            articles.forEach(this::removeArticle);
        }
    }

    /**
     * Returns an list of articles.
     *
     * @return The list of articles.
     */
    @Override
    public Collection<Article> getArticles() {
        return this.articles;
    }

    /**
     * Sets a new articles to list of articles.
     * Clears the list of articles and adds new articles.
     *
     * @param articles an articles to add.
     */
    @Override
    public void setArticles(final Collection<Article> articles) {
        clearArticles();
        addArticles(articles);
    }

    /**
     * Contains article in the list of articles.
     *
     * @param article an article to contain.
     * @return {@code true} if article is contains,
     * {@code false} otherwise.
     */
    @Override
    public boolean containsArticle(final Article article) {
        return this.articles.contains(article);
    }

    /**
     * Contains articles in the list of articles.
     *
     * @param articles an articles to contain.
     * @return {@code true} if articles are contains,
     * {@code false} otherwise.
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
     *
     * @param category a category to copy.
     * @return The this category with new fields.
     */
    @Override
    public Category initialize(final Category category) {
        if (category != null) {
            super.initialize(category);
        }
        return this;
    }
}
