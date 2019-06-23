package com.techstuff.algods.graphs;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

public class DFSTest {

	@Test
	public void testDFS() {
		Graph<Integer> graph = new AdjacencyListGraph<>(new LinkedHashMap<Vertex<Integer>, List<Vertex<Integer>>>());
		Vertex<Integer> node1 = new Vertex<Integer>(1);
		Vertex<Integer> node2 = new Vertex<Integer>(2);
		Vertex<Integer> node3 = new Vertex<Integer>(3);
		Vertex<Integer> node4 = new Vertex<Integer>(4);
		Vertex<Integer> node5 = new Vertex<Integer>(5);
		Vertex<Integer> node6 = new Vertex<Integer>(6);
		Vertex<Integer> node7 = new Vertex<Integer>(7);
		Vertex<Integer> node8 = new Vertex<Integer>(8);
		graph.addEdge(node1, node2);
		graph.addEdge(node1, node3);
		graph.addEdge(node2, node3);
		graph.addEdge(node3, node4);
		graph.addEdge(node5, node8);
		graph.addEdge(node8, node6);
		graph.addEdge(node4, node7);
		DFS<Integer> dfs = new DFS<>(graph);
		dfs.calculate();
		assertTrue(node1.getAttribute("start").equals(1) && node1.getAttribute("end").equals(10));
		assertTrue(node2.getAttribute("start").equals(2) && node2.getAttribute("end").equals(9));
		assertTrue(node3.getAttribute("start").equals(3) && node3.getAttribute("end").equals(8));
		assertTrue(node4.getAttribute("start").equals(4) && node4.getAttribute("end").equals(7));
		assertTrue(node5.getAttribute("start").equals(11) && node5.getAttribute("end").equals(16));
		assertTrue(node6.getAttribute("start").equals(13) && node6.getAttribute("end").equals(14));
		assertTrue(node7.getAttribute("start").equals(5) && node7.getAttribute("end").equals(6));
		assertTrue(node8.getAttribute("start").equals(12) && node8.getAttribute("end").equals(15));
	}
}
