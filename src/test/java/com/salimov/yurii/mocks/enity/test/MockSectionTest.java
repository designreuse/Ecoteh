package com.salimov.yurii.mocks.enity.test;

import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.Section;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getSection;
import static com.salimov.yurii.mocks.enity.MockEntity.getSections;

public class MockSectionTest extends MockModelTest<Section> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        Section section = getSection();
        assertNotNull(section.getId());
        assertNotNull(section.getTitle());
        assertNotNull(section.getUrl());
        assertNotNull(section.getDescription());
        assertNotNull(section.getKeywords());
        assertNull(section.getPhoto());
    }

    @Ignore
    @Override
    protected Section getObject() {
        return getSection();
    }

    @Ignore
    @Override
    protected Collection<Section> getObjects() {
        return getSections();
    }

    @Ignore
    @Override
    protected Collection<Section> getObjects(int size) {
        return getSections(size);
    }
}
