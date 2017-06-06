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
     * The text of this article entity.
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * The date of this article entity.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * The price of this article entity.
     */
    @Column(name = "price")
    private String price;

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
     *
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
                ", text='" + this.text + '\'' +
                ", date=" + this.date +
                ", price=" + this.price +
                ", category entity=" + this.category +
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
            result = this.number.equalsIgnoreCase(other.number) &&
                    this.text.equalsIgnoreCase(other.text);
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
        return super.hashCode() +
                this.number.hashCode() +
                this.text.hashCode();
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
     * If parameter number is blank, then call method newNumber().
     * <pre>
     *     setNumber(null) - number = "NewRandomNumber"
     *     setNumber("") - number = "NewRandomNumber"
     *     setNumber(" ") - number = "NewRandomNumber"
     *     setNumber("bob") - number = "bob"
     *     setNumber(" bob ") - number = "bob"
     * </pre>
     *
     * @param number the new number to the article entity.
     */
    public void setNumber(final String number) {
            this.number = number;
    }

    /**
     * Returns a text of the article entity.
     *
     * @return The article entity text or empty string (newer null).
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the article entity.
     * If parameter text is blank, then sets empty string.
     * <pre>
     *     setText(null) - text = ""
     *     setText("") - text = ""
     *     setText(" ") - text = ""
     *     setText("bob") - text = "bob"
     *     setText(" bob ") - text = "bob"
     * </pre>
     *
     * @param text a new text to the article entity.
     */
    public void setText(final String text) {
        this.text = text;
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
     * If parameter date is null, then sets new Date().
     * <pre>
     *     setDate(null) - date = new Date()
     *     setDate(someRealDate) - date = someRealDate
     * </pre>
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
    public String getPrice() {
        return this.price;
    }

    /**
     * Sets a new price to the article entity.
     *
     * @param price the new price to the article entity.
     */
    public void setPrice(final String price) {
        this.price = price;
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
     */
    @Override
    public Article convert() {
        return new ArticleEntityConverter(this).convert();
    }
}
