package ua.com.ecoteh.entity.file;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelEntityConverterTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FileEntityConverterTest extends ModelEntityConverterTest<FileEntity, File> {

    private static FileEntityConverter converter;
    private static FileEntity entity;

    @BeforeClass
    public static void beforeClass() {
        entity = getFileEntity();
        converter = new FileEntityConverter(entity);
    }

    @Override
    protected void checkEntity(final File file) {
        super.checkEntity(file);
        assertEquals(file.getTitle(), entity.getTitle());
        assertEquals(file.getUrl(), entity.getUrl());
        assertEquals(file.getType(), entity.getType());
    }

    @Override
    protected FileEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected FileEntity getEntity() {
        return entity;
    }
}
