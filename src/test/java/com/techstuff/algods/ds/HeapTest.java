package com.techstuff.algods.ds;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HeapTest {

    @Test
    public void testBuildMaxHeep() {
        Heap<Integer> heap = new Heap<>(Arrays.asList(5, 4, 7, 10, 6, 8, 9), false);
        List<Integer> actual = heap.backingSequence();
        List<Integer> expected = Arrays.asList(10, 6, 9, 4, 5, 8, 7);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testBuildMinHeap() {
        Heap<Long> heap = new Heap<>(Arrays.asList(12l, 5l, 8l, 7l, 4l, 9l, 6l, 1l), true);
        List<Long> actual = heap.backingSequence();
        List<Long> expected = Arrays.asList(1l, 4l, 6l, 5l, 12l, 9l, 8l, 7l);
        assertEquals(expected, actual);
    }
}
