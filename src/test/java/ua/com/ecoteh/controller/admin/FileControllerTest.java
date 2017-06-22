package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.service.data.FileService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FileControllerTest {

    private static FileController controller;

    @BeforeClass
    public static void beforeClass() {
        final MainMVFabric fabric = getCacheMVFabric();
        final FileService fileService = getFileService();
        controller = new FileController(fabric, fileService);
    }

    @Test
    public void whenGetAllFilePageThenReturnModelAndView() {
        final String viewName = "file/all";
        final String[] keys = { "main_company", "categories", "favicon", "files", "revers" };
        final ModelAndView modelAndView = controller.getAllFilePage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenGetNewFilePageThenReturnModelAndView() {
        final String viewName = "file/add";
        final String[] keys = { "main_company", "categories", "favicon" };
        final ModelAndView modelAndView = controller.getNewFilePage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenAddFileThenReturnAllFilesPage() {
        final File file = getFile();
        final String actualRedirect = controller.addFile(
                file.getTitle(), file.getUrl(),
                file.getMultipartFile()
        );
        final String expectedRedirect = "redirect:/admin/file/all";
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddFileByGetMethodThenThrowIllegalMappingException() {
        controller.addFile();
    }

    @Test
    public void whenEditFilePageThenReturnModelAndView() {
        final String viewName = "file/edit";
        final String[] keys = { "main_company", "categories", "favicon", "file" };
        final ModelAndView modelAndView = controller.editFilePage(ID);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenUpdateFileThenReturnAllFilesPage() {
        final File file = getFile();
        final String actualRedirect = controller.updateFile(
                file.getId(), file.getTitle(), file.getUrl(),
                file.getMultipartFile()
        );
        final String expectedRedirect = "redirect:/admin/file/all";
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateFileByGetMethodThenThrowIllegalMappingException() {
        controller.updateFile();
    }

    @Test
    public void whenDeleteFileByIdThenReturnRedirect() {
        final String expectedRedirect = "redirect:/admin/file/all";
        final String actualRedirect = controller.deleteFileById(ID);
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test
    public void whenDeleteAllFilesThenReturnRedirect() {
        final String expectedRedirect = "redirect:/admin/file/all";
        final String actualRedirect = controller.deleteAllFiles();
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test
    public void whenGetAllFileSortByTitleThenReturnSomeModelAndView() {
        final String viewName = "file/all";
        final String[] keys = { "main_company", "categories", "favicon", "files", "revers" };
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("revers")).thenReturn("true");
        final ModelAndView modelAndView = controller.getAllFileSortByTitle(request);
        checkModelAndView(modelAndView, viewName, keys);
    }
}
