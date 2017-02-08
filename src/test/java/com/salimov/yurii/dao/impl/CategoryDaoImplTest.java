package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.CategoryDao;
import com.salimov.yurii.entity.Category;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.ANY_STRING;
import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static com.salimov.yurii.mocks.MockConstants.URL;
import static com.salimov.yurii.mocks.enity.MockEntity.getCategories;
import static com.salimov.yurii.mocks.enity.MockEntity.getCategory;
import static com.salimov.yurii.mocks.repository.MockRepository.getCategoryRepository;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class CategoryDaoImplTest
        extends DataDAOImplTest<Category, Long> {

    private CategoryDao dao;

    @Before
    public void beforeTest() {
        this.dao = new CategoryDaoImpl(
                getCategoryRepository()
        );
    }

    @Test
    public void whenGetByNullTitleThenReturnsNull() {
        assertNull(this.dao.getByTitle(null));
    }

    @Test
    public void whenGetByInvalidTitleThenReturnsNull() {
        assertNull(this.dao.getByTitle(ANY_STRING));
    }

    @Test
    public void whenGetByValidTitleThenReturnsSomeCategory() {
        assertNotNull(this.dao.getByTitle(TITLE));
    }

    @Test
    public void whenGetByNullUrlThenReturnsNull() {
        assertNull(this.dao.getByUrl(ANY_STRING));
    }

    @Test
    public void whenGetByInvalidUrlThenReturnsNull() {
        assertNull(this.dao.getByUrl(null));
    }

    @Test
    public void whenGetByValidUrlThenReturnsSomeCategory() {
        assertNotNull(this.dao.getByUrl(URL));
    }

    @Test
    public void whenRemoveByTitleThenDoIt() {
        this.dao.removeByTitle(TITLE);
    }

    @Test
    public void whenRemoveByUrlThenDoIt() {
        this.dao.removeByUrl(URL);
    }

    @Ignore
    @Override
    protected CategoryDao getDao() {
        return this.dao;
    }

    @Ignore
    @Override
    protected Category getObject() {
        return getCategory();
    }

    @Ignore
    @Override
    protected Collection<Category> getObjects() {
        return getCategories();
    }
}
