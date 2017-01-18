package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Response;
import com.salimov.yurii.service.data.interfaces.ResponseService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponse;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponses;
import static com.salimov.yurii.mocks.service.data.MockServices.getResponseService;
import static org.junit.Assert.assertNotNull;

public class MockResponseServiceTest extends MockDataServiceTest<Response> {

    private ResponseService service;

    @Before
    public void initResponseService() {
        this.service = getResponseService();
    }

    @Test
    public void whenInitAndAddResponseThenRetutnThisResponse() {
        assertNotNull(
                this.service.initAndAdd(NAME, TEXT)
        );
    }

    @Test
    public void whenInitAndUpdateResponseThenReturnThisResponse() {
        assertNotNull(
                this.service.initAndUpdate(ID, NAME, TEXT)
        );
    }

    @Test
    public void whenSortRensonsesByDateThenReturnSortResponses() {
        assertNotNull(
                this.service.sortByDate(
                        getResponses(), true
                )
        );
        assertNotNull(
                this.service.sortByDate(
                        getResponses(), false
                )
        );
    }

    @Test
    public void whenGetAndSortRensonsesByDateThenReturnSortResponses() {
        assertNotNull(
                this.service.getAndSortByDate(true)
        );
        assertNotNull(
                this.service.getAndSortByDate(false)
        );
    }

    @Test
    public void whenFilterResponsesByDateThenReturnFilterResponses() {
        final Response response = getResponse();
        assertNotNull(
                this.service.filterByDate(
                        getResponses(),
                        DATE, DATE
                )
        );
    }

    @Test
    public void whenGetAndFilterResponsesByDateThenReturnFilterResponses() {
        assertNotNull(
                this.service.getAndFilterByDate(
                        DATE, DATE
                )
        );
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
