package com.techstuff.algods.ds;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.techstuff.algods.ds.RedBlackTree.Color;
import com.techstuff.algods.util.Tuple2;

public class RedBlackTreeTest {

	@Test
	public void testCreate() {
		List<Integer> sequence = Arrays.asList(3, 1, 2);
		RedBlackTree<Integer> rbt = new RedBlackTree<>(sequence);
		List<Tuple2<Integer, Color>> expected = 
				Arrays.asList(new Tuple2<>(1, Color.RED), new Tuple2<>(2, Color.BLACK),
						new Tuple2<>(3, Color.RED));
		assertEquals(expected, rbt.getInorder());
		expected = Arrays.asList(new Tuple2<>(2, Color.BLACK), new Tuple2<>(1, Color.RED)
				, new Tuple2<>(3, Color.RED));
		assertEquals(expected, rbt.getLevelorder());
				
	}
	
	@Test
	public void testInsertRedUncle() {
		List<Integer> sequence = Arrays.asList(15, 5, 10);
		RedBlackTree<Integer> rbt = new RedBlackTree<>(sequence);
		rbt.insert(3);
		List<Tuple2<Integer, Color>> expected = 
				Arrays.asList(new Tuple2<>(3, Color.RED), new Tuple2<>(5, Color.BLACK),
						new Tuple2<>(10, Color.BLACK), new Tuple2<>(15, Color.BLACK));
		assertEquals(expected, rbt.getInorder());
		expected = Arrays.asList(new Tuple2<>(10, Color.BLACK), new Tuple2<>(5, Color.BLACK)
				, new Tuple2<>(15, Color.BLACK), new Tuple2<>(3, Color.RED));
		assertEquals(expected, rbt.getLevelorder());
	}
	
	@Test
	public void testInsertBlackUncleStraightLineParent() {
		List<Integer> sequence = Arrays.asList(15, 5, 10, 3);
		RedBlackTree<Integer> rbt = new RedBlackTree<>(sequence);
		rbt.insert(1);
		List<Tuple2<Integer, Color>> expected = 
				Arrays.asList(new Tuple2<>(1, Color.RED), new Tuple2<>(3, Color.BLACK), 
						new Tuple2<>(5, Color.RED), new Tuple2<>(10, Color.BLACK), 
						new Tuple2<>(15, Color.BLACK));
		assertEquals(expected, rbt.getInorder());
		expected = Arrays.asList(new Tuple2<>(10, Color.BLACK), new Tuple2<>(3, Color.BLACK), 
				new Tuple2<>(15, Color.BLACK), new Tuple2<>(1, Color.RED), 
				new Tuple2<>(5, Color.RED));
		assertEquals(expected, rbt.getLevelorder());
	}
	
	@Test
	public void testInsertBlackUncleAngularParent() {
		List<Integer> sequence = Arrays.asList(15, 5, 10, 3, 1, 12);
		RedBlackTree<Integer> rbt = new RedBlackTree<>(sequence);
		rbt.insert(14);
		List<Tuple2<Integer, Color>> expected = 
				Arrays.asList(new Tuple2<>(1, Color.RED), new Tuple2<>(3, Color.BLACK), 
						new Tuple2<>(5, Color.RED), new Tuple2<>(10, Color.BLACK), 
						new Tuple2<>(12, Color.RED), new Tuple2<>(14, Color.BLACK),
						new Tuple2<>(15, Color.RED));
		assertEquals(expected, rbt.getInorder());
		expected = Arrays.asList(new Tuple2<>(10, Color.BLACK), new Tuple2<>(3, Color.BLACK), 
				new Tuple2<>(14, Color.BLACK),  new Tuple2<>(1, Color.RED),
				new Tuple2<>(5, Color.RED), new Tuple2<>(12, Color.RED),
				new Tuple2<>(15, Color.RED));
		assertEquals(expected, rbt.getLevelorder());
	}
	
	@Test
	public void testDeleteSingleLeftChild() {
		List<Integer> sequence = Arrays.asList(20, 10, 30, 5, 3, 1, 25, 40, 50);
		RedBlackTree<Integer> rbt = new RedBlackTree<>(sequence);
		rbt.delete(3);
		List<Tuple2<Integer, Color>> expected = 
				Arrays.asList(new Tuple2<>(1, Color.BLACK), new Tuple2<>(5, Color.RED),
						new Tuple2<>(10, Color.BLACK), new Tuple2<>(20, Color.BLACK),
						new Tuple2<>(25, Color.BLACK), new Tuple2<>(30, Color.RED),
						new Tuple2<>(40, Color.BLACK), new Tuple2<>(50, Color.RED));
		assertEquals(expected, rbt.getInorder());
		expected = Arrays.asList(new Tuple2<>(20, Color.BLACK), new Tuple2<>(5, Color.RED),
				new Tuple2<>(30, Color.RED), new Tuple2<>(1, Color.BLACK), new Tuple2<>(10, Color.BLACK),
				new Tuple2<>(25, Color.BLACK), new Tuple2<>(40, Color.BLACK), 
				new Tuple2<>(50, Color.RED));
		assertEquals(expected, rbt.getLevelorder());
	}
	
	@Test
	public void testDeleteSingleRightChild() {
		List<Integer> sequence = Arrays.asList(20, 10, 30, 5, 3, 1, 25, 40, 50);
		RedBlackTree<Integer> rbt = new RedBlackTree<>(sequence);
		rbt.delete(40);
		List<Tuple2<Integer, Color>> expected =
				Arrays.asList(new Tuple2<>(1, Color.RED), new Tuple2<>(3, Color.BLACK),
						new Tuple2<>(5, Color.RED), new Tuple2<>(10, Color.BLACK),
						new Tuple2<>(20, Color.BLACK), new Tuple2<>(25, Color.BLACK),
						new Tuple2<>(30, Color.RED), new Tuple2<>(50, Color.BLACK));
		assertEquals(expected, rbt.getInorder());
		expected = Arrays.asList(new Tuple2<>(20, Color.BLACK), new Tuple2<>(5, Color.RED),
				new Tuple2<>(30, Color.RED), new Tuple2<>(3, Color.BLACK),
				new Tuple2<>(10, Color.BLACK), new Tuple2<>(25, Color.BLACK),
				new Tuple2<>(50, Color.BLACK), new Tuple2<>(1, Color.RED));
		assertEquals(expected, rbt.getLevelorder());
	}
	
	@Test
	public void testDeleteTwoChildren() {
		List<Integer> sequence = Arrays.asList(20, 10, 30, 5, 3, 1, 25, 40, 50);
		RedBlackTree<Integer> rbt = new RedBlackTree<>(sequence);
		rbt.delete(20);
		List<Tuple2<Integer, Color>> expected = 
				Arrays.asList(new Tuple2<>(1, Color.RED), new Tuple2<>(3, Color.BLACK),
						new Tuple2<>(5, Color.RED), new Tuple2<>(10, Color.BLACK),
						new Tuple2<>(25, Color.BLACK), new Tuple2<>(30, Color.BLACK),
						new Tuple2<>(40, Color.RED), new Tuple2<>(50, Color.BLACK));
		assertEquals(expected, rbt.getInorder());
		expected = Arrays.asList(new Tuple2<>(25, Color.BLACK), new Tuple2<>(5, Color.RED),
				new Tuple2<>(40, Color.RED), new Tuple2<>(3, Color.BLACK), 
				new Tuple2<>(10, Color.BLACK), new Tuple2<>(30, Color.BLACK),
				new Tuple2<>(50, Color.BLACK), new Tuple2<>(1, Color.RED));
		assertEquals(expected, rbt.getLevelorder());
	}
}
