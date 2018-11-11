package com.techstuff.algods.dp;

/**
 * Given n friends, each one can remain single or can be paired up with some other friend. 
 * Each friend can be paired only once. Find out the total number of ways in which friends can remain single 
 * or can be paired up.
 * 
 *  Input  : n = 3
	Output : 4
	
	Explanation
	{1}, {2}, {3} : all single
	{1}, {2,3} : 2 and 3 paired but 1 is single.
	{1,2}, {3} : 1 and 2 are paired but 3 is single.
	{1,3}, {2} : 1 and 3 are paired but 2 is single.
	Note that {1,2} and {2,1} are considered same.
 *
 */
public class FriendsPairingProblem {

	private int n;
	
	public FriendsPairingProblem(int n) {
		this.n = n;
	}
	
	public int totalWays() {
		int[] ways = new int[n];
		for(int i = 1 ; i <= n ; i++) {
			if(i <= 2) {
				ways[i - 1] = i;
			} else {
				ways[i - 1] = ways[i - 2] + (i - 1) * ways[i - 3];
			}
		}
		return ways[n - 1];
	}
}
