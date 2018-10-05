package com.techstuff.algods.ds;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PriorityQueueTest {

    @Test
    public void testGetMaximum() {
        PriorityQuery<Long> pq = new PriorityQuery<>(Arrays.asList(10l, 9l, 4l, 5l, 11l, 7l));
        long max = pq.getMaximum();
        assertEquals(11l, max);
    }
    
    @Test
    public void testExtractMaximum() {
        PriorityQuery<Long> pq = new PriorityQuery<>(Arrays.asList(10l, 9l, 4l, 5l, 11l, 7l));
        long max = pq.extractMaximum();
        assertEquals(11l, max);
        max = pq.getMaximum();
        assertEquals(10l, max);
    }
    
    @Test
    public void testIncreaseKey() {
        PriorityQuery<Long> pq = new PriorityQuery<>(Arrays.asList(10l, 9l, 4l, 5l, 11l, 7l));
        pq.increaseKey(3, 12l);
        List<Long> expected = Arrays.asList(12l, 11l, 7l, 10l, 9l, 4l);
        List<Long> actual = pq.getHeap().backingSequence();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInsertKey() {
        PriorityQuery<Long> pq = new PriorityQuery<>(Arrays.asList(10l, 9l, 4l, 5l, 11l, 7l));
        pq.insertKey(8l);
        List<Long> expected = Arrays.asList(11l, 10l, 8l, 5l, 9l, 4l, 7l);
        List<Long> actual = pq.getHeap().backingSequence();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInsertKeyAfterDeletions() {
        PriorityQuery<Long> pq = new PriorityQuery<>(Arrays.asList(10l, 9l, 4l, 5l, 11l, 7l));
        pq.extractMaximum();
        pq.extractMaximum();
        pq.insertKey(6l);
        List<Long> expected = Arrays.asList(9l, 6l, 7l, 4l, 5l);
        List<Long> actual = pq.getHeap().backingSequence().subList(0, pq.getHeap().heapSize());
        assertEquals(expected, actual);
    }
}
