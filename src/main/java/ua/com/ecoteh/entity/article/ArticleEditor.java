package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.category.CategoryEditor;
import ua.com.ecoteh.entity.content.ContentEditor;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
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
    private String price;

    /**
     * The sort price of the article.
     */
    private int sortPrice;

    /**
     * The new value of novelty of the article.
     */
    private int isNovelty;

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
                .addSortPrice(getSortPrice())
                .addNovelty(isNovelty())
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
                    .addNovelty(article.isNovelty())
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
    public ArticleEditor addPrice(final String price) {
        this.price = price;
        return this;
    }

    /**
     * Adds a new sort price to the article.
     *
     * @param sortPrice a new sort price to the article.
     * @return the article editor.
     */
    public ArticleEditor addSortPrice(final int sortPrice) {
        this.sortPrice = sortPrice;
        return this;
    }

    /**
     * Adds new novelty to the article.
     *
     * @param isNovelty a new novelty to the article.
     * @return the article editor.
     */
    public ArticleEditor addNovelty(final boolean isNovelty) {
        this.isNovelty = isNovelty ? 1 : 2;
        return this;
    }

    /**
     * Sets novelty article.
     *
     * @return the article editor.
     */
    public ArticleEditor setNovelty() {
        return addNovelty(true);
    }

    /**
     * Sets not novelty article.
     *
     * @return the article editor.
     */
    public ArticleEditor setNotNovelty() {
        return addNovelty(false);
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
    private String getPrice() {
        return isNotNull(this.price) ? this.price : this.article.getPrice();
    }

    /**
     * Returns a sort price of a new article.
     *
     * @return The sort price (newer null).
     */
    private int getSortPrice() {
        final int sortPrice;
        if (isNotEmpty(getPrice())) {
            sortPrice = (this.sortPrice >= 0) ? this.sortPrice : this.article.getSortPrice();
        } else {
            sortPrice = 0;
        }
        return sortPrice;
    }

    /**
     * Validates the model.
     *
     * @return true if the model is valid, false otherwise.
     */
    private boolean isNovelty() {
        final boolean result;
        if (this.isNovelty > 0) {
            result = (this.isNovelty == 1);
        } else {
            result = this.article.isValidated();
        }
        return result;
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
