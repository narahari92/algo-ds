package com.techstuff.algods.sort;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HeapSortTest {

    @Test
    public void testAscending() {
        List<Integer> sequence = Arrays.asList(5, 3, 0, 1, 10, 1, 2, 5, 3, 4);
        HeapSort<Integer> heapSort = new HeapSort<>(sequence);
        heapSort.sort(true);
        List<Integer> actual = heapSort.getSequence();
        assertEquals(Arrays.asList(0, 1, 1, 2, 3, 3, 4, 5, 5, 10), actual);
    }
    
    @Test
    public void testDescending() {
        List<Integer> sequence = Arrays.asList(12, 10, 11, 12, 3, 5, 3, 7, 3);
        HeapSort<Integer> heapSort = new HeapSort<>(sequence);
        heapSort.sort(false);
        List<Integer> actual = heapSort.getSequence();
        assertEquals(Arrays.asList(12, 12, 11, 10, 7, 5, 3, 3, 3), actual);
    }
}
