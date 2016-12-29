package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Response;
import com.salimov.yurii.service.data.interfaces.ResponseService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponse;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponses;
import static com.salimov.yurii.mocks.service.data.MockServices.getResponseService;

public class MockResponseServiceTest extends MockDataServiceTest<Response> {

    private ResponseService service;

    @Before
    public void initResponseService() {
        this.service = getResponseService();
    }

    @Test
    public void whenInitAndAddResponseThenRetutnThisResponse() {
        Response response = this.service.initAndAdd(NAME, TEXT);
        assertNotNull(response);
    }

    @Test
    public void whenInitAndUpdateResponseThenReturnThisResponse() {
        Response response = this.service.initAndUpdate(ID, NAME, TEXT);
        assertNotNull(response);
    }

    @Test
    public void whenSortRensonsesByDateThenReturnSortResponses() {
        final Response response = getResponse();
        final Collection<Response> responses1 = new ArrayList<>();
        responses1.add(response);
        Collection<Response> responses2 = this.service.sortByDate(responses1, true);
        assertNotNull(responses2);
        responses2 = this.service.sortByDate(responses1, false);
        assertNotNull(responses2);
    }

    @Test
    public void whenGetAndSortRensonsesByDateThenReturnSortResponses() {
        Collection<Response> responses2 = this.service.getAndSortByDate(true);
        assertNotNull(responses2);
        responses2 = this.service.getAndSortByDate(false);
        assertNotNull(responses2);
    }

    @Test
    public void whenFilterResponsesByDateThenReturnFilterResponses() {
        final Response response = getResponse();
        final Collection<Response> responses1 = new ArrayList<>();
        responses1.add(response);
        Collection<Response> responses2 = this.service.filterByDate(responses1, DATE, DATE);
        assertNotNull(responses2);
    }

    @Test
    public void whenGetAndFilterResponsesByDateThenReturnFilterResponses() {
        Collection<Response> responses = this.service.getAndFilterByDate(DATE, DATE);
        assertNotNull(responses);
    }

    @Ignore
    @Override
    protected ResponseService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected Response getObject() {
        return getResponse();
    }

    @Ignore
    @Override
    protected Collection<Response> getObjects() {
        return getResponses();
    }
}
