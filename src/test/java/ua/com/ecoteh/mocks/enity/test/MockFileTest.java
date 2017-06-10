package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.mocks.enity.MockModels;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public final class MockFileTest extends MockModelTest<File> {

    private static File file;
    private static Collection<File> files;

    @BeforeClass
    public static void beforeClass() {
        file = MockModels.getFile();
        files = MockModels.getFiles();
    }

    @Test
    public void whenGetTitleThenReturnNotEmpty() {
        assertFalse(file.getTitle().isEmpty());
    }

    @Test
    public void whenGetUrlThenReturnNotEmpty() {
        assertFalse(file.getUrl().isEmpty());
    }

    @Test
    public void whenGetTypeThenReturnNotNull() {
        assertNotNull(file.getType());
    }

    @Ignore
    @Override
    protected File getObject() {
        return file;
    }

    @Ignore
    @Override
    protected Collection<File> getObjects() {
        return files;
    }
}
