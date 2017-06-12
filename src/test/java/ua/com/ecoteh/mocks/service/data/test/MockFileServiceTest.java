package ua.com.ecoteh.mocks.service.data.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileType;
import ua.com.ecoteh.service.data.FileService;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.TITLE;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;
import static ua.com.ecoteh.mocks.enity.MockModels.getFiles;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

public final class MockFileServiceTest extends MockDataServiceTest<File> {

    private static FileService service;
    private static File file;
    private static Collection<File> files;

    @BeforeClass
    public static void beforeClass() {
        service = getFileService();
        file = getFile();
        files = getFiles();
    }

    @Test
    public void whenGetByTitleThenReturnNotNull() {
        final File file = service.getByTitle(TITLE);
        assertNotNull(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullTitleThenThrowIllegalArgumentException() {
        service.getByTitle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyTitleThenThrowIllegalArgumentException() {
        service.getByTitle("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownTitleThenThrowNullPointerException() {
        service.getByTitle(ANY_STRING);
    }

    @Test
    public void whenGetByUrlThenReturnNotNull() {
        final File file = service.getByUrl(URL);
        assertNotNull(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullUrlThenThrowIllegalArgumentException() {
        service.getByUrl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyUrlThenThrowIllegalArgumentException() {
        service.getByUrl("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownUrlThenThrowNullPointerException() {
        service.getByUrl(ANY_STRING);
    }

    @Test
    public void whenGetByTypeThenReturnNotEmptyCollection() {
        Collection<File> files;
        for (FileType type : FileType.values()) {
            files = service.getByType(type);
            assertFalse(files.isEmpty());
        }
    }

    @Test
    public void whenGetByTypeThenReturnCollectionWithNotNullObjects() {
        Collection<File> files;
        for (FileType type : FileType.values()) {
            files = service.getByType(type);
            files.forEach(Assert::assertNotNull);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullTypeThenThrownIllegalArgumentException() {
        service.getByType(null);
    }

    @Test
    public void whenSortByTitleThenReturnNotEmptyCollection() {
        Collection<File> sortedFiles = service.sortByTitle(files, true);
        assertFalse(sortedFiles.isEmpty());
        sortedFiles = service.sortByTitle(files, false);
        assertFalse(sortedFiles.isEmpty());
    }

    @Test
    public void whenSortByTitleThenReturnCollectionWithNotNullObjects() {
        Collection<File> sortedFiles = service.sortByTitle(files, true);
        sortedFiles.forEach(Assert::assertNotNull);
        sortedFiles = service.sortByTitle(files, false);
        sortedFiles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortEmptyCollectionByTitleThenReturnEmptyCollection() {
        Collection<File> sortedFiles = service.sortByTitle(new ArrayList<>(), true);
        assertTrue(sortedFiles.isEmpty());
        sortedFiles = service.sortByTitle(new ArrayList<>(), false);
        assertTrue(sortedFiles.isEmpty());
    }

    @Test
    public void whenSortNullCollectionByTitleThenReturnEmptyCollection() {
        Collection<File> sortedFiles = service.sortByTitle(null, true);
        assertTrue(sortedFiles.isEmpty());
        sortedFiles = service.sortByTitle(null, false);
        assertTrue(sortedFiles.isEmpty());
    }

    @Ignore
    @Override
    protected FileService getService() {
        return service;
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
