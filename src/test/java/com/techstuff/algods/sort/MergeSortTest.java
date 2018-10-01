package com.techstuff.algods.sort;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MergeSortTest {

    @Test
    public void testAscending() {
        List<Integer> sequence = Arrays.asList(5, 3, 0, 1, 10, 1, 2, 5, 3, 4);
        MergeSort<Integer> mergeSort = new MergeSort<>(sequence);
        mergeSort.sort(true);
        List<Integer> expected = mergeSort.getSequence();
        assertEquals(expected, Arrays.asList(0, 1, 1, 2, 3, 3, 4, 5, 5, 10));
    }
    
    @Test
    public void testDescending() {
        List<Integer> sequence = Arrays.asList(12, 10, 11, 12, 3, 5, 3, 7, 3);
        MergeSort<Integer> mergeSort = new MergeSort<>(sequence);
        mergeSort.sort(false);
        List<Integer> expected = mergeSort.getSequence();
        assertEquals(expected, Arrays.asList(12, 12, 11, 10, 7, 5, 3, 3, 3));
    }
}
