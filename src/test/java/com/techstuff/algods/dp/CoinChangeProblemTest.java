package com.techstuff.algods.dp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CoinChangeProblemTest {

	@Test
	public void testForN3() {
		CoinChangeProblem ccp = new CoinChangeProblem(4, new int[] {1, 2, 3});
		Integer actual = ccp.totalWays();
		Integer expected = 4;
		assertEquals(expected, actual);
	}
}
