package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.service.data.CompanyService;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.DOMAIN;
import static ua.com.ecoteh.mocks.enity.MockModels.getCompanies;
import static ua.com.ecoteh.mocks.enity.MockModels.getCompany;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class MockCompanyService extends MockContentService<Company> {

    private final CompanyService service;
    private final Company company;
    private final Collection<Company> companies;

    MockCompanyService() {
        this.service = mock(CompanyService.class);
        this.company = getCompany();
        this.companies = getCompanies();
    }

    @Override
    CompanyService create() {
        super.create();
        initGetAll();
        initUpdateMainCompany();
        initGetMainCompany();
        initGetPartners();
        initGetByDomain();
        return this.service;
    }
    
    @Override
    CompanyService getService() {
        return this.service;
    }

    @Override
    Company getModel() {
        return this.company;
    }

    @Override
    Collection<Company> getModels() {
        return this.companies;
    }

    private void initGetAll() {
        when(this.service.getAll(true)).thenReturn(this.companies);
        when(this.service.getAll(false)).thenReturn(this.companies);
    }

    private void initUpdateMainCompany() {
        when(this.service.updateMainCompany(this.company)).thenReturn(this.company);
        when(this.service.updateMainCompany(null)).thenThrow(new IllegalArgumentException());
    }

    private void initGetMainCompany() {
        when(this.service.getMainCompany()).thenReturn(this.company);
    }

    private void initGetPartners() {
        when(this.service.getPartners(true)).thenReturn(this.companies);
        when(this.service.getPartners(false)).thenReturn(this.companies);
    }

    private void initGetByDomain() {
        when(this.service.getByDomain(DOMAIN)).thenReturn(this.company);
        when(this.service.getByDomain(null)).thenThrow(new IllegalArgumentException());
        when(this.service.getByDomain("")).thenThrow(new IllegalArgumentException());
        when(this.service.getByDomain(ANY_STRING)).thenThrow(new NullPointerException());
    }
}
