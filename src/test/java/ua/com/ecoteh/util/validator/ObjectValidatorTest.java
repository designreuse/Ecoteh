package ua.com.ecoteh.util.validator;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class ObjectValidatorTest {

    @Test
    public void isNull() throws Exception {
        assertTrue(ObjectValidator.isNull(null));
        assertFalse(ObjectValidator.isNull(new Object()));
    }

    @Test
    public void isNotNull() throws Exception {
        assertTrue(ObjectValidator.isNotNull(new Object()));
        assertFalse(ObjectValidator.isNotNull(null));
    }

    @Test
    public void isEmptyCollection() throws Exception {
        assertTrue(ObjectValidator.isEmpty((Collection) null));
        assertTrue(ObjectValidator.isEmpty(new ArrayList()));
        assertTrue(ObjectValidator.isEmpty(new LinkedList()));
        assertTrue(ObjectValidator.isEmpty(new HashSet()));
        assertTrue(ObjectValidator.isEmpty(new TreeSet()));

        Collection<String> collection = new ArrayList<>();
        String object = "string;";
        collection.add(object);
        assertFalse(ObjectValidator.isEmpty(collection));

        collection = new LinkedList<>();
        collection.add(object);
        assertFalse(ObjectValidator.isEmpty(collection));

        collection = new HashSet<>();
        collection.add(object);
        assertFalse(ObjectValidator.isEmpty(collection));

        collection = new TreeSet<>();
        collection.add(object);
        assertFalse(ObjectValidator.isEmpty(collection));
    }

    @Test
    public void isNotEmptyCollection() throws Exception {
        assertFalse(ObjectValidator.isNotEmpty((Collection) null));
        assertFalse(ObjectValidator.isNotEmpty(new ArrayList()));
        assertFalse(ObjectValidator.isNotEmpty(new LinkedList()));
        assertFalse(ObjectValidator.isNotEmpty(new HashSet()));
        assertFalse(ObjectValidator.isNotEmpty(new TreeSet()));

        Collection<String> collection = new ArrayList<>();
        String object = "string;";
        collection.add(object);
        assertTrue(ObjectValidator.isNotEmpty(collection));

        collection = new LinkedList<>();
        collection.add(object);
        assertTrue(ObjectValidator.isNotEmpty(collection));

        collection = new HashSet<>();
        collection.add(object);
        assertTrue(ObjectValidator.isNotEmpty(collection));

        collection = new TreeSet<>();
        collection.add(object);
        assertTrue(ObjectValidator.isNotEmpty(collection));
    }

    @Test
    public void isEmptyMap() throws Exception {
        assertTrue(ObjectValidator.isEmpty((Map) null));
        assertTrue(ObjectValidator.isEmpty(new HashMap()));
        assertTrue(ObjectValidator.isEmpty(new TreeMap()));

        Map<String, String> map = new HashMap<>();
        String object = "string;";
        map.put(object, object);
        assertFalse(ObjectValidator.isEmpty(map));

        map = new TreeMap<>();
        map.put(object, object);
        assertFalse(ObjectValidator.isEmpty(map));
    }

    @Test
    public void isNotEmptyMap() throws Exception {
        assertFalse(ObjectValidator.isNotEmpty((Map) null));
        assertFalse(ObjectValidator.isNotEmpty(new HashMap()));
        assertFalse(ObjectValidator.isNotEmpty(new TreeMap()));

        Map<String, String> map = new HashMap<>();
        String object = "string;";
        map.put(object, object);
        assertTrue(ObjectValidator.isNotEmpty(map));

        map = new TreeMap<>();
        map.put(object, object);
        assertTrue(ObjectValidator.isNotEmpty(map));
    }

    @Test
    public void isEmptyString() throws Exception {
        assertTrue(ObjectValidator.isEmpty((String) null));
        assertTrue(ObjectValidator.isEmpty(""));
        assertTrue(ObjectValidator.isEmpty("   "));
        assertTrue(ObjectValidator.isEmpty("          "));
        assertFalse(ObjectValidator.isEmpty("string"));
    }

    @Test
    public void isNotEmptyString() throws Exception {
        assertFalse(ObjectValidator.isNotEmpty((String) null));
        assertFalse(ObjectValidator.isNotEmpty(""));
        assertFalse(ObjectValidator.isNotEmpty("   "));
        assertFalse(ObjectValidator.isNotEmpty("          "));
        assertTrue(ObjectValidator.isNotEmpty("string"));
    }

    @Test
    public void isEmptyMultipartFile() throws Exception {
        assertTrue(ObjectValidator.isEmpty((MultipartFile) null));
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);
        assertFalse(ObjectValidator.isEmpty(file));
        when(file.isEmpty()).thenReturn(true);
        assertTrue(ObjectValidator.isEmpty(file));
    }

    @Test
    public void isNotEmptyMultipartFile() throws Exception {
        assertFalse(ObjectValidator.isNotEmpty((MultipartFile) null));
        assertTrue(ObjectValidator.isNotEmpty(mock(MultipartFile.class)));
    }

    @Test
    public void whenCallIsEmptyForObjectsArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isEmpty((Object[]) null));
        assertTrue(ObjectValidator.isEmpty(new Object[] {}));
        assertTrue(ObjectValidator.isEmpty(new Object[0]));
    }

    @Test
    public void whenCallIsEmptyForEmptyObjectsArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isEmpty(new Object[10]));
        assertFalse(ObjectValidator.isEmpty(new Object[] { new Object() }));
        assertFalse(ObjectValidator.isEmpty(new Object[] { new Object(), new Object() }));
    }

    @Test
    public void whenCallIsNotEmptyForObjectsArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isNotEmpty(new Object[10]));
        assertTrue(ObjectValidator.isNotEmpty(new Object[] { new Object() }));
        assertTrue(ObjectValidator.isNotEmpty(new Object[] { new Object(), new Object() }));
    }

    @Test
    public void whenCallIsNotEmptyForEmptyObjectsArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isNotEmpty((Object[]) null));
        assertFalse(ObjectValidator.isNotEmpty(new Object[] {}));
        assertFalse(ObjectValidator.isNotEmpty(new Object[0]));
    }

    @Test
    public void whenCallIsEmptyForByteArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isEmpty((byte[]) null));
        assertTrue(ObjectValidator.isEmpty(new byte[] {}));
        assertTrue(ObjectValidator.isEmpty(new byte[0]));
    }

    @Test
    public void whenCallIsEmptyForEmptyByteArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isEmpty(new byte[10]));
        assertFalse(ObjectValidator.isEmpty(new byte[] { 1 }));
        assertFalse(ObjectValidator.isEmpty(new byte[] { 1, 2 }));
    }

    @Test
    public void whenCallIsNotEmptyForByteArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isNotEmpty(new byte[10]));
        assertTrue(ObjectValidator.isNotEmpty(new byte[] { 1 }));
        assertTrue(ObjectValidator.isNotEmpty(new byte[] { 1, 2 }));
    }

    @Test
    public void whenCallIsNotEmptyForEmptyByteArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isNotEmpty((byte[]) null));
        assertFalse(ObjectValidator.isNotEmpty(new byte[] {}));
        assertFalse(ObjectValidator.isNotEmpty(new byte[0]));
    }

   @Test
   public void whenCallIsEmptyForShortArrayThenReturnTrue() {
       assertTrue(ObjectValidator.isEmpty((short[]) null));
       assertTrue(ObjectValidator.isEmpty(new short[] {}));
       assertTrue(ObjectValidator.isEmpty(new short[0]));
   }

    @Test
    public void whenCallIsEmptyForEmptyShortArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isEmpty(new short[10]));
        assertFalse(ObjectValidator.isEmpty(new short[] { 1 }));
        assertFalse(ObjectValidator.isEmpty(new short[] { 1, 2 }));
    }

    @Test
    public void whenCallIsNotEmptyForShortArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isNotEmpty(new short[10]));
        assertTrue(ObjectValidator.isNotEmpty(new short[] { 1 }));
        assertTrue(ObjectValidator.isNotEmpty(new short[] { 1, 2 }));
    }

    @Test
    public void whenCallIsNotEmptyForEmptyShortArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isNotEmpty((short[]) null));
        assertFalse(ObjectValidator.isNotEmpty(new short[] {}));
        assertFalse(ObjectValidator.isNotEmpty(new short[0]));
    }

    @Test
    public void whenCallIsEmptyForCharArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isEmpty((char[]) null));
        assertTrue(ObjectValidator.isEmpty(new char[] {}));
        assertTrue(ObjectValidator.isEmpty(new char[0]));
    }

    @Test
    public void whenCallIsEmptyForEmptyCharArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isEmpty(new char[10]));
        assertFalse(ObjectValidator.isEmpty(new char[] { 1 }));
        assertFalse(ObjectValidator.isEmpty(new char[] { 1, 2 }));
    }

    @Test
    public void whenCallIsNotEmptyForCharArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isNotEmpty(new char[10]));
        assertTrue(ObjectValidator.isNotEmpty(new char[] { 1 }));
        assertTrue(ObjectValidator.isNotEmpty(new char[] { 1, 2 }));
    }

    @Test
    public void whenCallIsNotEmptyForEmptyCharArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isNotEmpty((char[]) null));
        assertFalse(ObjectValidator.isNotEmpty(new char[] {}));
        assertFalse(ObjectValidator.isNotEmpty(new char[0]));
    }

    @Test
    public void whenCallIsEmptyForIntArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isEmpty((int[]) null));
        assertTrue(ObjectValidator.isEmpty(new int[] {}));
        assertTrue(ObjectValidator.isEmpty(new int[0]));
    }

    @Test
    public void whenCallIsEmptyForEmptyIntArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isEmpty(new int[10]));
        assertFalse(ObjectValidator.isEmpty(new int[] { 1 }));
        assertFalse(ObjectValidator.isEmpty(new int[] { 1, 2 }));
    }

    @Test
    public void whenCallIsNotEmptyForIntArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isNotEmpty(new int[10]));
        assertTrue(ObjectValidator.isNotEmpty(new int[] { 1 }));
        assertTrue(ObjectValidator.isNotEmpty(new int[] { 1, 2 }));
    }

    @Test
    public void whenCallIsNotEmptyForEmptyIntArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isNotEmpty((int[]) null));
        assertFalse(ObjectValidator.isNotEmpty(new int[] {}));
        assertFalse(ObjectValidator.isNotEmpty(new int[0]));
    }

    @Test
    public void whenCallIsEmptyForLongArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isEmpty((long[]) null));
        assertTrue(ObjectValidator.isEmpty(new long[] {}));
        assertTrue(ObjectValidator.isEmpty(new long[0]));
    }

    @Test
    public void whenCallIsEmptyForEmptyLongArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isEmpty(new long[10]));
        assertFalse(ObjectValidator.isEmpty(new long[] { 1L }));
        assertFalse(ObjectValidator.isEmpty(new long[] { 1L, 2L }));
    }

    @Test
    public void whenCallIsNotEmptyForLongArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isNotEmpty(new long[10]));
        assertTrue(ObjectValidator.isNotEmpty(new long[] { 1L }));
        assertTrue(ObjectValidator.isNotEmpty(new long[] { 1L, 2L }));
    }

    @Test
    public void whenCallIsNotEmptyForEmptyLongArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isNotEmpty((long[]) null));
        assertFalse(ObjectValidator.isNotEmpty(new long[] {}));
        assertFalse(ObjectValidator.isNotEmpty(new long[0]));
    }

    @Test
    public void whenCallIsEmptyForFloatArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isEmpty((float[]) null));
        assertTrue(ObjectValidator.isEmpty(new float[] {}));
        assertTrue(ObjectValidator.isEmpty(new float[0]));
    }

    @Test
    public void whenCallIsEmptyForEmptyFloatArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isEmpty(new float[10]));
        assertFalse(ObjectValidator.isEmpty(new float[] { 1.2345F }));
        assertFalse(ObjectValidator.isEmpty(new float[] { 1.2345F, 2.345F }));
    }

    @Test
    public void whenCallIsNotEmptyForFloatArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isNotEmpty(new float[10]));
        assertTrue(ObjectValidator.isNotEmpty(new float[] { 1.2345F }));
        assertTrue(ObjectValidator.isNotEmpty(new float[] { 1.2345F, 2.345F }));
    }

    @Test
    public void whenCallIsNotEmptyForEmptyFloatArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isNotEmpty((float[]) null));
        assertFalse(ObjectValidator.isNotEmpty(new float[] {}));
        assertFalse(ObjectValidator.isNotEmpty(new float[0]));
    }

    @Test
    public void whenCallIsEmptyForDoubleArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isEmpty((double[]) null));
        assertTrue(ObjectValidator.isEmpty(new double[] {}));
        assertTrue(ObjectValidator.isEmpty(new double[0]));
    }

    @Test
    public void whenCallIsEmptyForEmptyDoubleArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isEmpty(new double[10]));
        assertFalse(ObjectValidator.isEmpty(new double[] { 1.2345 }));
        assertFalse(ObjectValidator.isEmpty(new double[] { 1.2345, 2.345 }));
    }

    @Test
    public void whenCallIsNotEmptyForDoubleArrayThenReturnTrue() {
        assertTrue(ObjectValidator.isNotEmpty(new double[10]));
        assertTrue(ObjectValidator.isNotEmpty(new double[] { 1.2345 }));
        assertTrue(ObjectValidator.isNotEmpty(new double[] { 1.2345, 2.345 }));
    }

    @Test
    public void whenCallIsNotEmptyForEmptyDoubleArrayThenReturnFalse() {
        assertFalse(ObjectValidator.isNotEmpty((double[]) null));
        assertFalse(ObjectValidator.isNotEmpty(new double[] {}));
        assertFalse(ObjectValidator.isNotEmpty(new double[0]));
    }
}
