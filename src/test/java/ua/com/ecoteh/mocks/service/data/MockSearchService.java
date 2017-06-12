package ua.com.ecoteh.mocks.service.data;

import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.search.SearchService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.KEYWORDS;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockSearchService {

    private final SearchService searchService;

    MockSearchService() {
        searchService = mock(SearchService.class);
    }

    SearchService create() {
        initSearch();
        return this.searchService;
    }

    private void initSearch() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("all", true);
        modelAndView.setViewName("search/search");
        when(this.searchService.search(KEYWORDS, "all", false)).thenReturn(modelAndView);
        when(this.searchService.search(KEYWORDS, "all", true)).thenReturn(modelAndView);
    }
}
