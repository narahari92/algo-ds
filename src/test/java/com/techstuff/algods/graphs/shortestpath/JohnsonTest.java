package com.techstuff.algods.graphs.shortestpath;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.techstuff.algods.graphs.ComparableVertex;
import com.techstuff.algods.graphs.DirectedAdjacencyMatrixGraph;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedDirectedGraph;

public class JohnsonTest {

	@Test
	public void testOperation() {
		WeightedDirectedGraph<Double, Integer> graph = getGraph();
		Johnson shortestPaths = new Johnson(graph);
		DirectedAdjacencyMatrixGraph allPairShortestPaths = shortestPaths.allPairShortestPath();
		assertEquals(allPairShortestPaths.getNodeCount(), 5);
		List<List<Double>> shortestPairGraph = allPairShortestPaths.getGraph();
		System.out.println(shortestPairGraph);
		assertEquals(shortestPairGraph.get(0), Arrays.asList(0.0, 1.0, -3.0, 2.0, -4.0));
		assertEquals(shortestPairGraph.get(1), Arrays.asList(3.0, 0.0, -4.0, 1.0, -1.0));
		assertEquals(shortestPairGraph.get(2), Arrays.asList(7.0, 4.0, 0.0, 5.0, 3.0));
		assertEquals(shortestPairGraph.get(3), Arrays.asList(2.0, -1.0, -5.0, 0.0, -2.0));
		assertEquals(shortestPairGraph.get(4), Arrays.asList(8.0, 5.0, 1.0, 6.0, 0.0));
	}
	
	public WeightedDirectedGraph<Double, Integer> getGraph() {
		WeightedDirectedGraph<Double, Integer> graph = new WeightedDirectedGraph<>();
		Vertex<Integer> vertex0 = new ComparableVertex<>(0);
		Vertex<Integer> vertex1 = new ComparableVertex<>(1);
		Vertex<Integer> vertex2 = new ComparableVertex<>(2);
		Vertex<Integer> vertex3 = new ComparableVertex<>(3);
		Vertex<Integer> vertex4 = new ComparableVertex<>(4);
		graph.addEdge(vertex0, vertex1, 3.0);
		graph.addEdge(vertex2, vertex1, 4.0);
		graph.addEdge(vertex3, vertex2, -5.0);
		graph.addEdge(vertex4, vertex3, 6.0);
		graph.addEdge(vertex0, vertex4, -4.0);
		graph.addEdge(vertex0, vertex2, 8.0);
		graph.addEdge(vertex3, vertex0, 2.0);
		graph.addEdge(vertex1, vertex3, 1.0);
		graph.addEdge(vertex1, vertex4, 7.0);
		return graph;
	}
}
