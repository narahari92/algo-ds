package com.techstuff.algods.graphs.shortestpath;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.techstuff.algods.graphs.ComparableVertex;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedDirectedGraph;
import com.techstuff.algods.util.Tuple2;

public class DijkstraTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testOperations() {
		Map<Character, Tuple2<Integer, Character>> result = new HashMap<>();
		result.put('S', new Tuple2<>(0, null));
		result.put('T', new Tuple2<>(8, 'Y'));
		result.put('X', new Tuple2<>(9, 'T'));
		result.put('Y', new Tuple2<>(5, 'S'));
		result.put('Z', new Tuple2<>(7, 'Y'));
		Tuple2<WeightedDirectedGraph<Integer, Character>, Vertex<Character>> graphSourceTuple = createGraph();
		Dijkstra<Character> dijkstra = new Dijkstra<>(graphSourceTuple.getFirst());
		dijkstra.calculateShortestPaths(graphSourceTuple.getSecond());
		for(Vertex<Character> node : graphSourceTuple.getFirst().getNodes()) {
			Tuple2<Integer, Character>expected = result.get(node.getPayload());
			Character parent = node.getAttribute("parent") == null ? null : 
				((Vertex<Character>)node.getAttribute("parent")).getPayload();
			assertEquals(expected.getFirst(), ((ComparableVertex<Character>)node).getKey());
			assertEquals(expected.getSecond(), parent);
		}
	}
	
	private Tuple2<WeightedDirectedGraph<Integer, Character>, Vertex<Character>> createGraph() {
		WeightedDirectedGraph<Integer, Character> graph = new WeightedDirectedGraph<>();
		ComparableVertex<Character> vertexs = new ComparableVertex<>('S');
		ComparableVertex<Character> vertext = new ComparableVertex<>('T');
		ComparableVertex<Character> vertexy = new ComparableVertex<>('Y');
		ComparableVertex<Character> vertexx = new ComparableVertex<>('X');
		ComparableVertex<Character> vertexz = new ComparableVertex<>('Z');
		graph.addEdge(vertexs, vertext, 10);
		graph.addEdge(vertexs, vertexy, 5);
		graph.addEdge(vertext, vertexx, 1);
		graph.addEdge(vertext, vertexy, 2);
		graph.addEdge(vertexy, vertext, 3);
		graph.addEdge(vertexx, vertexz, 4);
		graph.addEdge(vertexy, vertexx, 9);
		graph.addEdge(vertexy, vertexz, 2);
		graph.addEdge(vertexz, vertexs, 7);
		graph.addEdge(vertexz, vertexx, 6);
		return new Tuple2<WeightedDirectedGraph<Integer,Character>, Vertex<Character>>(graph, vertexs);
	}
}
