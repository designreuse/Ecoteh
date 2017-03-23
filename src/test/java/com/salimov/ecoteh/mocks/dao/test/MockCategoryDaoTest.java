package com.salimov.ecoteh.mocks.dao.test;

import com.salimov.ecoteh.dao.interfaces.CategoryDao;
import com.salimov.ecoteh.entity.Category;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.dao.MockDao.getCategoryDao;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCategories;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCategory;

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
