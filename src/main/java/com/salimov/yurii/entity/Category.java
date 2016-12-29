package com.salimov.yurii.entity;

import javax.persistence.*;
import java.util.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Category} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Content
 * @see Model
 */
@Entity
@Table(name = "categories")
public final class Category extends Content<Long> {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The photo of a category.
     *
     * @see Photo
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "photo_id",
            referencedColumnName = "id"
    )
    private Photo photo;

    /**
     * The section of a category.
     *
     * @see Section
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "section_id",
            referencedColumnName = "id"
    )
    private Section section;

    /**
     * The set of a articles.
     *
     * @see Article
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
     * Initializes a main category parameters.
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

    /**
     * Initializes some parameter of the category.
     *
     * @param title       a new title of the category.
     * @param description a new description of the category.
     * @param keywords    a new keywords of the category.
     * @param photo       a new photo of the category.
     * @param section     a new section of the category.
     * @see Photo
     * @see Section
     */
    public void initialize(
            final String title,
            final String description,
            final String keywords,
            final Photo photo,
            final Section section
    ) {
        super.initialize(title, description, keywords);
        setPhoto(photo);
        setSection(section);
    }

    /**
     * Returns a photo of the category.
     *
     * @return The category photo.
     * @see Photo
     */
    public Photo getPhoto() {
        return this.photo;
    }

    /**
     * Sets a new photo to the category.
     * If parameter photo is blank, then sets {@code null}.
     *
     * @param photo a new main photo to the category.
     * @see Photo
     */
    public void setPhoto(final Photo photo) {
        this.photo = Photo.isValidated(photo) ? photo : null;
    }

    /**
     * Sets a new section to the category.
     * Sets a new section if this section equals null
     * or this section not equals new section.
     * Also the category deletes from old section and adds to new section.
     *
     * @param section a new section of the category.
     * @see Section
     */
    public void setSection(final Section section) {
        if (this.section == null || !this.section.equals(section)) {
            final Section temp = this.section;
            this.section = section;
            if (this.section != null && !this.section.containsCategory(this)) {
                this.section.addCategory(this);
            }
            if (temp != null) {
                temp.removeCategory(this);
            }
        }
    }

    /**
     * Returns a section of the category.
     *
     * @return The category section.
     * @see Section
     */
    public Section getSection() {
        return this.section;
    }

    /**
     * Adds new article to the list of articles.
     * Adds a new article, if it is valid.
     *
     * @param article an article to add.
     * @see Article
     */
    public void addArticle(final Article article) {
        if (article != null) {
            this.articles.add(article);
            final Category category = article.getCategory();
            if (category == null || !category.equals(this)) {
                article.setCategory(this);
            }
        }
    }

    /**
     * Adds new articles to the list of articles.
     * Adds a new articles, if they are valid.
     *
     * @param articles an articles to add.
     * @see Article
     */
    public void addArticles(final Collection<Article> articles) {
        if (articles != null && !articles.isEmpty()) {
            articles.forEach(this::addArticle);
        }
    }

    /**
     * Removes article from the list of articles.
     *
     * @param article an article to remove.
     * @see Article
     */
    public void removeArticle(final Article article) {
        if (article != null && containsArticle(article)) {
            this.articles.remove(article);
            final Category category = article.getCategory();
            if (category != null && category.equals(this)) {
                article.setCategory(null);
            }
        }
    }

    /**
     * Removes articles from the list of articles.
     *
     * @param articles an articles to remove.
     * @see Article
     */
    public void removeArticles(final Collection<Article> articles) {
        if (articles != null && !articles.isEmpty()) {
            articles.forEach(this::removeArticle);
        }
    }

    /**
     * Returns an list of articles.
     *
     * @return The list of articles.
     * @see Article
     */
    public Collection<Article> getArticles() {
        return this.articles;
    }

    /**
     * Sets a new articles to list of articles.
     * Clears the list of articles and adds new articles.
     *
     * @param articles an articles to add.
     * @see Article
     */
    public void setArticles(final Collection<Article> articles) {
        clearArticles();
        addArticles(articles);
    }

    /**
     * Contains article in the list of articles.
     *
     * @param article an article to contain.
     * @return {@code true} if article is contains, {@code false} otherwise.
     * @see Article
     */
    public boolean containsArticle(final Article article) {
        return this.articles.contains(article);
    }

    /**
     * Contains articles in the list of articles.
     *
     * @param articles an articles to contain.
     * @return {@code true} if articles are contains, {@code false} otherwise.
     * @see Article
     */
    public boolean containsArticles(final Collection<Article> articles) {
        return this.articles.containsAll(articles);
    }

    /**
     * Clears the list of articles.
     *
     * @see Article
     */
    public void clearArticles() {
        removeArticles(new ArrayList<>(this.articles));
        this.articles = new HashSet<>();
    }

    /**
     * Statically validates the category.
     * Category is valid if it is a valid content object
     * and if it has at least one article.
     *
     * @param category a category to validate.
     * @return {@code true} if the article is valid, {@code false} otherwise.
     */
    public static boolean isValidated(final Category category) {
        boolean result = false;
        if (Content.isValidated(category)) {
            for (Article article : category.getArticles()) {
                if (Article.isValidated(article)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
