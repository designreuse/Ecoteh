package com.salimov.yurii.dao.impl;

import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.ArticleDao;
import com.salimov.yurii.dao.interfaces.CategoryDao;
import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static org.junit.Assert.*;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ArticleDaoImplTest extends ContentDAOImplTest<Article> {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CategoryDao categoryDao;

    @Test
    @Transactional
    public void whenGetByNumberThenReturnSomeArticle() {
        final Article article = getObject();
        final Article article1 = this.articleDao.add(article);
        final Article article2 = this.articleDao.getByNumber(
                article1.getNumber()
        );
        assertNotNull(article2);
        assertEquals(article1, article2);
    }

    @Test
    @Transactional
    public void whenGetByCategoryIdThenReturnSomeArticles() {
        final Article article = getObject();
        final Category category = this.categoryDao.add(
                getCategory()
        );
        article.setCategory(category);
        this.articleDao.add(article);
        final List<Article> articles = this.articleDao.getByCategoryId(
                category.getId()
        );
        assertNotNull(articles);
        assertFalse(articles.isEmpty());
    }

    @Test
    @Transactional
    public void whenRemoveByNumber() {
        final Article article = this.articleDao.add(
                getArticle()
        );
        assertTrue(
                this.articleDao.exists(
                        article.getId()
                )
        );
        this.articleDao.removeByNumber(article.getNumber());
        assertTrue(
                this.articleDao.exists(
                        article.getId()
                )
        );
    }

    @Ignore
    @Override
    protected ArticleDao getDao() {
        return this.articleDao;
    }

    @Ignore
    @Override
    protected Article getObject() {
        final Article article = getArticle();
        article.setTitle(article.getTitle() + " " + getRandomInt(10));
        article.setId(Long.MAX_VALUE);
        return article;
    }

    @Ignore
    @Override
    protected Collection<Article> getObjects() {
        return getArticles();
    }
}
