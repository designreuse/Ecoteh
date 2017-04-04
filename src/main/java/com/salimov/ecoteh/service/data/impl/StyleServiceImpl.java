package com.salimov.ecoteh.service.data.impl;

import com.salimov.ecoteh.service.data.interfaces.StyleService;
import com.salimov.ecoteh.util.compressor.CssCompressor;
import com.salimov.ecoteh.util.loader.FileContentsLoader;
import com.salimov.ecoteh.util.properties.ContentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, implements a set of methods
 * for working with CSS styles.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Service
@ComponentScan(basePackages = "com.salimov.ecoteh.util.properties")
public final class StyleServiceImpl implements StyleService {

    /**
     * The relative path to file with default CSS styles.
     */
    private final static String DEFAULT_STYLES_PATH = "css/style.default.css";

    /**
     * The relative path to file with CSS styles.
     */
    private final static String STYLES_PATH = "css/style.css";

    /**
     * The relative path to file with compressing CSS styles.
     */
    private final static String MIN_STYLES_PATH = "css/style.min.css";

    /**
     * The implementation of the interface describes
     * the methods for getting application properties.
     */
    private final ContentProperties properties;

    /**
     * Constructor.
     *
     * @param properties a implementation of the {@link ContentProperties} interface.
     */
    @Autowired
    public StyleServiceImpl(final ContentProperties properties) {
        this.properties = properties;
    }

    /**
     * Returns a CSS styles of the site.
     *
     * @return The CSS styles.
     */
    @Override
    public String get() {
        return new FileContentsLoader(
                getAbsolutePath(STYLES_PATH)
        ).read();
    }

    /**
     * Saves a new CSS styles of the site.
     *
     * @param styles a new CSS styles to save.
     */
    @Override
    public void save(final String styles) {
        if (isNotBlank(styles)) {
            new FileContentsLoader(
                    getAbsolutePath(STYLES_PATH),
                    styles
            ).write();
            new FileContentsLoader(
                    getAbsolutePath(MIN_STYLES_PATH),
                    new CssCompressor().compress(styles)
            ).write();
        }
    }

    /**
     * Rollbacks a CSS styles to default.
     */
    @Override
    public void rollback() {
        save(
                new FileContentsLoader(
                        getAbsolutePath(DEFAULT_STYLES_PATH)
                ).read()
        );
    }

    /**
     * Returns a absolute path to the file.
     *
     * @param path a relative path to file.
     * @return The absolute path
     */
    private String getAbsolutePath(final String path) {
        return this.properties.getResourcesAbsolutePath() + path;
    }
}
