package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.file.FileType;
import ua.com.ecoteh.repository.FileRepository;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntity;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockFileRepository extends MockDataRepository<FileEntity> {

    private final FileRepository repository;
    private final FileEntity entity;
    private final Collection<FileEntity> entities;

    MockFileRepository() {
        this.repository = mock(FileRepository.class);
        this.entity = getFileEntity();
        this.entities = getFileEntities();
    }

    @Override
    FileRepository create() {
        super.create();
        initSave();
        initFindByTitle();
        initFindByUrl();
        initFindByType();
        initFindLastByType();
        return this.repository;
    }

    @Override
    FileRepository getRepository() {
        return this.repository;
    }

    @Override
    FileEntity getEntity() {
        return this.entity;
    }

    @Override
    Collection<FileEntity> getEntities() {
        return this.entities;
    }

    private void initSave() {
        final File file = getFile();
        final FileEntity entity = file.convert();
        when(this.repository.save(entity)).thenReturn(entity);
    }

    private void initFindByTitle() {
        when(this.repository.findByTitle(TITLE)).thenReturn(this.entity);
        when(this.repository.findByTitle(ANY_STRING)).thenReturn(null);
        when(this.repository.findByTitle(null)).thenReturn(null);
    }

    private void initFindByUrl() {
        when(this.repository.findByUrl(URL)).thenReturn(this.entity);
        when(this.repository.findByUrl(ANY_STRING)).thenReturn(null);
        when(this.repository.findByUrl(null)).thenReturn(null);
    }

    private void initFindByType() {
        for (FileType type : FileType.values()) {
            when(this.repository.findAllByType(type)).thenReturn(this.entities);
        }
    }

    private void initFindLastByType() {
        for (FileType type : FileType.values()) {
            when(this.repository.findLastByType(type)).thenReturn(this.entity);
        }
    }
}
