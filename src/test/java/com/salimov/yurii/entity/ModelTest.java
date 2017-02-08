package com.salimov.yurii.entity;

import org.junit.Ignore;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public abstract class ModelTest<T extends Model<Long>> {

    @Test
    public void equalsValidObjects() {
        final T model1 = getObject();
        final T model2 = (T) model1.clone();
        final T model3 = (T) model2.clone();
        for (int i = 0; i < 10; i++) {
            assertTrue(model1.equals(model1));
            assertTrue(model1.equals(model2));
            assertTrue(model2.equals(model1));
            assertTrue(model1.equals(model2));
            assertTrue(model2.equals(model3));
            assertTrue(model1.equals(model3));
            assertFalse(model1.equals(null));
            assertFalse(model2.equals(null));
            assertFalse(model3.equals(null));
        }
        final boolean value = (model1 == model2) ||
                (model1.getClass() == model2.getClass());
        assertEquals(model1.equals(model2), value);
    }

    @Test
    public void hashCodeValidObject() {
        final T model = getObject();
        int hash1 = model.hashCode();
        for (int i = 0; i < 10; i++) {
            int hash2 = model.hashCode();
            assertEquals(hash1, hash2);
            hash1 = hash2;
        }
    }

    @Test
    public void whenSetInvalidIdThenGetNull() {
        final T model = getObject();
        model.setId(null);
        assertNull(model.getId());
    }

    @Test
    public void whenSetValidIdThenGetThisId() {
        final T model = getObject();
        final Long id = 1234L;
        model.setId(id);
        assertNotNull(model.getId());
        assertEquals(model.getId(), id);
    }

    @Test
    public void validObject() {
        assertFalse(Model.isValidated(null));
        final T model = getObject();
        assertEquals(
                Model.isValidated(model),
                model != null
        );
    }

    @Test
    public void whenCreateRandomStringWithNullPatternThenReturnBlankString() {
        assertTrue(
                isBlank(
                        Model.createRandomString(
                                null, Model.CODE_LENGTH
                        )
                )
        );
    }

    @Test
    public void whenCreateRandomStringWithEmptyPatternThenReturnBlankString() {
        assertTrue(
                isBlank(
                        Model.createRandomString(
                                new char[0], Model.CODE_LENGTH
                        )
                )
        );
    }

    @Test
    public void whenCreateRandomStringWithZeroLengthThenReturnBlankString() {
        assertTrue(
                isBlank(
                        Model.createRandomString(
                                Model.CODE_PATTERN, 0
                        )
                )
        );
    }

    @Test
    public void whenCreateRandomStringWithNegativeLengthThenReturnBlankString() {
        assertTrue(
                isBlank(
                        Model.createRandomString(
                                Model.CODE_PATTERN, -1
                        )
                )
        );
    }

    @Test
    public void whenCreateRandomStringWithValidParametersThenReturnSomeValue() {
        String value1 = Model.createRandomString(
                Model.CODE_PATTERN,
                Model.CODE_LENGTH
        );
        assertTrue(isNotBlank(value1));
        for (int i = 0; i < 10; i++) {
            String value2 = Model.createRandomString(
                    Model.CODE_PATTERN,
                    Model.CODE_LENGTH
            );
            assertTrue(isNotBlank(value2));
            assertFalse(value1.equals(value2));
            value1 = value2;
        }
    }

    @Test
    public void whenCreateRandomNumberWithNegativeLengthThenReturnZero() {
        assertTrue(
                Model.createRandomNumber(-1) == 0
        );
    }

    @Test
    public void whenCreateRandomNumberWithEqualsZeroThenReturnZero() {
        assertTrue(Model.createRandomNumber(0) == 0);
    }

    @Test
    public void whenCreateRandomNumberWithPositiveLengthThenReturnSomeNumber() {
        assertTrue(Model.createRandomNumber(Model.CODE_LENGTH) != 0);
    }

    @Test
    public void whenCreateRandomNumberThenAlwaysReturnSomeRandomNumber() {
        for (int i = 0; i < 10; i++) {
            assertTrue(
                    Model.createRandomNumber(Model.CODE_LENGTH) !=
                            Model.createRandomNumber(Model.CODE_LENGTH)
            );
        }
    }

    @Test
    public void whenGetDateToStringWithNullDateThenReturnNewDate() {
        assertTrue(
                isNotBlank(
                        Model.getDateToString(null)
                )
        );
    }

    @Test
    public void whenGetDateToStringWithNotNullDateThenReturnThisDate() {
        final Date date = new Date();
        final DateFormat dateFormat = new SimpleDateFormat(Model.DATE_PATTERN);
        dateFormat.setTimeZone(
                TimeZone.getTimeZone(Model.TIME_ZONE)
        );
        final String dateString1 = dateFormat.format(date);
        final String dateString2 = Model.getDateToString(date);
        assertTrue(isNotBlank(dateString2));
        assertEquals(dateString1, dateString2);
    }

    @Test
    public void cloneTest() {
        final T model = getObject();
        final T clone = (T) model.clone();
        assertFalse(model == clone);
        assertEquals(model, clone);
    }

    @Test
    public void validCodeLengthConstants() {
        assertNotNull(Model.CODE_LENGTH);
        assertNotNull(getObject().CODE_LENGTH);
    }

    @Test
    public void validCodePatternConstants() {
        assertNotNull(Model.CODE_PATTERN);
        assertNotNull(getObject().CODE_PATTERN);
    }

    @Test
    public void validDatePatternConstants() {
        assertNotNull(Model.DATE_PATTERN);
        assertNotNull(getObject().DATE_PATTERN);
    }

    @Test
    public void validTimeZoneConstants() {
        assertNotNull(Model.TIME_ZONE);
        assertNotNull(getObject().TIME_ZONE);
    }

    @Test
    public void whenGetObjectThenReturnNotNull() {
        assertNotNull(getObject());
    }

    @Ignore
    protected abstract T getObject();
}
