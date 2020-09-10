package com.techstuff.algods.graphs.shortestpath;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedDirectedGraph;
import com.techstuff.algods.util.Tuple2;

public class BellmanFordTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testOperation() {
		Map<Character, Tuple2<Double, Character>> result = new HashMap<>();
		result.put('S', new Tuple2<>(0.0, null));
		result.put('T', new Tuple2<>(2.0, 'X'));
		result.put('X', new Tuple2<>(4.0, 'Y'));
		result.put('Y', new Tuple2<>(7.0, 'S'));
		result.put('Z', new Tuple2<>(-2.0, 'T'));
		Tuple2<WeightedDirectedGraph<Double, Character>, Vertex<Character>> graphSourceTuple = createGraph();
		BellmanFord<Character> bellmanFord = new BellmanFord<>(graphSourceTuple.getFirst());
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
		Vertex<Character> vertexs = new Vertex<>('S');
		Vertex<Character> vertext = new Vertex<>('T');
		Vertex<Character> vertexy = new Vertex<>('Y');
		Vertex<Character> vertexx = new Vertex<>('X');
		Vertex<Character> vertexz = new Vertex<>('Z');
		graph.addEdge(vertexs, vertext, 6.0);
		graph.addEdge(vertexs, vertexy, 7.0);
		graph.addEdge(vertext, vertexx, 5.0);
		graph.addEdge(vertext, vertexy, 8.0);
		graph.addEdge(vertext, vertexz, -4.0);
		graph.addEdge(vertexx, vertext, -2.0);
		graph.addEdge(vertexy, vertexx, -3.0);
		graph.addEdge(vertexy, vertexz, 9.0);
		graph.addEdge(vertexz, vertexx, 7.0);
		graph.addEdge(vertexz, vertexs, 2.0);
		return new Tuple2<WeightedDirectedGraph<Double,Character>, Vertex<Character>>(graph, vertexs);
	}
}
