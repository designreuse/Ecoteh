package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.category.CategoryEditor;
import ua.com.ecoteh.entity.content.ContentEditor;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for editing an objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Article
 */
public final class ArticleEditor extends ContentEditor<Article, ArticleEditor> {

    /**
     * The article to edit.
     */
    private final Article article;

    /**
     * The new number of the article.
     */
    private String number;

    /**
     * The new date of the article.
     */
    private Date date;

    /**
     * The new price of the article.
     */
    private double price = -1;

    /**
     * The new price currency of the article.
     */
    private String currency;

    /**
     * The new category of the article.
     */
    private Category category;

    /**
     * Constructor.
     *
     * @param article the article to edit.
     */
    ArticleEditor(final Article article) {
        super(article);
        this.article = article;
    }

    /**
     * Updates and returns a new article.
     *
     * @return The updated article.
     */
    @Override
    public Article update() {
        final ArticleBuilder builder = Article.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addTitle(getTitle())
                .addUrl(getUrl())
                .addDescription(getDescription())
                .addKeywords(getKeywords())
                .addNumber(getNumber())
                .addText(getText())
                .addDate(getDate())
                .addPrice(getPrice())
                .addCurrency(getCurrency())
                .addLogo(getLogo())
                .addCategory(getCategory());
        return builder.build();
    }

    /**
     * Copies the incoming article.
     *
     * @param article the article to copy.
     * @return the article editor.
     */
    @Override
    public ArticleEditor copy(final Article article) {
        if (isNotNull(article)) {
            super.copy(article)
                    .addNumber(article.getNumber())
                    .addDate(article.getDate())
                    .addPrice(article.getPrice())
                    .addCurrency(article.getCurrency())
                    .addCategory(article.getCategory());
        }
        return this;
    }

    /**
     * Adds new number to the article.
     *
     * @param number a new number to the article.
     * @return the article editor.
     */
    public ArticleEditor addNumber(final String number) {
        this.number = number;
        return this;
    }

    /**
     * Adds new date to the article.
     *
     * @param date a new date to the article.
     * @return the article editor.
     */
    public ArticleEditor addDate(final Date date) {
        this.date = date;
        return this;
    }

    /**
     * Adds new price to the article.
     *
     * @param price a new price to the article.
     * @return the article editor.
     */
    public ArticleEditor addPrice(final double price) {
        this.price = price;
        return this;
    }

    /**
     * Adds a new price currency to the article.
     *
     * @param currency the new price currency to athe article.
     * @return the article editor.
     */
    public ArticleEditor addCurrency(final String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Adds new category to the article.
     *
     * @param category a new category to the article.
     * @return the article editor.
     */
    public ArticleEditor addCategory(final Category category) {
        this.category = category;
        return this;
    }

    /**
     * Returns a new number of the article.
     * Returns the article number if the number is null.
     *
     * @return The new article number.
     */
    private String getNumber() {
        return isNotNull(this.number) ? this.number : this.article.getNumber();
    }

    /**
     * Returns a new date of the article.
     * Returns the article date if the date is null.
     *
     * @return The new article date.
     */
    private Date getDate() {
        return isNotNull(this.date) ? this.date : this.article.getDate();
    }

    /**
     * Returns a new price of the article.
     * Returns the article price if the price is null.
     *
     * @return The new article price.
     */
    private double getPrice() {
        return (this.price > 0) ? this.price : this.article.getPrice();
    }

    /**
     * Returns a price currency of a new article.
     * Returns an empty string if the price currency is null or empty.
     *
     * @return The price currency or empty string (newer null).
     */
    private String getCurrency() {
        return isNotNull(this.currency) ? this.currency : this.article.getCurrency();
    }

    /**
     * Returns a new category of the article.
     * Returns the article category if the category is null.
     *
     * @return The new article category.
     * @see CategoryEditor
     */
    private Category getCategory() {
        final Category articleCategory = this.article.getCategory();
        final CategoryEditor categoryEditor = articleCategory.getEditor();
        categoryEditor.copy(this.category);
        return categoryEditor.update();
    }
}
