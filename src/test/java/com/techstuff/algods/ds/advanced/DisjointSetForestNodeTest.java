package com.techstuff.algods.ds.advanced;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.techstuff.algods.ds.advanced.DisjointSetForestNode;

public class DisjointSetForestNodeTest {

	@Test
	public void testOperations() {
		DisjointSetForestNode<Integer> disjointSet1 = new DisjointSetForestNode<>(10);
		DisjointSetForestNode<Integer> disjointSet2 = new DisjointSetForestNode<>(11);
		DisjointSetForestNode<Integer> disjointSet3 = new DisjointSetForestNode<>(12);
		DisjointSetForestNode<Integer> disjointSet4 = new DisjointSetForestNode<>(13);
		DisjointSetForestNode<Integer> disjointSet5 = new DisjointSetForestNode<>(14);
		disjointSet1.union(disjointSet2);
		disjointSet3.union(disjointSet4);
		disjointSet3.union(disjointSet5);
		assertEquals(disjointSet2, disjointSet1.findSet());
		disjointSet1.union(disjointSet3);
		assertEquals(disjointSet4, disjointSet1.findSet());
	}
}
