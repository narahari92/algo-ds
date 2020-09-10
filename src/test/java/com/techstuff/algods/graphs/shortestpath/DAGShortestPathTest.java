package com.techstuff.algods.graphs.shortestpath;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedDirectedGraph;
import com.techstuff.algods.util.Tuple2;

public class DAGShortestPathTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testOperation() {
		Map<Character, Tuple2<Double, Character>> result = new HashMap<>();
		result.put('R', new Tuple2<>(Double.POSITIVE_INFINITY, null));
		result.put('S', new Tuple2<>(0.0, null));
		result.put('T', new Tuple2<>(2.0, 'S'));
		result.put('X', new Tuple2<>(6.0, 'S'));
		result.put('Y', new Tuple2<>(5.0, 'X'));
		result.put('Z', new Tuple2<>(3.0, 'Y'));
		Tuple2<WeightedDirectedGraph<Double, Character>, Vertex<Character>> graphSourceTuple = createGraph();
		DAGShortestPath<Character> bellmanFord = new DAGShortestPath<>(graphSourceTuple.getFirst());
		bellmanFord.calculateShortestPaths(graphSourceTuple.getSecond());
		for(Vertex<Character> node : graphSourceTuple.getFirst().getNodes()) {
			Tuple2<Double, Character>expected = result.get(node.getPayload());
			Character parent = node.getAttribute("parent") == null ? null : 
				((Vertex<Character>)node.getAttribute("parent")).getPayload();
			assertEquals(expected.getFirst(), node.getAttribute("distance"));
			assertEquals(expected.getSecond(), parent);
		}
	}
	
	private Tuple2<WeightedDirectedGraph<Double, Character>, Vertex<Character>> createGraph() {
		WeightedDirectedGraph<Double, Character> graph = new WeightedDirectedGraph<>();
		Vertex<Character> vertexr = new Vertex<>('R');
		Vertex<Character> vertexs = new Vertex<>('S');
		Vertex<Character> vertext = new Vertex<>('T');
		Vertex<Character> vertexy = new Vertex<>('Y');
		Vertex<Character> vertexx = new Vertex<>('X');
		Vertex<Character> vertexz = new Vertex<>('Z');
		graph.addEdge(vertexr, vertexs, 5.0);
		graph.addEdge(vertexr, vertext, 3.0);
		graph.addEdge(vertexs, vertext, 2.0);
		graph.addEdge(vertexs, vertexx, 6.0);
		graph.addEdge(vertext, vertexx, 7.0);
		graph.addEdge(vertext, vertexy, 4.0);
		graph.addEdge(vertext, vertexz, 2.0);
		graph.addEdge(vertexx, vertexy, -1.0);
		graph.addEdge(vertexx, vertexz, 1.0);
		graph.addEdge(vertexy, vertexz, -2.0);
		return new Tuple2<WeightedDirectedGraph<Double,Character>, Vertex<Character>>(graph, vertexs);
	}
}
