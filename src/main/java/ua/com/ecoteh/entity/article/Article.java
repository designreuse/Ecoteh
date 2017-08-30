package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.util.time.Time;

import java.util.Date;

/**
 * The class implements a set of methods for working
 * with objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ArticleEntity
 */
public final class Article extends Content {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The number of this article.
     */
    private final String number;

    /**
     * The date of this article.
     */
    private final Date date;

    /**
     * The price of this article.
     */
    private final String price;

    /**
     * The sort price of this article.
     */
    private int sortPrice;

    /**
     * It`s novelty product.
     */
    private final boolean isNovelty;

    /**
     * The category of this article.
     */
    private final Category category;

    /**
     * Constructor.
     *
     * @param id          the unique identifier for each article.
     * @param validated   the validations of a new article.
     * @param title       the title of a new article.
     * @param url         the url of a new article.
     * @param description the description of a new article.
     * @param keywords    the keywords of a new article.
     * @param number      the number of a new article.
     * @param text        the text of a new article.
     * @param date        the date of a new article.
     * @param price       the price of a new article.
     * @param sortPrice   the sort price of a new article.
     * @param logo        the logo of a new article.
     * @param category    the category of a new article.
     */
    Article(
            final long id, final boolean validated,
            final String title, final String url, final String text,
            final String description, final String keywords, final String number,
            final Date date, final String price, final int sortPrice,
            final File logo, final boolean isNovelty, final Category category
    ) {
        super(id, validated, title, url, text, description, keywords, logo);
        this.number = number;
        this.date = date;
        this.price = price;
        this.sortPrice = sortPrice;
        this.isNovelty = isNovelty;
        this.category = category;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "Article{" + super.toString() +
                ", number='" + this.number + '\'' +
                ", date=" + this.date +
                ", price=" + this.price +
                ", sortPrice=" + this.sortPrice +
                ", isNovelty=" + this.isNovelty +
                ", category=" + this.category +
                '}';
    }

    /**
     * Returns a string representation of the object to search.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toSearch() {
        return super.toSearch() + " , " + this.number;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the object
     * argument, false otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Article other = (Article) object;
            result = this.number.equalsIgnoreCase(other.number);
        }
        return result;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return super.hashCode() + this.number.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public Article clone() {
        final Article clone = (Article) super.clone();
        final ArticleEditor articleEditor = clone.getEditor();
        articleEditor.addCategory(this.category.clone());
        return articleEditor.update();
    }

    /**
     * Returns a number of the article.
     *
     * @return The article number (newer null).
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Returns a date of the article.
     *
     * @return The article date (newer null).
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Returns a price of the article.
     *
     * @return The article price (newer null).
     */
    public String getPrice() {
        return this.price;
    }

    /**
     * Returns a sort price of the article entity.
     *
     * @return The article entity sort price (newer null).
     */
    public int getSortPrice() {
        return this.sortPrice;
    }

    /**
     * Novelty the model entity.
     *
     * @return true if the model entity is novelty, false otherwise.
     */
    public boolean isNovelty() {
        return this.isNovelty;
    }

    /**
     * Returns an article date in string format.
     *
     * @return The article string-date (newer null).
     */
    public String getDateToString() {
        return Time.getDate(getDate());
    }

    /**
     * Returns a category of the article.
     *
     * @return The article category.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Converts this object and returns an entity
     * of the {@link ArticleEntity} class.
     *
     * @return The entity of the {@link ArticleEntity} class (newer null).
     * @see ArticleConverter
     */
    @Override
    public ArticleEntity convert() {
        return new ArticleConverter(this).convert();
    }

    /**
     * Returns a editor for updating this article.
     *
     * @return the article editor (newer null).
     */
    @Override
    public ArticleEditor getEditor() {
        return new ArticleEditor(this);
    }

    /**
     * Returns a builder for creating a new article.
     *
     * @return the article builder (newer null).
     */
    public static ArticleBuilder getBuilder() {
        return new ArticleBuilder();
    }
}
