package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.SectionDao;
import com.salimov.yurii.entity.Section;
import com.salimov.yurii.mocks.dao.MockDAO;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getSections;

public class MockSectionDAOTest extends MockContentDAOTest<Section> {

    private SectionDao dao;

    @Before
    public void initSectionDao() {
        this.dao = MockDAO.getSectionDAO();
    }

    @Ignore
    @Override
    protected SectionDao getDao() {
        return this.dao;
    }

    @Ignore
    @Override
    protected Section getObject() {
        return MockEntity.getSection();
    }

    @Ignore
    @Override
    protected Collection<Section> getObjects() {
        return MockEntity.getSections();
    }
}
