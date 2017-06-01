package ua.com.ecoteh.entity.category;

import ua.com.ecoteh.entity.content.ContentEntity;
import ua.com.ecoteh.entity.article.ArticleEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link CategoryEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * The set of a articleEntities.
     */
    @OneToMany(
            mappedBy = "categoryEntity",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }
    )
    private Set<ArticleEntity> articleEntities = new HashSet<>();

    /**
     * Default constructor.
     */
    public CategoryEntity() {

    }

    /**
     * Constructor.
     *
     * @param title       the title of the new categoryEntity.
     * @param description the description of the new categoryEntity.
     * @param keywords    the keywords of the new categoryEntity.
     */
    public CategoryEntity(
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
     * Adds new articleEntity to the list of articleEntities.
     * Adds a new articleEntity, if it is not null.
     * <pre>
     *     addArticle(null) - does nothing
     *     addArticle(new ArticleEntity()) - Adds the articleEntity
     *     to the categoryEntity articleEntity list.
     *     Also, to the articleEntity sets this categoryEntity.
     * </pre>
     *
     * @param articleEntity an articleEntity to add.
     */
    public void addArticle(final ArticleEntity articleEntity) {
        if (isNotNull(articleEntity)) {
            this.articleEntities.add(articleEntity);
            if (!this.equals(articleEntity.getCategoryEntity())) {
                articleEntity.setCategoryEntity(this);
            }
        }
    }

    /**
     * Adds new articleEntities to the list of articleEntities.
     * Adds a new articleEntities, if they are not null and not empty.
     * <pre>
     *     addArticles(null) - does nothing
     *     addArticles(new ArrayList()) - does nothing
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new Object);
     *     addArticles(collection) - Adds the collection.
     *     Also, for each articleEntity sets this categoryEntity.
     * </pre>
     *
     * @param articleEntities the articleEntities to add.
     */
    public void addArticles(final Collection<ArticleEntity> articleEntities) {
        if (isNotEmpty(articleEntities)) {
            articleEntities.forEach(this::addArticle);
        }
    }

    /**
     * Removes an articleEntity from the list of articleEntities.
     * Removes an incoming articleEntity if it contains in the articleEntity list.
     * <pre>
     *     removeArticle(null) - does nothing
     *     removeArticle(new ArticleEntity()) - does nothing
     *
     *     ArticleEntity articleEntity = new ArticleEntity();
     *     articleEntity.setCategoryEntity(this);
     *     removeArticle(articleEntity) - Removes the articleEntities
     *     from the categoryEntity articleEntity list.
     *     Also, to the articleEntity sets null categoryEntity.
     * </pre>
     *
     * @param articleEntity the articleEntity to remove.
     */
    public void removeArticle(final ArticleEntity articleEntity) {
        if (isNotNull(articleEntity) && containsArticle(articleEntity)) {
            this.articleEntities.remove(articleEntity);
            if (this.equals(articleEntity.getCategoryEntity())) {
                articleEntity.setCategoryEntity(null);
            }
        }
    }

    /**
     * Removes articleEntities from the list of articleEntities.
     * Removes a articleEntities, if they are not null and not empty.
     * <pre>
     *     removeArticles(null) - does nothing
     *     removeArticles(new ArrayList()) - does nothing
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new Object);
     *     removeArticles(collection) - Removes the collection.
     *     Also, for each articleEntity sets null categoryEntity.
     * </pre>
     *
     * @param articleEntities the articleEntities to remove.
     */
    public void removeArticles(final Collection<ArticleEntity> articleEntities) {
        if (isNotEmpty(articleEntities)) {
            articleEntities.forEach(this::removeArticle);
        }
    }

    /**
     * Returns an collection of articleEntities.
     * Collection can be empty.
     *
     * @return The collection of articleEntities (newer null).
     */
    public Collection<ArticleEntity> getArticleEntities() {
        return this.articleEntities;
    }

    /**
     * Sets a new articleEntities to list of articleEntities.
     * Clears the list of articleEntities and adds new articleEntities.
     *
     * @param articleEntities the articleEntities to add.
     */
    public void setArticleEntities(final Collection<ArticleEntity> articleEntities) {
        clearArticles();
        addArticles(articleEntities);
    }

    /**
     * Contains an articleEntity in the list of articleEntities.
     *
     * @param articleEntity the articleEntity to contain.
     * @return true if articleEntity is contains, false otherwise.
     */
    public boolean containsArticle(final ArticleEntity articleEntity) {
        return this.articleEntities.contains(articleEntity);
    }

    /**
     * Contains articleEntities in the list of articleEntities.
     *
     * @param articleEntities an articleEntities to contain.
     * @return true if articleEntities are contains, false otherwise.
     */
    public boolean containsArticles(final Collection<ArticleEntity> articleEntities) {
        return this.articleEntities.containsAll(articleEntities);
    }

    /**
     * Clears the list of articleEntities.
     */
    public void clearArticles() {
        removeArticles(new ArrayList<>(this.articleEntities));
        this.articleEntities = new HashSet<>();
    }

    /**
     * Initializes the categoryEntity.
     * Returns this categoryEntity with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this categoryEntity
     *     initialize(new CategoryEntity()) - does nothing, returns this
     *     categoryEntity with a new copied fields
     * </pre>
     *
     * @param categoryEntity the categoryEntity to copy.
     * @return This categoryEntity with new fields (newer null).
     */
    public CategoryEntity initialize(final CategoryEntity categoryEntity) {
        super.initialize(categoryEntity);
        return this;
    }

    public Category convert() {
        return new CategoryEntityConverter(this).convert();
    }
}
