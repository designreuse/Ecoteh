package com.salimov.ecoteh.entity;

import com.salimov.ecoteh.entity.interfaces.IArticle;
import com.salimov.ecoteh.util.generator.StringGenerator;
import com.salimov.ecoteh.util.time.Time;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
     * @param title       a title of the new article.
     * @param description a description of the new article.
     * @param text        a text of the new article.
     * @param keywords    a keywords of the new article.
     * @param number      a number of the new article.
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
     * @param title       a title of the new article.
     * @param description a description of the new article.
     * @param text        a text of the new article.
     * @param keywords    a keywords of the new article.
     * @param number      a number of the new article.
     * @param price       a price of the new article.
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
     * @return A string representation of the object.
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
     * @return {@code true} if this object is the same as the object
     * argument, {@code false} otherwise otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Article other = (Article) object;
            result = this.number.equalsIgnoreCase(other.number)
                    && this.text.equalsIgnoreCase(other.text);
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
        return super.hashCode() + this.number.hashCode() + this.text.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Article clone() {
        return (Article) super.clone();
    }

    /**
     * Returns a number of the article.
     *
     * @return The article number.
     */
    @Override
    public String getNumber() {
        return this.number;
    }

    /**
     * Sets new number to the article.
     * If parameter number is blank, then call method newNumber().
     *
     * @param number a new number to the article.
     */
    @Override
    public void setNumber(final String number) {
        if (isNotBlank(number)) {
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
     * @return The article text.
     */
    @Override
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the article.
     * If parameter text is blank, then sets empty string.
     *
     * @param text a new text to the article.
     */
    @Override
    public void setText(final String text) {
        this.text = isNotBlank(text) ? text : "";
    }

    /**
     * Returns a date of the article.
     *
     * @return The article date.
     */
    @Override
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new date to the article.
     * If parameter date is {@code null}, then sets {@code new Date()}.
     *
     * @param date a new date to the article.
     */
    @Override
    public void setDate(final Date date) {
        this.date = (date != null) ? date : new Date();
    }

    /**
     * Returns a price of the article.
     *
     * @return The article price.
     */
    @Override
    public String getPrice() {
        return this.price;
    }

    /**
     * Sets a new price to the article.
     * If parameter text is blank, then sets {@code new Date()}.
     *
     * @param price a new price to the article.
     */
    @Override
    public void setPrice(final String price) {
        this.price = isNotBlank(price) ? price : "0";
    }

    /**
     * Returns an article date in string format.
     *
     * @return The article string-date.
     */
    @Override
    public String getDateToString() {
        return Time.getDateToString(this.date);
    }

    /**
     * Sets a new category to the article.
     * Sets a new category if this category equals null
     * or this category not equals new category.
     * Also the article deletes from old category and adds to new category.
     *
     * @param category a new category to the article.
     */
    @Override
    public void setCategory(final Category category) {
        if (this.category == null) {
            this.category = category;
        } else if (!this.category.equals(category)) {
            final Category temp = this.category;
            this.category = category;
            if ((this.category != null) && !this.category.containsArticle(this)) {
                this.category.addArticle(this);
            }
            if (temp != null) {
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
     *
     * @param article a article to copy.
     * @return The this article with new fields.
     */
    @Override
    public Article initialize(final Article article) {
        if (article != null) {
            super.initialize(article);
            this.setNumber(article.getNumber());
            this.setText(article.getText());
            this.setDate(article.getDate());
            this.setPrice(article.getPrice());
            if (article.getCategory() != null) {
                this.setCategory(article.getCategory());
            }
        }
        return this;
    }
}
