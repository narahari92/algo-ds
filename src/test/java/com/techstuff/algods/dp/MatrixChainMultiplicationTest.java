package com.techstuff.algods.dp;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.techstuff.algods.util.Tuple2;

public class MatrixChainMultiplicationTest {

	@Test
	public void testBottomUp() {
		int[] sizes = {30, 35, 15, 5, 10, 20, 25};
		MatrixChainMultiplication mcm = new MatrixChainMultiplication(sizes);
		Tuple2<Integer, List<Integer>> expected = new Tuple2<>(15125, Arrays.asList(3, 5));
		Tuple2<Integer, List<Integer>> actual = mcm.findOptimalParanthesisBottomUp();
		assertEquals(expected.getFirst(), actual.getFirst());
		assertEquals(expected.getSecond(), actual.getSecond());
	}
	
	@Test
	public void testTopDown() {
		int[] sizes = {30, 35, 15, 5, 10, 20, 25};
		MatrixChainMultiplication mcm = new MatrixChainMultiplication(sizes);
		Tuple2<Integer, List<Integer>> expected = new Tuple2<>(15125, Arrays.asList(3, 5));
		Tuple2<Integer, List<Integer>> actual = mcm.findOptimalParanthesisTopDown();
		assertEquals(expected.getFirst(), actual.getFirst());
		assertEquals(expected.getSecond(), actual.getSecond());
	}
	
}
