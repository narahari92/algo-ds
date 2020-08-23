package com.techstuff.algods.ds.advanced;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.techstuff.algods.ds.advanced.FibonacciHeap.Node;

public class FibonacciHeapTest {

	@Test
	public void testInsert() {
		FibonacciHeap<Integer> heap = new FibonacciHeap<>();
		heap.insert(15);
		heap.insert(20);
		heap.insert(12);
		heap.insert(14);
		List<Integer> rootList = heap.getRootListData();
		assertEquals(rootList, Arrays.asList(15, 20, 12, 14));
		assertEquals(heap.minimum(), new Integer(12));
	}
	
	@Test
	public void union() {
		FibonacciHeap<Integer> first = new FibonacciHeap<>();
		first.insert(15);
		first.insert(20);
		first.insert(12);
		first.insert(14);
		FibonacciHeap<Integer> second = new FibonacciHeap<>();
		second.insert(8);
		second.insert(17);
		FibonacciHeap<Integer> union = first.union(second);
		List<Integer> rootList = union.getRootListData();
		assertEquals(rootList, Arrays.asList(15, 20, 12, 14, 8, 17));
		assertEquals(union.minimum(), new Integer(8));
	}
	
	@Test
	public void testExtractMin() {
		FibonacciHeap<Integer> heap = new FibonacciHeap<>();
		heap.insert(24);
		heap.insert(17);
		heap.insert(3);
		heap.insert(21);
		heap.insert(7);
		heap.insert(23);
		Integer min = heap.extractMin();
		assertEquals(new Integer(3), min);
		assertEquals(Arrays.asList(17, 7), heap.getRootListData());
	}
	
	@Test
	public void testDecreaseKey() {
		FibonacciHeap<Integer> heap = new FibonacciHeap<>();
		Map<Integer, Node> nodeMap = new HashMap<>();
		nodeMap.put(24, heap.insert(24));
		nodeMap.put(17, heap.insert(17));
		nodeMap.put(3, heap.insert(3));
		nodeMap.put(21, heap.insert(21));
		nodeMap.put(7, heap.insert(7));
		nodeMap.put(23, heap.insert(23));
		nodeMap.put(12, heap.insert(12));
		nodeMap.put(15, heap.insert(15));
		nodeMap.put(13, heap.insert(13));
		nodeMap.put(19, heap.insert(19));
		nodeMap.put(30, heap.insert(30));
		heap.extractMin();
		heap.decreaseKey(nodeMap.get(15), 5);
		nodeMap.put(5, nodeMap.remove(15));
		assertEquals(new Integer(5), heap.minimum());
		heap.decreaseKey(nodeMap.get(19), 10);
		nodeMap.put(10, nodeMap.remove(19));
		assertEquals(heap.getRootListData(), Arrays.asList(17, 7, 5, 10, 13));
	}
	
	@Test
	public void testDeleteNode() {
		FibonacciHeap<Integer> heap = new FibonacciHeap<>();
		Map<Integer, Node> nodeMap = new HashMap<>();
		nodeMap.put(24, heap.insert(24));
		nodeMap.put(17, heap.insert(17));
		nodeMap.put(3, heap.insert(3));
		nodeMap.put(21, heap.insert(21));
		nodeMap.put(7, heap.insert(7));
		nodeMap.put(23, heap.insert(23));
		nodeMap.put(12, heap.insert(12));
		nodeMap.put(15, heap.insert(15));
		nodeMap.put(13, heap.insert(13));
		nodeMap.put(19, heap.insert(19));
		nodeMap.put(30, heap.insert(30));
		heap.extractMin();
		heap.decreaseKey(nodeMap.get(19), -10000);    // Assume -10000 = -infinity
		heap.deleteNode(nodeMap.remove(19));
		assertEquals(heap.getRootListData(), Arrays.asList(30, 17, 7));
	}
}
