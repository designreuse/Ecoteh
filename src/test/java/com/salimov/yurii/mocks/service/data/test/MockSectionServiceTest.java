package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Section;
import com.salimov.yurii.mocks.enity.MockEntity;
import com.salimov.yurii.mocks.service.data.MockServices;
import com.salimov.yurii.service.data.interfaces.SectionService;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

public class MockSectionServiceTest extends MockContentServiceTest<Section> {

    private SectionService service;

    @Before
    public void initSectionService() {
        this.service = MockServices.getSectionService();
    }

    /*@Test
    public void whenInitAndAddSectionThenReturnThisSection() {
        final Photo photo = MockEntity.getPhoto();
        Section section = this.service.initAndAdd(MockConstants.TITLE, MockConstants.DESCRIPTION, MockConstants.KEYWORDS, photo);
        assertNotNull(section);
    }*/

    /*@Test
    public void whenInitAndUpdateSectionThenReturnThisSection() {
        final Photo photo = MockEntity.getPhoto();
        Section section = this.service.initAndUpdate(MockConstants.ID, MockConstants.TITLE, MockConstants.DESCRIPTION, MockConstants.KEYWORDS, photo);
        assertNotNull(section);
    }*/

    @Ignore
    @Override
    protected SectionService getService() {
        return this.service;
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
