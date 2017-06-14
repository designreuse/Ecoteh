package ua.com.ecoteh.entity.category;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.content.ContentEditorTest;

import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getArticles;
import static ua.com.ecoteh.mocks.enity.MockModels.getCategory;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CategoryEditorTest extends ContentEditorTest<Category> {

    private CategoryEditor editor;
    private Category category;

    @Before
    public void beforeTests() {
        this.category = getCategory();
        this.editor = new CategoryEditor(this.category);
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Category updatedCategory = this.editor.update();
        assertEquals(updatedCategory.getArticles(), this.category.getArticles());
    }

    @Test
    public void whenAddArticlesThenBuildWithIt() {
        final Collection<Article> articles = getArticles();
        this.editor.addArticles(articles);
        final Category category = this.editor.update();
        assertFalse(category.getArticles().isEmpty());
    }

    @Test
    public void whenAddNullTitleThenBuildWithEmptyIt() {
        this.editor.addArticles(null);
        final Category category = this.editor.update();
        assertEquals(category.getArticles(), this.category.getArticles());
    }

    @Test
    public void whenRemoveArticleThenBuildWithIt() {
        final Collection<Article> articles = getArticles();
        this.editor.addArticles(articles);
        final Article article = articles.iterator().next();
        this.editor.removeArticle(article);
        final Category category = this.editor.update();
        assertFalse(category.getArticles().contains(article));
    }

    @Test
    public void whenRemoveArticlesThenBuildWithIt() {
        final Collection<Article> articles = getArticles();
        this.editor.addArticles(articles);
        this.editor.removeArticles(articles);
        final Category category = this.editor.update();
        for (Article article : articles) {
            assertFalse(category.getArticles().contains(article));
        }
    }

    @Test
    public void whenClearArticlesThenBuildWithIt() {
        final Collection<Article> articles = getArticles();
        this.editor.addArticles(articles);
        this.editor.clearArticles();
        final Category category = this.editor.update();
        for (Article article : articles) {
            assertFalse(category.getArticles().contains(article));
        }
    }

    @Override
    protected CategoryEditor getEditor() {
        return this.editor;
    }

    @Override
    protected Category getModel() {
        return this.category;
    }
}