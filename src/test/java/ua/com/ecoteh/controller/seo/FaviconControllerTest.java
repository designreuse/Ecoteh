package ua.com.ecoteh.controller.seo;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.service.data.FileService;

import static org.junit.Assert.assertFalse;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FaviconControllerTest {

    private static FaviconController controller;

    @BeforeClass
    public static void beforeClass() {
        final FileService fileService = getFileService();
        controller = new FaviconController(fileService);
    }

    @Test
    public void whenGetFaviconThenReturnNotNull() {
        final String faviconUrl = controller.getFavicon();
        assertFalse(faviconUrl.isEmpty());
    }
}