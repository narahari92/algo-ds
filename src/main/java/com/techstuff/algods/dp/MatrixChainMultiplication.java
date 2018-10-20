package com.techstuff.algods.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techstuff.algods.util.Tuple2;

/* In case of matrix chain(A1 . A2 . A3 . A4 ...), compute best way to multiply(since matrix multiplication is 
 * associative) by finding position of paranthesis.
 */
public class MatrixChainMultiplication {

	private int[] sizes;
	
	public MatrixChainMultiplication(int[] sizes) {
		this.sizes = sizes;
	}
	
	public Tuple2<Integer, List<Integer>> findOptimalParanthesisBottomUp() {
		Map<Tuple2<Integer, Integer>, Integer> costMap = new HashMap<>();
		Map<Tuple2<Integer, Integer>, Integer> splitMap = new HashMap<>();
		for(int i = 1 ; i < sizes.length ; i++) {
			costMap.put(new Tuple2<>(i, i), 0);
		}
		int n = sizes.length - 1;
		for(int l = 2 ; l <= n ; l++) {
			for(int i = 1 ; i <= n - l + 1 ; i++) {
				int j = i + l - 1;
				int cost = Integer.MAX_VALUE;
				for(int k = i ; k < j ; k++) {
					int newCost = costMap.get(new Tuple2<>(i, k)) + costMap.get(new Tuple2<>(k + 1, j))
									+ (sizes[i - 1] * sizes[k] * sizes[j]);
					if(newCost < cost) {
						cost = newCost;
						splitMap.put(new Tuple2<>(i, j), k);
					}
				}
				costMap.put(new Tuple2<>(i, j), cost);
			}
		}
		int totalCost = costMap.get(new Tuple2<>(1, n));
		List<Integer> paranthesisPos = getParanthesisPos(new ArrayList<Integer>(), splitMap, 1, n);
		return new Tuple2<>(totalCost, paranthesisPos);
	}
	
	public Tuple2<Integer, List<Integer>> findOptimalParanthesisTopDown() {
		Map<Tuple2<Integer, Integer>, Integer> costMap = new HashMap<>();
		Map<Tuple2<Integer, Integer>, Integer> splitMap = new HashMap<>();
		for(int i = 1 ; i < sizes.length ; i++) {
			costMap.put(new Tuple2<>(i, i), 0);
		}
		int cost = findOptimalParanthesisTopDown(costMap, splitMap, 1, sizes.length - 1);
		List<Integer> paranthesisPos = getParanthesisPos(new ArrayList<Integer>(), splitMap, 1, sizes.length - 1);
		return new Tuple2<>(cost, paranthesisPos);
	}
	
	private int findOptimalParanthesisTopDown(Map<Tuple2<Integer, Integer>, Integer> costMap,
			Map<Tuple2<Integer, Integer>, Integer> splitMap, int i, int j) {
		if(costMap.containsKey(new Tuple2<>(i, j))) {
			return costMap.get(new Tuple2<>(i, j));
		}
		int cost = Integer.MAX_VALUE;
		for(int k = i ; k < j ; k++) {
			int newCost = findOptimalParanthesisTopDown(costMap, splitMap, i, k) +
					findOptimalParanthesisTopDown(costMap, splitMap, k + 1, j) + (sizes[i - 1] * sizes[k] * sizes[j]);
			if(newCost < cost) {
				cost = newCost;
				splitMap.put(new Tuple2<>(i, j), k);
			}
		}
		costMap.put(new Tuple2<>(i, j), cost);
		return cost;
	}
	
	
	private List<Integer> getParanthesisPos(List<Integer> posList, Map<Tuple2<Integer, Integer>, Integer> splitMap, 
			int i, int j) {
		if(i == j) {
			return posList;
		}
		Integer k = splitMap.get(new Tuple2<>(i, j));
		if(i == k) {
			return posList;
		}
		posList.add(k);
		getParanthesisPos(posList, splitMap, i, k - 1);
		getParanthesisPos(posList, splitMap, k + 1, j);
		return posList;
	}
	
}
