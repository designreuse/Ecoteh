package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.service.data.ResponseService;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.DATE;
import static ua.com.ecoteh.mocks.enity.MockModels.getResponse;
import static ua.com.ecoteh.mocks.enity.MockModels.getResponses;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockResponseService extends MockDataService<Response> {

    private final ResponseService service;
    private final Response response;
    private final Collection<Response> responses;

    MockResponseService() {
        this.service = mock(ResponseService.class);
        this.response = getResponse();
        this.responses = getResponses();
    }

    @Override
    ResponseService create() {
        super.create();
        initSortByDate();
        initGetAndSortByDate();
        initFilterByDate();
        initGetAndFilterByDate();
        initFilterByValid();
        return this.service;
    }

    @Override
    ResponseService getService() {
        return this.service;
    }

    @Override
    Response getModel() {
        return this.response;
    }

    @Override
    Collection<Response> getModels() {
        return this.responses;
    }

    private void initSortByDate() {
        when(this.service.sortByDate(this.responses, true)).thenReturn(new ArrayList<>(this.responses));
        when(this.service.sortByDate(this.responses, false)).thenReturn(new ArrayList<>(this.responses));
        when(this.service.sortByDate(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(this.service.sortByDate(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(this.service.sortByDate(null, true)).thenReturn(new ArrayList<>());
        when(this.service.sortByDate(null, false)).thenReturn(new ArrayList<>());
    }

    private void initGetAndSortByDate() {
        when(this.service.getAndSortByDate(true)).thenReturn(new ArrayList<>(this.responses));
        when(this.service.getAndSortByDate(false)).thenReturn(new ArrayList<>(this.responses));
    }

    private void initFilterByDate() {
        when(this.service.filterByDate(this.responses, DATE, DATE)).thenReturn(new ArrayList<>(this.responses));
        when(this.service.filterByDate(new ArrayList<>(), DATE, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, DATE, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(this.responses, null, DATE)).thenReturn(new ArrayList<>(this.responses));
        when(this.service.filterByDate(new ArrayList<>(), null, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, null, DATE)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(this.responses, DATE, null)).thenReturn(new ArrayList<>(this.responses));
        when(this.service.filterByDate(new ArrayList<>(), DATE, null)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, DATE, null)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(this.responses, null, null)).thenReturn(new ArrayList<>(this.responses));
        when(this.service.filterByDate(new ArrayList<>(), null, null)).thenReturn(new ArrayList<>());
        when(this.service.filterByDate(null, null, null)).thenReturn(new ArrayList<>());
    }

    private void initGetAndFilterByDate() {
        when(this.service.getAndFilterByDate(DATE, DATE)).thenReturn(new ArrayList<>(this.responses));
        when(this.service.getAndFilterByDate(null, DATE)).thenReturn(new ArrayList<>());
        when(this.service.getAndFilterByDate(DATE, null)).thenReturn(new ArrayList<>());
        when(this.service.getAndFilterByDate(null, null)).thenReturn(new ArrayList<>());
    }

    private void initFilterByValid() {
        when(this.service.filterByValid(this.responses)).thenReturn(new ArrayList<>(this.responses));
        when(this.service.filterByValid(new ArrayList<>())).thenReturn(new ArrayList<>());
        when(this.service.filterByValid(null)).thenReturn(new ArrayList<>());
    }
}
