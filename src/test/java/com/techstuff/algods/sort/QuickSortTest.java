package com.techstuff.algods.sort;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class QuickSortTest {

    @Test
    public void testAscending() {
        List<Integer> sequence = Arrays.asList(5, 3, 0, 1, 10, 1, 2, 5, 3, 4);
        QuickSort<Integer> quickSort = new QuickSort<>(sequence, false);
        quickSort.sort(true);
        List<Integer> actual = quickSort.getSequence();
        assertEquals(Arrays.asList(0, 1, 1, 2, 3, 3, 4, 5, 5, 10), actual);
    }
    
    @Test
    public void testDescending() {
        List<Integer> sequence = Arrays.asList(12, 10, 11, 12, 3, 5, 3, 7, 3);
        QuickSort<Integer> quickSort = new QuickSort<>(sequence, false);
        quickSort.sort(false);
        List<Integer> actual = quickSort.getSequence();
        assertEquals(Arrays.asList(12, 12, 11, 10, 7, 5, 3, 3, 3), actual);
    }
    
    @Test
    public void testAscendingRandomizedPivot() {
        List<Integer> sequence = Arrays.asList(5, 3, 0, 1, 10, 1, 2, 5, 3, 4);
        QuickSort<Integer> quickSort = new QuickSort<>(sequence, true);
        quickSort.sort(true);
        List<Integer> actual = quickSort.getSequence();
        assertEquals(Arrays.asList(0, 1, 1, 2, 3, 3, 4, 5, 5, 10), actual);
    }
    
    @Test
    public void testDescendingRandomizedPivot() {
        List<Integer> sequence = Arrays.asList(12, 10, 11, 12, 3, 5, 3, 7, 3);
        QuickSort<Integer> quickSort = new QuickSort<>(sequence, true);
        quickSort.sort(false);
        List<Integer> actual = quickSort.getSequence();
        assertEquals(Arrays.asList(12, 12, 11, 10, 7, 5, 3, 3, 3), actual);
    }
}
