package ua.com.ecoteh.entity.file;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelConverterTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FileConverterTest extends ModelConverterTest<File, FileEntity> {

    private static FileConverter converter;
    private static File file;

    @BeforeClass
    public static void beforeClass() {
        file = getFile();
        converter = new FileConverter(file);
    }

    @Override
    protected void checkEntity(final FileEntity entity) {
        super.checkEntity(entity);
        assertEquals(entity.getTitle(), file.getTitle());
        assertEquals(entity.getUrl(), file.getUrl());
        assertEquals(entity.getType(), file.getType());
    }

    @Override
    protected FileConverter getConverter() {
        return converter;
    }

    @Override
    protected File getModel() {
        return file;
    }
}
