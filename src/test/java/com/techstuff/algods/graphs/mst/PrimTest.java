package com.techstuff.algods.graphs.mst;

import java.util.List;

import org.junit.Test;

import com.techstuff.algods.graphs.Edge;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedGraph;

public class PrimTest {

	@Test
	public void testOperation() {
		WeightedGraph<Integer, Character> graph = constructWeightedGraph();
		Prim<Character> prim = new Prim<>(graph);
		List<Edge<Integer, Character>> mst = prim.mst();
		for(Edge<Integer, Character> edge : mst) {
			System.out.println(edge.getSource().getPayload() + "--" + edge.getAttributes() + "--" + edge.getDestination().getPayload());
		}
		/**
		 * Edges in above for loop should display as below
		 *  a--4--b
			a--8--h
			h--1--g
			g--2--f
			f--4--c
			c--2--i
			c--7--d
			d--9--e
		 */
	}
	
	private WeightedGraph<Integer, Character> constructWeightedGraph() {
		WeightedGraph<Integer, Character> graph = new WeightedGraph<>();
		PrimVertex<Character> node1 = new PrimVertex<Character>('a');
		PrimVertex<Character> node2 = new PrimVertex<Character>('b');
		PrimVertex<Character> node3 = new PrimVertex<Character>('c');
		PrimVertex<Character> node4 = new PrimVertex<Character>('d');
		PrimVertex<Character> node5 = new PrimVertex<Character>('e');
		PrimVertex<Character> node6 = new PrimVertex<Character>('f');
		PrimVertex<Character> node7 = new PrimVertex<Character>('g');
		PrimVertex<Character> node8 = new PrimVertex<Character>('h');
		PrimVertex<Character> node9 = new PrimVertex<Character>('i');
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
