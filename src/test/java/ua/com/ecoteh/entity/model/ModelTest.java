package ua.com.ecoteh.entity.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.MockConstants.VALIDATION;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelTest {

    @Test
    public void toStringTest() throws Exception {
        final Model model = getInstance();
        final String testString = "Model{id=" + model.getId() +
                ", validated=" + model.isValidated() + '}';
        assertEquals(model.toString(), testString);
    }

    @Test
    public void equalsWithThisObjectThenReturnTrue() throws Exception {
        final Model model = getInstance();
        assertTrue(model.equals(model));
    }

    @Test
    public void equalsWithNull() throws Exception {
        final Model model = getInstance();
        assertFalse(model.equals(null));
    }

    @Test
    public void equalsWithObjectOfTheAnotherClass() throws Exception {
        final Model model = getInstance();
        assertFalse(model.equals(ANY_STRING));
    }

    @Test
    public void equalsTwoObjects() throws Exception {
        final Model first = getInstance();
        final Model second = getInstance().clone();
        for (int i = 0; i < 5; i++) {
            assertTrue(first.equals(second));
            assertTrue(second.equals(first));
        }
    }

    @Test
    public void equalsThreeObjects() throws Exception {
        final Model first = getInstance();
        final Model second = getInstance().clone();
        final Model third = getInstance().clone();
        for (int i = 0; i < 5; i++) {
            assertTrue(first.equals(second));
            assertTrue(second.equals(third));
            assertTrue(third.equals(first));
        }
    }

    @Test
    public void hashCodeTest() throws Exception {
        final Model model = getInstance();
        int hashCode = model.hashCode();
        for (int i = 0; i < 5; i++) {
            int temp = model.hashCode();
            assertEquals(temp, hashCode);
            hashCode = temp;
        }
    }

    @Test
    public void cloneTest() throws Exception {
        final Model model = getInstance();
        final Model clone = model.clone();
        assertTrue(model != clone);
        assertEquals(model, clone);
    }

    @Test
    public void getId() throws Exception {
        final Model model = getInstance();
        assertEquals(model.getId(), ID);
    }

    @Test
    public void isValidated() throws Exception {
        final Model model = getInstance();
        assertEquals(model.isValidated(), VALIDATION);
    }

    @Test
    public void getEditor() throws Exception {
        final Model model = getInstance();
        assertNotNull(model.getEditor());
    }

    @Test
    public void convert() throws Exception {
        final Model model = getInstance();
        assertNotNull(model.convert());
    }

    @Test
    public void getInstanceMethodReturnsNotNull() {
        final Model model = getInstance();
        assertNotNull(model);
    }

    protected abstract Model getInstance();
}