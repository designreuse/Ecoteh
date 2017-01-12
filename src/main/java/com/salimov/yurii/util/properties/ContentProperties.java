package com.salimov.yurii.util.properties;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface ContentProperties {
    /**
     * @return
     */
    String getCatalinaHome();

    /**
     * @return
     */
    String getContentType();

    /**
     * @return
     */
    String getPrefix();

    /**
     * @return
     */
    String getSuffix();

    /**
     * @return
     */
    boolean isExposeContextBeansAsAttributes();

    /**
     * @return
     */
    String getResourcesUrl();

    /**
     * @return
     */
    String getResourcesLocation();

    /**
     * @return
     */
    String getRequestLogin();

    /**
     * @return
     */
    String getLoginViewName();

    /**
     * @return
     */
    String getProjectDirectory();

    /**
     * @return
     */
    String getProjectAbsolutePath();

    /**
     * @return
     */
    String getResourcesPath();
}
