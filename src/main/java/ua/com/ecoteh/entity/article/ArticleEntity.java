package ua.com.ecoteh.entity.article;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.entity.content.ContentEntity;
import ua.com.ecoteh.util.generator.StringGenerator;
import ua.com.ecoteh.util.time.Time;

import javax.persistence.*;
import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

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
    private CategoryEntity categoryEntity;

    /**
     * Default constructor.
     */
    public ArticleEntity() {
        this.text = "";
        this.date = new Date();
        newNumber();
    }

    /**
     * Constructor.
     *
     * @param title       the title of a new articleEntity.
     * @param description the description of a new articleEntity.
     * @param text        the text of a new articleEntity.
     * @param keywords    the keywords of a new articleEntity.
     * @param number      the number of a new articleEntity.
     */
    public ArticleEntity(
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number
    ) {
        super(title, description, keywords);
        setText(text);
        setDate(new Date());
        setNumber(number);
    }

    /**
     * Constructor.
     *
     * @param title       the title of a new articleEntity.
     * @param description the description of a new articleEntity.
     * @param text        the text of a new articleEntity.
     * @param keywords    the keywords of a new articleEntity.
     * @param number      the number of a new articleEntity.
     * @param price       the price of a new articleEntity.
     */
    public ArticleEntity(
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number,
            final String price
    ) {
        this(title, description, text, keywords, number);
        setPrice(price);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "ArticleEntity{" + super.toString() +
                ", number='" + getNumber() + '\'' +
                ", text='" + getText() + '\'' +
                ", date=" + getDate() +
                ", price=" + getPrice() +
                ", categoryEntity=" + getCategoryEntity() +
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
            result = this.getNumber().equalsIgnoreCase(other.getNumber()) &&
                    this.getText().equalsIgnoreCase(other.getText());
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
                getNumber().hashCode() +
                getText().hashCode();
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
        if (isNotEmpty(number)) {
            this.number = number;
        } else {
            newNumber();
        }
    }

    /**
     * Generates new number to the articleEntity.
     */
    public void newNumber() {
        this.number = new StringGenerator().generate();
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
        this.text = isNotEmpty(text) ? text : "";
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
        this.date = isNotNull(date) ? date : new Date();
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
        this.price = isNotEmpty(price) ? price : "0";
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
     * @param categoryEntity the new categoryEntity to the articleEntity.
     */
    public void setCategoryEntity(final CategoryEntity categoryEntity) {
        if (isNull(this.categoryEntity)) {
            this.categoryEntity = categoryEntity;
        } else if (!this.categoryEntity.equals(categoryEntity)) {
            final CategoryEntity temp = this.categoryEntity;
            this.categoryEntity = categoryEntity;
            if (isNotNull(this.categoryEntity) && !this.categoryEntity.containsArticle(this)) {
                this.categoryEntity.addArticle(this);
            }
            if (isNotNull(temp)) {
                temp.removeArticle(this);
            }
        }
    }

    /**
     * Returns a categoryEntity of the articleEntity.
     *
     * @return The articleEntity categoryEntity.
     */
    public CategoryEntity getCategoryEntity() {
        return this.categoryEntity;
    }

    /**
     * Initializes the articleEntity.
     * Returns this articleEntity with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this articleEntity
     *     initialize(new ArticleEntity()) - does nothing, returns this
     *     articleEntity with a new copied fields
     * </pre>
     *
     * @param articleEntity the articleEntity to copy.
     * @return This articleEntity with new fields (newer null).
     */
    public ArticleEntity initialize(final ArticleEntity articleEntity) {
        if (isNotNull(articleEntity)) {
            super.initialize(articleEntity);
            this.setNumber(articleEntity.getNumber());
            this.setText(articleEntity.getText());
            this.setDate(articleEntity.getDate());
            this.setPrice(articleEntity.getPrice());
            this.setCategoryEntity(articleEntity.getCategoryEntity());
        }
        return this;
    }

    public Article convert() {
        return new ArticleEntityConverter(this).convert();
    }
}
