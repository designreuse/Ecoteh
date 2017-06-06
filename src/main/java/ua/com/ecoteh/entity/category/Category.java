package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.entity.file.File;

import java.util.Collection;

/**
 * The class implements a set of methods for working
 * with objects of the {@link Category} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see CategoryEntity
 */
public final class Category extends Content {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The set of a article.
     *
     * @see Article
     */
    private final Collection<Article> articles;

    /**
     * Constructor.
     *
     * @param id          the unique identifier for each category.
     * @param validated   the validations of a new category.
     * @param title       the title of a new category.
     * @param url         the URL of a new category.
     * @param description the description of a new category.
     * @param keywords    the keywords of a new category.
     * @param logo        the logo of a new category.
     * @param articles    the articles of a new category.
     */
    Category(
            final long id, final boolean validated, final String title,
            final String url, final String description, final String keywords,
            final File logo, final Collection<Article> articles
    ) {
        super(id, validated, title, url, description, keywords, logo);
        this.articles = articles;
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
     * Returns an collection of articles.
     * Collection can be empty.
     *
     * @return The collection of articles (newer null).
     * @see Article
     */
    public Collection<Article> getArticles() {
        return this.articles;
    }

    /**
     * Converts this object and returns an entity
     * of the {@link CategoryEntity} class.
     *
     * @return The entity of the {@link CategoryEntity} class (newer null).
     * @see CategoryEntity
     */
    @Override
    public CategoryEntity convert() {
        return new CategoryConverter(this).convert();
    }

    /**
     * Returns a editor for updating this category.
     *
     * @return the category editor (newer null).
     * @see CategoryEditor
     */
    @Override
    public CategoryEditor getEditor() {
        return new CategoryEditor(this);
    }

    /**
     * Returns a builder for creating a new category.
     *
     * @return the category builder (newer null).
     * @see CategoryBuilder
     */
    public static CategoryBuilder getBuilder() {
        return new CategoryBuilder();
    }
}
