package com.salimov.ecoteh.mocks;

import com.salimov.ecoteh.enums.CompanyType;
import com.salimov.ecoteh.enums.UserRole;
import org.junit.Ignore;

import java.util.Date;

@Ignore
public interface MockConstants {
    int DEFAULT_SIZE = 10;
    int INDEX = 1;
    long ID = 1L;
    long UNKNOWN_ID = -1L;
    String TITLE = "It`s a title of some content.";
    String URL = "url";
    String DESCRIPTION = "description";
    String TEXT = "text";
    String KEYWORDS = "keywords, key, words";
    String NUMBER = "NuMbEr2016";
    String NAME = "Yurii Salimov";
    String LOGIN = "login";
    String PASSWORD = "password";
    String EMAIL = "my_email@gmail.com";
    String PHONE = "+380000000000";
    String VKONTAKTE = "vkontakte";
    String FACEBOOK = "facebook";
    String TWITTER = "twitter";
    String SKYPE = "skype";
    String DOMAIN = "domain.com";
    String TAGLINE = "It`s the best web-site.";
    String INFORMATION = "I am a information about some content.";
    String ADDRESS = "Ukraine, Kyiv, Some Str., 51";
    String TIME = "12:00";
    String GOOGLE_MAPS = "google maps";
    String ANY_STRING = "Some unknown text.";
    String PATH = "c:/some_dir/file.txt";
    String FILE_TYPE = "OTHER";
    String PRICE = "500 $";
    Date DATE = new Date();
    CompanyType COMPANY_TYPE = CompanyType.PARTNER;
    UserRole USER_ROLE = UserRole.ADMIN;
}
