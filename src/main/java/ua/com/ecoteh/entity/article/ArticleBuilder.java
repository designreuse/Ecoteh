package ua.com.ecoteh.entity.article;

import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.content.ContentBuilder;
import ua.com.ecoteh.util.generator.StringGenerator;

import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ArticleBuilder extends ContentBuilder<Article, ArticleBuilder> {

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

    ArticleBuilder() {
    }

    @Override
    public Article build() {
        return new Article(
                getId(), isValidated(),
                getTitle(), getUrl(),
                getDescription(), getKeywords(),
                getNumber(), getText(),
                getDate(), getPrice(),
                getLogo(), getCategory()
        );
    }

    public ArticleBuilder addNumber(String number) {
        this.number = number;
        return this;
    }

    public ArticleBuilder addText(String text) {
        this.text = text;
        return this;
    }

    public ArticleBuilder addDate(Date date) {
        this.date = date;
        return this;
    }

    public ArticleBuilder addPrice(String price) {
        this.price = price;
        return this;
    }

    public ArticleBuilder addCategory(Category category) {
        this.category = category;
        return this;
    }

    private String getNumber() {
        String number;
        if (isNotEmpty(this.number)) {
            number = this.number;
        } else {
            number = new StringGenerator().generate();
        }
        return number;
    }

    private String getText() {
        return isNotEmpty(this.text) ? this.text : "";
    }

    private Date getDate() {
        return isNotNull(this.date) ? this.date : new Date();
    }

    private String getPrice() {
        return isNotEmpty(this.price) ? this.price : "0";
    }

    private Category getCategory() {
        return isNotNull(this.category) ? this.category : Category.getBuilder().build();
    }
}
