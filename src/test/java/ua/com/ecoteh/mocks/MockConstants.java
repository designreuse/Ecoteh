package ua.com.ecoteh.mocks;

import ua.com.ecoteh.entity.company.CompanyType;
import ua.com.ecoteh.entity.file.FileType;
import ua.com.ecoteh.entity.user.UserRole;
import org.junit.Ignore;

import java.util.Date;

@Ignore
public interface MockConstants {
    int DEFAULT_SIZE = 10;
    int INDEX = 1;
    long ID = 1L;
    boolean VALIDATION = true;
    boolean MAILING = true;
    boolean LOCKED = false;
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
    String PHONE = "+380000000001";
    String PHONE_2 = "+380000000002";
    String FAX = "+380000000003";
    String VKONTAKTE = "vkontakte";
    String FACEBOOK = "facebook";
    String TWITTER = "twitter";
    String SKYPE = "skype";
    String DOMAIN = "domain.com";
    String TAGLINE = "It`s the best web-site.";
    String INFORMATION = "I am a information about some content.";
    String ADDRESS = "Ukraine, Kyiv, Some Str., 51";
    String TIME = "00:00";
    String GOOGLE_MAPS = "google maps";
    String ANY_STRING = "Some unknown text.";
    String PATH = "c:/some_dir/file.txt";
    String STYLE = "Some CSS styles";
    double PRICE = 500;
    String CURRENCY = "$";
    Date DATE = new Date();
    CompanyType COMPANY_TYPE = CompanyType.PARTNER;
    UserRole USER_ROLE = UserRole.ADMIN;
    FileType FILE_TYPE = FileType.ANOTHER;
}
