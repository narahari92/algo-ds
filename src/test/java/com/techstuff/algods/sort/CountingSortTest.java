package com.techstuff.algods.sort;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CountingSortTest {

    @Test
    public void testAscending() {
        List<Integer> sequence = Arrays.asList(5, 3, 0, 1, 10, 1, 2, 5, 3, 4);
        CountingSort countingSort = new CountingSort(11, sequence);
        countingSort.sort(true);
        List<Integer> actual = countingSort.getSequence();
        assertEquals(Arrays.asList(0, 1, 1, 2, 3, 3, 4, 5, 5, 10), actual);
    }
    
    @Test
    public void testDescending() {
        List<Integer> sequence = Arrays.asList(2, 4, 4, 1, 0, 3, 2);
        CountingSort countingSort = new CountingSort(5, sequence);
        countingSort.sort(false);
        List<Integer> actual = countingSort.getSequence();
        assertEquals(Arrays.asList(4, 4, 3, 2, 2, 1, 0), actual);
    }
}
