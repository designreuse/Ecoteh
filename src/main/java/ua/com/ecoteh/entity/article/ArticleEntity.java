package ua.com.ecoteh.entity.article;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.entity.content.ContentEntity;
import ua.com.ecoteh.util.time.Time;

import javax.persistence.*;
import java.util.Date;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link ArticleEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Entity
@Table(name = "articles")
public class ArticleEntity extends ContentEntity {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The number of this article entity.
     */
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    /**
     * The date of this article entity.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * The price of this article entity.
     */
    @Column(name = "price")
    private double price;

    /**
     * The price currency of this article.
     */
    @Column(name = "currency")
    private String currency;

    /**
     * The category entity of this article entity.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    @Fetch(FetchMode.JOIN)
    private CategoryEntity category;

    /**
     * Constructor.
     */
    protected ArticleEntity() {
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "ArticleEntity{" + super.toString() +
                ", number='" + this.number + '\'' +
                ", date=" + this.date +
                ", price=" + this.price +
                ", currency=" + this.currency +
                ", categoryEntity=" + this.category +
                '}';
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
            final ArticleEntity other = (ArticleEntity) object;
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
    public ArticleEntity clone() {
        return (ArticleEntity) super.clone();
    }

    /**
     * Returns a number of the article entity.
     *
     * @return The article entity number (newer null).
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Sets new number to the article entity.
     *
     * @param number the new number to the article entity.
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * Returns a date of the article entity.
     *
     * @return The article entity date (newer null).
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new date to the article entity.
     *
     * @param date the new date to the article entity.
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Returns a price of the article entity.
     *
     * @return The article entity price (newer null).
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets a new price to the article entity.
     *
     * @param price the new price to the article entity.
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * Returns a price currency of the article.
     *
     * @return The article price currency (newer null).
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     * Sets a new price currency to the article entity.
     *
     * @param currency the new price currency to the article entity.
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * Returns this article entity date in string format.
     *
     * @return The article entity string-date (newer null).
     */
    public String getDateToString() {
        return Time.getDate(getDate());
    }

    /**
     * Sets a new category entity to the article entity.
     *
     * @param category the new category entity to the article entity.
     */
    public void setCategoryEntity(final CategoryEntity category) {
        this.category = category;
    }

    /**
     * Returns a category entity of the article entity.
     *
     * @return The article entity category entity.
     */
    public CategoryEntity getCategoryEntity() {
        return this.category;
    }

    /**
     * Converts this entity and returns a object
     * of the {@link Article} class.
     *
     * @return The object of the {@link Article} class (newer null).
     * @see ArticleEntityConverter
     */
    @Override
    public Article convert() {
        return new ArticleEntityConverter(this).convert();
    }
}
