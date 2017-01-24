package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.CategoryDao;
import com.salimov.yurii.entity.Category;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.yurii.mocks.dao.MockDao.getCategoryDao;
import static com.salimov.yurii.mocks.enity.MockEntity.getCategories;
import static com.salimov.yurii.mocks.enity.MockEntity.getCategory;

public class MockCategoryDaoTest extends MockContentDAOTest<Category> {

    private CategoryDao dao;

    @Before
    public void initCategoryDao() {
        this.dao = getCategoryDao();
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
