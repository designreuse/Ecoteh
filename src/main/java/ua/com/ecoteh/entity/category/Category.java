package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.entity.file.File;

import java.util.Collection;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * The set of a articleEntities.
     */
    private final Collection<Article> articles;

    /**
     *
     * @param id
     * @param validated
     * @param title
     * @param url
     * @param description
     * @param keywords
     * @param logo
     * @param articles
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
        return "CategoryEntity{" + super.toString() + '}';
    }

    /**
     * Returns an collection of articleEntities.
     * Collection can be empty.
     *
     * @return The collection of articleEntities (newer null).
     */
    public Collection<Article> getArticle() {
        return this.articles;
    }

    /**
     *
     * @return
     */
    public static CategoryBuilder getBuilder() {
        return new CategoryBuilder();
    }
}
