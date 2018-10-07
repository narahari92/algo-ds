package com.techstuff.algods.sort;

import java.util.Arrays;
import java.util.List;

public class CountingSort {

    private Integer[] sequence;
    
    private int k;
    
    public CountingSort(int k, List<Integer> sequence) {
        this.sequence = new Integer[sequence.size()];
        this.k = k;
        for(int i = 0 ; i < sequence.size() ; i++) {
            if(sequence.get(i) >= k) {
                throw new RuntimeException("Number can't be greater than k in counting sort");
            }
            this.sequence[i] = sequence.get(i);
        }
    }
    
    public void sort(boolean nonDescending) {
        int[] temp = new int[k];
        for(int i = 0 ; i < k ; i++) {
            temp[i] = 0;
        }
        for(int i = 0 ; i < sequence.length ; i++) {
            temp[sequence[i]] = temp[sequence[i]] + 1;   // Count of each element in array
        }
        if(nonDescending) {
            for(int i = 1 ; i < k ; i++) {
                temp[i] = temp[i] + temp[i - 1];     // Index of each element
            }
        } else {
            for(int i = k - 2 ; i >= 0 ; i--) {
                temp[i] = temp[i] + temp[i + 1];
            }
        }
        Integer[] sorted = new Integer[sequence.length];
        for(int i = sequence.length - 1 ; i >= 0 ; i--) {
            sorted[temp[sequence[i]] - 1] = sequence[i];
            temp[sequence[i]] = temp[sequence[i]] - 1;
        }
        sequence = sorted;
    }
    
    public List<Integer> getSequence() {
        return Arrays.asList(sequence);
    }
}
