package com.techstuff.algods;

import org.junit.Test;

import com.techstuff.algods.util.Tuple3;

import static org.junit.Assert.assertEquals;

public class MaxSubArrayTest {

    @Test
    public void testLeftIsMax() {
        long[] arr = {-9, 2, 4, -2, 3, -8, 4, 2, -1, -1, 2, -4};
        MaxSubArray msa = new MaxSubArray(arr);
        Tuple3<Integer, Integer, Long> expected = new Tuple3<Integer, Integer, Long>(1, 4, 7l);
        Tuple3<Integer, Integer, Long> actual = msa.getResult();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testRightIsMax() {
        long[] arr = {1l, 2l, -1l, 3l, -4l, -1l, 2l, -3l, 3l, 2l, -1l, 4l, -3l, 4l};
        MaxSubArray msa = new MaxSubArray(arr);
        Tuple3<Integer, Integer, Long> expected = new Tuple3<Integer, Integer, Long>(8, 13, 9l);
        Tuple3<Integer, Integer, Long> actual = msa.getResult();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCrossIsMax() {
        long[] arr = {1l, -3l, 2l, -1l, 3l, -4l, 2l, 4l, -3l, -4l, -1l};
        MaxSubArray msa = new MaxSubArray(arr);
        Tuple3<Integer, Integer, Long> expected = new Tuple3<Integer, Integer, Long>(2, 7, 6l);
        Tuple3<Integer, Integer, Long> actual = msa.getResult();
        assertEquals(expected, actual);
    }
}
