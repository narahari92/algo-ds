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
		Map<Character, Tuple2<Integer, Character>> result = new HashMap<>();
		result.put('R', new Tuple2<>(Integer.MAX_VALUE, null));
		result.put('S', new Tuple2<>(0, null));
		result.put('T', new Tuple2<>(2, 'S'));
		result.put('X', new Tuple2<>(6, 'S'));
		result.put('Y', new Tuple2<>(5, 'X'));
		result.put('Z', new Tuple2<>(3, 'Y'));
		Tuple2<WeightedDirectedGraph<Integer, Character>, Vertex<Character>> graphSourceTuple = createGraph();
		BellmanFord<Character> bellmanFord = new BellmanFord<>(graphSourceTuple.getFirst());
		bellmanFord.calculateShortestPaths(graphSourceTuple.getSecond());
		for(Vertex<Character> node : graphSourceTuple.getFirst().getNodes()) {
			Tuple2<Integer, Character>expected = result.get(node.getPayload());
			Character parent = node.getAttribute("parent") == null ? null : 
				((Vertex<Character>)node.getAttribute("parent")).getPayload();
			assertEquals(expected.getFirst(), node.getAttribute("distance"));
			assertEquals(expected.getSecond(), parent);
		}
	}
	
	private Tuple2<WeightedDirectedGraph<Integer, Character>, Vertex<Character>> createGraph() {
		WeightedDirectedGraph<Integer, Character> graph = new WeightedDirectedGraph<>();
		Vertex<Character> vertexr = new Vertex<>('R');
		Vertex<Character> vertexs = new Vertex<>('S');
		Vertex<Character> vertext = new Vertex<>('T');
		Vertex<Character> vertexy = new Vertex<>('Y');
		Vertex<Character> vertexx = new Vertex<>('X');
		Vertex<Character> vertexz = new Vertex<>('Z');
		graph.addEdge(vertexr, vertexs, 5);
		graph.addEdge(vertexr, vertext, 3);
		graph.addEdge(vertexs, vertext, 2);
		graph.addEdge(vertexs, vertexx, 6);
		graph.addEdge(vertext, vertexx, 7);
		graph.addEdge(vertext, vertexy, 4);
		graph.addEdge(vertext, vertexz, 2);
		graph.addEdge(vertexx, vertexy, -1);
		graph.addEdge(vertexx, vertexz, 1);
		graph.addEdge(vertexy, vertexz, -2);
		return new Tuple2<WeightedDirectedGraph<Integer,Character>, Vertex<Character>>(graph, vertexs);
	}
}
