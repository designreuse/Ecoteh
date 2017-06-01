package ua.com.ecoteh.entity;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.company.CompanyType;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Calendar;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.COMPANY_TYPE;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.util.validator.ObjectValidator.isEmpty;

public final class CompanyEntityTest extends ContentTest<CompanyEntity> {

    @Test
    public void whenSetNullTypeThenGetDefaultType() {
        final CompanyEntity companyEntity = MockEntity.getCompanyEntity();
        companyEntity.setType(null);
        assertNotNull(companyEntity.getType());
        assertEquals(companyEntity.getType(), COMPANY_TYPE);
    }

    @Test
    public void whenSetValidTypeThenGetThisType() {
        final CompanyEntity companyEntity = MockEntity.getCompanyEntity();
        companyEntity.setType(COMPANY_TYPE);
        assertNotNull(companyEntity.getType());
        assertEquals(companyEntity.getType(), COMPANY_TYPE);
    }

    @Test
    public void isOpen() {
        final CompanyEntity companyEntity = MockEntity.getCompanyEntity();
        companyEntity.setWorkTimeFrom("09:00");
        companyEntity.setWorkTimeTo("17:00");
        assertEquals(
                companyEntity.isOpen(),
                isDayAndHour(
                        companyEntity.getWorkTimeFrom(),
                        companyEntity.getWorkTimeTo()
                )
        );
    }

    @Test
    @Override
    public void whenSetNullTitleThenGetEmptyString() {
        final CompanyEntity companyEntity = MockEntity.getCompanyEntity();
        companyEntity.setTitle(null);
        assertNotNull(companyEntity.getTitle());
        assertEquals(companyEntity.getTitle(), "");
    }

    @Ignore
    @Override
    public void whenSetBlankTitleThenGetEmptyString() {
        final CompanyEntity companyEntity = MockEntity.getCompanyEntity();
        companyEntity.setTitle("");
        assertNotNull(companyEntity.getTitle());
        companyEntity.setTitle(" ");
        assertNotNull(companyEntity.getTitle());
        companyEntity.setTitle("    ");
        assertNotNull(companyEntity.getTitle());
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
        final CompanyEntity companyEntity = MockEntity.getCompanyEntity();
        if (isEmpty(companyEntity.getDomain())) {
            companyEntity.setDomain(URL);
        }
        companyEntity.setUrl(null);
        assertNotNull(companyEntity.getUrl());
        assertEquals(companyEntity.getUrl(), "");
    }

    @Test
    public void whenSetBlankUrlThenGetEmptyString() {
        final CompanyEntity companyEntity = MockEntity.getCompanyEntity();
        if (isEmpty(companyEntity.getDomain())) {
            companyEntity.setDomain(URL);
        }
        companyEntity.setUrl("");
        assertNotNull(companyEntity.getUrl());
        companyEntity.setUrl(" ");
        assertNotNull(companyEntity.getUrl());
        companyEntity.setUrl("  ");
        assertNotNull(companyEntity.getUrl());
        assertTrue(companyEntity.getUrl().isEmpty());
    }

    @Test
    @Override
    public void toStringTest() {
        final CompanyEntity companyEntity = MockEntity.getCompanyEntity();
        assertEquals(companyEntity.toString(), companyToString(companyEntity));
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final CompanyEntity companyEntity = MockEntity.getCompanyEntity();
        final int value = companyEntity.getTitle().hashCode()
                + companyEntity.getUrl().hashCode()
                + companyEntity.getDescription().hashCode()
                + companyEntity.getType().hashCode()
                + companyEntity.getDomain().hashCode()
                + companyEntity.getTagline().hashCode()
                + companyEntity.getInformation().hashCode();
        for (int i = 0; i < 10; i++) {
            assertEquals(companyEntity.hashCode(), value);
        }
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final CompanyEntity model1 = getInstance();
        final CompanyEntity model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final CompanyEntity model1 = getInstance();
        final CompanyEntity model2 = getObject();
        model1.initialize(model2);
        assertTrue(model1.equals(model2));
    }

    @Ignore
    @Override
    protected CompanyEntity getObject() {
        return MockEntity.getCompanyEntity();
    }

    @Override
    protected CompanyEntity getInstance() {
        return new CompanyEntity();
    }

    @Ignore
    private String companyToString(final CompanyEntity companyEntity) {
        return "CompanyEntity{" +
                "ContentEntity{" +
                "ModelEntity{" +
                "id=" + companyEntity.getId() +
                ", validated=" + companyEntity.isValidated() +
                '}' +
                ", title='" + companyEntity.getTitle() + '\'' +
                ", url='" + companyEntity.getUrl() + '\'' +
                ", description='" + companyEntity.getDescription() + '\'' +
                ", keywords='" + companyEntity.getKeywords() + '\'' +
                ", logo=" + companyEntity.getLogoEntity() +
                '}' +
                ", contacts=" + companyEntity.getContactsEntity() +
                ", address=" + companyEntity.getAddressEntity() +
                ", tagline='" + companyEntity.getTagline() + '\'' +
                ", information='" + companyEntity.getInformation() + '\'' +
                ", domain='" + companyEntity.getDomain() + '\'' +
                ", senderEmail='" + companyEntity.getSenderEmail() + '\'' +
                ", senderPass='" + companyEntity.getSenderPass() + '\'' +
                ", workTimeFrom='" + companyEntity.getWorkTimeFrom() + '\'' +
                ", workTimeTo='" + companyEntity.getWorkTimeTo() + '\'' +
                ", type=" + companyEntity.getType() +
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
