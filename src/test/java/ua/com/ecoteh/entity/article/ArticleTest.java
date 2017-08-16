package ua.com.ecoteh.entity.article;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.content.ContentTest;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileBuilder;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategory;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ArticleTest extends ContentTest {

    private static Article article;

    @BeforeClass
    public static void setUp() throws Exception {
        article = new Article(
                ID, VALIDATION, TITLE, URL, TEXT, DESCRIPTION, KEYWORDS, NUMBER,
                DATE, PRICE, CURRENCY, getFile(), VALIDATION, getCategory()
        );
    }

    @Test
    @Override
    public void toStringTest() throws Exception {
        final String testString = "Article{" +
                "Content{" +
                "Model{id=" + article.getId() +
                ", validated=" + article.isValidated() + '}' +
                ", title='" + article.getTitle() + '\'' +
                ", url='" + article.getUrl() + '\'' +
                ", text='" + article.getText() + '\'' +
                ", description='" + article.getDescription() + '\'' +
                ", keywords='" + article.getKeywords() + '\'' +
                ", logo=" + article.getLogo() + '}' +
                ", number='" + article.getNumber() + '\'' +
                ", date=" + article.getDate() +
                ", price=" + article.getPrice() +
                ", currency=" + article.getCurrency() +
                ", isNovelty=" + article.isNovelty() +
                ", category=" + article.getCategory() +
                '}';
        assertEquals(article.toString(), testString);
    }

    @Test
    public void getNumber() throws Exception {
        assertFalse(article.getNumber().isEmpty());
    }

    @Test
    public void getText() throws Exception {
        assertFalse(article.getText().isEmpty());
    }

    @Test
    public void getDate() throws Exception {
        assertNotNull(article.getDate());
    }

    @Test
    public void getPrice() throws Exception {
        assertTrue(article.getPrice() >= 0);
    }

    @Test
    public void getCurrency() throws Exception {
        assertFalse(article.getCurrency().isEmpty());
    }

    @Test
    public void getDateToString() throws Exception {
        assertFalse(article.getDateToString().isEmpty());
    }

    @Test
    public void getCategoryTest() throws Exception {
        assertNotNull(article.getCategory());
    }

    @Test
    @Override
    public void convert() throws Exception {
        super.convert();
        final ArticleEntity entity = article.convert();
        assertTrue(entity.getId() >= 0);
        assertEquals(entity.isValidated(), article.isValidated());
        assertEquals(entity.getTitle(), article.getTitle());
        assertEquals(entity.getUrl(), article.getUrl());
        assertEquals(entity.getDescription(), article.getDescription());
        assertEquals(entity.getKeywords(), article.getKeywords());
        assertEquals(entity.getNumber(), article.getNumber());
        assertEquals(entity.getText(), article.getText());
        assertEquals(entity.getDate(), article.getDate());
        assertTrue(entity.getPrice() == article.getPrice());
        assertEquals(entity.getCurrency(), article.getCurrency());
        assertNotNull(entity.getLogoEntity());
        assertNotNull(entity.getCategoryEntity());
    }

    @Test
    public void getBuilder() throws Exception {
        final FileBuilder builder = File.getBuilder();
        assertNotNull(builder);
    }

    @Override
    protected Article getInstance() {
        return article;
    }
}