package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.CategoryDao;
import com.salimov.yurii.entity.Category;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static com.salimov.yurii.mocks.MockConstants.ID;
import static com.salimov.yurii.mocks.MockConstants.UNKNOWN_ID;
import static com.salimov.yurii.mocks.enity.MockEntity.getCategories;
import static com.salimov.yurii.mocks.enity.MockEntity.getCategory;
import static com.salimov.yurii.mocks.dao.MockDAO.getCategoryDao;

public class MockCategoryDaoTest extends MockContentDAOTest<Category> {

    private CategoryDao dao;

    @Before
    public void initCategoryDao() {
        this.dao = getCategoryDao();
    }

    @Test
    public void whenGetByInvalidSectionIdThenReturnNull() {
        Collection<Category> category = this.dao.getBySectionId(null);
        assertNull(category);

        category = this.dao.getBySectionId(UNKNOWN_ID);
        assertNull(category);
    }

    @Test
    public void whenGetByValidSectionIdThenReturnSomeCategories() {
        Collection<Category> category = this.dao.getBySectionId(ID);
        assertNotNull(category);
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
