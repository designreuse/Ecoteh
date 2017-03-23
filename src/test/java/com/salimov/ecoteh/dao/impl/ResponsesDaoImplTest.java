package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.ResponseDao;
import com.salimov.ecoteh.entity.Response;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.enity.MockEntity.getResponse;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getResponses;
import static com.salimov.ecoteh.mocks.repository.MockRepository.getResponseRepository;


public final class ResponsesDaoImplTest extends DataDAOImplTest<Response> {

    @Ignore
    @Override
    protected ResponseDao getDao() {
        return new ResponseDaoImpl(getResponseRepository());
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
