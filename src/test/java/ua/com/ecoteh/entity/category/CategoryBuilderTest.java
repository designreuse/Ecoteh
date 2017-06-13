package ua.com.ecoteh.entity.category;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.content.ContentBuilderTest;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.enity.MockModels.getArticles;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CategoryBuilderTest extends ContentBuilderTest<Category> {

    private CategoryBuilder builder;

    @Before
    public void beforeTests() {
        this.builder = new CategoryBuilder();
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Category category = this.builder.build();
        assertNotNull(category.getArticles());
    }

    @Test
    public void whenAddArticlesThenBuildWithIt() {
        final Collection<Article> articles = getArticles();
        this.builder.addArticles(articles);
        final Category category = this.builder.build();
        assertFalse(category.getArticles().isEmpty());
    }

    @Test
    public void whenAddNullTitleThenBuildWithEmptyIt() {
        this.builder.addArticles(null);
        final Category category = this.builder.build();
        assertTrue(category.getArticles().isEmpty());
    }

    @Test
    public void whenRemoveArticleThenBuildWithIt() {
        final Collection<Article> articles = getArticles();
        this.builder.addArticles(articles);
        final Article article = articles.iterator().next();
        this.builder.removeArticle(article);
        final Category category = this.builder.build();
        assertFalse(category.getArticles().contains(article));
    }

    @Test
    public void whenRemoveArticlesThenBuildWithIt() {
        final Collection<Article> articles = getArticles();
        this.builder.addArticles(articles);
        this.builder.removeArticles(articles);
        final Category category = this.builder.build();
        for (Article article : articles) {
            assertFalse(category.getArticles().contains(article));
        }
    }

    @Test
    public void whenClearArticlesThenBuildWithIt() {
        final Collection<Article> articles = getArticles();
        this.builder.addArticles(articles);
        this.builder.clearArticles();
        final Category category = this.builder.build();
        for (Article article : articles) {
            assertFalse(category.getArticles().contains(article));
        }
    }

    @Override
    protected CategoryBuilder getBuilder() {
        return this.builder;
    }
}