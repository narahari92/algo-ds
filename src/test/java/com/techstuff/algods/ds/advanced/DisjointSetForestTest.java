package com.techstuff.algods.ds.advanced;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.techstuff.algods.ds.advanced.DisjointSetForest.Node;

public class DisjointSetForestTest {
	
	@Test
	public void testOperations() {
		Node<Integer> node1 = new Node<>(10);
		Node<Integer> node2 = new Node<>(11);
		Node<Integer> node3 = new Node<>(12);
		Node<Integer> node4 = new Node<>(13);
		Node<Integer> node5 = new Node<>(14);
		DisjointSetForest<Integer> disjointSet1 = new DisjointSetForest<Integer>(node1);
		DisjointSetForest<Integer> disjointSet2 = new DisjointSetForest<Integer>(node2);
		DisjointSetForest<Integer> disjointSet3 = new DisjointSetForest<Integer>(node3);
		DisjointSetForest<Integer> disjointSet4 = new DisjointSetForest<Integer>(node4);
		DisjointSetForest<Integer> disjointSet5 = new DisjointSetForest<Integer>(node5);
		disjointSet1.union(disjointSet2);
		disjointSet3.union(disjointSet4);
		disjointSet3.union(disjointSet5);
		assertEquals(node2, node1.findSet());
		disjointSet1.union(disjointSet3);
		assertEquals(node4, node1.findSet());
	}
}
