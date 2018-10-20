package com.techstuff.algods.dp;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.techstuff.algods.util.Tuple2;

public class RodCuttingTest {

	@Test
	public void testBottomUp() {
		RodCutting rc = new RodCutting(Arrays.asList(1, 5, 7, 9, 13, 30), 4);
		Integer expected = 10;
		Tuple2<Integer, List<Integer>> actual = rc.getBestValueBottomUp();
		assertEquals(expected, actual.getFirst());
		assertEquals(Arrays.asList(2, 2), actual.getSecond());
	}
	
	@Test
	public void testBottomUpNoCutting() {
		RodCutting rc = new RodCutting(Arrays.asList(1, 5, 7, 9, 13, 30), 6);
		Integer expected = 30;
		Tuple2<Integer, List<Integer>> actual = rc.getBestValueBottomUp();
		assertEquals(expected, actual.getFirst());
		assertEquals(Arrays.asList(6), actual.getSecond());
	}
	
	@Test
	public void testTopDown() {
		RodCutting rc = new RodCutting(Arrays.asList(1, 5, 7, 9, 13, 30), 4);
		Integer expected = 10;
		Tuple2<Integer, List<Integer>> actual = rc.getBestValueTopDown();
		assertEquals(expected, actual.getFirst());
		assertEquals(Arrays.asList(2, 2), actual.getSecond());
	}
	
	@Test
	public void testTopDownNoCutting() {
		RodCutting rc = new RodCutting(Arrays.asList(1, 5, 7, 9, 13, 30), 6);
		Integer expected = 30;
		Tuple2<Integer, List<Integer>> actual = rc.getBestValueTopDown();
		assertEquals(expected, actual.getFirst());
		assertEquals(Arrays.asList(6), actual.getSecond());
	}
}
