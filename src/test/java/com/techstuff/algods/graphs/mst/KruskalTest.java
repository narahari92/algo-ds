package com.techstuff.algods.graphs.mst;

import java.util.List;

import org.junit.Test;

import com.techstuff.algods.graphs.Edge;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedGraph;

public class KruskalTest {

	@Test
	public void testOperation() {
		WeightedGraph<Integer, Character> graph = constructWeightedGraph();
		Kruskal<Character> kruskal = new Kruskal<>(graph);
		List<Edge<Integer, Character>> mst = kruskal.mst();
		for(Edge<Integer, Character> edge : mst) {
			System.out.println(edge.getSource().getPayload() + "--" + edge.getAttributes() + "--" + edge.getDestination().getPayload());
		}
		/**
		 * Edges in above for loop should display as below
		 *  g--1--h
			c--2--i
			f--2--g
			a--4--b
			c--4--f
			c--7--d
			a--8--h
			d--9--e
		 */
	}
	
	private WeightedGraph<Integer, Character> constructWeightedGraph() {
		WeightedGraph<Integer, Character> graph = new WeightedGraph<>();
		Vertex<Character> node1 = new Vertex<Character>('a');
		Vertex<Character> node2 = new Vertex<Character>('b');
		Vertex<Character> node3 = new Vertex<Character>('c');
		Vertex<Character> node4 = new Vertex<Character>('d');
		Vertex<Character> node5 = new Vertex<Character>('e');
		Vertex<Character> node6 = new Vertex<Character>('f');
		Vertex<Character> node7 = new Vertex<Character>('g');
		Vertex<Character> node8 = new Vertex<Character>('h');
		Vertex<Character> node9 = new Vertex<Character>('i');
		graph.addEdge(node1, node2, 4);
		graph.addEdge(node2, node3, 8);
		graph.addEdge(node3, node4, 7);
		graph.addEdge(node4, node5, 9);
		graph.addEdge(node5, node6, 10);
		graph.addEdge(node6, node7, 2);
		graph.addEdge(node7, node8, 1);
		graph.addEdge(node8, node1, 8);
		graph.addEdge(node8, node9, 7);
		graph.addEdge(node7, node9, 6);
		graph.addEdge(node3, node9, 2);
		graph.addEdge(node3, node6, 4);
		graph.addEdge(node4, node6, 14);
		graph.addEdge(node2, node8, 11);
		return graph;
	}
}
