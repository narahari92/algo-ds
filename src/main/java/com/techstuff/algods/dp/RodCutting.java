package com.techstuff.algods.dp;

import java.util.ArrayList;
import java.util.List;

import com.techstuff.algods.util.Tuple2;

public class RodCutting {
	
	private int[] prices;
	
	private int n;

	public RodCutting(List<Integer> prices, int n) {
		this.prices = new int[prices.size()];
		for(int i = 0 ; i < prices.size() ; i++) {
			this.prices[i] = prices.get(i);
		}
		this.n = n;
	}
	
	public Tuple2<Integer, List<Integer>> getBestValueBottomUp() {
		int[] r = new int[n + 1];
		int[] s = new int[n + 1];
		r[0] = 0;
		s[0] = 0;
		for(int i = 1 ; i <= n ; i++) {
			int q = -1;
			for(int j = 1 ; j <= i ; j++) {
				if(q < prices[j - 1] + r[i - j]) {
					q = prices[j - 1] + r[i - j];
					s[i] = j;
				}
			}
			r[i] = q;
		}
		List<Integer> sizes = new ArrayList<>();
		int size = n;
		while(size > 0) {
			sizes.add(s[size]);
			size = size - s[size];
		}
		return new Tuple2<>(r[n], sizes);
	}
	
	public Tuple2<Integer, List<Integer>> getBestValueTopDown() {
		int[] r = new int[n + 1];
		int[] s = new int[n + 1];
		for(int i = 0 ; i <= n ; i++) {
			r[i] = -1;
		}
		r[0] = 0;
		s[0] = 0;
		int bestValue = getBestValueOptimized(r, s, n);
		List<Integer> sizes = new ArrayList<>();
		int size = n;
		while(size > 0) {
			sizes.add(s[size]);
			size = size - s[size];
		}
		return new Tuple2<>(bestValue, sizes);
	}
	
	private int getBestValueOptimized(int[] r, int[] s, int n) {
		if(r[n] >= 0) {
			return r[n];
		}
		
		int q = -1;
		for(int i = 1 ; i <= n ; i++) {
			int newVal = prices[i - 1] + getBestValueOptimized(r, s, n - i);
			if(q < newVal) {
				q = newVal;
				s[n] = i;
			}
		}
		r[n] = q;
		return q;
	}
	
}
