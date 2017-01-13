package com.salimov.yurii.entity;

import com.salimov.yurii.enums.CompanyType;
import com.salimov.yurii.util.translator.Translator;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class CompanyTest extends ContentTest<Company> {

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        Company company = new Company(
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null
        );
        assertNull(company.getTitle());
        assertNull(company.getDomain());
        assertNull(company.getTagline());
        assertNull(company.getDescription());
        assertNull(company.getInformation());
        assertNull(company.getAdvantages());
        assertNull(company.getLandlinePhone());
        assertNull(company.getMobilePhone());
        assertNull(company.getEmail());
        assertNull(company.getSenderEmail());
        assertNull(company.getSenderPass());
        assertNull(company.getVkontakte());
        assertNull(company.getFacebook());
        assertNull(company.getTwitter());
        assertNull(company.getSkype());
        assertNull(company.getAddress());
        assertNull(company.getKeywords());
        assertNull(company.getGoogleMaps());
        assertNull(company.getLogo());
        assertNull(company.getFavicon());

        final File logo = new File();
        final File favicon = new File();
        company = new Company(
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "",
                "", logo, favicon
        );
        assertNull(company.getTitle());
        assertNull(company.getDomain());
        assertNull(company.getTagline());
        assertNull(company.getDescription());
        assertNull(company.getInformation());
        assertNull(company.getAdvantages());
        assertNull(company.getLandlinePhone());
        assertNull(company.getMobilePhone());
        assertNull(company.getEmail());
        assertNull(company.getSenderEmail());
        assertNull(company.getSenderPass());
        assertNull(company.getVkontakte());
        assertNull(company.getFacebook());
        assertNull(company.getTwitter());
        assertNull(company.getSkype());
        assertNull(company.getAddress());
        assertNull(company.getKeywords());
        assertNull(company.getGoogleMaps());
        assertNull(company.getLogo());
        assertNull(company.getFavicon());

        company = new Company(
                " ", " ", " ", " ", " ", " ", " ",
                " ", " ", " ", " ", " ", " ", " ",
                " ", " ", " ", " ", " ", logo, favicon
        );
        assertNull(company.getTitle());
        assertNull(company.getDomain());
        assertNull(company.getTagline());
        assertNull(company.getDescription());
        assertNull(company.getInformation());
        assertNull(company.getAdvantages());
        assertNull(company.getLandlinePhone());
        assertNull(company.getMobilePhone());
        assertNull(company.getEmail());
        assertNull(company.getSenderEmail());
        assertNull(company.getSenderPass());
        assertNull(company.getVkontakte());
        assertNull(company.getFacebook());
        assertNull(company.getTwitter());
        assertNull(company.getSkype());
        assertNull(company.getAddress());
        assertNull(company.getKeywords());
        assertNull(company.getGoogleMaps());
        assertNull(company.getLogo());
        assertNull(company.getFavicon());

        company = new Company(
                "   ", "   ", "   ", "   ", "   ",
                "   ", "   ", "   ", "   ", "   ",
                "   ", "   ", "   ", "   ", "   ",
                "   ", "   ", "   ", "   ", logo, favicon
        );
        assertNull(company.getTitle());
        assertNull(company.getDomain());
        assertNull(company.getTagline());
        assertNull(company.getDescription());
        assertNull(company.getInformation());
        assertNull(company.getAdvantages());
        assertNull(company.getLandlinePhone());
        assertNull(company.getMobilePhone());
        assertNull(company.getEmail());
        assertNull(company.getSenderEmail());
        assertNull(company.getSenderPass());
        assertNull(company.getVkontakte());
        assertNull(company.getFacebook());
        assertNull(company.getTwitter());
        assertNull(company.getSkype());
        assertNull(company.getAddress());
        assertNull(company.getKeywords());
        assertNull(company.getGoogleMaps());
        assertNull(company.getLogo());
        assertNull(company.getFavicon());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        Company company = new Company();
        assertNotNull(company.getType());
        assertEquals(
                company.getType(),
                CompanyType.PARTNER
        );
        final File logo = getPhoto();
        final File favicon = getPhoto();
        company = new Company(
                TITLE, DOMAIN, TAGLINE, DESCRIPTION,
                INFORMATION, ADVANTAGES, PHONE, PHONE,
                PHONE, EMAIL, EMAIL, PASSWORD, VKONTAKTE,
                FACEBOOK, TWITTER, SKYPE, ADDRESS,
                KEYWORDS, GOOGLE_MAPS, logo, favicon
        );
        assertNotNull(company.getTitle());
        assertNotNull(company.getDomain());
        assertNotNull(company.getTagline());
        assertNotNull(company.getDescription());
        assertNotNull(company.getInformation());
        assertNotNull(company.getAdvantages());
        assertNotNull(company.getLandlinePhone());
        assertNotNull(company.getMobilePhone());
        assertNotNull(company.getEmail());
        assertNotNull(company.getSenderEmail());
        assertNotNull(company.getSenderPass());
        assertNotNull(company.getVkontakte());
        assertNotNull(company.getFacebook());
        assertNotNull(company.getTwitter());
        assertNotNull(company.getSkype());
        assertNotNull(company.getAddress());
        assertNotNull(company.getKeywords());
        assertNotNull(company.getGoogleMaps());
        assertNotNull(company.getLogo());
        assertNotNull(company.getFavicon());
        assertEquals(
                company.getTitle(),
                TITLE
        );
        assertEquals(
                company.getDomain(),
                DOMAIN
        );
        assertEquals(
                company.getTagline(),
                TAGLINE
        );
        assertEquals(
                company.getDescription(),
                DESCRIPTION
        );
        assertEquals(
                company.getInformation(),
                INFORMATION
        );
        assertEquals(
                company.getAdvantages(),
                ADVANTAGES
        );
        assertEquals(
                company.getLandlinePhone(),
                PHONE
        );
        assertEquals(
                company.getMobilePhone(),
                PHONE
        );
        assertEquals(
                company.getEmail(),
                EMAIL
        );
        assertEquals(
                company.getSenderEmail(),
                EMAIL
        );
        assertEquals(
                company.getSenderPass(),
                PASSWORD
        );
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
        assertEquals(
                company.getSkype(),
                SKYPE
        );
        assertEquals(
                company.getAddress(),
                ADDRESS
        );
        assertEquals(
                company.getKeywords(),
                KEYWORDS
        );
        assertEquals(
                company.getGoogleMaps(),
                GOOGLE_MAPS
        );
        assertEquals(
                company.getLogo(),
                logo
        );
        assertEquals(
                company.getFavicon(),
                favicon
        );
    }

    @Test
    @Override
    public void toStringTest() {
        final Company company = getCompany();
        company.setType(CompanyType.MAIN);
        assertNotNull(company.toString());
        assertEquals(
                company.toString(),
                companyToString(company)
        );
    }

    @Test
    public void toStringTestWhenPartnerCompany() {
        final Company company = getCompany();
        company.setType(CompanyType.PARTNER);
        assertNotNull(company.toString());
        assertEquals(
                company.toString(),
                companyToString(company)
        );
    }

    @Test
    public void equalsInvalidObjects() {
        final Company company1 = new Company();
        company1.setType(null);

        assertTrue(
                company1.equals(company1)
        );
        assertFalse(
                company1.equals(null)
        );
        Company company2 = new Company();
        company2.setType(null);
        assertTrue(
                company1.equals(company2)
        );
        company1.setTitle(TITLE);
        company2.setTitle(TITLE);
        assertTrue(
                company1.equals(company2)
        );
        company1.setUrl(URL);
        company2.setUrl(URL);
        assertTrue(
                company1.equals(company2)
        );
        company1.setType(CompanyType.PARTNER);
        company2.setType(CompanyType.PARTNER);
        assertTrue(
                company1.equals(company2)
        );
        company1.setDomain(DOMAIN);
        company2.setDomain(DOMAIN);
        assertTrue(
                company1.equals(company2)
        );
        company1.setTagline(TAGLINE);
        company2.setTagline(TAGLINE);
        assertTrue(
                company1.equals(company2)
        );
        company1.setDescription(DESCRIPTION);
        company2.setDescription(DESCRIPTION);
        assertTrue(
                company1.equals(company2)
        );
        company1.setInformation(INFORMATION);
        company2.setInformation(INFORMATION);
        assertTrue(
                company1.equals(company2)
        );
        company1.setAddress(ADDRESS);
        company2.setAddress(ADDRESS);
        assertTrue(
                company1.equals(company2)
        );
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();

        final Company company1 = getCompany();
        final Company company2 = (Company) company1.clone();
        assertEquals(company1, company2);
        final boolean value = (
                company1.getType() != null ?
                        company1.getType().equals(
                                company2.getType()
                        ) : company2.getType() == null
        ) && (
                isNotBlank(company1.getDomain()) ?
                        company1.getDomain()
                                .equalsIgnoreCase(
                                        company2.getDomain()
                                ) : isBlank(company2.getDomain())
        ) && (
                isNotBlank(company1.getTagline()) ?
                        company1.getTagline()
                                .equals(
                                        company2.getTagline()
                                ) : isBlank(company2.getTagline())
        ) && (
                isNotBlank(company1.getDescription()) ?
                        company1.getDescription()
                                .equalsIgnoreCase(
                                        company2.getDescription()
                                ) : isBlank(company2.getDescription())
        ) && (
                isNotBlank(company1.getInformation()) ?
                        company1.getInformation()
                                .equals(
                                        company2.getInformation()
                                ) : isBlank(company2.getInformation())
        ) && (
                isNotBlank(company1.getAddress()) ?
                        company1.getAddress()
                                .equalsIgnoreCase(
                                        company2.getAddress()
                                ) : isBlank(company2.getAddress())
        );
        assertEquals(company1.equals(company2), value);
    }

    @Test
    public void hashCodeInvalidObject() {
        final Company company = new Company();
        int value = company.getType() != null ? company.getType().hashCode() : 0;
        assertEquals(
                company.hashCode(),
                value
        );
        company.setTitle(TITLE);
        value += (
                isNotBlank(company.getTitle()) ? company.getTitle().hashCode() : 0
        ) + (
                isNotBlank(company.getUrl()) ? company.getUrl().hashCode() : 0
        );
        assertEquals(
                company.hashCode(),
                value
        );
        company.setDomain(DOMAIN);
        value += isNotBlank(company.getDomain()) ? company.getDomain().hashCode() : 0;
        assertEquals(
                company.hashCode(),
                value
        );
        company.setTagline(TAGLINE);
        value += isNotBlank(company.getTagline()) ? company.getTagline().hashCode() : 0;
        assertEquals(
                company.hashCode(),
                value
        );
        company.setDescription(DESCRIPTION);
        value += isNotBlank(company.getDescription()) ? company.getDescription().hashCode() : 0;
        assertEquals(
                company.hashCode(),
                value
        );
        company.setInformation(INFORMATION);
        value += isNotBlank(company.getInformation()) ? company.getInformation().hashCode() : 0;
        assertEquals(
                company.hashCode(),
                value
        );
        company.setAddress(ADDRESS);
        value += isNotBlank(company.getAddress()) ? company.getAddress().hashCode() : 0;
        assertEquals(
                company.hashCode(),
                value
        );
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final Company company = getCompany();
        final int value = (
                isNotBlank(company.getTitle()) ? company.getTitle().hashCode() : 0
        ) + (
                isNotBlank(company.getUrl()) ? company.getUrl().hashCode() : 0
        ) + (
                company.getType() != null ? company.getType().hashCode() : 0
        ) + (
                isNotBlank(company.getDomain()) ? company.getDomain().hashCode() : 0
        ) + (
                isNotBlank(company.getTagline()) ? company.getTagline().hashCode() : 0
        ) + (
                isNotBlank(company.getDescription()) ? company.getDescription().hashCode() : 0
        ) + (
                isNotBlank(company.getInformation()) ? company.getInformation().hashCode() : 0
        ) + (
                isNotBlank(company.getAddress()) ? company.getAddress().hashCode() : 0
        );
        for (int i = 0; i < 10; i++) {
            assertEquals(
                    company.hashCode(),
                    value
            );
        }
    }

    @Test
    @Override
    public void whenInitializeObjectWithInvalidParametersThenGetNull() {
        super.whenInitializeObjectWithInvalidParametersThenGetNull();
        final Company company = new Company();
        company.initialize(
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null
        );
        assertNull(company.getTitle());
        assertNull(company.getDomain());
        assertNull(company.getTagline());
        assertNull(company.getDescription());
        assertNull(company.getInformation());
        assertNull(company.getAdvantages());
        assertNull(company.getLandlinePhone());
        assertNull(company.getMobilePhone());
        assertNull(company.getEmail());
        assertNull(company.getSenderEmail());
        assertNull(company.getSenderPass());
        assertNull(company.getVkontakte());
        assertNull(company.getFacebook());
        assertNull(company.getTwitter());
        assertNull(company.getSkype());
        assertNull(company.getAddress());
        assertNull(company.getKeywords());
        assertNull(company.getGoogleMaps());
        assertNull(company.getLogo());
        assertNull(company.getFavicon());

        final File logo = new File();
        final File favicon = new File();
        company.initialize(
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "",
                "", logo, favicon
        );
        assertNull(company.getTitle());
        assertNull(company.getDomain());
        assertNull(company.getTagline());
        assertNull(company.getDescription());
        assertNull(company.getInformation());
        assertNull(company.getAdvantages());
        assertNull(company.getLandlinePhone());
        assertNull(company.getMobilePhone());
        assertNull(company.getEmail());
        assertNull(company.getSenderEmail());
        assertNull(company.getSenderPass());
        assertNull(company.getVkontakte());
        assertNull(company.getFacebook());
        assertNull(company.getTwitter());
        assertNull(company.getSkype());
        assertNull(company.getAddress());
        assertNull(company.getKeywords());
        assertNull(company.getGoogleMaps());
        assertNull(company.getLogo());
        assertNull(company.getFavicon());

        company.initialize(
                " ", " ", " ", " ", " ", " ", " ",
                " ", " ", " ", " ", " ", " ", " ",
                " ", " ", " ", " ", " ", logo, favicon
        );
        assertNull(company.getTitle());
        assertNull(company.getDomain());
        assertNull(company.getTagline());
        assertNull(company.getDescription());
        assertNull(company.getInformation());
        assertNull(company.getAdvantages());
        assertNull(company.getLandlinePhone());
        assertNull(company.getMobilePhone());
        assertNull(company.getEmail());
        assertNull(company.getSenderEmail());
        assertNull(company.getSenderPass());
        assertNull(company.getVkontakte());
        assertNull(company.getFacebook());
        assertNull(company.getTwitter());
        assertNull(company.getSkype());
        assertNull(company.getAddress());
        assertNull(company.getKeywords());
        assertNull(company.getGoogleMaps());
        assertNull(company.getLogo());
        assertNull(company.getFavicon());

        company.initialize(
                "   ", "   ", "   ", "   ", "   ", "   ",
                "   ", "   ", "   ", "   ", "   ", "   ",
                "   ", "   ", "   ", "   ", "   ", "   ",
                "   ", logo, favicon
        );
        assertNull(company.getTitle());
        assertNull(company.getDomain());
        assertNull(company.getTagline());
        assertNull(company.getDescription());
        assertNull(company.getInformation());
        assertNull(company.getAdvantages());
        assertNull(company.getLandlinePhone());
        assertNull(company.getMobilePhone());
        assertNull(company.getEmail());
        assertNull(company.getSenderEmail());
        assertNull(company.getSenderPass());
        assertNull(company.getVkontakte());
        assertNull(company.getFacebook());
        assertNull(company.getTwitter());
        assertNull(company.getSkype());
        assertNull(company.getAddress());
        assertNull(company.getKeywords());
        assertNull(company.getGoogleMaps());
        assertNull(company.getLogo());
        assertNull(company.getFavicon());
    }

    @Test
    @Override
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        super.whenInitializeObjectWithValidParametersThenGetThisValue();
        final Company company = new Company();
        final File logo = getPhoto();
        final File favicon = getPhoto();
        company.initialize(
                TITLE, DOMAIN, TAGLINE, DESCRIPTION,
                INFORMATION, ADVANTAGES, PHONE, PHONE,
                PHONE, EMAIL, EMAIL, PASSWORD, VKONTAKTE,
                FACEBOOK, TWITTER, SKYPE, ADDRESS, KEYWORDS,
                GOOGLE_MAPS, logo, favicon
        );
        assertNotNull(company.getTitle());
        assertNotNull(company.getDomain());
        assertNotNull(company.getTagline());
        assertNotNull(company.getDescription());
        assertNotNull(company.getInformation());
        assertNotNull(company.getAdvantages());
        assertNotNull(company.getLandlinePhone());
        assertNotNull(company.getMobilePhone());
        assertNotNull(company.getEmail());
        assertNotNull(company.getSenderEmail());
        assertNotNull(company.getSenderPass());
        assertNotNull(company.getVkontakte());
        assertNotNull(company.getFacebook());
        assertNotNull(company.getTwitter());
        assertNotNull(company.getSkype());
        assertNotNull(company.getAddress());
        assertNotNull(company.getKeywords());
        assertNotNull(company.getGoogleMaps());
        assertNotNull(company.getLogo());
        assertNotNull(company.getFavicon());
        assertEquals(
                company.getTitle(),
                TITLE
        );
        assertEquals(
                company.getDomain(),
                DOMAIN
        );
        assertEquals(
                company.getTagline(),
                TAGLINE
        );
        assertEquals(
                company.getDescription(),
                DESCRIPTION
        );
        assertEquals(
                company.getInformation(),
                INFORMATION
        );
        assertEquals(
                company.getAdvantages(),
                ADVANTAGES
        );
        assertEquals(
                company.getLandlinePhone(),
                PHONE
        );
        assertEquals(
                company.getMobilePhone(),
                PHONE
        );
        assertEquals(
                company.getEmail(),
                EMAIL
        );
        assertEquals(
                company.getSenderEmail(),
                EMAIL
        );
        assertEquals(
                company.getSenderPass(),
                PASSWORD
        );
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
        assertEquals(
                company.getSkype(),
                SKYPE
        );
        assertEquals(
                company.getAddress(),
                ADDRESS
        );
        assertEquals(
                company.getKeywords(),
                KEYWORDS
        );
        assertEquals(
                company.getGoogleMaps(),
                GOOGLE_MAPS
        );
        assertEquals(
                company.getLogo(),
                logo
        );
        assertEquals(
                company.getFavicon(),
                favicon
        );
    }

    @Test
    public void whenSetInvalidDomainThenGetNull() {
        final Company company = getCompany();
        company.setDomain(null);
        assertNull(company.getDomain());
        company.setDomain("");
        assertNull(company.getDomain());
        company.setDomain(" ");
        assertNull(company.getDomain());
        company.setDomain("   ");
        assertNull(company.getDomain());
    }

    @Test
    public void whenSetValidDomainThenGetThisDomain() {
        final Company company = getCompany();
        company.setDomain(DOMAIN);
        assertNotNull(company.getDomain());
        assertEquals(
                company.getDomain(),
                DOMAIN
        );
        company.setDomain("http://" + DOMAIN);
        assertNotNull(company.getDomain());
        assertEquals(
                company.getDomain(),
                DOMAIN
        );
        company.setDomain("https://" + DOMAIN);
        assertNotNull(company.getDomain());
        assertEquals(
                company.getDomain(),
                DOMAIN
        );
    }

    @Test
    public void whenSetInvalidTaglineThenGetNull() {
        final Company company = getCompany();
        company.setTagline(null);
        assertNull(company.getTagline());
        company.setTagline("");
        assertNull(company.getTagline());
        company.setTagline(" ");
        assertNull(company.getTagline());
        company.setTagline("   ");
        assertNull(company.getTagline());
    }

    @Test
    public void whenSetValidTaglineThenGetThisTagline() {
        final Company company = getCompany();
        company.setTagline(TAGLINE);
        assertNotNull(company.getTagline());
        assertEquals(
                company.getTagline(),
                TAGLINE
        );
    }

    @Test
    public void whenSetInvalidInformationThenGetNull() {
        final Company company = getCompany();
        company.setInformation(null);
        assertNull(company.getInformation());
        company.setInformation("");
        assertNull(company.getInformation());
        company.setInformation(" ");
        assertNull(company.getInformation());
        company.setInformation("   ");
        assertNull(company.getInformation());
    }

    @Test
    public void whenSetValidInformationThenGetThisInformation() {
        final Company company = getCompany();
        company.setInformation(INFORMATION);
        assertNotNull(company.getInformation());
        assertEquals(
                company.getInformation(),
                INFORMATION
        );
    }

    @Test
    public void whenSetInvalidAdvantagesThenGetNull() {
        final Company company = getCompany();
        company.setAdvantages(null);
        assertNull(company.getAdvantages());
        company.setAdvantages("");
        assertNull(company.getAdvantages());
        company.setAdvantages(" ");
        assertNull(company.getAdvantages());
        company.setAdvantages("   ");
        assertNull(company.getAdvantages());
    }

    @Test
    public void whenSetValidAdvantagesThenGetThisAdvantages() {
        final Company company = getCompany();
        company.setAdvantages(ADVANTAGES);
        assertNotNull(company.getAdvantages());
        assertEquals(
                company.getAdvantages(),
                ADVANTAGES
        );
    }

    @Test
    public void whenSetInvalidEmailThenGetNull() {
        final Company company = getCompany();
        company.setEmail(null);
        assertNull(company.getEmail());
        company.setEmail("");
        assertNull(company.getEmail());
        company.setEmail(" ");
        assertNull(company.getEmail());
        company.setEmail("   ");
        assertNull(company.getEmail());
    }

    @Test
    public void whenSetValidEmailThenGetThisEmail() {
        final Company company = getCompany();
        company.setEmail(EMAIL);
        assertNotNull(company.getEmail());
        assertEquals(
                company.getEmail(),
                EMAIL
        );
    }

    @Test
    public void whenSetInvalidSenderEmailThenGetNull() {
        final Company company = getCompany();
        company.setSenderEmail(null);
        assertNull(company.getSenderEmail());
        company.setSenderEmail("");
        assertNull(company.getSenderEmail());
        company.setSenderEmail(" ");
        assertNull(company.getSenderEmail());
        company.setSenderEmail("   ");
        assertNull(company.getSenderEmail());
    }

    @Test
    public void whenSetValidSenderEmailThenGetThisEmail() {
        final Company company = getCompany();
        company.setSenderEmail(EMAIL);
        assertNotNull(company.getSenderEmail());
        assertEquals(
                company.getSenderEmail(),
                EMAIL
        );
    }

    @Test
    public void whenSetInvalidSenderPassThenGetNull() {
        final Company company = getCompany();
        company.setSenderPass(null);
        assertNull(company.getSenderPass());
        company.setSenderPass("");
        assertNull(company.getSenderPass());
        company.setSenderPass(" ");
        assertNull(company.getSenderPass());
        company.setSenderPass("   ");
        assertNull(company.getSenderPass());
    }

    @Test
    public void whenSetValidSenderPassThenGetThisPass() {
        final Company company = getCompany();
        company.setSenderPass(PASSWORD);
        assertNotNull(company.getSenderPass());
        assertEquals(
                company.getSenderPass(),
                PASSWORD
        );
    }

    @Test
    public void whenSetInvalidMobilePhoneThenGetNull() {
        final Company company = getCompany();
        company.setMobilePhone(null);
        assertNull(company.getMobilePhone());
        company.setMobilePhone("");
        assertNull(company.getMobilePhone());
        company.setMobilePhone(" ");
        assertNull(company.getMobilePhone());
        company.setMobilePhone("   ");
        assertNull(company.getMobilePhone());
    }

    @Test
    public void whenSetValidMobilePhoneThenGetThisPhone() {
        final Company company = getCompany();
        company.setLandlinePhone(PHONE);
        assertNotNull(company.getLandlinePhone());
        assertEquals(
                company.getLandlinePhone(),
                PHONE
        );
    }

    @Test
    public void whenSetLandlineMobilePhoneThenGetNull() {
        final Company company = getCompany();
        company.setLandlinePhone(null);
        assertNull(company.getLandlinePhone());
        company.setLandlinePhone("");
        assertNull(company.getLandlinePhone());
        company.setLandlinePhone(" ");
        assertNull(company.getLandlinePhone());
        company.setLandlinePhone("   ");
        assertNull(company.getLandlinePhone());
    }

    @Test
    public void whenSetValidLandlinePhoneThenGetThisPhone() {
        final Company company = getCompany();
        company.setLandlinePhone(PHONE);
        assertNotNull(company.getLandlinePhone());
        assertEquals(
                company.getLandlinePhone(),
                PHONE
        );
    }

    @Test
    public void whenSetFaxPhoneThenGetNull() {
        final Company company = getCompany();
        company.setFax(null);
        assertNull(company.getFax());
        company.setFax("");
        assertNull(company.getFax());
        company.setFax(" ");
        assertNull(company.getFax());
        company.setFax("   ");
        assertNull(company.getFax());
    }

    @Test
    public void whenSetValidFaxThenGetThisFax() {
        final Company company = getCompany();
        company.setFax(PHONE);
        assertNotNull(company.getFax());
        assertEquals(
                company.getFax(),
                PHONE
        );
    }

    @Test
    public void whenSetInvalidVkontakteThenGetNull() {
        final Company company = getCompany();
        company.setVkontakte(null);
        assertNull(company.getVkontakte());
        company.setVkontakte("");
        assertNull(company.getVkontakte());
        company.setVkontakte(" ");
        assertNull(company.getVkontakte());
        company.setVkontakte("   ");
        assertNull(company.getVkontakte());
    }

    @Test
    public void whenSetValidVkontakteThenGetThisVkontakte() {
        final Company company = getCompany();
        company.setVkontakte(VKONTAKTE);
        assertNotNull(company.getVkontakte());
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
        company.setVkontakte("http://" + VKONTAKTE);
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
        company.setVkontakte("https://" + VKONTAKTE);
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
        company.setVkontakte("m.vk.com" + VKONTAKTE);
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
        company.setVkontakte("vk.com" + VKONTAKTE);
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
        company.setVkontakte("http://m.vk.com" + VKONTAKTE);
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
        company.setVkontakte("https://m.vk.com" + VKONTAKTE);
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
        company.setVkontakte("http://vk.com" + VKONTAKTE);
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
        company.setVkontakte("https://vk.com" + VKONTAKTE);
        assertEquals(
                company.getVkontakte(),
                VKONTAKTE
        );
    }

    @Test
    public void whenSetInvalidFacebookThenGetNull() {
        final Company company = getCompany();
        company.setFacebook(null);
        assertNull(company.getFacebook());
        company.setFacebook("");
        assertNull(company.getFacebook());
        company.setFacebook(" ");
        assertNull(company.getFacebook());
        company.setFacebook("   ");
        assertNull(company.getFacebook());
    }

    @Test
    public void whenSetValidFacebookThenGetThisFacebook() {
        final Company company = getCompany();
        company.setFacebook(FACEBOOK);
        assertNotNull(company.getFacebook());
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
        company.setFacebook("http://" + FACEBOOK);
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
        company.setFacebook("https://" + FACEBOOK);
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
        company.setFacebook("m.facebook.com" + FACEBOOK);
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
        company.setFacebook("www.facebook.com" + FACEBOOK);
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
        company.setFacebook("http://m.facebook.com" + FACEBOOK);
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
        company.setFacebook("https://m.facebook.com" + FACEBOOK);
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
        company.setFacebook("http://www.facebook.com" + FACEBOOK);
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
        company.setFacebook("https://www.facebook.com" + FACEBOOK);
        assertEquals(
                company.getFacebook(),
                FACEBOOK
        );
    }

    @Test
    public void whenSetInvalidTwitterThenGetNull() {
        final Company company = getCompany();
        company.setTwitter(null);
        assertNull(company.getTwitter());
        company.setTwitter("");
        assertNull(company.getTwitter());
        company.setTwitter(" ");
        assertNull(company.getTwitter());
        company.setTwitter("   ");
        assertNull(company.getTwitter());
    }

    @Test
    public void whenSetValidTwitterThenGetThisTwitter() {
        final Company company = getCompany();
        company.setTwitter(TWITTER);
        assertNotNull(company.getTwitter());
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
        company.setTwitter("http://" + TWITTER);
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
        company.setTwitter("https://" + TWITTER);
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
        company.setTwitter("mobile.TWITTER.com" + TWITTER);
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
        company.setTwitter("TWITTER.com" + TWITTER);
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
        company.setTwitter("http://mobile.TWITTER.com" + TWITTER);
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
        company.setTwitter("https://mobile.TWITTER.com" + TWITTER);
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
        company.setTwitter("http://TWITTER.com" + TWITTER);
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
        company.setTwitter("https://TWITTER.com" + TWITTER);
        assertEquals(
                company.getTwitter(),
                TWITTER
        );
    }

    @Test
    public void whenSetInvalidSkypeThenGetNull() {
        final Company company = getCompany();
        company.setSkype(null);
        assertNull(company.getSkype());
        company.setSkype("");
        assertNull(company.getSkype());
        company.setSkype(" ");
        assertNull(company.getSkype());
        company.setSkype("   ");
        assertNull(company.getSkype());
    }

    @Test
    public void whenSetValidSkypeThenGetThisSkype() {
        final Company company = getCompany();
        company.setSkype(SKYPE);
        assertNotNull(company.getSkype());
        assertEquals(
                company.getSkype(),
                SKYPE
        );
    }

    @Test
    public void whenSetInvalidAddressThenGetNull() {
        final Company company = getCompany();
        company.setAddress(null);
        assertNull(company.getAddress());
        company.setAddress("");
        assertNull(company.getAddress());
        company.setAddress(" ");
        assertNull(company.getAddress());
        company.setAddress("   ");
        assertNull(company.getAddress());
    }

    @Test
    public void whenSetValidAddressThenGetThisAddress() {
        final Company company = getCompany();
        company.setAddress(ADDRESS);
        assertNotNull(company.getAddress());
        assertEquals(
                company.getAddress(),
                ADDRESS
        );
    }

    @Test
    public void whenSetInvalidWorkTimeFromThenGetZero() {
        Company company = getCompany();
        company.setWorkTimeFrom(null);
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "00:00"
        );
        company.setWorkTimeFrom("");
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "00:00"
        );
        company.setWorkTimeFrom(" ");
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "00:00"
        );
        company.setWorkTimeFrom("  ");
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "00:00"
        );
        company.setWorkTimeFrom(ANY_STRING);
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "00:00"
        );
        company.setWorkTimeFrom("-5620:0.5550");
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "00:00"
        );
    }

    @Test
    public void whenSetValidWorkTimeFromThenGetThisTime() {
        final Company company = getCompany();
        company.setWorkTimeFrom(TIME);
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                TIME
        );
        company.setWorkTimeFrom("25:30");
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "00:30"
        );
        company.setWorkTimeFrom("-25:30");
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "00:30"
        );
        company.setWorkTimeFrom("25:130");
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "02:10"
        );
        company.setWorkTimeFrom("-25:130");
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "02:10"
        );
        company.setWorkTimeFrom("25:-130");
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "00:00"
        );
        company.setWorkTimeFrom("14:35");
        assertNotNull(company.getWorkTimeFrom());
        assertEquals(
                company.getWorkTimeFrom(),
                "14:35"
        );
    }

    @Test
    public void whenSetInvalidWorkTimeToThenGetZero() {
        final Company company = getCompany();
        company.setWorkTimeTo(null);
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "00:00"
        );
        company.setWorkTimeTo("");
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "00:00"
        );
        company.setWorkTimeTo(" ");
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "00:00"
        );
        company.setWorkTimeTo("  ");
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "00:00"
        );
        company.setWorkTimeTo(ANY_STRING);
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "00:00"
        );
        company.setWorkTimeTo("-5620:0.5550");
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "00:00"
        );
    }

    @Test
    public void whenSetValidWorkTimeToThenGetThisTime() {
        final Company company = getCompany();
        company.setWorkTimeTo(TIME);
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                TIME
        );
        company.setWorkTimeTo("25:30");
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "00:30"
        );
        company.setWorkTimeTo("-25:30");
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "00:30"
        );
        company.setWorkTimeTo("25:130");
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "02:10"
        );
        company.setWorkTimeTo("-25:130");
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "02:10"
        );
        company.setWorkTimeTo("25:-130");
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "00:00"
        );
        company.setWorkTimeTo("14:35");
        assertNotNull(company.getWorkTimeTo());
        assertEquals(
                company.getWorkTimeTo(),
                "14:35"
        );
    }

    @Test
    public void whenSetInvalidGoogleMapsThenGetNull() {
        final Company company = getCompany();
        company.setGoogleMaps(null);
        assertNull(company.getGoogleMaps());
        company.setGoogleMaps("");
        assertNull(company.getGoogleMaps());
        company.setGoogleMaps(" ");
        assertNull(company.getGoogleMaps());
        company.setGoogleMaps("   ");
        assertNull(company.getGoogleMaps());
    }

    @Test
    public void whenSetValidGoogleMapsThenGetThisMaps() {
        final Company company = getCompany();
        company.setGoogleMaps(GOOGLE_MAPS);
        assertNotNull(company.getGoogleMaps());
        assertEquals(
                company.getGoogleMaps(),
                GOOGLE_MAPS
        );
    }

    @Test
    public void whenSetInvalidLogoThenGetNull() {
        final Company company = getCompany();
        company.setLogo(null);
        assertNull(company.getLogo());
        company.setLogo(new File());
        assertNull(company.getLogo());
    }

    @Test
    public void whenSetValidLogoThenGetThisLogo() {
        final Company company = getCompany();
        final File logo = getPhoto();
        company.setLogo(logo);
        assertNotNull(company.getLogo());
        assertEquals(
                company.getLogo(),
                logo
        );
    }

    @Test
    public void whenSetInvalidFaviconThenGetNull() {
        final Company company = getCompany();
        company.setFavicon(null);
        assertNull(company.getFavicon());
        company.setFavicon(new File());
        assertNull(company.getFavicon());
    }

    @Test
    public void whenSetValidFaviconThenGetThisFavicon() {
        final Company company = getCompany();
        final File favicon = getPhoto();
        company.setFavicon(favicon);
        assertNotNull(company.getFavicon());
        assertEquals(
                company.getFavicon(),
                favicon
        );
    }

    @Test
    public void whenSlidesAreInvalidThenNotAddThey() {
        final Company company = getCompany();
        company.setSlides(null);
        assertTrue(company.getSlides().isEmpty());
        final List<File> files = new ArrayList<>();
        addInvalidSlides(10, company, files);
        assertTrue(
                company.getSlides().isEmpty()
        );
        company.setSlides(files);
        assertTrue(
                company.getSlides().isEmpty()
        );
        company.addSlides(files);
        assertTrue(
                company.getSlides().isEmpty()
        );
    }

    @Test
    public void whenSlidesAreValidThenAddThey() {
        final Company company = getCompany();

        final List<File> slides = getPhotos(DEFAULT_SIZE);
        company.setSlides(slides);
        assertFalse(
                company.getSlides().isEmpty()
        );
        assertEquals(
                company.getSlides().size(),
                slides.size()
        );
        company.clearSlides();
        assertTrue(
                company.getSlides().isEmpty()
        );
        for (int i = 0; i < slides.size(); i++) {
            company.addSlides(slides);
        }
        assertFalse(
                company.getSlides().isEmpty()
        );
        assertEquals(
                company.getSlides().size(),
                slides.size()
        );
        company.removeSlides(slides);
        assertTrue(
                company.getSlides().isEmpty()
        );
        company.setSlides(slides);
        assertTrue(
                company.containsSlides(slides)
        );
        for (File file : slides) {
            assertTrue(
                    company.containsSlide(file)
            );
            company.removeSlide(file);
            assertFalse(
                    company.containsSlide(file)
            );
        }
    }

    @Test
    public void whenSetInvalidTypeThenGetNull() {
        final Company company = getCompany();
        company.setType(null);
        assertNull(company.getType());
    }

    @Test
    public void whenSetValidTypeThenGetThisType() {
        final Company company = getCompany();
        company.setType(COMPANY_TYPE);
        assertNotNull(company.getType());
        assertEquals(
                company.getType(),
                COMPANY_TYPE
        );
    }

    @Test
    public void isOpen() {
        final Company company = getCompany();
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
    public void whenSetInvalidTitleThenGetNull() {
        final Company company = getCompany();
        company.setTitle(null);
        assertNull(company.getTitle());
        company.setTitle("");
        assertNull(company.getTitle());
        company.setTitle(" ");
        assertNull(company.getTitle());
        company.setTitle("    ");
        assertNull(company.getTitle());
    }

    @Ignore
    @Override
    public void whenSetInvalidUrlThenGetNull() {
    }

    @Test
    public void whenSetInvalidUrlThenGetNotNull() {
        final Company company = new Company();
        final String url = Translator.fromCyrillicToLatin(company.getDomain());
        company.setUrl(null);
        assertNotNull(company.getUrl());
        assertEquals(
                company.getUrl(), url
        );
        company.setUrl("");
        assertNotNull(company.getUrl());
        assertEquals(
                company.getUrl(), url
        );
        company.setUrl(" ");
        assertNotNull(company.getUrl());
        assertEquals(
                company.getUrl(), url
        );
        company.setUrl("   ");
        assertNotNull(company.getUrl());
        assertEquals(
                company.getUrl(), url
        );
    }

    @Test
    public void companyTypeValueOf() {
        for (CompanyType temp : CompanyType.values()) {
            CompanyType type = CompanyType.valueOf(temp.name());
            assertNotNull(type);
            assertEquals(type, temp);
        }
    }

    @Ignore
    @Override
    protected Company getObject() {
        return getCompany();
    }

    @Ignore
    private void addInvalidSlides(
            final int size,
            final Company company,
            final List<File> files
    ) {
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                final File invalidFile = new File();
                files.add(invalidFile);
                company.addSlide(invalidFile);
            } else {
                files.add(null);
                company.addSlide(null);
            }
        }
    }

    @Ignore
    private String companyToString(final Company company) {
        final StringBuilder sb = new StringBuilder();
        sb.append(company.getType().name()).append(" ")
                .append(company.getClass().getSimpleName()).append(" ")
                .append(company.getTitle())
                .append(" \nKeywords: ").append(company.getKeywords())
                .append(" \nURL: ").append(company.getUrl())
                .append(" \nDescription: ").append(company.getDescription())
                .append(" \nTagline: ").append(company.getTagline())
                .append(" \nInformation: ").append(company.getInformation())
                .append(" \nAdvantages: ").append(company.getAdvantages())
                .append(" \nDomain: ").append(company.getDomain())
                .append(" \nMobile Phone: ").append(company.getMobilePhone())
                .append(" \nLandline Phone: ").append(company.getLandlinePhone())
                .append(" \nFax: ").append(company.getFax())
                .append(" \nE-mail:").append(company.getEmail())
                .append(" \nVkontakte: ").append(company.getVkontakte())
                .append(" \nFacebook: ").append(company.getFacebook())
                .append(" \nTwitter: ").append(company.getTwitter())
                .append(" \nSkype: ").append(company.getSkype());
        if (company.getType().equals(CompanyType.MAIN)) {
            sb.append(" \nSenderImpl E-mail: ").append(company.getSenderEmail())
                    .append(" \nSenderImpl Password: ").append(company.getSenderPass())
                    .append(" \nWork Time: ").append(company.getWorkTimeFrom())
                    .append(" - ").append(company.getWorkTimeTo());
        }
        sb.append(" \nAddress: ").append(company.getAddress())
                .append(" \nGoogle Maps: ").append(company.getGoogleMaps());
        return sb.toString();
    }

    @Ignore
    private static boolean isDayAndHour(String workTimeFrom, String workTimeTo) {
        try {
            final Calendar calendar = Calendar.getInstance();
            final int hourNow = calendar.get(Calendar.HOUR_OF_DAY);
            boolean isHour = (hourNow >= getHour(workTimeFrom)) &&
                    (hourNow < getHour(workTimeTo));
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
        return Integer.parseInt(
                time.substring(0, time.indexOf(':'))
        ) + (
                Integer.parseInt(
                        time.substring(time.indexOf(':') + 1)
                ) > 30 ? 1 : 0
        );
    }
}
