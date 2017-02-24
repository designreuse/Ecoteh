package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.ContentDao;
import com.salimov.yurii.entity.Content;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.ContentService;
import com.salimov.yurii.service.data.interfaces.FileService;
import com.salimov.yurii.util.comparator.ContentComparator;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, describes a set of methods
 * for working with objects of {@link Content} class or subclass.
 *
 * @param <T> entity type, extends {@link Content}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Content
 * @see DataServiceImpl
 * @see ContentService
 * @see com.salimov.yurii.service.data.impl.ArticleServiceImpl
 * @see com.salimov.yurii.service.data.impl.CategoryServiceImpl
 * @see com.salimov.yurii.service.data.impl.CompanyServiceImpl
 * @see ContentDao
 */
public abstract class ContentServiceImpl<T extends Content>
        extends DataServiceImpl<T> implements ContentService<T> {

    /**
     * The object provides a set of standard JPA methods
     * for working {@link Content} objects with the database.
     *
     * @see ContentDao
     * @see Content
     */
    private final ContentDao<T> dao;

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link File}.
     *
     * @see FileService
     * @see File
     */
    private final FileService fileService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao         a implementation of the {@link ContentDao} interface.
     * @param fileService a implementation of the {@link FileService} interface.
     * @see ContentDao
     */
    ContentServiceImpl(
            final ContentDao<T> dao,
            final FileService fileService
    ) {
        super(dao);
        this.dao = dao;
        this.fileService = fileService;
    }

    /**
     * Initializes, updates and returns content with parameter url.
     *
     * @param url     a url of the content to update.
     * @param content a content to update.
     * @return The updating content with parameter id.
     * @see Content
     * @see File
     */
    @Override
    @Transactional
    public Content update(
            final String url,
            final T content
    ) {
        final T contentToUpdate = getByUrl(url, false);
        final File newLogo = content.getLogo();
        final File oldLogo = contentToUpdate.getLogo();
        if (!newLogo.equals(oldLogo) && isNotBlank(newLogo.getUrl())) {
            this.fileService.deleteFile(oldLogo.getUrl());
        }
        contentToUpdate.initialize(content);
        return update(contentToUpdate);
    }

    /**
     * Returns object of {@link Content} or subclasses with the parameter title.
     *
     * @param title   a title of the content to return.
     * @param isValid is get valid content or not.
     * @return The content with parameter title or {@code null}.
     * @throws IllegalArgumentException Throw exception when object  parameter title is blank.
     * @throws NullPointerException     Throw exception when object with parameter title is not exist.
     * @see Content
     */
    @Override
    @Transactional(readOnly = true)
    public T getByTitle(
            final String title,
            final boolean isValid
    ) throws IllegalArgumentException, NullPointerException {
        if (isBlank(title)) {
            throw new IllegalArgumentException(getClassSimpleName() + " title is blank!");
        }
        final T content = this.dao.getByTitle(title);
        if ((content == null) || (isValid && !content.isValidated())) {
            throw new NullPointerException(
                    "Can`t find object of " + getClassSimpleName() + " by title \"" + title + "\"!"
            );
        }
        return content;
    }

    /**
     * Returns object of {@link Content} or subclasses with the parameter url.
     *
     * @param url     a url of the content to return.
     * @param isValid is get valid content or not.
     * @return the content with parameter url or {@code null}.
     * @throws IllegalArgumentException Throw exception when object parameter url is blank.
     * @throws NullPointerException     Throw exception when object with parameter url is not exist.
     * @see Content
     */
    @Override
    @Transactional(readOnly = true)
    public T getByUrl(
            final String url,
            final boolean isValid
    ) throws IllegalArgumentException, NullPointerException {
        if (isBlank(url)) {
            throw new IllegalArgumentException(getClassSimpleName() + " url is blank!");
        }
        final T content = this.dao.getByUrl(url);
        if ((content == null) || (isValid && !content.isValidated())) {
            throw new NullPointerException(
                    "Can`t find object of " + getClassSimpleName() + " by URL \"" + url + "\"!"
            );
        }
        return content;
    }

    /**
     * Removes object of {@link Content} or subclasses with the parameter title.
     * Removes content if title is not blank.
     *
     * @param title a title of the content to remove.
     * @see Content
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotBlank(title)) {
            this.dao.removeByTitle(title);
        }
    }

    /**
     * Removes object of {@link Content} or subclasses with the parameter url.
     *
     * @param url a url of the content to remove.
     * @see Content
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotBlank(url)) {
            this.dao.removeByUrl(url);
        }
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by title.
     *
     * @param contents the contents to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of contents.
     * @see Content
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sortByTitle(
            final Collection<T> contents,
            final boolean revers
    ) {
        return sort(contents, new ContentComparator.ByTitle<>(), revers);
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by url.
     *
     * @param contents the contents to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of contents.
     * @see Content
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sortByUrl(
            final Collection<T> contents,
            final boolean revers
    ) {
        return sort(contents, new ContentComparator.ByUrl<>(), revers);
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by title.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of contents.
     * @see Content
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAndSortByTitle(final boolean revers) {
        return sortByTitle(getAll(), revers);
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by url.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of contents.
     * @see Content
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAndSortByUrl(final boolean revers) {
        return sortByUrl(getAll(), revers);
    }

    /**
     * Validates input object of {@link Content} class or subclasses.
     *
     * @param content   the contents to valid.
     * @param exist     is validate input object by exists.
     * @param duplicate is validate input object by duplicate.
     * @return Returns {@code true} if object is valid, otherwise returns {@code false}.
     * @see Content
     */
    @Override
    protected boolean validated(
            final T content,
            final boolean exist,
            final boolean duplicate
    ) {
        if (content == null) {
            return false;
        }
        if (exist && !exists(content)) {
            return false;
        }
        if (duplicate) {
            if ((this.dao.getByTitle(content.getTitle()) != null)
                    || (this.dao.getByUrl(content.getUrl()) != null)) {
                return false;
            }
        }
        return true;
    }
}
