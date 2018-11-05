package com.techstuff.algods.dp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TilingProblemTest {

	@Test
	public void testProblemNEquals3() {
		TilingProblem tp = new TilingProblem(3);
		Integer actual = tp.countWays();
		Integer expected = 3;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testProblemNEquals4() {
		TilingProblem tp = new TilingProblem(4);
		Integer actual = tp.countWays();
		Integer expected = 5;
		assertEquals(expected, actual);
	}
}
