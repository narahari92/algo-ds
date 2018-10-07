package com.techstuff.algods.sort;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RadixSortTest {

    @Test
    public void testAscending() {
        RadixSort radixSort = new RadixSort(Arrays.asList(1234l, 2345l, 412l, 12334l, 3456l, 3455l,  2222l, 2345l));
        radixSort.sort(true);
        List<Long> actual = radixSort.getSequence();
        assertEquals(Arrays.asList(412l, 1234l, 2222l, 2345l, 2345l, 3455l, 3456l, 12334l), actual);
    }
    
    @Test
    public void testDescending() {
        RadixSort radixSort = new RadixSort(Arrays.asList(1234l, 2345l, 3456l, 412l, 12334l, 3456l, 3455l, 2222l));
        radixSort.sort(false);
        List<Long> actual = radixSort.getSequence();
        assertEquals(Arrays.asList(12334l, 3456l, 3456l, 3455l, 2345l, 2222l, 1234l, 412l), actual);
    }
}
