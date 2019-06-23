package com.techstuff.algods.graphs;

import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BFSTest {

	@Test
	public void testBFS() {
		Graph<Integer> graph = new AdjacencyListGraph<>(new HashMap<Vertex<Integer>, List<Vertex<Integer>>>());
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
		BFS<Integer> bfs = new BFS<>(graph);
		bfs.calculate(new Vertex<Integer>(1));
		assertTrue(node1.getAttribute("color").equals("black") && node1.getAttribute("distance").equals(0));
		assertTrue(node2.getAttribute("color").equals("black") && node2.getAttribute("distance").equals(1));
		assertTrue(node3.getAttribute("color").equals("black") && node3.getAttribute("distance").equals(1));
		assertTrue(node4.getAttribute("color").equals("black") && node4.getAttribute("distance").equals(2));
		assertTrue(node5.getAttribute("color").equals("white") && node5.getAttribute("distance").equals(-1));
		assertTrue(node6.getAttribute("color").equals("white") && node6.getAttribute("distance").equals(-1));
		assertTrue(node7.getAttribute("color").equals("black") && node7.getAttribute("distance").equals(3));
		assertTrue(node8.getAttribute("color").equals("white") && node8.getAttribute("distance").equals(-1));
	}
}
