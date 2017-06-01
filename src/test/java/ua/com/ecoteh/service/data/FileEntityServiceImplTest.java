package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import org.junit.Ignore;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.Collection;

import static ua.com.ecoteh.mocks.enity.MockEntity.getFileEntity;
import static ua.com.ecoteh.mocks.enity.MockEntity.getFileEntities;
import static ua.com.ecoteh.mocks.properties.MockContentProperties.getContentProperties;

public final class FileEntityServiceImplTest extends DataServiceImplTest<FileEntity> {

    private static FileService service;

    @BeforeClass
    public static void beforeTest() {
        service = new FileServiceImpl(
                MockRepository.getFileRepository(),
                getContentProperties()
        );
    }

    @Ignore
    @Override
    protected FileService getService() {
        return service;
    }

    @Ignore
    @Override
    protected FileEntity getObject() {
        return getFileEntity();
    }

    @Ignore
    @Override
    protected Collection<FileEntity> getObjects() {
        return getFileEntities();
    }

    @Ignore
    @Override
    protected FileEntity getInvalidObject() {
        return new FileEntity();
    }
}
