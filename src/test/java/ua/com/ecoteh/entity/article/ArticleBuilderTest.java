package ua.com.ecoteh.entity.article;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.content.ContentBuilderTest;

import java.util.Date;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategory;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ArticleBuilderTest extends ContentBuilderTest<Article> {

    private ArticleBuilder builder;

    @Before
    public void beforeTests() {
        this.builder = new ArticleBuilder();
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Article article = this.builder.build();
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
        assertTrue(article.getPrice() >= 0);
        assertNotNull(article.getCurrency());
        assertNotNull(article.getCategory());
    }

    @Test
    public void whenAddNumberThenBuildWithIt() {
        Article article;
        String number;
        for (int i = 0; i < 5; i++) {
            number = NUMBER + i;
            this.builder.addNumber(number);
            article = this.builder.build();
            assertEquals(article.getNumber(), number);
        }
    }

    @Test
    public void whenAddNullNumberThenBuildWithNewIt() {
        this.builder.addNumber(null);
        final Article article = this.builder.build();
        assertFalse(article.getNumber().isEmpty());
    }

    @Test
    public void whenAddDateThenBuildWithIt() {
        Article article;
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.builder.addDate(date);
            article = this.builder.build();
            assertEquals(article.getDate(), date);
        }
    }

    @Test
    public void whenAddNullDateThenBuildWithEmptyIt() {
        this.builder.addDate(null);
        final Article article = this.builder.build();
        assertNotNull(article.getDate());
    }

    @Test
    public void whenAddPriceThenBuildWithIt() {
        Article article;
        for (int i = 0; i < 5; i++) {
            this.builder.addPrice(i);
            article = this.builder.build();
            assertTrue(article.getPrice() == i);
        }
    }

    @Test
    public void whenAddNegativePriceThenBuildWithZero() {
        this.builder.addPrice(-PRICE);
        final Article article = this.builder.build();
        assertTrue(article.getPrice() == 0);
    }

    @Test
    public void whenAddCurrencyThenBuildWithIt() {
        Article article;
        String currency;
        for (int i = 0; i < 5; i++) {
            currency = CURRENCY + i;
            this.builder.addCurrency(currency);
            article = this.builder.build();
            assertEquals(article.getCurrency(), currency);
        }
    }

    @Test
    public void whenAddNullCurrencyThenBuildWithEmptyIt() {
        this.builder.addCurrency(null);
        final Article article = this.builder.build();
        assertTrue(article.getCurrency().isEmpty());
    }

    @Test
    public void whenAddCategoryThenBuildWithIt() {
        final Category category = getCategory();
        this.builder.addCategory(category);
        final Article article = this.builder.build();
        assertEquals(article.getCategory(), category);
    }

    @Test
    public void whenAddNullCategoryThenBuildWithEmptyIt() {
        this.builder.addCategory(null);
        final Article article = this.builder.build();
        assertNotNull(article.getCategory());
    }

    @Override
    protected ArticleBuilder getBuilder() {
        return this.builder;
    }
}
