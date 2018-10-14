package com.techstuff.algods.ds;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BSTTest {

	@Test
	public void testCreate() {
		BST<Integer> bst = new BST<>(Arrays.asList(3, 4, 1, 5, 6, 2, 8, 7));
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		List<Integer> actual = bst.getInorder();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMinimum() {
		BST<Integer> bst = new BST<>(Arrays.asList(3, 4, 1, 5, 6, 2, 8, 7));
		Integer expected = 1;
		assertEquals(expected, bst.getMinimum());
	}
	
	@Test
	public void testMaximum() {
		BST<Integer> bst = new BST<>(Arrays.asList(3, 4, 1, 5, 6, 2, 8, 7));
		Integer expected = 8;
		assertEquals(expected, bst.getMaximum());
	}
	
	@Test
	public void testSuccessor() {
		BST<Integer> bst = new BST<>(Arrays.asList(3, 4, 1, 5, 6, 2, 8, 7));
		assertEquals(new Integer(7), bst.getSuccessor(6));
	}
	
	@Test
	public void testSuccessorNoRight() {
		BST<Integer> bst = new BST<>(Arrays.asList(3, 7, 4, 1, 5, 6, 2, 8));
		assertEquals(new Integer(7), bst.getSuccessor(6));
	}
	
	@Test
	public void testSuccessorForMax() {
		BST<Integer> bst = new BST<>(Arrays.asList(3, 4, 1, 5, 6, 2, 8, 7));
		assertEquals(null, bst.getSuccessor(8));
	}
	
	@Test
	public void testPredecessor() {
		BST<Integer> bst = new BST<>(Arrays.asList(3, 4, 1, 5, 6, 2, 8, 7));
		assertEquals(new Integer(2), bst.getPredecessor(3));
	}
	
	@Test
	public void testPredecessorNoLeft() {
		BST<Integer> bst = new BST<>(Arrays.asList(3, 7, 4, 1, 5, 6, 2, 8));
		assertEquals(new Integer(3), bst.getPredecessor(4));
	}
	
	@Test
	public void testPredecessorForMin() {
		BST<Integer> bst = new BST<>(Arrays.asList(3, 4, 1, 5, 6, 2, 8, 7));
		assertEquals(null, bst.getPredecessor(1));
	}
	
	@Test
	public void testInsert() {
		BST<Integer> bst = new BST<>(Arrays.asList(3, 4, 10, 5, 6, 2, 8, 7));
		bst.insert(1);
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 10);
		List<Integer> actual = bst.getInorder();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeleteNoChildren() {
		BST<Integer> bst = new BST<>(Arrays.asList(5, 3, 10, 1, 4, 2, 7, 15, 13, 16, 
				12, 14, 11));
		bst.delete(2);
		List<Integer> expected = Arrays.asList(1, 3, 4, 5, 7, 10, 11, 12, 13, 14, 15, 16);
		assertEquals(expected, bst.getInorder());
		expected = Arrays.asList(5, 3, 10, 1, 4, 7, 15, 13, 16, 12, 14, 11);
		assertEquals(expected, bst.getLevelorder());
	}
	
	@Test
	public void testDeleteOnlyLeft() {
		BST<Integer> bst = new BST<>(Arrays.asList(5, 3, 10, 1, 4, 2, 7, 15, 13, 16, 
				12, 14, 11));
		bst.delete(12);
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 7, 10, 11, 13, 14, 15, 16);
		assertEquals(expected, bst.getInorder());
		expected = Arrays.asList(5, 3, 10, 1, 4, 7, 15, 2, 13, 16, 11, 14);
		assertEquals(expected, bst.getLevelorder());
	}
	
	@Test
	public void testDeleteOnlyRight() {
		BST<Integer> bst = new BST<>(Arrays.asList(5, 3, 10, 1, 4, 2, 7, 15, 13, 16, 
				12, 14, 11));
		bst.delete(1);
		List<Integer> expected = Arrays.asList(2, 3, 4, 5, 7, 10, 11, 12, 13, 14, 15, 16);
		assertEquals(expected, bst.getInorder());
		expected = Arrays.asList(5, 3, 10, 2, 4, 7, 15, 13, 16, 12, 14, 11);
		assertEquals(expected, bst.getLevelorder());
	}
	
	@Test
	public void testDeleteBothChildren() {
		BST<Integer> bst = new BST<>(Arrays.asList(5, 3, 10, 1, 4, 2, 7, 15, 13, 16, 
				12, 14, 11));
		bst.delete(10);
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 7, 11, 12, 13, 14, 15, 16);
		assertEquals(expected, bst.getInorder());
		expected = Arrays.asList(5, 3, 11, 1, 4, 7, 15, 2, 13, 16, 12, 14);
		assertEquals(expected, bst.getLevelorder());
	}
	
	@Test
	public void testDeleteBothChildrenNonLeafReplacement() {
		BST<Integer> bst = new BST<>(Arrays.asList(5, 3, 10, 1, 4, 2, 7, 15, 13, 20, 
				12, 14, 11, 18, 19, 21));
		bst.delete(15);
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 7, 10, 11, 12, 13, 14, 18, 
				19, 20, 21);
		assertEquals(expected, bst.getInorder());
		expected = Arrays.asList(5, 3, 10, 1, 4, 7, 18, 2, 13, 20, 12, 14, 19, 21, 11);
		assertEquals(expected, bst.getLevelorder());
	}
	
	@Test
	public void testSize() {
		BST<Integer> bst = new BST<>(Arrays.asList(5, 3, 10, 1, 4, 2, 7, 15, 13, 16, 
				12, 14, 11));
		assertEquals(new Integer(13), new Integer(bst.getSize()));
	}
	
	@Test
	public void testLevelorder() {
		BST<Integer> bst = new BST<>(Arrays.asList(5, 3, 10, 1, 4, 2, 7, 15, 13, 16, 
				12, 14, 11));
		List<Integer> expected = Arrays.asList(5, 3, 10, 1, 4, 7, 15, 2, 13, 16, 12, 14, 11);
		assertEquals(expected, bst.getLevelorder());
	}
}
