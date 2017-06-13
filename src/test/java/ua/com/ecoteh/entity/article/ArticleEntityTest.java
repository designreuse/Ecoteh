package ua.com.ecoteh.entity.article;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.content.ContentEntityTest;

import java.util.Date;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCategoryEntity;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ArticleEntityTest extends ContentEntityTest {

    private ArticleEntity article;

    @Before
    public void beforeTest() {
        this.article = new ArticleEntity();
        this.article.setId(ID);
        this.article.setValidated(VALIDATION);
        this.article.setTitle(TITLE);
        this.article.setUrl(URL);
        this.article.setDescription(DESCRIPTION);
        this.article.setKeywords(KEYWORDS);
        this.article.setNumber(NUMBER);
        this.article.setText(TEXT);
        this.article.setDate(DATE);
        this.article.setPrice(PRICE);
        this.article.setCurrency(CURRENCY);
        this.article.setLogoEntity(getFileEntity());
        this.article.setCategoryEntity(getCategoryEntity());
    }

    @Test
    @Override
    public void toStringTest() {
        final String toStringTest = "ArticleEntity{" +
                "ContentEntity{" +
                "ModelEntity{" +
                "id=" + this.article.getId() +
                ", validated=" + this.article.isValidated() +
                '}'+
                ", title='" + this.article.getTitle() + '\'' +
                ", url='" + this.article.getUrl() + '\'' +
                ", description='" + this.article.getDescription() + '\'' +
                ", keywords='" + this.article.getKeywords() + '\'' +
                ", logo=" + this.article.getLogoEntity() +
                '}'+
                ", number='" + this.article.getNumber() + '\'' +
                ", text='" + this.article.getText() + '\'' +
                ", date=" + this.article.getDate() +
                ", price=" + this.article.getPrice() +
                ", currency=" + this.article.getCurrency() +
                ", categoryEntity=" + this.article.getCategoryEntity() +
                '}';
        assertEquals(this.article.toString(), toStringTest);
    }

    @Test
    public void whenSetNumberThenGetIt() {
        String number;
        for (int i = 0; i < 5; i++) {
            number = NUMBER + i;
            this.article.setNumber(number);
            assertEquals(this.article.getNumber(), number);
        }
    }

    @Test
    public void whenSetTextThenGetIt() {
        String text;
        for (int i = 0; i < 5; i++) {
            text = TEXT + i;
            this.article.setText(text);
            assertEquals(this.article.getText(), text);
        }
    }

    @Test
    public void whenSetDateThenGetIt() {
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.article.setDate(date);
            assertEquals(this.article.getDate(), date);
        }
    }

    @Test
    public void whenSetPriceThenGetIt() {
        for (int i = 0; i < 5; i++) {
            this.article.setPrice(i);
            assertTrue(this.article.getPrice() == i);
        }
    }

    @Test
    public void whenSetCurrencyThenGetIt() {
        String currency;
        for (int i = 0; i < 5; i++) {
            currency = CURRENCY + i;
            this.article.setCurrency(currency);
            assertEquals(this.article.getCurrency(), currency);
        }
    }

    @Test
    public void whenSetDateToStringTheReturnNotNull() {
        assertNotNull(this.article.getDateToString());
    }

    @Test
    public void whenConvertThenReturnValidModel() {
        final Article article = this.article.convert();
        assertEquals(article.getId(), this.article.getId());
        assertEquals(article.isValidated(), this.article.isValidated());
        assertEquals(article.getTitle(), this.article.getTitle());
        assertEquals(article.getUrl(), this.article.getUrl());
        assertEquals(article.getDescription(), this.article.getDescription());
        assertEquals(article.getKeywords(), this.article.getKeywords());
        assertEquals(article.getNumber(), this.article.getNumber());
        assertEquals(article.getText(), this.article.getText());
        assertEquals(article.getDate(), this.article.getDate());
        assertTrue(article.getPrice() == this.article.getPrice());
        assertEquals(article.getCurrency(), this.article.getCurrency());
        assertNotNull(article.getLogo());
        assertNotNull(article.getCategory());
    }

    @Override
    protected ArticleEntity getInstance() {
        return this.article;
    }
}
