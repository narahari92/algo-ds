package com.techstuff.algods.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a value N, if we want to make change for N cents, and we have infinite supply of each of 
 * S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? The order of coins doesn’t matter.
 * 
 * For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. 
 * So output should be 4. For N = 10 and S = {2, 5, 3, 6}, there are five solutions: 
 * {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5.
 *
 */
public class CoinChangeProblem {

	private int n;
	
	private int[] set;
	
	public CoinChangeProblem(int n, int[] set) {
		this.n = n;
		this.set = set;
	}
	
	public int totalWays() {
		Map<Integer, Integer> changeMap = new HashMap<>();
		changeMap.put(0, 1);
		return totalWays(n, changeMap);
	}
	
	private int totalWays(int num, Map<Integer, Integer> changeMap) {
		if(num < 0) {
			return 0;
		}
		if(changeMap.containsKey(num)) {
			return changeMap.get(num);
		}
		int count = 0;
		for(int s : set) {
			count += totalWays(num - s, changeMap);			
		}
		changeMap.put(num, count);
		return count;
	}
}
