package ua.com.ecoteh.entity.address;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelTest;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.MockConstants.VALIDATION;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class AddressTest extends ModelTest {

    private final static String POST_ADDRESS = "Post address";

    private final static String GOOGLE_MAPS = "Google Maps";

    private static Address address;

    @BeforeClass
    public static void setUp() throws Exception {
        address = new Address(ID, VALIDATION, POST_ADDRESS, GOOGLE_MAPS);
    }

    @Test
    public void toStringTest() throws Exception {
        final String testString = "Address{" +
                "Model{id=" + address.getId() +
                ", validated=" + address.isValidated() +
                "}, postAddress='" + address.getPostAddress() + '\'' +
                ", googleMaps='" + address.getGoogleMaps() + '\'' +
                '}';
        assertEquals(address.toString(), testString);
    }

    @Test
    public void getPostAddress() throws Exception {
        assertNotNull(address.getPostAddress());
    }

    @Test
    public void getGoogleMaps() throws Exception {
        assertNotNull(address.getGoogleMaps());
    }

    @Test
    @Override
    public void convert() throws Exception {
        super.convert();
        final AddressEntity entity = address.convert();
        assertTrue(entity.getId() >= 0);
        assertEquals(entity.isValidated(), address.isValidated());
        assertEquals(entity.getPostAddress(), address.getPostAddress());
        assertEquals(entity.getGoogleMaps(), address.getGoogleMaps());
    }

    @Test
    public void getBuilder() throws Exception {
        final AddressBuilder builder = Address.getBuilder();
        assertNotNull(builder);
    }

    @Override
    protected Address getInstance() {
        return address;
    }
}