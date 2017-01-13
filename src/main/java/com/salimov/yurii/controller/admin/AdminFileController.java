package com.salimov.yurii.controller.admin;

import com.salimov.yurii.service.data.interfaces.FileService;
import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.yurii.service.fabrica.interfaces.AdminMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.CacheMVFabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/admin/file")
@ComponentScan(basePackages = "com.salimov.yurii.service")
@SuppressWarnings("SpringMVCViewInspection")
public class AdminFileController {

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     *
     * @see CacheMVFabric
     */
    private final CacheMVFabric fabric;

    private final FileService fileService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AdminFileController(
            final AdminMVFabric fabric,
            final FileService fileService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.fileService = fileService;
    }

    @RequestMapping(
            value = {"", "/", "all"},
            method = RequestMethod.GET
    )
    public ModelAndView getAllFile() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("files", this.fileService.getAll(false));
        modelAndView.setViewName("admin/file/all_page");
        return modelAndView;
    }

    @RequestMapping(
            value = "/new",
            method = RequestMethod.GET
    )
    public ModelAndView getNewFilePage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("admin/file/new_page");
        return modelAndView;
    }

    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST
    )
    public ModelAndView addFile(
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "path") final String path,
            @RequestParam(value = "file") final MultipartFile multipartFile,
            final ModelAndView modelAndView
    ) {
        this.fileService.initAndAdd(title, path, multipartFile);
        modelAndView.setViewName("redirect:/admin/file/all");
        return modelAndView;
    }

    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView editFilePage(
            @PathVariable("id") final Long id
    ) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("file", this.fileService.get(id));
        modelAndView.setViewName("admin/file/edit_page");
        return modelAndView;
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public ModelAndView updateFile(
            @RequestParam(value = "id") final Long id,
            @RequestParam(value = "title") final String title,
            @RequestParam(value = "path") final String path,
            @RequestParam(value = "file") final MultipartFile multipartFile,
            final ModelAndView modelAndView
    ) {
        this.fileService.initAndUpdate(id, title, path, multipartFile);
        modelAndView.setViewName("redirect:/admin/file/all");
        return modelAndView;
    }

    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView deleteFileById(
            @PathVariable("id") final Long id,
            final ModelAndView modelAndView
    ) {
        this.fileService.remove(id);
        modelAndView.setViewName("redirect:/admin/file/all");
        return modelAndView;
    }

    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public ModelAndView deleteAllFiles(
            final ModelAndView modelAndView
    ) {
        this.fileService.removeAll();
        modelAndView.setViewName("redirect:/admin/file/all");
        return modelAndView;
    }
}
