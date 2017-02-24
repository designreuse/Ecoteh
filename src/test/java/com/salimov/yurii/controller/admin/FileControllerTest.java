package com.salimov.yurii.controller.admin;

import com.salimov.yurii.enums.FileType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;

import static com.salimov.yurii.mocks.MockConstants.FILE_TYPE;
import static com.salimov.yurii.mocks.MockConstants.ID;
import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.yurii.mocks.controller.MockAdminController.getFileController;


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
                "admin/file/all_page",
                new String[]{"main_company", "categories", "files"}
        );
    }

    @Test
    public void whenGetNewFilePageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getNewFilePage(),
                "admin/file/new_page",
                new String[]{"main_company", "categories"}
        );
    }

    @Test
    public void whenAddFileByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.addFile(TITLE, FILE_TYPE, null, new ModelAndView()),
                "redirect:/admin/file/all",
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenAddFileByGetMethodThenThrowsIllegalMappingException() {
        controller.addFile();
    }

    @Test
    public void whenEditFilePageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.editFilePage(ID),
                "admin/file/edit_page",
                new String[]{"main_company", "categories", "file"}
        );
    }

    @Test
    public void whenUpdateFileByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.updateFile(ID, TITLE, FILE_TYPE, null, new ModelAndView()),
                "redirect:/admin/file/all",
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateFileByGetMethodThenThrowsIllegalMappingException() {
        controller.updateFile();
    }

    @Test
    public void whenDeleteFileByIdThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteFileById(ID, new ModelAndView()),
                "redirect:/admin/file/all",
                null
        );
    }

    @Test
    public void whenDeleteAllFilesThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteAllFiles(new ModelAndView()),
                "redirect:/admin/file/all",
                null
        );
    }
}
