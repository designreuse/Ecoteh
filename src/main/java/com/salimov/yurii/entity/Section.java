package com.salimov.yurii.entity;

import javax.persistence.*;
import java.util.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Section} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Content
 * @see Model
 */
@Entity
@Table(name = "sections")
public final class Section extends Content<Long> {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The photo of an article.
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
     * The set of a categories.
     *
     * @see Category
     */
    // TODO: EAGER -> LAZY !!!
    @OneToMany(
            mappedBy = "section",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            }
    )
    private Set<Category> categories = new HashSet<>();

    /**
     * Default constructor.
     */
    public Section() {
    }

    /**
     * Constructor.
     * Initializes a main section parameters.
     *
     * @param title       a title of the new section.
     * @param description a description of the new section.
     * @param keywords    a keywords of the new section.
     */
    public Section(
            final String title,
            final String description,
            final String keywords
    ) {
        super(title, description, keywords);
    }

    /**
     * Initializes some parameter of the section.
     *
     * @param title       a new title of the section.
     * @param description a new title of the description.
     * @param keywords    a new title of the keywords.
     * @param photo       a new photo of the section.
     * @see Photo
     */
    public void initialize(
            final String title,
            final String description,
            final String keywords,
            final Photo photo
    ) {
        super.initialize(title, description, keywords);
        setPhoto(photo);
    }

    /**
     * Returns a photo of the section.
     *
     * @return The section photo.
     * @see Photo
     */
    public Photo getPhoto() {
        return this.photo;
    }

    /**
     * Sets a new photo to the section.
     * If parameter photo is blank, then sets {@code null}.
     *
     * @param photo a new main photo to the section.
     * @see Photo
     */
    public void setPhoto(final Photo photo) {
        this.photo = Photo.isValidated(photo) ? photo : null;
    }

    /**
     * Adds new category to the list of categories.
     * Adds a new category, if it is valid.
     *
     * @param category a category to add.
     * @see Category
     */
    public void addCategory(final Category category) {
        if (category != null) {
            this.categories.add(category);
            if (category.getSection() == null ||
                            !category.getSection().equals(this)) {
                category.setSection(this);
            }
        }
    }

    /**
     * Adds new categories to the list of categories.
     * Adds new categories, if they are valid.
     *
     * @param categories a categories to add.
     * @see Category
     */
    public void addCategories(final Collection<Category> categories) {
        if (categories != null && !categories.isEmpty()) {
            categories.forEach(this::addCategory);
        }
    }

    /**
     * Removes category from the list of categories.
     *
     * @param category a category to remove.
     * @see Category
     */
    public void removeCategory(final Category category) {
        if (category != null && containsCategory(category)) {
            this.categories.remove(category);
            if (category.getSection() != null &&
                    category.getSection().equals(this)) {
                category.setSection(null);
            }
        }
    }

    /**
     * Removes categories from the list of categories.
     *
     * @param categories a categories to remove.
     * @see Category
     */
    public void removeCategories(final Collection<Category> categories) {
        if (categories != null && !categories.isEmpty()) {
            categories.forEach(this::removeCategory);
        }
    }

    /**
     * Returns an unmodifiable list of categories.
     *
     * @return The list of categories.
     * @see Category
     */
    public Collection<Category> getCategories() {
        return this.categories;
    }

    /**
     * Sets a new categories to list of articles.
     * Clears the list of categories and adds new categories.
     *
     * @param categories a categories to add.
     * @see Category
     */
    public void setCategories(final Collection<Category> categories) {
        clearCategories();
        addCategories(categories);
    }

    /**
     * Contains category in the list of categories.
     *
     * @param category a category to contain.
     * @return {@code true} if category is contains, {@code false} otherwise.
     * @see Category
     */
    public boolean containsCategory(final Category category) {
        return this.categories.contains(category);
    }

    /**
     * Contains categories in the list of categories.
     *
     * @param categories a categories to contain.
     * @return {@code true} if categories is contains, {@code false} otherwise.
     * @see Category
     */
    public boolean containsCategories(final Collection<Category> categories) {
        return this.categories.containsAll(categories);
    }

    /**
     * Clears the list of categories.
     *
     * @see Category
     */
    public void clearCategories() {
        removeCategories(new ArrayList<>(this.categories));
        this.categories = new HashSet<>();
    }

    /**
     * Statically validates the section.
     * Section is valid if it is a valid content object
     * and if it has at least one article.
     *
     * @param section a section to validate.
     * @return {@code true} if the section are valid, {@code false} otherwise.
     */
    public static boolean isValidated(final Section section) {
        boolean result = false;
        if (Content.isValidated(section)) {
            if (!section.getCategories().isEmpty()) {
                for (Category category : section.getCategories()) {
                    if (Category.isValidated(category)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
