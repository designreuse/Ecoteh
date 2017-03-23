package com.salimov.ecoteh.mocks.enity.test;

import org.junit.Ignore;
import org.junit.Test;
import com.salimov.ecoteh.entity.File;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.enity.MockEntity.getFile;
import static org.junit.Assert.assertNotNull;
import static com.salimov.ecoteh.mocks.enity.MockEntity.*;

public final class MockFileTest extends MockModelTest<File> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final File file = getFile();
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
        assertNotNull(file.getId());
    }

    @Ignore
    @Override
    protected File getObject() {
        return getFile();
    }

    @Ignore
    @Override
    protected Collection<File> getObjects() {
        return getFiles();
    }
}
