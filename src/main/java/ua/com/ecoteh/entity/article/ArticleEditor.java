package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.category.CategoryEditor;
import ua.com.ecoteh.entity.content.ContentEditor;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ArticleEditor extends ContentEditor<Article, ArticleEditor> {

    private final Article article;

    /**
     * The number of an articleEntity.
     */
    private String number;

    /**
     * The text of an articleEntity.
     */
    private String text;

    /**
     * The date of an articleEntity.
     */
    private Date date;

    /**
     * The price of an articleEntity.
     */
    private String price;

    /**
     * The categoryEntity of an articleEntity.
     */
    private Category category;

    /**
     * Constructor.
     * @param article
     */
    ArticleEditor(final Article article) {
        super(article);
        this.article = article;
    }

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
                .addLogo(getLogo())
                .addCategory(getCategory());
        return builder.build();
    }

    public ArticleEditor addNumber(final String number) {
        this.number = number;
        return this;
    }

    public ArticleEditor addText(final String text) {
        this.text = text;
        return this;
    }

    public ArticleEditor addDate(final Date date) {
        this.date = date;
        return this;
    }

    public ArticleEditor addPrice(final String price) {
        this.price = price;
        return this;
    }

    public ArticleEditor addCategory(final Category category) {
        this.category = category;
        return this;
    }

    private String getNumber() {
        return isNotNull(this.number) ? this.number : this.article.getNumber();
    }

    private String getText() {
        return isNotNull(this.text) ? this.text : this.article.getText();
    }

    private Date getDate() {
        return isNotNull(this.date) ? this.date : this.article.getDate();
    }

    private String getPrice() {
        return isNotNull(this.price) ? this.price : this.article.getPrice();
    }

    private Category getCategory() {
        final CategoryEditor categoryEditor = this.category.getEditor();
        categoryEditor.copy(this.category);
        return categoryEditor.update();
    }
}
