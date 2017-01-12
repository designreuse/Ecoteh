package com.salimov.yurii.dao.impl;

import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.CategoryDao;
import com.salimov.yurii.entity.Category;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@SuppressWarnings("SpringJavaAutowiringInspection")
public class CategoryDaoImplTest extends ContentDAOImplTest<Category> {

    @Autowired
    private CategoryDao categoryDao;

    @Ignore
    @Override
    protected CategoryDao getDao() {
        return this.categoryDao;
    }

    @Ignore
    @Override
    protected Category getObject() {
        final Category category = getCategory();
        category.setTitle(category.getTitle() + getRandomInt(10));
        category.setId(Long.MAX_VALUE);
        return category;
    }

    @Ignore
    @Override
    protected Collection<Category> getObjects() {
        return getCategories();
    }
}
