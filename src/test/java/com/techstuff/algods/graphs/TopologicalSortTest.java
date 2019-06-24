package com.techstuff.algods.graphs;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TopologicalSortTest {

	@Test
	public void testTopologicalSort() {
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
		TopologicalSort<Integer> topologicalSort = new TopologicalSort<>(graph);
		topologicalSort.calculate();
		List<Vertex<Integer>> list = topologicalSort.getTopologicalOrder();
		assertEquals(Arrays.asList(node5, node8, node6, node1, node2, node3, node4, node7), list);
	}
}
