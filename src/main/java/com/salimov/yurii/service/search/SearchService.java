package com.salimov.yurii.service.search;

import org.springframework.web.servlet.ModelAndView;

/**
 * The interface of the service layer, describes a set of methods
 * for search for content on the site.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface SearchService {

    /**
     * Returns the page with the found content.
     *
     * @param keywords  a keywords for content search.
     * @param content   a objects list for content search.
     * @param howSearch a search mode.
     * @return The ready object of class ModelAndView.
     */
    ModelAndView search(
            final String keywords,
            final String content,
            final boolean howSearch
    );
}
