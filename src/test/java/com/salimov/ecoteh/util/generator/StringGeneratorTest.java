package com.salimov.ecoteh.util.generator;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class StringGeneratorTest {

    @Test
    public void whenGenerateByDefaultConstructorThenReturnSomeString() {
        assertNotNull(new StringGenerator().generate());
    }

    @Test
    public void whenGenerateByConstructorWithLengthThenReturnSomeString() {
        assertNotNull(new StringGenerator(10).generate());
    }

    @Test
    public void whenGenerateByConstructorWithNegativeLengthThenReturnSomeString() {
        assertNotNull(new StringGenerator(-10).generate());
    }

    @Test
    public void whenGenerateByConstructorWithPatternThenReturnSomeString() {
        assertNotNull(new StringGenerator("ABCDEFGHIJKL".toCharArray()).generate());
    }

    @Test
    public void whenGenerateByConstructorWithNullPatternThenReturnSomeString() {
        assertNotNull(new StringGenerator(null).generate());
    }

    @Test
    public void whenGenerateByConstructorWithEmptyPatternThenReturnSomeString() {
        assertNotNull(new StringGenerator(new char[]{}).generate());
    }

    @Test
    public void whenGenerateByConstructorWithLengthAndPatternThenReturnSomeString() {
        assertNotNull(new StringGenerator("ABCDEFGHIJKL".toCharArray(), 10).generate());
    }

    @Test
    public void whenGenerateByConstructorWithNegativeLengthAndPatternThenReturnSomeString() {
        assertNotNull(new StringGenerator("ABCDEFGHIJKL".toCharArray(), -10).generate());
    }

    @Test
    public void whenGenerateByConstructorWithLengthAndNullPatternThenReturnSomeString() {
        assertNotNull(new StringGenerator(null, -10).generate());
    }

    @Test
    public void whenGenerateByConstructorWithLengthAndEmptyPatternThenReturnSomeString() {
        assertNotNull(new StringGenerator(new char[]{}, -10).generate());
    }

    @Test
    public void alwaysCreateNewLine() {
        final Generator<String> generator = new StringGenerator();
        for (int i = 0; i < 10; i++) {
            assertNotEquals(generator.generate(), generator.generate());
        }
    }

    @Test
    public void whenGetThenReturnNotNull() {
        assertNotNull(new StringGenerator().get());
    }
}