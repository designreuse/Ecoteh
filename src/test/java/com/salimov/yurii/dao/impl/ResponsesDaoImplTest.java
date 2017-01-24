package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.ResponseDao;
import com.salimov.yurii.entity.Response;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getResponse;
import static com.salimov.yurii.mocks.enity.MockEntity.getResponses;
import static com.salimov.yurii.mocks.repository.MockRepository.getResponseRepository;


public final class ResponsesDaoImplTest
        extends DataDAOImplTest<Response, Long> {

    @Ignore
    @Override
    protected ResponseDao getDao() {
        return new ResponseDaoImpl(
                getResponseRepository()
        );
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
