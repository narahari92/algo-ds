package com.techstuff.algods.ds.advanced;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class BTreeTest {

	@Test
	public void testInsert() {
		BTree<Integer> btree = new BTree<>(2);
		for(int i = 1 ; i <= 6 ; i++) {
			btree.insert(i);
		}
		assertTrue(btree.getRoot().getKeys().equals(Arrays.asList(2, 4)));
		assertTrue(btree.getRoot().getChildren().size() == 3);
		assertTrue(btree.getRoot().getChildren().get(0).getKeys().equals(Arrays.asList(1)));
		assertTrue(btree.getRoot().getChildren().get(1).getKeys().equals(Arrays.asList(3)));
		assertTrue(btree.getRoot().getChildren().get(2).getKeys().equals(Arrays.asList(5, 6)));
	}
	
	@Test
	public void testSearch() {
		BTree<Integer> btree = new BTree<>(3);
		for(int i = 20 ; i >= 1 ; i--) {
			btree.insert(i);
		}
		BTreeSearchResult<Integer> result = btree.search(4);
		assertTrue(result.getNode().getKeys().equals(Arrays.asList(1, 2, 3, 4, 5)));
		assertTrue(result.getNode().getKeys().get(result.getIndex()) == 4);
	}
	
	@Test
	public void testDelete() {
		BTree<String> btree = constructDeleteBTree();
		//btree.levelOrder();
		btree.delete("F");
		//btree.levelOrder();
		btree.delete("M");
		//btree.levelOrder();
		btree.delete("G");
		//btree.levelOrder();
		btree.delete("D");
		//btree.levelOrder();
		btree.delete("B");
		//btree.levelOrder();
		btree.delete("O");
		//btree.levelOrder();
		btree.delete("P");
		btree.levelOrder();
	}
	
	private BTree<String> constructDeleteBTree() {
		BTree<String> btree = new BTree<>(3);
		BTree<String>.Node root = btree.new Node();
		root.getKeys().add("P");
		BTree<String>.Node levelOneFirst = btree.new Node();
		levelOneFirst.getKeys().addAll(Arrays.asList("C", "G", "M"));
		BTree<String>.Node levelOneSecond = btree.new Node();
		levelOneSecond.getKeys().addAll(Arrays.asList("T", "X"));
		root.getChildren().add(levelOneFirst);
		root.getChildren().add(levelOneSecond);
		BTree<String>.Node levelTwoFirst = btree.new Node();
		levelTwoFirst.getKeys().addAll(Arrays.asList("A", "B"));
		BTree<String>.Node levelTwoSecond = btree.new Node();
		levelTwoSecond.getKeys().addAll(Arrays.asList("D", "E", "F"));
		BTree<String>.Node levelTwoThird = btree.new Node();
		levelTwoThird.getKeys().addAll(Arrays.asList("J", "K", "L"));
		BTree<String>.Node levelTwoFourth = btree.new Node();
		levelTwoFourth.getKeys().addAll(Arrays.asList("N", "O"));
		BTree<String>.Node levelTwoFifth = btree.new Node();
		levelTwoFifth.getKeys().addAll(Arrays.asList("Q", "R", "S"));
		BTree<String>.Node levelTwoSixth = btree.new Node();
		levelTwoSixth.getKeys().addAll(Arrays.asList("U", "V"));
		BTree<String>.Node levelTwoSeventh = btree.new Node();
		levelTwoSeventh.getKeys().addAll(Arrays.asList("Y", "Z"));
		levelOneFirst.getChildren().addAll(Arrays.asList(levelTwoFirst, levelTwoSecond, levelTwoThird, levelTwoFourth));
		levelOneSecond.getChildren().addAll(Arrays.asList(levelTwoFifth, levelTwoSixth, levelTwoSeventh));
		btree.setRoot(root);;
		return btree;
	}
}
