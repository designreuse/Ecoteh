package ua.com.ecoteh.mocks.repository.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.file.FileType;
import ua.com.ecoteh.repository.FileRepository;

import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntity;
import static ua.com.ecoteh.mocks.repository.MockRepository.getFileRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockFileRepositoryTest extends MockDataRepositoryTest<FileEntity> {

    private static FileEntity file;
    private static Collection<FileEntity> files;
    private static FileRepository repository;

    @BeforeClass
    public static void beforeClass() {
        file = getFileEntity();
        files = getFileEntities();
        repository = getFileRepository();
    }

    @Test
    public void whenFindByTitleThenReturnNotNull() {
        final FileEntity entity = repository.findByTitle(TITLE);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownTitleThenReturnNull() {
        final FileEntity entity = repository.findByTitle(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullTitleThenReturnNull() {
        final FileEntity entity = repository.findByTitle(null);
        assertNull(entity);
    }

    @Test
    public void whenFindByUrlThenReturnNotNull() {
        final FileEntity entity = repository.findByUrl(URL);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownUrlThenReturnNull() {
        final FileEntity entity = repository.findByUrl(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullUrlThenReturnNull() {
        final FileEntity entity = repository.findByUrl(null);
        assertNull(entity);
    }

    @Test
    public void whenFindByTypeThenReturnNotEmptyCollection() {
        Collection<FileEntity> files;
        for (FileType type : FileType.values()) {
            files = repository.findAllByType(type);
            assertFalse(files.isEmpty());
        }
    }

    @Test
    public void whenFindByTypeThenReturnCollectionWithNotNullObjects() {
        Collection<FileEntity> entities;
        for (FileType type : FileType.values()) {
            entities = repository.findAllByType(type);
            entities.forEach(Assert::assertNotNull);
        }
    }

    @Test
    public void whenFindLastByTypeThenReturnNotNull() {
        FileEntity entity;
        for (FileType type : FileType.values()) {
            entity = repository.findLastByType(type);
            assertNotNull(entity);
        }
    }

    @Override
    protected FileRepository getRepository() {
        return repository;
    }

    @Override
    protected FileEntity getObject() {
        return file;
    }

    @Override
    protected Collection<FileEntity> getObjects() {
        return files;
    }
}
