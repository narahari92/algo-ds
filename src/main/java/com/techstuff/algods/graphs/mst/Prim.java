package com.techstuff.algods.graphs.mst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.techstuff.algods.ds.advanced.FibonacciHeap;
import com.techstuff.algods.graphs.Edge;
import com.techstuff.algods.graphs.Vertex;
import com.techstuff.algods.graphs.WeightedGraph;
import com.techstuff.algods.util.Tuple2;

public class Prim<T> {

private WeightedGraph<Integer, T> graph;
	
	public Prim(WeightedGraph<Integer, T> graph) {
		this.graph = graph;
	}
	
	public List<Edge<Integer, T>> mst() {
		List<Edge<Integer, T>> mst = new ArrayList<>();
		FibonacciHeap<PrimVertex<T>> heap = new FibonacciHeap<>();
		Set<Vertex<T>> nodes = graph.getNodes();
		if(nodes.isEmpty()) {
			return mst;
		}
		int i = 0;
		Map<Vertex<T>, FibonacciHeap<PrimVertex<T>>.Node> heapMap = new HashMap<>();
		for(Vertex<T> vertex : nodes) {
			((PrimVertex<T>)vertex).setKey(Integer.MAX_VALUE);
			if(i == 0) {
				((PrimVertex<T>)vertex).setKey(0);
			}
			vertex.setAttribute("inHeap", true);
			vertex.setAttribute("parent", null);
			heapMap.put(vertex, heap.insert(((PrimVertex<T>)vertex)));
			i++;
		}
		PrimVertex<T> vertex = null;
		while((vertex = heap.extractMin()) != null) {
			vertex.setAttribute("inHeap", false);
			if(vertex.getAttribute("parent") != null) {
				mst.add(new Edge<>((Vertex<T>)vertex.getAttribute("parent"), vertex, vertex.getKey()));
			}
			for(Tuple2<Integer, Vertex<T>> edge : graph.getEdges(vertex)) {
				Integer weight = edge.getFirst();
				PrimVertex<T> neighbour = (PrimVertex<T>)edge.getSecond();
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
