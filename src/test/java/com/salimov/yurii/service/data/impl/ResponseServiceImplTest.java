package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.Response;
import com.salimov.yurii.service.data.interfaces.ResponseService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static com.salimov.yurii.mocks.MockConstants.ID;
import static com.salimov.yurii.mocks.MockConstants.NAME;
import static com.salimov.yurii.mocks.MockConstants.TEXT;
import static com.salimov.yurii.mocks.dao.MockDao.getResponseDao;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponse;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponses;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class ResponseServiceImplTest
        extends DataServiceImplTest<Response, Long> {

    private ResponseService service;

    @Before
    public void beforeTest() {
        this.service = new ResponseServiceImpl(getResponseDao());
    }

    @Test
    public void whenInitAndAddTheReturnSomeResponse() {
        assertNotNull(this.service.initAndAdd(NAME, TEXT));
    }

    @Test
    public void whenInitAndUpdateTheReturnSomeResponse() {
        assertNotNull(this.service.initAndUpdate(ID, NAME, TEXT));
    }

    @Test
    public void whenSortByDateWithNullCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByDate(null, true).isEmpty()
        );
    }

    @Test
    public void whenSortByDateWithNullCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(this.service.sortByDate(null, false).isEmpty());
    }

    @Test
    public void whenSortByDateWithEmptyCollectionAndTrueReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByDate(
                        new ArrayList<>(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByDateWithEmptyCollectionAndFalseReversThenReturnEmptyList() {
        assertTrue(
                this.service.sortByDate(
                        new ArrayList<>(), false
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByDateWithTrueReversThenReturnSomeList() {
        assertFalse(
                this.service.getAndSortByDate(true).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByDateWithFalseReversThenReturnSomeList() {
        assertFalse(
                this.service.getAndSortByDate(false).isEmpty()
        );
    }

    @Test
    public void whenFilteredByDateWithNullCollectionThenReturnEmptyList() {
        assertTrue(
                this.service.filterByDate(
                        null, new Date(), new Date()
                ).isEmpty()
        );
    }

    @Test
    public void whenFilteredByDateWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(
                this.service.filterByDate(
                        null, new Date(), new Date()
                ).isEmpty()
        );
    }

    @Test
    public void whenFilteredByDateWithNullStartDateThenReturnNotSortList() {
        assertFalse(
                this.service.filterByDate(
                        getResponses(),
                        null, new Date()
                ).isEmpty()
        );
    }

    @Test
    public void whenFilteredByDateWithNullFinishDateThenReturnNotFilterList() {
        assertFalse(
                this.service.filterByDate(
                        getResponses(),
                        new Date(), null
                ).isEmpty()
        );
    }

    @Test
    public void whenFilteredByDateWithEqualsDatesThenReturnNotFilterList() {
        final Date date = new Date();
        assertFalse(
                this.service.filterByDate(
                        getResponses(),
                        date, date
                ).isEmpty()
        );
    }

    @Test
    public void whenFilteredByDateWithStartDateGreatFinishDateThenReturnNotFilterList() {
        final Date finishDate = new Date();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date startDate = new Date();
        assertFalse(
                this.service.filterByDate(
                        getResponses(),
                        startDate, finishDate
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByDateThenReturnSomeList() {
        assertFalse(
                this.service.filterByDate(
                        getResponses(),
                        new Date(), new Date()
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndFilterByDateWithNullStartDateThenReturnNotFilterList() {
        assertFalse(
                this.service.getAndFilterByDate(
                        null, new Date()
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndFilterByDateWithNullFinishDateThenReturnNotFilterList() {
        assertFalse(
                this.service.getAndFilterByDate(
                        new Date(), null
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndFilterByDateWithEqualsDatesThenReturnNotFilterList() {
        final Date date = new Date();
        assertFalse(
                this.service.getAndFilterByDate(
                        date, date
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndFilterByDateWithStartDateGreatFinishDateThenReturnNotFilterList() {
        final Date finishDate = new Date();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date startDate = new Date();
        assertFalse(
                this.service.getAndFilterByDate(
                        startDate, finishDate
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndFilterByDateThenReturnSomeList() {
        assertFalse(
                this.service.getAndFilterByDate(
                        new Date(), new Date()
                ).isEmpty()
        );
    }

    @Test
    public void whenFilteredByValidWithNullCollectionThenReturnEmptyList() {
        assertTrue(this.service.filteredByValid(null).isEmpty());
    }

    @Test
    public void whenFilteredByValidWithEmptyCollectionThenReturnEmptyList() {
        assertTrue(
                this.service.filteredByValid(
                        new ArrayList<>()
                ).isEmpty()
        );
    }

    @Test
    public void whenFilteredByValidThenReturnSomeList() {
        assertFalse(
                this.service.filteredByValid(
                        getResponses()
                ).isEmpty()
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

    @Ignore
    @Override
    protected Response getInvalidObject() {
        return new Response();
    }
}
