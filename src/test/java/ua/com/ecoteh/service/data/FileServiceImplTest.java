package ua.com.ecoteh.service.data;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileBuilder;
import ua.com.ecoteh.entity.file.FileType;
import ua.com.ecoteh.repository.FileRepository;
import ua.com.ecoteh.service.data.comparator.FileComparator;
import ua.com.ecoteh.config.properties.ContentProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;
import static ua.com.ecoteh.mocks.enity.MockModels.getFiles;
import static ua.com.ecoteh.mocks.properties.MockContentProperties.getContentProperties;
import static ua.com.ecoteh.mocks.repository.MockRepository.getFileRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FileServiceImplTest extends DataServiceImplTest<File> {

    private static FileService service;
    private static File file;
    private static Collection<File> files;

    @BeforeClass
    public static void beforeClass() {
        final FileRepository repository = getFileRepository();
        final ContentProperties properties = getContentProperties();
        service = new FileServiceImpl(repository, properties);
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
    public void whenRemoveByTitleThenDoIt() {
        service.removeByTitle(TITLE);
    }

    @Test
    public void whenRemoveByNullTitleThenDoNothing() {
        service.removeByTitle(null);
    }

    @Test
    public void whenRemoveByEmptyTitleThenDoNothing() {
        service.removeByTitle("");
    }

    @Test
    public void whenRemoveByUrlThenDoIt() {
        service.removeByUrl(URL);
    }

    @Test
    public void whenRemoveByNullUrlThenDoNothing() {
        service.removeByUrl(null);
    }

    @Test
    public void whenRemoveByEmptyUrlThenDoNothing() {
        service.removeByUrl("");
    }

    @Test
    public void whenRemoveModelThenDoIt() {
        service.remove(file);
    }

    @Test
    public void whenRemoveNullModelThenDoNothing() {
        final File file = null;
        service.remove(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenRemoveStaticFileThenThrownIllegalArgumentException() {
        final FileBuilder builder = File.getBuilder();
        builder.addType(FileType.STATIC);
        final File file = builder.build();
        service.remove(file);
    }

    @Test
    public void whenRemoveModelsThenDoIt() {
        service.remove(files);
    }

    @Test
    public void whenRemoveNullModelsThenDoNothing() {
        final Collection<File> files = null;
        service.remove(files);
    }

    @Test
    public void whenRemoveEmptyModelsThenDoNothing() {
        final Collection<File> files = new ArrayList<>();
        service.remove(files);
    }

    @Test
    public void whenRemoveAllThenDoIt() {
        service.removeAll();
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
    public void whenSortNullCollectionByTitleThenReturnEmptyCollection() {
        final Collection<File> files = null;
        Collection<File> sortedFiles = service.sortByTitle(files, true);
        assertTrue(sortedFiles.isEmpty());
        sortedFiles = service.sortByTitle(files, false);
        assertTrue(sortedFiles.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionByTitleThenReturnEmptyCollection() {
        final Collection<File> files = new ArrayList<>();
        Collection<File> sortedFiles = service.sortByTitle(files, true);
        assertTrue(sortedFiles.isEmpty());
        sortedFiles = service.sortByTitle(files, false);
        assertTrue(sortedFiles.isEmpty());
    }

    @Test
    public void whenGetByTypeThenReturnEmptyCollection() {
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
    public void whenGetByNullTypeThenThrowIllegalArgumentException() {
        service.getByType(null);
    }

    @Test
    public void whenGetLastByTypeThenReturnNotNull() {
        File file;
        for (FileType type : FileType.values()) {
            file = service.getLastByType(type);
            assertNotNull(file);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetLastByNullTypeThenThrowIllegalArgumentException() {
        service.getLastByType(null);
    }

    @Test(expected = NullPointerException.class)
    public void whenAddUnknownModelThenThrowNullPointerException() {
        final File unknownFile = getUnknownModel();
        service.add(unknownFile);
    }

    @Test(expected = NullPointerException.class)
    public void whenUpdateUnknownModelThenThrowNullPointerException() {
        final File unknownFile = getUnknownModel();
        service.update(unknownFile);
    }

    @Override
    protected FileService getService() {
        return service;
    }

    @Override
    protected File getModel() {
        return file;
    }

    @Override
    protected Collection<File> getModels() {
        return files;
    }

    @Override
    protected File getUnknownModel() {
        final FileBuilder builder = File.getBuilder();
        builder.addId(UNKNOWN_ID).addTitle(ANY_STRING).addUrl(ANY_STRING);
        return builder.build();
    }

    @Override
    protected Comparator<File> getComparator() {
        return new FileComparator.ByTitle();
    }

    @Test
    public void whenAddModelThenReturnNotNull() {
    }
}
