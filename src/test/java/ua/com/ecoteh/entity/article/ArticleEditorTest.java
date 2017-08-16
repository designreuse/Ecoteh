package ua.com.ecoteh.entity.article;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.content.ContentEditorTest;

import java.util.Date;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getArticle;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategory;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ArticleEditorTest extends ContentEditorTest<Article> {

    private ArticleEditor editor;
    private Article article;

    @Before
    public void beforeTests() {
        this.article = getArticle();
        this.editor = new ArticleEditor(this.article);
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Article updatedArticle = this.editor.update();
        assertEquals(updatedArticle.getNumber(), this.article.getNumber());
        assertEquals(updatedArticle.getDate(), this.article.getDate());
        assertTrue(updatedArticle.getPrice() == this.article.getPrice());
        assertEquals(updatedArticle.getCurrency(), this.article.getCurrency());
        assertEquals(updatedArticle.getCategory(), this.article.getCategory());
    }

    @Test
    public void whenAddNumberThenBuildWithIt() {
        Article article;
        String number;
        for (int i = 0; i < 5; i++) {
            number = NUMBER + i;
            this.editor.addNumber(number);
            article = this.editor.update();
            assertEquals(article.getNumber(), number);
        }
    }

    @Test
    public void whenAddNullNumberThenBuildWithNewIt() {
        this.editor.addNumber(null);
        final Article article = this.editor.update();
        assertEquals(article.getNumber(), this.article.getNumber());
    }

    @Test
    public void whenAddDateThenBuildWithIt() {
        Article article;
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.editor.addDate(date);
            article = this.editor.update();
            assertEquals(article.getDate(), date);
        }
    }

    @Test
    public void whenAddNullDateThenBuildWithEmptyIt() {
        this.editor.addDate(null);
        final Article article = this.editor.update();
        assertEquals(article.getDate(), this.article.getDate());
    }

    @Test
    public void whenAddPriceThenBuildWithIt() {
        Article article;
        for (int i = 1; i < 5; i++) {
            this.editor.addPrice(i);
            article = this.editor.update();
            assertTrue(article.getPrice() == i);
        }
    }

    @Test
    public void whenAddNegativePriceThenBuildWithZero() {
        this.editor.addPrice(-PRICE);
        final Article article = this.editor.update();
        assertTrue(article.getPrice() == this.article.getPrice());
    }

    @Test
    public void whenAddCurrencyThenBuildWithIt() {
        Article article;
        String currency;
        for (int i = 0; i < 5; i++) {
            currency = CURRENCY + i;
            this.editor.addCurrency(currency);
            article = this.editor.update();
            assertEquals(article.getCurrency(), currency);
        }
    }

    @Test
    public void whenAddNullCurrencyThenBuildWithEmptyIt() {
        this.editor.addCurrency(null);
        final Article article = this.editor.update();
        assertEquals(article.getCurrency(), this.article.getCurrency());
    }

    @Test
    public void whenAddNoveltyThenBuildWithIt() {
        this.editor.addNovelty(false);
        Article article = this.editor.update();
        assertFalse(article.isNovelty());

        this.editor.addNovelty(true);
        article = this.editor.update();
        assertTrue(article.isNovelty());
    }

    @Test
    public void whenAddCategoryThenBuildWithIt() {
        final Category category = getCategory();
        this.editor.addCategory(category);
        final Article article = this.editor.update();
        assertEquals(article.getCategory(), category);
    }

    @Test
    public void whenAddNullCategoryThenBuildWithEmptyIt() {
        this.editor.addCategory(null);
        final Article article = this.editor.update();
        assertEquals(article.getCategory(), this.article.getCategory());
    }

    @Override
    protected ArticleEditor getEditor() {
        return this.editor;
    }

    @Override
    protected Article getModel() {
        return this.article;
    }
}