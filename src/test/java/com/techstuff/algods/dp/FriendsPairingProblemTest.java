package com.techstuff.algods.dp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FriendsPairingProblemTest {

	@Test
	public void testForNEquals3() {
		FriendsPairingProblem fpp = new FriendsPairingProblem(3);
		Integer actual = fpp.totalWays();
		Integer expected = 4;
		assertEquals(expected, actual);
	}
}
