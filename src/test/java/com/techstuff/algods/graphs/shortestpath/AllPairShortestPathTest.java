package com.techstuff.algods.graphs.shortestpath;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.techstuff.algods.graphs.DirectedAdjacencyMatrixGraph;

public class AllPairShortestPathTest {
	
	@Test
	public void testOperation() {
		DirectedAdjacencyMatrixGraph graph = getGraph();
		AllPairShortestPath shortestPaths = new AllPairShortestPath(graph);
		DirectedAdjacencyMatrixGraph allPairShortestPaths = shortestPaths.allPairShortestPath();
		assertEquals(allPairShortestPaths.getNodeCount(), 5);
		List<List<Double>> shortestPairGraph = allPairShortestPaths.getGraph();
		assertEquals(shortestPairGraph.get(0), Arrays.asList(0.0, 1.0, -3.0, 2.0, -4.0));
		assertEquals(shortestPairGraph.get(1), Arrays.asList(3.0, 0.0, -4.0, 1.0, -1.0));
		assertEquals(shortestPairGraph.get(2), Arrays.asList(7.0, 4.0, 0.0, 5.0, 3.0));
		assertEquals(shortestPairGraph.get(3), Arrays.asList(2.0, -1.0, -5.0, 0.0, -2.0));
		assertEquals(shortestPairGraph.get(4), Arrays.asList(8.0, 5.0, 1.0, 6.0, 0.0));
	}
	
	public DirectedAdjacencyMatrixGraph getGraph() {
		DirectedAdjacencyMatrixGraph graph = new DirectedAdjacencyMatrixGraph(5);
		graph.addEdge(0, 1, 3.0);
		graph.addEdge(2, 1, 4.0);
		graph.addEdge(3, 2, -5.0);
		graph.addEdge(4, 3, 6.0);
		graph.addEdge(0, 4, -4.0);
		graph.addEdge(0, 2, 8.0);
		graph.addEdge(3, 0, 2.0);
		graph.addEdge(1, 3, 1.0);
		graph.addEdge(1, 4, 7.0);
		return graph;
	}
}
