package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.repository.UserRepository;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.LOGIN;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getUserEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getUserEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockUserRepository extends MockDataRepository<UserEntity> {

    private final UserRepository repository;
    private final UserEntity entity;
    private final Collection<UserEntity> entities;

    MockUserRepository() {
        this.repository = mock(UserRepository.class);
        this.entity = getUserEntity();
        this.entities = getUserEntities();
    }

    @Override
    UserRepository create() {
        super.create();
        initFindByName();
        initFindByUrl();
        initFindByLogin();
        initFindByContactsEmail();
        initFindByContactsMobilePhone();
        initFindByContactsLandlinesPhone();
        initFindByContactsFax();
        return this.repository;
    }

    @Override
    UserRepository getRepository() {
        return this.repository;
    }

    @Override
    UserEntity getEntity() {
        return this.entity;
    }

    @Override
    Collection<UserEntity> getEntities() {
        return this.entities;
    }

    private void initFindByName() {
        when(this.repository.findByName(NAME)).thenReturn(this.entity);
        when(this.repository.findByName(ANY_STRING)).thenReturn(null);
        when(this.repository.findByName(null)).thenReturn(null);
    }

    private void initFindByUrl() {
        when(this.repository.findByUrl(URL)).thenReturn(this.entity);
        when(this.repository.findByUrl(ANY_STRING)).thenReturn(null);
        when(this.repository.findByUrl(null)).thenReturn(null);
    }

    private void initFindByContactsEmail() {
        when(this.repository.findByContactsEmail(EMAIL)).thenReturn(this.entity);
        when(this.repository.findByContactsEmail(ANY_STRING)).thenReturn(null);
        when(this.repository.findByContactsEmail(null)).thenReturn(null);
    }

    private void initFindByContactsMobilePhone() {
        when(this.repository.findByContactsMobilePhone(PHONE)).thenReturn(this.entity);
        when(this.repository.findByContactsMobilePhone(ANY_STRING)).thenReturn(null);
        when(this.repository.findByContactsMobilePhone(null)).thenReturn(null);
    }

    private void initFindByContactsLandlinesPhone() {
        when(this.repository.findByContactsLandlinesPhone(PHONE_2)).thenReturn(this.entity);
        when(this.repository.findByContactsLandlinesPhone(ANY_STRING)).thenReturn(null);
        when(this.repository.findByContactsLandlinesPhone(null)).thenReturn(null);
    }

    private void initFindByContactsFax() {
        when(this.repository.findByContactsFax(FAX)).thenReturn(this.entity);
        when(this.repository.findByContactsFax(ANY_STRING)).thenReturn(null);
        when(this.repository.findByContactsFax(null)).thenReturn(null);
    }

    private void initFindByLogin() {
        final Encryptor encryptor = new Base64Encryptor();
        when(this.repository.findByLogin(encryptor.encrypt(LOGIN))).thenReturn(this.entity);
        when(this.repository.findByLogin(LOGIN)).thenReturn(this.entity);
        when(this.repository.findByLogin(encryptor.encrypt(ANY_STRING))).thenReturn(null);
        when(this.repository.findByLogin(ANY_STRING)).thenReturn(null);
        when(this.repository.findByLogin(null)).thenReturn(null);
    }
}
