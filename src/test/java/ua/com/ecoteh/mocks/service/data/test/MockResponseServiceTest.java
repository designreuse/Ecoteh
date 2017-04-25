package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.Response;
import ua.com.ecoteh.service.data.ResponseService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static ua.com.ecoteh.mocks.service.data.MockServices.getResponseService;
import static org.junit.Assert.assertNotNull;

public final class MockResponseServiceTest extends MockDataServiceTest<Response> {

    private ResponseService service;

    @Before
    public void initResponseService() {
        this.service = getResponseService();
    }

    @Test
    public void whenAddResponseThenRetutnThisResponse() {
        assertNotNull(this.service.add(MockEntity.getResponse()));
    }

    @Test
    public void whenUpdateResponseThenReturnThisResponse() {
        assertNotNull(this.service.update(MockConstants.ID, MockEntity.getResponse()));
    }

    @Test
    public void whenSortRensonsesByDateThenReturnSortResponses() {
        assertNotNull(this.service.sortByDate(MockEntity.getResponses(), true));
        assertNotNull(this.service.sortByDate(MockEntity.getResponses(), false));
    }

    @Test
    public void whenGetAndSortRensonsesByDateThenReturnSortResponses() {
        assertNotNull(this.service.getAndSortByDate(true));
        assertNotNull(this.service.getAndSortByDate(false));
    }

    @Test
    public void whenFilterResponsesByDateThenReturnFilterResponses() {
        assertNotNull(this.service.filterByDate(MockEntity.getResponses(), MockConstants.DATE, MockConstants.DATE));
    }

    @Test
    public void whenGetAndFilterResponsesByDateThenReturnFilterResponses() {
        assertNotNull(this.service.getAndFilterByDate(MockConstants.DATE, MockConstants.DATE));
    }

    @Ignore
    @Override
    protected ResponseService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected Response getObject() {
        return MockEntity.getResponse();
    }

    @Ignore
    @Override
    protected Collection<Response> getObjects() {
        return MockEntity.getResponses();
    }
}
