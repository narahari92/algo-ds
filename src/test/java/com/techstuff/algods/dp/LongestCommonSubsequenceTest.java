package com.techstuff.algods.dp;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.techstuff.algods.util.Tuple2;

public class LongestCommonSubsequenceTest {

	@Test
	public void testBottomUp() {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence(new char[] {'A', 'B', 'C', 'B', 'D', 'A', 'B'}, 
				new char[] {'B', 'D', 'C', 'A', 'B', 'A'});
		Tuple2<Integer, List<Character>> expected = new Tuple2<>(4, Arrays.asList('B', 'D', 'A', 'B'));
		Tuple2<Integer, List<Character>> actual = lcs.findLCSBottomUp();
		assertEquals(expected.getFirst(), actual.getFirst());
		assertEquals(expected.getSecond(), actual.getSecond());
	}
	
	@Test
	public void testTopDown() {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence(new char[] {'A', 'B', 'C', 'B', 'D', 'A', 'B'}, 
				new char[] {'B', 'D', 'C', 'A', 'B', 'A'});
		Tuple2<Integer, List<Character>> expected = new Tuple2<>(4, Arrays.asList('B', 'C', 'B', 'A'));
		Tuple2<Integer, List<Character>> actual = lcs.findLCSTopDown();
		assertEquals(expected.getFirst(), actual.getFirst());
		assertEquals(expected.getSecond(), actual.getSecond());
	}
}
