package ua.com.ecoteh.entity;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.enums.CompanyType;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Calendar;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.COMPANY_TYPE;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.util.validator.ObjectValidator.isEmpty;

public final class CompanyTest extends ContentTest<Company> {

    @Test
    public void whenSetNullTypeThenGetDefaultType() {
        final Company company = MockEntity.getCompany();
        company.setType(null);
        assertNotNull(company.getType());
        assertEquals(company.getType(), COMPANY_TYPE);
    }

    @Test
    public void whenSetValidTypeThenGetThisType() {
        final Company company = MockEntity.getCompany();
        company.setType(COMPANY_TYPE);
        assertNotNull(company.getType());
        assertEquals(company.getType(), COMPANY_TYPE);
    }

    @Test
    public void isOpen() {
        final Company company = MockEntity.getCompany();
        company.setWorkTimeFrom("09:00");
        company.setWorkTimeTo("17:00");
        assertEquals(
                company.isOpen(),
                isDayAndHour(
                        company.getWorkTimeFrom(),
                        company.getWorkTimeTo()
                )
        );
    }

    @Test
    @Override
    public void whenSetNullTitleThenGetEmptyString() {
        final Company company = MockEntity.getCompany();
        company.setTitle(null);
        assertNotNull(company.getTitle());
        assertEquals(company.getTitle(), "");
    }

    @Ignore
    @Override
    public void whenSetBlankTitleThenGetEmptyString() {
        final Company company = MockEntity.getCompany();
        company.setTitle("");
        assertNotNull(company.getTitle());
        company.setTitle(" ");
        assertNotNull(company.getTitle());
        company.setTitle("    ");
        assertNotNull(company.getTitle());
    }

    @Test
    public void companyTypeValueOf() {
        for (CompanyType temp : CompanyType.values()) {
            CompanyType type = CompanyType.valueOf(temp.name());
            assertNotNull(type);
            assertEquals(type, temp);
        }
    }

    @Test
    public void whenSetNullUrlThenGetEmptyString() {
        final Company company = MockEntity.getCompany();
        if (isEmpty(company.getDomain())) {
            company.setDomain(URL);
        }
        company.setUrl(null);
        assertNotNull(company.getUrl());
        assertEquals(company.getUrl(), "");
    }

    @Test
    public void whenSetBlankUrlThenGetEmptyString() {
        final Company company = MockEntity.getCompany();
        if (isEmpty(company.getDomain())) {
            company.setDomain(URL);
        }
        company.setUrl("");
        assertNotNull(company.getUrl());
        company.setUrl(" ");
        assertNotNull(company.getUrl());
        company.setUrl("  ");
        assertNotNull(company.getUrl());
        assertTrue(company.getUrl().isEmpty());
    }

    @Test
    @Override
    public void toStringTest() {
        final Company company = MockEntity.getCompany();
        assertEquals(company.toString(), companyToString(company));
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final Company company = MockEntity.getCompany();
        final int value = company.getTitle().hashCode()
                + company.getUrl().hashCode()
                + company.getDescription().hashCode()
                + company.getType().hashCode()
                + company.getDomain().hashCode()
                + company.getTagline().hashCode()
                + company.getInformation().hashCode();
        for (int i = 0; i < 10; i++) {
            assertEquals(company.hashCode(), value);
        }
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final Company model1 = getInstance();
        final Company model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final Company model1 = getInstance();
        final Company model2 = getObject();
        model1.initialize(model2);
        assertTrue(model1.equals(model2));
    }

    @Ignore
    @Override
    protected Company getObject() {
        return MockEntity.getCompany();
    }

    @Override
    protected Company getInstance() {
        return new Company();
    }

    @Ignore
    private String companyToString(final Company company) {
        return "Company{" +
                "Content{" +
                "Model{" +
                "id=" + company.getId() +
                ", validated=" + company.isValidated() +
                '}' +
                ", title='" + company.getTitle() + '\'' +
                ", url='" + company.getUrl() + '\'' +
                ", description='" + company.getDescription() + '\'' +
                ", keywords='" + company.getKeywords() + '\'' +
                ", logo=" + company.getLogo() +
                '}' +
                ", contacts=" + company.getContacts() +
                ", address=" + company.getAddress() +
                ", tagline='" + company.getTagline() + '\'' +
                ", information='" + company.getInformation() + '\'' +
                ", domain='" + company.getDomain() + '\'' +
                ", senderEmail='" + company.getSenderEmail() + '\'' +
                ", senderPass='" + company.getSenderPass() + '\'' +
                ", workTimeFrom='" + company.getWorkTimeFrom() + '\'' +
                ", workTimeTo='" + company.getWorkTimeTo() + '\'' +
                ", type=" + company.getType() +
                '}';
    }

    @Ignore
    private static boolean isDayAndHour(String workTimeFrom, String workTimeTo) {
        try {
            final Calendar calendar = Calendar.getInstance();
            final int hourNow = calendar.get(Calendar.HOUR_OF_DAY);
            boolean isHour = (hourNow >= getHour(workTimeFrom)) && (hourNow < getHour(workTimeTo));
            boolean isDay;
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY:
                case Calendar.SATURDAY:
                    isDay = false;
                    break;
                case Calendar.FRIDAY:
                    isDay = isHour;
                    break;
                default:
                    isDay = true;
                    break;
            }
            return isHour && isDay;
        } catch (Exception ex) {
            return false;
        }
    }

    @Ignore
    private static int getHour(String time) {
        return Integer.parseInt(time.substring(0, time.indexOf(':'))) +
                (Integer.parseInt(time.substring(time.indexOf(':') + 1)) > 30 ? 1 : 0);
    }
}
