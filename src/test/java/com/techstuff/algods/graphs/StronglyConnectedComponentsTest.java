package com.techstuff.algods.graphs;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class StronglyConnectedComponentsTest {

	@Test
	public void testScc() {
		Graph<Integer> graph = new DirectedGraph<>(new LinkedHashMap<Vertex<Integer>, List<Vertex<Integer>>>());
		Vertex<Integer> node1 = new Vertex<Integer>(1);
		Vertex<Integer> node2 = new Vertex<Integer>(2);
		Vertex<Integer> node3 = new Vertex<Integer>(3);
		Vertex<Integer> node4 = new Vertex<Integer>(4);
		Vertex<Integer> node5 = new Vertex<Integer>(5);
		Vertex<Integer> node6 = new Vertex<Integer>(6);
		Vertex<Integer> node7 = new Vertex<Integer>(7);
		Vertex<Integer> node8 = new Vertex<Integer>(8);
		graph.addEdge(node1, node2);
		graph.addEdge(node2, node3);
		graph.addEdge(node3, node1);
		graph.addEdge(node3, node4);
		graph.addEdge(node4, node7);
		graph.addEdge(node7, node4);
		graph.addEdge(node4, node8);
		graph.addEdge(node8, node6);
		graph.addEdge(node6, node5);
		graph.addEdge(node5, node8);
		StronglyConnectedComponents<Integer> scc = new StronglyConnectedComponents<>(graph);
		scc.calculate();
		List<List<Vertex<Integer>>> sccs = scc.getSccs();
		assertTrue(sccs.size() == 3);
		assertTrue(sccs.get(0).equals(Arrays.asList(node1, node3, node2)));
		assertTrue(sccs.get(1).equals(Arrays.asList(node4, node7)));
		assertTrue(sccs.get(2).equals(Arrays.asList(node8, node5, node6)));
	}
}
