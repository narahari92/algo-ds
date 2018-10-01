package com.techstuff.algods.sort;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsertionSortTest {

    @Test
    public void testAscending() {
        List<Integer> sequence = Arrays.asList(5, 3, 0, 1, 10, 1, 2, 5, 3);
        InsertionSort<Integer> insertionSort = new InsertionSort<>(sequence);
        insertionSort.sort(true);
        List<Integer> expected = insertionSort.getSequence();
        assertEquals(expected, Arrays.asList(0, 1, 1, 2, 3, 3, 5, 5, 10));
    }
    
    @Test
    public void testDescending() {
        List<Integer> sequence = Arrays.asList(12, 10, 11, 12, 3, 5, 3, 7, 3);
        InsertionSort<Integer> insertionSort = new InsertionSort<>(sequence);
        insertionSort.sort(false);
        List<Integer> expected = insertionSort.getSequence();
        assertEquals(expected, Arrays.asList(12, 12, 11, 10, 7, 5, 3, 3, 3));
    }
}
