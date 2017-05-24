package ua.com.ecoteh.service.search;

import org.springframework.web.servlet.ModelAndView;

/**
 * The interface of the service layer, describes a set of methods
 * for search for content on the site.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
    ModelAndView search(String keywords, String content, boolean howSearch);
}
