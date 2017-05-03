package ua.com.ecoteh.util.time;

import org.junit.Assert;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;

import java.util.Date;

import static org.junit.Assert.*;

public final class TimeTest {

    @Test
    public void whenInitNotBlankTimeTimeThenGetNotBlankTime() {
        final Time time = new Time(MockConstants.TIME);
        assertNotNull(time.getTime());
    }

    @Test
    public void whenInitNullTimeTimeThenGetNotNull() {
        final Time time = new Time(null);
        assertNotNull(time.getTime());
    }

    @Test
    public void whenSetNullTimeThenGetCorrectTime() {
        assertEquals(new Time(null).getCorrectTime(), "00:00");
    }

    @Test
    public void whenSetInvalidTimeThenGetCorrectTime() {
        assertEquals(new Time(MockConstants.ANY_STRING).getCorrectTime(), "00:00");
    }

    @Test
    public void whenSetTimeThenGetCorrectTime() {
        Assert.assertEquals(
                new Time(MockConstants.TIME).getCorrectTime(),
                MockConstants.TIME
        );
        assertNotNull(new Time("-12:00").getCorrectTime());
        assertNotNull(new Time("435:234").getCorrectTime());
        assertNotNull(new Time("435:-234").getCorrectTime());
        assertNotNull(new Time("8:4").getCorrectTime());
        assertNotNull(new Time("08:04").getCorrectTime());
    }

    @Test
    public void whenSetInvalidTimeThenGetZeroHours() {
        assertTrue(new Time(null).getHours() == 0);
        assertTrue(new Time(MockConstants.ANY_STRING).getHours() == 0);
        assertTrue(new Time("121:00").getHours() == 0);
        assertTrue(new Time("-12:00").getHours() == 0);
    }

    @Test
    public void whenSetTimeThenGetHours() {
        assertEquals(new Time("12:00").getHours(), 12);
        assertEquals(new Time("12:65").getHours(), 13);
    }

    @Test
    public void whenSetInvalidTimeThenGetZeroMinutes() {
        assertTrue(new Time(null).getMinutes() == 0);
        assertTrue(new Time(MockConstants.ANY_STRING).getMinutes() == 0);
        assertTrue(new Time("00:-65").getMinutes() == 0);
    }

    @Test
    public void whenSetTimeThenGetMinutes() {
        assertEquals(new Time("00:32").getMinutes(), 32);
        assertEquals(new Time("00:-32").getMinutes(), 0);
        assertEquals(new Time("00:65").getMinutes(), 5);
    }

    @Test
    public void whenCheckNullCurrentTimeThenReturnFalse() {
        assertFalse(Time.checkTime(null, new Date(), new Date()));
    }

    @Test
    public void whenCheckNullStartTimeThenReturnFalse() {
        assertFalse(Time.checkTime(new Date(), null, new Date()));
    }

    @Test
    public void whenCheckNullFinishTimeThenReturnFalse() {
        assertFalse(Time.checkTime(new Date(), new Date(), null));
    }

    @Test
    public void whenCheckTimeThenReturnTrue() {
        final Date startDate = new Date();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date currentDate = new Date();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date finishDate = new Date();
        assertTrue(
                Time.checkTime(currentDate, startDate, finishDate)
        );
    }

    @Test
    public void whenCheckNullStartDateThenReturnFalse() {
        assertFalse(Time.checkDate(null, new Date()));
    }

    @Test
    public void whenCheckNullFinishDateThenReturnFalse() {
        assertFalse(Time.checkDate(new Date(), null));
    }

    @Test
    public void whenCheckDateWithStartDateGreatFinishDateThenReturnFalse() {
        final Date finishDate = new Date();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date startDate = new Date();
        assertFalse(Time.checkDate(startDate, finishDate));
    }

    @Test
    public void whenCheckDateThenReturnTrue() {
        final Date startDate = new Date();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date finishDate = new Date();
        assertTrue(Time.checkDate(startDate, finishDate));
    }

    @Test
    public void whenIsWorkTimeWithNullHoursThenReturnFalse() {
        assertFalse(Time.isWorkTime(null, null));
    }

    @Test
    public void whenIsWorkTimeWithNullStartHourThenReturnTrue() {
        Time.isWorkTime("09:00", "18:45");
    }

    @Test
    public void whenIsWorkTimeWithNullFinishHourThenReturnFalse() {
        assertFalse(Time.isWorkTime(MockConstants.TIME, null));
    }

    @Test
    public void whenIsWorkTimeWithStartHourGreatFinishHourThenReturnFalse() {
        assertFalse(Time.isWorkTime("20:00", "01:00"));
    }

    @Test
    public void whenIsWorkTimeThenReturnTrue() {
        Time.isWorkTime("00:00", "23:59");
    }
}
