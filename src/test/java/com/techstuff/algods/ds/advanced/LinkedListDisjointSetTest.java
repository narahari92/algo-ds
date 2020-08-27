package com.techstuff.algods.ds.advanced;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class LinkedListDisjointSetTest {

	@Test
	public void testOperations() {
		LinkedListDisjointSet<Integer> disjointSet1 = new LinkedListDisjointSet<Integer>(10);
		LinkedListDisjointSet<Integer> disjointSet2 = new LinkedListDisjointSet<Integer>(11);
		LinkedListDisjointSet<Integer> disjointSet3 = new LinkedListDisjointSet<Integer>(12);
		LinkedListDisjointSet<Integer> disjointSet4 = new LinkedListDisjointSet<Integer>(13);
		LinkedListDisjointSet<Integer> disjointSet5 = new LinkedListDisjointSet<Integer>(14);
		disjointSet1.union(disjointSet2);
		assertEquals(Arrays.asList(10, 11), disjointSet1.getAsList());
		disjointSet3.union(disjointSet4);
		disjointSet3.union(disjointSet5);
		assertEquals(Arrays.asList(12, 13, 14), disjointSet3.getAsList());
		assertEquals(disjointSet3.getNode(12), disjointSet3.getNode(13).findSet());
		disjointSet1.union(disjointSet3);
		assertEquals(Arrays.asList(10, 11, 12, 13, 14), disjointSet1.getAsList());
		assertEquals(disjointSet1.getNode(10), disjointSet1.getNode(13).findSet());
	}
}
