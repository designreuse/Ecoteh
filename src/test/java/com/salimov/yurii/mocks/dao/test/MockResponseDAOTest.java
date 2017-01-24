package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.ResponseDao;
import com.salimov.yurii.entity.Response;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getResponse;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponses;
import static com.salimov.yurii.mocks.dao.MockDao.getResponseDao;

public class MockResponseDAOTest extends MockDataDAOTest<Response> {

    private ResponseDao dao;

    @Before
    public void initVideoDao() {
        this.dao = getResponseDao();
    }

    @Ignore
    @Override
    protected ResponseDao getDao() {
        return this.dao;
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
