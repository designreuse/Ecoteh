package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.Response;
import com.salimov.yurii.service.data.interfaces.ResponseService;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.yurii.mocks.dao.MockDao.getResponseDao;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponse;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponses;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class ResponseServiceImplTest
        extends DataServiceImplTest<Response, Long> {

    private ResponseService service;

    @Before
    public void beforeTest() {
        this.service = new ResponseServiceImpl(
                getResponseDao()
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
