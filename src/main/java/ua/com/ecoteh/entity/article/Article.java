package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.util.time.Time;

import java.util.Date;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * The number of an articleEntity.
     */
    private final String number;

    /**
     * The text of an articleEntity.
     */
    private final String text;

    /**
     * The date of an articleEntity.
     */
    private final Date date;

    /**
     * The price of an articleEntity.
     */
    private final String price;

    /**
     * The categoryEntity of an articleEntity.
     */
    private final Category category;

    /**
     *
     * @param id
     * @param validated
     * @param title
     * @param url
     * @param description
     * @param keywords
     * @param number
     * @param text
     * @param date
     * @param price
     * @param logo
     * @param category
     */
    Article(
            final long id, final boolean validated,
            final String title, final String url,
            final String description, final String keywords,
            final String number, final String text,
            final Date date, final String price,
            final File logo, final Category category
    ) {
        super(id, validated, title, url, description, keywords, logo);
        this.number = number;
        this.text = text;
        this.date = date;
        this.price = price;
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
                ", text='" + this.text + '\'' +
                ", date=" + this.date +
                ", price=" + this.price +
                ", category=" + this.category +
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
     * Returns a number of the articleEntity.
     *
     * @return The articleEntity number (newer null).
     */
    public String getNumber() {
        return this.number;
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
     * Returns a date of the articleEntity.
     *
     * @return The articleEntity date (newer null).
     */
    public Date getDate() {
        return this.date;
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
     * Returns an articleEntity date in string format.
     *
     * @return The articleEntity string-date (newer null).
     */
    public String getDateToString() {
        return Time.getDate(getDate());
    }

    /**
     * Returns a categoryEntity of the articleEntity.
     *
     * @return The articleEntity categoryEntity.
     */
    public Category getCategory() {
        return this.category;
    }

    public ArticleEntity convert() {
        return new ArticleConverter(this).convert();
    }

    /**
     *
     * @return
     */
    public static ArticleBuilder getBuilder() {
        return new ArticleBuilder();
    }
}
