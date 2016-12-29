package com.salimov.yurii.dao.impl;

import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.CategoryDao;
import com.salimov.yurii.dao.interfaces.SectionDao;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.Section;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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

    @Autowired
    private SectionDao sectionDAO;

    @Test
    public void whenGetBySectionIdThenReturnSomeCategories() {
        final Category category = getObject();
        final Section section = this.sectionDAO.add(
                getSection()
        );
        category.setSection(section);

        final List<Category> categories1 = new ArrayList<>();
        categories1.add(category);
        this.categoryDao.addAll(categories1);

        final List<Category> categories2 = this.categoryDao.getBySectionId(
                section.getId()
        );
        assertNotNull(categories2);
        assertFalse(
                categories2.isEmpty()
        );
    }

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
