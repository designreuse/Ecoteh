package ua.com.ecoteh.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.util.generator.StringGenerator;
import ua.com.ecoteh.util.time.Time;

import javax.persistence.*;
import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "articles")
public class Article extends Content implements IArticle {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The number of an article.
     */
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    /**
     * The text of an article.
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * The date of an article.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * The price of an article.
     */
    @Column(name = "price")
    private String price;

    /**
     * The category of an article.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    @Fetch(FetchMode.JOIN)
    private Category category;

    /**
     * Default constructor.
     */
    public Article() {
        this.text = "";
        this.date = new Date();
        newNumber();
    }

    /**
     * Constructor.
     *
     * @param title       the title of a new article.
     * @param description the description of a new article.
     * @param text        the text of a new article.
     * @param keywords    the keywords of a new article.
     * @param number      the number of a new article.
     */
    public Article(
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
     * @param title       the title of a new article.
     * @param description the description of a new article.
     * @param text        the text of a new article.
     * @param keywords    the keywords of a new article.
     * @param number      the number of a new article.
     * @param price       the price of a new article.
     */
    public Article(
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
        return "Article{" + super.toString() +
                ", number='" + getNumber() + '\'' +
                ", text='" + getText() + '\'' +
                ", date=" + getDate() +
                ", price=" + getPrice() +
                ", category=" + getCategory() +
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
            final Article other = (Article) object;
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
    public Article clone() {
        return (Article) super.clone();
    }

    /**
     * Returns a number of the article.
     *
     * @return The article number (newer null).
     */
    @Override
    public String getNumber() {
        return this.number;
    }

    /**
     * Sets new number to the article.
     * If parameter number is blank, then call method newNumber().
     * <pre>
     *     setNumber(null) - number = "NewRandomNumber"
     *     setNumber("") - number = "NewRandomNumber"
     *     setNumber(" ") - number = "NewRandomNumber"
     *     setNumber("bob") - number = "bob"
     *     setNumber(" bob ") - number = "bob"
     * </pre>
     *
     * @param number the new number to the article.
     */
    @Override
    public void setNumber(final String number) {
        if (isNotEmpty(number)) {
            this.number = number;
        } else {
            newNumber();
        }
    }

    /**
     * Generates new number to the article.
     */
    @Override
    public void newNumber() {
        this.number = new StringGenerator().generate();
    }

    /**
     * Returns a text of the article.
     *
     * @return The article text or empty string (newer null).
     */
    @Override
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the article.
     * If parameter text is blank, then sets empty string.
     * <pre>
     *     setText(null) - text = ""
     *     setText("") - text = ""
     *     setText(" ") - text = ""
     *     setText("bob") - text = "bob"
     *     setText(" bob ") - text = "bob"
     * </pre>
     *
     * @param text a new text to the article.
     */
    @Override
    public void setText(final String text) {
        this.text = isNotEmpty(text) ? text : "";
    }

    /**
     * Returns a date of the article.
     *
     * @return The article date (newer null).
     */
    @Override
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new date to the article.
     * If parameter date is null, then sets new Date().
     * <pre>
     *     setDate(null) - date = new Date()
     *     setDate(someRealDate) - date = someRealDate
     * </pre>
     *
     * @param date the new date to the article.
     */
    @Override
    public void setDate(final Date date) {
        this.date = isNotNull(date) ? date : new Date();
    }

    /**
     * Returns a price of the article.
     *
     * @return The article price or '0' (newer null).
     */
    @Override
    public String getPrice() {
        return this.price;
    }

    /**
     * Sets a new price to the article.
     * If parameter price is blank, then sets 0.
     * <pre>
     *     setPrice(null) - price = "0"
     *     setPrice("") - address = "0"
     *     setPrice(" ") - address = "0"
     *     setPrice("$100") - address = "$100"
     *     setPrice(" $100 ") - address = " $100 "
     * </pre>
     *
     * @param price the new price to the article.
     */
    @Override
    public void setPrice(final String price) {
        this.price = isNotEmpty(price) ? price : "0";
    }

    /**
     * Returns an article date in string format.
     *
     * @return The article string-date (newer null).
     */
    @Override
    public String getDateToString() {
        return Time.getDate(getDate());
    }

    /**
     * Sets a new category to the article.
     * Sets a new category if this category equals null
     * or this category not equals new category.
     * Also the article deletes from old category and adds to new category.
     *
     * @param category the new category to the article.
     */
    @Override
    public void setCategory(final Category category) {
        if (isNull(this.category)) {
            this.category = category;
        } else if (!this.category.equals(category)) {
            final Category temp = this.category;
            this.category = category;
            if (isNotNull(this.category) && !this.category.containsArticle(this)) {
                this.category.addArticle(this);
            }
            if (isNotNull(temp)) {
                temp.removeArticle(this);
            }
        }
    }

    /**
     * Returns a category of the article.
     *
     * @return The article category.
     */
    @Override
    public Category getCategory() {
        return this.category;
    }

    /**
     * Initializes the article.
     * Returns this article with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this article
     *     initialize(new Article()) - does nothing, returns this
     *     article with a new copied fields
     * </pre>
     *
     * @param article the article to copy.
     * @return This article with new fields (newer null).
     */
    @Override
    public Article initialize(final Article article) {
        if (isNotNull(article)) {
            super.initialize(article);
            this.setNumber(article.getNumber());
            this.setText(article.getText());
            this.setDate(article.getDate());
            this.setPrice(article.getPrice());
            this.setCategory(article.getCategory());
        }
        return this;
    }
}
