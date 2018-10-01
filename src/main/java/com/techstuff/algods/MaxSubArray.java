package com.techstuff.algods;

import com.techstuff.algods.util.Tuple3;

public class MaxSubArray {

    private long[] arr;
    
    public MaxSubArray(long[] arr) {
        this.arr = new long[arr.length];
        for(int i = 0 ; i < arr.length ; i++) {
            this.arr[i] = arr[i];
        }
    }
    
    public Tuple3<Integer, Integer, Long> getResult() {
        return getResult(0, arr.length - 1);
    }
    
    private Tuple3<Integer, Integer, Long> getResult(int low, int high) {
        if(low == high) {
            return new Tuple3<>(low, high, arr[low]);
        }
        int mid = (low + high) / 2;
        Tuple3<Integer, Integer, Long> left = getResult(low, mid);
        Tuple3<Integer, Integer, Long> right = getResult(mid + 1, high);
        Tuple3<Integer, Integer, Long> cross = findMaxCrossingSubArray(low, high, mid);
        if((left.getThird() > right.getThird()) && (left.getThird() > cross.getThird())) {
            return left;
        } else if((right.getThird() > left.getThird()) && (right.getThird() > cross.getThird())){
            return right;
        } else {
            return cross;
        }
    }
    
    private Tuple3<Integer, Integer, Long> findMaxCrossingSubArray(int low, int high, int mid) {
        long leftSum = Long.MIN_VALUE;
        int leftMax = mid;
        long sum = 0;
        for(int i = mid ; i >= low ; i--) {
            sum += arr[i];
            if(sum > leftSum) {
                leftSum = sum;
                leftMax = i;
            }
        }
        long rightSum = Long.MIN_VALUE;
        int rightMax = mid + 1;
        sum = 0;
        for(int i = mid + 1 ; i <= high ; i++) {
            sum += arr[i];
            if(sum > rightSum) {
                rightSum = sum;
                rightMax = i;
            }
        }
        return new Tuple3<Integer, Integer, Long>(leftMax, rightMax, leftSum + rightSum);
    }
}
