package com.salimov.yurii.dao.impl;

import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.SectionDao;
import com.salimov.yurii.entity.Section;
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
public class SectionDAOImplTest extends ContentDAOImplTest<Section> {

    @Autowired
    private SectionDao sectionDAO;

    @Ignore
    @Override
    protected SectionDao getDao() {
        return this.sectionDAO;
    }

    @Ignore
    @Override
    protected Section getObject() {
        final Section section = getSection();
        section.setTitle(section.getTitle() + " " + getRandomInt(10));
        section.setId(Long.MAX_VALUE);
        return section;
    }

    @Ignore
    @Override
    protected Collection<Section> getObjects() {
        return getSections();
    }
}
