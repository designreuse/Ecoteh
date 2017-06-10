package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.content.ContentBuilder;
import ua.com.ecoteh.util.generator.StringGenerator;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for build an objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Article
 */
public final class ArticleBuilder extends ContentBuilder<Article, ArticleBuilder> {

    /**
     * The number of a new article.
     */
    private String number;

    /**
     * The text of a new article.
     */
    private String text;

    /**
     * The date of a new article.
     */
    private Date date;

    /**
     * The price of a new article.
     */
    private double price;

    /**
     * The price currency of this article.
     */
    private String currency;

    /**
     * The category of a new article.
     */
    private Category category;

    /**
     * Constructor.
     */
    ArticleBuilder() {
    }

    /**
     * Builds and returns a new article.
     *
     * @return The new article.
     * @see Article
     */
    @Override
    public Article build() {
        return new Article(
                getId(), isValidated(),
                getTitle(), getUrl(),
                getDescription(), getKeywords(),
                getNumber(), getText(), getDate(),
                getPrice(), getCurrency(),
                getLogo(), getCategory()
        );
    }

    /**
     * Adds a new number to a new article.
     *
     * @param number the new number to a new article.
     * @return the article builder.
     */
    public ArticleBuilder addNumber(final String number) {
        this.number = number;
        return this;
    }

    /**
     * Adds a new text to a new article.
     *
     * @param text the new text to a new article.
     * @return the article builder.
     */
    public ArticleBuilder addText(final String text) {
        this.text = text;
        return this;
    }

    /**
     * Adds a new date to a new article.
     *
     * @param date the new date to a new article.
     * @return the article builder.
     */
    public ArticleBuilder addDate(final Date date) {
        this.date = date;
        return this;
    }

    /**
     * Adds a new price to a new article.
     *
     * @param price the new price to a new article.
     * @return the article builder.
     */
    public ArticleBuilder addPrice(final double price) {
        this.price = price;
        return this;
    }

    /**
     * Adds a new price currency to a new article.
     *
     * @param currency the new price currency to a new article.
     * @return the article builder.
     */
    public ArticleBuilder addCurrency(final String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Adds a new category to a new article.
     *
     * @param category the new category to a new article.
     * @return the article builder.
     */
    public ArticleBuilder addCategory(final Category category) {
        this.category = category;
        return this;
    }

    /**
     * Returns a number of a new article.
     * Returns a new random number if the number is null or empty.
     *
     * @return The number or random number (newer null).
     */
    private String getNumber() {
        String number;
        if (isNotEmpty(this.number)) {
            number = this.number;
        } else {
            number = new StringGenerator().generate();
        }
        return number;
    }

    /**
     * Returns a text of a new article.
     * Returns an empty string if the text is null or empty.
     *
     * @return The text or empty string (newer null).
     */
    private String getText() {
        return isNotEmpty(this.text) ? this.text : "";
    }

    /**
     * Returns a text of a new article.
     * Returns an empty string if the text is null or empty.
     *
     * @return The text or empty string (newer null).
     */
    private Date getDate() {
        return isNotNull(this.date) ? this.date : new Date();
    }

    /**
     * Returns a price of a new article.
     * Returns a "0" if the price is null or empty.
     *
     * @return The price or "0" (newer null).
     */
    private double getPrice() {
        return (this.price > 0) ? this.price : 0;
    }

    /**
     * Returns a price currency of a new article.
     * Returns an empty string if the price currency is null or empty.
     *
     * @return The price currency or empty string (newer null).
     */
    private String getCurrency() {
        return isNotEmpty(this.currency) ? this.currency : "";
    }

    /**
     * Returns a category of a new article.
     * Returns a new category if the category is null.
     *
     * @return The category (newer null).
     */
    private Category getCategory() {
        return isNotNull(this.category) ? this.category : Category.getBuilder().build();
    }
}
