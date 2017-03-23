package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.ArticleDao;
import com.salimov.ecoteh.entity.Article;
import com.salimov.ecoteh.repository.ArticleRepository;
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
 */
@Repository
@ComponentScan(basePackages = "com.salimov.ecoteh.repository")
public final class ArticleDaoImpl extends DataDaoImpl<Article> implements ArticleDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link Article} objects with a database.
     */
    private final ArticleRepository repository;

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides a set of JPA methods
     *                   for working {@link Article} objects with a database.
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
     */
    @Override
    public Article getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns article with the parameter url from a database.
     *
     * @param url a URL of the article to return.
     * @return The article with the parameter url.
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
     */
    @Override
    public List<Article> getByCategoryId(final Long id) {
        return this.repository.findByCategoryId(id);
    }

    /**
     * Removes article with the parameter title from a database.
     *
     * @param title a title of the article to remove.
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes article with the parameter url from a database.
     *
     * @param url a URL of the article to remove.
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }

    /**
     * Removes article with the parameter number from a database.
     *
     * @param number a number of the article to remove.
     */
    @Override
    public void removeByNumber(final String number) {
        this.repository.deleteByNumber(number);
    }
}
