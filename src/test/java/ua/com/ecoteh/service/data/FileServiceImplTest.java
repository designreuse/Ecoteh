package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.File;
import org.junit.Before;
import org.junit.Ignore;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.Collection;

import static ua.com.ecoteh.mocks.enity.MockEntity.getFile;
import static ua.com.ecoteh.mocks.enity.MockEntity.getFiles;
import static ua.com.ecoteh.mocks.properties.MockContentProperties.getContentProperties;

public final class FileServiceImplTest extends DataServiceImplTest<File> {

    private FileService service;

    @Before
    public void beforeTest() {
        this.service = new FileServiceImpl(
                MockRepository.getFileRepository(),
                getContentProperties()
        );
    }

    @Ignore
    @Override
    protected FileService getService() {
        return this.service;
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

    @Ignore
    @Override
    protected File getInvalidObject() {
        return new File();
    }
}
