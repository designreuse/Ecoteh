package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.response.ResponseEntity;
import ua.com.ecoteh.service.data.ResponseService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static ua.com.ecoteh.mocks.service.data.MockServices.getResponseService;
import static org.junit.Assert.assertNotNull;

public final class MockResponseEntityServiceTest extends MockDataServiceTest<ResponseEntity> {

    private ResponseService service;

    @Before
    public void initResponseService() {
        this.service = getResponseService();
    }

    @Test
    public void whenAddResponseThenRetutnThisResponse() {
        assertNotNull(this.service.add(MockEntity.getResponseEntity()));
    }

    @Test
    public void whenUpdateResponseThenReturnThisResponse() {
        assertNotNull(this.service.update(MockConstants.ID, MockEntity.getResponseEntity()));
    }

    @Test
    public void whenSortRensonsesByDateThenReturnSortResponses() {
        assertNotNull(this.service.sortByDate(MockEntity.getResponseEntities(), true));
        assertNotNull(this.service.sortByDate(MockEntity.getResponseEntities(), false));
    }

    @Test
    public void whenGetAndSortRensonsesByDateThenReturnSortResponses() {
        assertNotNull(this.service.getAndSortByDate(true));
        assertNotNull(this.service.getAndSortByDate(false));
    }

    @Test
    public void whenFilterResponsesByDateThenReturnFilterResponses() {
        assertNotNull(this.service.filterByDate(MockEntity.getResponseEntities(), MockConstants.DATE, MockConstants.DATE));
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
    protected ResponseEntity getObject() {
        return MockEntity.getResponseEntity();
    }

    @Ignore
    @Override
    protected Collection<ResponseEntity> getObjects() {
        return MockEntity.getResponseEntities();
    }
}
