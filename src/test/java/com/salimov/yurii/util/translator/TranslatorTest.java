package com.salimov.yurii.util.translator;

import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.INFORMATION;
import static org.junit.Assert.*;

public final class TranslatorTest {

    @Test
    public void whenTranslateNullValueFromCyrillicToLatinThenReturnNotNull() {
        assertNotNull(
                Translator.fromCyrillicToLatin(null)
        );
    }

    @Test
    public void whenTranslateInvalidValueFromCyrillicToLatinThenReturnNotNull() {
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
    public void whenTranslateNullValueToAsciiThenReturnNull() {
        assertNull(
                Translator.toAscii(null)
        );
    }

    @Test
    public void whenTranslateInvalidValueToAsciiThenReturnNull() {
        assertNull(
                Translator.toAscii("")
        );
        assertNull(
                Translator.toAscii(" ")
        );
        assertNull(
                Translator.toAscii("   ")
        );
    }

    @Test
    public void whenTranslateValidValueToAsciiThenReturnTranslateValue() {
        assertNotNull(
                Translator.toAscii(INFORMATION)
        );
    }

    @Test
    public void whenTranslateValidIntegerToAsciiThenReturnTranslateValue() {
        assertNotNull(
                Translator.toAscii(123)
        );
    }

    @Test
    public void whenTranslateNullValueFromAsciiThenReturnNull() {
        assertNull(
                Translator.fromAscii(null)
        );
    }

    @Test
    public void whenTranslateInvalidValueFromAsciiThenReturnNull() {
        assertNull(
                Translator.fromAscii("")
        );
        assertNull(
                Translator.fromAscii(" ")
        );
        assertNull(
                Translator.fromAscii("   ")
        );
    }

    @Test
    public void whenTranslateValidValueFromAsciiThenReturnTranslateValue() {
        assertNotNull(
                Translator.fromAscii("123")
        );
    }

    @Test
    public void whenTranslateValidIntegerFromAsciiThenReturnTranslateValue() {
        assertNotNull(
                Translator.fromAscii(123)
        );
    }

    @Test
    public void whenTranslateValidValueToAsciiAndBackThenEqualsResults() {
        final String temp = Translator.toAscii(INFORMATION);
        assertNotNull(temp);
        final String result = Translator.fromAscii(temp);
        assertNotNull(result);
        assertEquals(
                INFORMATION,
                result
        );
    }
}
