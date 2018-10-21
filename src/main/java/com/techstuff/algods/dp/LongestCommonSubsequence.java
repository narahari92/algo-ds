package com.techstuff.algods.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techstuff.algods.util.Tuple2;

public class LongestCommonSubsequence {

	private char[] first;
	
	private char[] second;
	
	public LongestCommonSubsequence(char[] first, char[] second) {
		this.first = first;
		this.second = second;
	}
	
	public Tuple2<Integer, List<Character>> findLCSBottomUp() {
		Map<Tuple2<Integer, Integer>, Integer> lengthMap = new HashMap<>();
		Map<Tuple2<Integer, Integer>, Character> directionMap = new HashMap<>();
		for(int i = 0 ; i < first.length ; i++) {
			lengthMap.put(new Tuple2<>(i, -1), 0);
		}
		for(int j = 0 ; j < second.length ; j++) {
			lengthMap.put(new Tuple2<>(-1, j), 0);
		}
		for(int i = 0 ; i < first.length ; i++) {
			for(int j = 0 ; j < second.length ; j++) {
				if(first[i] == second[j]) {
					lengthMap.put(new Tuple2<>(i, j), lengthMap.get(new Tuple2<>(i - 1, j - 1)) + 1);
					directionMap.put(new Tuple2<>(i, j), 's');
				} else if(lengthMap.get(new Tuple2<>(i, j - 1)) > lengthMap.get(new Tuple2<>(i - 1, j))) {
					lengthMap.put(new Tuple2<>(i, j), lengthMap.get(new Tuple2<>(i, j - 1)));
					directionMap.put(new Tuple2<>(i, j), 'u');
				} else {
					lengthMap.put(new Tuple2<>(i, j), lengthMap.get(new Tuple2<>(i - 1, j)));
					directionMap.put(new Tuple2<>(i, j), 'u');
				}
			}
		}
		return new Tuple2<>(lengthMap.get(new Tuple2<>(first.length - 1, second.length - 1)),
				getSubsequence(new ArrayList<Character>(), directionMap, first.length - 1, second.length - 1));
	}
	
	private List<Character> getSubsequence(List<Character> subsequence, 
			Map<Tuple2<Integer, Integer>, Character> directionMap, int i, int j) {
		if(i == -1 || j == -1) {
			return subsequence;
		}
		if(directionMap.get(new Tuple2<>(i, j)) == 's') {
			getSubsequence(subsequence, directionMap, i - 1, j - 1);
			subsequence.add(first[i]);
		} else if(directionMap.get(new Tuple2<>(i, j)) == 'u') {
			getSubsequence(subsequence, directionMap, i, j - 1);
		} else {
			getSubsequence(subsequence, directionMap, i - 1, j);
		}
		return subsequence;
	}
	
	public Tuple2<Integer, List<Character>> findLCSTopDown() {
		Map<Tuple2<Integer, Integer>, Integer> lengthMap = new HashMap<>();
		Map<Tuple2<Integer, Integer>, Character> directionMap = new HashMap<>();
		for(int i = 0 ; i < first.length ; i++) {
			lengthMap.put(new Tuple2<>(i, -1), 0);
		}
		for(int j = 0 ; j < second.length ; j++) {
			lengthMap.put(new Tuple2<>(-1, j), 0);
		}
		Integer length = findLCSTopDown(first.length - 1, second.length - 1, new ArrayList<Character>(),
				lengthMap, directionMap);
		List<Character> subsequence = getSubsequence(new ArrayList<Character>(), directionMap, first.length - 1, 
				second.length - 1);
		return new Tuple2<>(length, subsequence);
	}
	
	public int findLCSTopDown(int i, int j, List<Character> subsequence,
			Map<Tuple2<Integer, Integer>, Integer> lengthMap, Map<Tuple2<Integer, Integer>, Character> directionMap) {
		if(i == -1 || j == -1) {
			return 0;
		}
		if(lengthMap.containsKey(new Tuple2<>(i, j))) {
			return lengthMap.get(new Tuple2<>(i, j));
		}
		if(first[i] == second[j]) {
			int subLength = findLCSTopDown(i - 1, j - 1, subsequence, lengthMap, directionMap);
			lengthMap.put(new Tuple2<>(i, j), subLength + 1);
			directionMap.put(new Tuple2<>(i, j), 's');
			return subLength + 1;
		} else {
			int firstLength = findLCSTopDown(i, j - 1, subsequence, lengthMap, directionMap);
			int secondLength = findLCSTopDown(i - 1, j, subsequence, lengthMap, directionMap);
			if(firstLength > secondLength) {
				lengthMap.put(new Tuple2<>(i, j), firstLength);
				directionMap.put(new Tuple2<>(i, j), 'u');
				return firstLength;
			} else {
				lengthMap.put(new Tuple2<>(i, j), secondLength);
				directionMap.put(new Tuple2<>(i, j), 'd');
				return secondLength;
			}
		}
	}
}
