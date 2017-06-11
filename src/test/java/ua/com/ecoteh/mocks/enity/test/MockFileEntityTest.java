package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.file.FileEntity;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockFileEntityTest extends MockModelEntityTest<FileEntity> {

    private static FileEntity file;
    private static Collection<FileEntity> files;

    @BeforeClass
    public static void beforeClass() {
        file = getFileEntity();
        files = getFileEntities();
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
    protected FileEntity getObject() {
        return file;
    }

    @Ignore
    @Override
    protected Collection<FileEntity> getObjects() {
        return files;
    }
}
