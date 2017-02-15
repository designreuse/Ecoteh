package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.ArticleDao;
import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class implements a set of standard methods for working {@link Article}
 * objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see ArticleDao
 * @see Article
 * @see ArticleRepository
 * @see DataDaoImpl
 */
@Repository
@ComponentScan(basePackages = "com.salimov.yurii.repository")
public final class ArticleDaoImpl extends DataDaoImpl<Article, Long> implements ArticleDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link Article} objects with a database.
     *
     * @see ArticleRepository
     */
    private final ArticleRepository repository;

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides a set of JPA methods
     *                   for working {@link Article} objects with a database.
     * @see ArticleRepository
     */
    @Autowired
    public ArticleDaoImpl(final ArticleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Returns article with the parameter title from a database.
     *
     * @param title a title of the article to return.
     * @return The article with the parameter title.
     * @see Article
     */
    @Override
    public Article getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns article with the parameter url from a database.
     *
     * @param url a url of the article to return.
     * @return The article with the parameter url.
     * @see Article
     */
    @Override
    public Article getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Returns article with the parameter number from a database.
     *
     * @param number a title of the article to return.
     * @return The article with parameter title.
     * @see Article
     */
    @Override
    public Article getByNumber(final String number) {
        return this.repository.findByNumber(number);
    }

    /**
     * Returns articles with the parameter category id from a database.
     *
     * @param id a category id of the article to return.
     * @return The article with parameter category id.
     * @see Article
     * @see Category
     */
    @Override
    public List<Article> getByCategoryId(final Long id) {
        return this.repository.findByCategoryId(id);
    }

    /**
     * Removes article with the parameter title from a database.
     *
     * @param title a title of the article to remove.
     * @see Article
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes article with the parameter url from a database.
     *
     * @param url a url of the article to remove.
     * @see Article
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }

    /**
     * Removes article with the parameter number from a database.
     *
     * @param number a number of the article to remove.
     * @see Article
     */
    @Override
    public void removeByNumber(final String number) {
        this.repository.deleteByNumber(number);
    }
}
