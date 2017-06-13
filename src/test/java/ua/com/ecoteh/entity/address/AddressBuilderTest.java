package ua.com.ecoteh.entity.address;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelBuilderTest;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.ADDRESS;
import static ua.com.ecoteh.mocks.MockConstants.GOOGLE_MAPS;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class AddressBuilderTest extends ModelBuilderTest<Address> {

    private AddressBuilder builder;

    @Before
    public void beforeTests() {
        this.builder = new AddressBuilder();
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Address address = this.builder.build();
        assertTrue(address.getId() >= 0);
        assertNotNull(address.getPostAddress());
        assertNotNull(address.getGoogleMaps());
    }

    @Test
    public void whenAddPostAddressThenBuildWithIt() {
        Address address;
        String postAddress;
        for (int i = 0; i < 5; i++) {
            postAddress = ADDRESS + i;
            this.builder.addPostAddress(postAddress);
            address = this.builder.build();
            assertEquals(address.getPostAddress(), postAddress);
        }
    }

    @Test
    public void whenAddNullPostAddressThenBuildWithEmptyIt() {
        this.builder.addPostAddress(null);
        final Address address = this.builder.build();
        assertTrue(address.getPostAddress().isEmpty());
    }

    @Test
    public void whenAddGoogleMapsThenBuildWithIt() {
        Address address;
        String googleMaps;
        for (int i = 0; i < 5; i++) {
            googleMaps = GOOGLE_MAPS + i;
            this.builder.addGoogleMaps(googleMaps);
            address = this.builder.build();
            assertEquals(address.getGoogleMaps(), googleMaps);
        }
    }

    @Test
    public void whenAddNullGoogleMapsThenBuildWithEmptyIt() {
        this.builder.addGoogleMaps(null);
        final Address address = this.builder.build();
        assertTrue(address.getGoogleMaps().isEmpty());
    }

    @Override
    protected AddressBuilder getBuilder() {
        return this.builder;
    }
}