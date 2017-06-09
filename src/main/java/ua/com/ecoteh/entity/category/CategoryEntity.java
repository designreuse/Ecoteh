package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.content.ContentEntity;

import javax.persistence.*;
import java.util.Collection;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link CategoryEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Category
 */
@Entity
@Table(name = "categories")
public class CategoryEntity extends ContentEntity {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The set of a article entities.
     */
    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.REMOVE,
                    /*CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH*/
            }
    )
    private Collection<ArticleEntity> articles;

    /**
     * Default constructor.
     */
    protected CategoryEntity() {
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
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public CategoryEntity clone() {
        return (CategoryEntity) super.clone();
    }

    /**
     * Returns an collection of article entities.
     * Collection can be empty.
     *
     * @return The collection of article entities (newer null).
     * @see ArticleEntity
     */
    public Collection<ArticleEntity> getArticleEntities() {
        return this.articles;
    }

    /**
     * Sets a new article entities to list of article entities.
     *
     * @param articles the article entities to add.
     */
    public void setArticleEntities(final Collection<ArticleEntity> articles) {
        this.articles = articles;
    }

    /**
     * Converts this entity and returns a object
     * of the {@link Category} class.
     *
     * @return The object of the {@link Category} class (newer null).
     * @see CategoryEntityConverter
     */
    @Override
    public Category convert() {
        return new CategoryEntityConverter(this).convert();
    }
}
