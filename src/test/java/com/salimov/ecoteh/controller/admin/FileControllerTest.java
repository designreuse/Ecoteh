package com.salimov.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockAdminController.getFileController;
import static org.junit.Assert.assertEquals;

public class FileControllerTest {

    private static FileController controller;

    @BeforeClass
    public static void setUp() {
        controller = getFileController();
    }

    @Test
    public void whenGetAllFilePageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getAllFilePage(),
                "admin/file/all",
                new String[]{"main_company", "categories", "files"}
        );
    }

    @Test
    public void whenGetNewFilePageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getNewFilePage(),
                "admin/file/add",
                new String[]{"main_company", "categories"}
        );
    }

    @Test
    public void whenAddFileByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.addFile(TITLE, FILE_TYPE, null);
        assertEquals(viewName, "redirect:/admin/file/all");
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddFileByGetMethodThenThrowsIllegalMappingException() {
        controller.addFile();
    }

    @Test
    public void whenEditFilePageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editFilePage(ID),
                "admin/file/edit",
                new String[]{"main_company", "categories", "file"}
        );
    }

    @Test
    public void whenUpdateFileByPostMethodThenReturnSomeModelAndView() {
        String viewName = controller.updateFile(ID, TITLE, FILE_TYPE, null);
        assertEquals(viewName, "redirect:/admin/file/all");
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateFileByGetMethodThenThrowsIllegalMappingException() {
        controller.updateFile();
    }

    @Test
    public void whenDeleteFileByIdThenReturnSomeModelAndView() {
        String viewName = controller.deleteFileById(ID);
        assertEquals(viewName, "redirect:/admin/file/all");
    }

    @Test
    public void whenDeleteAllFilesThenReturnSomeModelAndView() {
        String viewName = controller.deleteAllFiles();
        assertEquals(viewName, "redirect:/admin/file/all");
    }
}
