package ua.com.ecoteh.entity.address;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEntityTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class AddressEntityTest extends ModelEntityTest {

    private AddressEntity address;

    @Before
    public void beforeTest() {
        this.address = new AddressEntity();
        this.address.setId(ID);
        this.address.setValidated(VALIDATION);
        this.address.setPostAddress(ADDRESS);
        this.address.setGoogleMaps(GOOGLE_MAPS);
    }

    @Test
    @Override
    public void toStringTest() {
        final String toStringTest = "AddressEntity{" +
                "ModelEntity{" +
                "id=" + this.address.getId() +
                ", validated=" + this.address.isValidated() +
                '}' +
                ", postAddress='" + this.address.getPostAddress() + '\'' +
                ", googleMaps='" + this.address.getGoogleMaps() + '\'' +
                '}';
        assertEquals(this.address.toString(), toStringTest);
    }

    @Test
    public void whenSetPostAddressThenReturnIt() {
        String postAddress;
        for (int i = 0; i < 5; i++) {
            postAddress = ADDRESS + i;
            this.address.setPostAddress(postAddress);
            assertEquals(this.address.getPostAddress(), postAddress);
        }
    }

    @Test
    public void whenSetGoogleMapsThenReturnIt() {
        String googleMaps;
        for (int i = 0; i < 5; i++) {
            googleMaps = GOOGLE_MAPS + i;
            this.address.setGoogleMaps(googleMaps);
            assertEquals(this.address.getGoogleMaps(), googleMaps);
        }
    }

    @Test
    public void whenConvertThenReturnValidModel() {
        final Address address = this.address.convert();
        assertEquals(address.getId(), this.address.getId());
        assertEquals(address.isValidated(), this.address.isValidated());
        assertEquals(address.getPostAddress(), this.address.getPostAddress());
        assertEquals(address.getGoogleMaps(), this.address.getGoogleMaps());
    }

    @Override
    protected AddressEntity getInstance() {
        return this.address;
    }
}