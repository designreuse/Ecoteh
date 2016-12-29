package com.salimov.yurii.dao.impl;

import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.ResponseDao;
import com.salimov.yurii.entity.Response;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@SuppressWarnings("SpringJavaAutowiringInspection")
public class ResponseDAOImplTest extends DataDAOImplTest<Response> {

    @Autowired
    private ResponseDao responseDao;

    @Ignore
    @Override
    protected ResponseDao getDao() {
        return this.responseDao;
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
