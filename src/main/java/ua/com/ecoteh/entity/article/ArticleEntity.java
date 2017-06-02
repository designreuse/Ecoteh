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
     * The number of an articleEntity.
     */
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    /**
     * The text of an articleEntity.
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * The date of an articleEntity.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * The price of an articleEntity.
     */
    @Column(name = "price")
    private String price;

    /**
     * The categoryEntity of an articleEntity.
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
     * Returns a number of the articleEntity.
     *
     * @return The articleEntity number (newer null).
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Sets new number to the articleEntity.
     * If parameter number is blank, then call method newNumber().
     * <pre>
     *     setNumber(null) - number = "NewRandomNumber"
     *     setNumber("") - number = "NewRandomNumber"
     *     setNumber(" ") - number = "NewRandomNumber"
     *     setNumber("bob") - number = "bob"
     *     setNumber(" bob ") - number = "bob"
     * </pre>
     *
     * @param number the new number to the articleEntity.
     */
    public void setNumber(final String number) {
            this.number = number;
    }

    /**
     * Returns a text of the articleEntity.
     *
     * @return The articleEntity text or empty string (newer null).
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the articleEntity.
     * If parameter text is blank, then sets empty string.
     * <pre>
     *     setText(null) - text = ""
     *     setText("") - text = ""
     *     setText(" ") - text = ""
     *     setText("bob") - text = "bob"
     *     setText(" bob ") - text = "bob"
     * </pre>
     *
     * @param text a new text to the articleEntity.
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Returns a date of the articleEntity.
     *
     * @return The articleEntity date (newer null).
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new date to the articleEntity.
     * If parameter date is null, then sets new Date().
     * <pre>
     *     setDate(null) - date = new Date()
     *     setDate(someRealDate) - date = someRealDate
     * </pre>
     *
     * @param date the new date to the articleEntity.
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Returns a price of the articleEntity.
     *
     * @return The articleEntity price or '0' (newer null).
     */
    public String getPrice() {
        return this.price;
    }

    /**
     * Sets a new price to the articleEntity.
     * If parameter price is blank, then sets 0.
     * <pre>
     *     setPrice(null) - price = "0"
     *     setPrice("") - address = "0"
     *     setPrice(" ") - address = "0"
     *     setPrice("$100") - address = "$100"
     *     setPrice(" $100 ") - address = " $100 "
     * </pre>
     *
     * @param price the new price to the articleEntity.
     */
    public void setPrice(final String price) {
        this.price = price;
    }

    /**
     * Returns an articleEntity date in string format.
     *
     * @return The articleEntity string-date (newer null).
     */
    public String getDateToString() {
        return Time.getDate(getDate());
    }

    /**
     * Sets a new categoryEntity to the articleEntity.
     * Sets a new categoryEntity if this categoryEntity equals null
     * or this categoryEntity not equals new categoryEntity.
     * Also the articleEntity deletes from old categoryEntity and adds to new categoryEntity.
     *
     * @param category the new categoryEntity to the articleEntity.
     */
    public void setCategoryEntity(final CategoryEntity category) {
        this.category = category;
    }

    /**
     * Returns a categoryEntity of the articleEntity.
     *
     * @return The articleEntity categoryEntity.
     */
    public CategoryEntity getCategoryEntity() {
        return this.category;
    }

    public Article convert() {
        return new ArticleEntityConverter(this).convert();
    }
}
