package com.salimov.yurii.util;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.util.translator.Translator;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public final class TranslatorTest {

    @Test
    public void whenTranslateInvalidValueFromCyrillicToLatinThenReturnNotNull() {
        assertNotNull(
                Translator.fromCyrillicToLatin(null)
        );
        assertNotNull(
                Translator.fromCyrillicToLatin("")
        );
        assertNotNull(
                Translator.fromCyrillicToLatin(" ")
        );
        assertNotNull(
                Translator.fromCyrillicToLatin("   ")
        );
    }

    @Test
    public void whenTranslateValidValueFromCyrillicToLatinThenReturnTranslateValue() {
        String value = "some text";
        String result = Translator.fromCyrillicToLatin(value);
        value = "some_text";
        assertNotNull(result);
        assertEquals(result, value);

        value = "SoMe TeXt";
        result = Translator.fromCyrillicToLatin(value);
        value = "some_text";
        assertEquals(result, value);

        value = "некоторой текст";
        result = Translator.fromCyrillicToLatin(value);
        value = "nekotoroy_tekst";
        assertEquals(result, value);
    }

    @Test
    public void whenTranslateInvalidValueToAsciiThenReturnNotNull() {
        assertNotNull(
                Translator.toAscii(null)
        );
        assertNotNull(
                Translator.toAscii("")
        );
        assertNotNull(
                Translator.toAscii(" ")
        );
        assertNotNull(
                Translator.toAscii("   ")
        );
    }

    @Test
    public void whenTranslateValidValueToAsciiThenReturnTranslateValue() {
        assertNotNull(
                Translator.toAscii(
                        MockConstants.INFORMATION
                )
        );
    }

    @Test
    public void whenTranslateValidValueToAsciiAndBackThenEqualsResults() {
        final String temp = Translator.toAscii(MockConstants.INFORMATION);
        assertNotNull(temp);
        final String result = Translator.fromAscii(temp);
        assertNotNull(result);
        Assert.assertEquals(
                MockConstants.INFORMATION,
                result
        );
    }
}
