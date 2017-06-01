package ua.com.ecoteh.controller.seo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.file.FileType;
import ua.com.ecoteh.service.data.FileService;

/**
 * Favicon controller.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
public class FaviconController {

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link FileEntity} class.
     */
    private final FileService fileService;

    /**
     * Constructor.
     *
     * @param fileService the implementation of the {@link FileEntity} interface.
     */
    @Autowired
    public FaviconController(final FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Returns a favicon.ico.
     * Request mapping: /favicon.ico
     * Method: GET
     *
     * @return The favicon.ico URL.
     */
    @RequestMapping(
            value = "/favicon.ico",
            method = RequestMethod.GET
    )
    public String getFavicon() {
        final FileEntity favicon = this.fileService.getLastByType(FileType.FAVICON);
        return "forward:" + favicon.getUrl();
    }
}
