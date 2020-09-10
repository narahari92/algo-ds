package com.techstuff.algods.graphs.mst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.techstuff.algods.ds.advanced.FibonacciHeap;
import com.techstuff.algods.graphs.ComparableVertex;
import com.techstuff.algods.graphs.Edge;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedGraph;
import com.techstuff.algods.util.Tuple2;

public class Prim<T> {

	private WeightedGraph<Double, T> graph;
	
	public Prim(WeightedGraph<Double, T> graph) {
		this.graph = graph;
	}
	
	public List<Edge<Double, T>> mst() {
		List<Edge<Double, T>> mst = new ArrayList<>();
		FibonacciHeap<ComparableVertex<T>> heap = new FibonacciHeap<>();
		Set<Vertex<T>> nodes = graph.getNodes();
		if(nodes.isEmpty()) {
			return mst;
		}
		int i = 0;
		Map<Vertex<T>, FibonacciHeap<ComparableVertex<T>>.Node> heapMap = new HashMap<>();
		for(Vertex<T> vertex : nodes) {
			((ComparableVertex<T>)vertex).setKey(Double.POSITIVE_INFINITY);
			if(i == 0) {
				((ComparableVertex<T>)vertex).setKey(0.0);
			}
			vertex.setAttribute("inHeap", true);
			vertex.setAttribute("parent", null);
			heapMap.put(vertex, heap.insert(((ComparableVertex<T>)vertex)));
			i++;
		}
		ComparableVertex<T> vertex = null;
		while((vertex = heap.extractMin()) != null) {
			vertex.setAttribute("inHeap", false);
			if(vertex.getAttribute("parent") != null) {
				mst.add(new Edge<>((Vertex<T>)vertex.getAttribute("parent"), vertex, vertex.getKey()));
			}
			for(Tuple2<Double, Vertex<T>> edge : graph.getEdges(vertex)) {
				Double weight = edge.getFirst();
				ComparableVertex<T> neighbour = (ComparableVertex<T>)edge.getSecond();
				if(neighbour.getAttribute("inHeap").equals(true) && neighbour.getKey() > weight) {
					neighbour.setKey(weight);
					heap.decreaseKey(heapMap.get(neighbour), neighbour);
					neighbour.setAttribute("parent", vertex);
				}
			}
		}
		return mst;
	}
}
