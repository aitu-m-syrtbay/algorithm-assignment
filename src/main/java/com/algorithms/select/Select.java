package com.algorithms.select;

import com.algorithms.metrics.AlgorithmMetrics;
import com.algorithms.util.ArrayUtils;

public class Select {
    private final AlgorithmMetrics metrics;
    private static final int GROUP_SIZE = 5;

    public Select(AlgorithmMetrics metrics) {
        this.metrics = metrics;
    }

    public int select(int[] arr, int k) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k is out of bounds");
        }
        metrics.startTiming();
        int result = selectRecursive(arr, 0, arr.length - 1, k);
        metrics.stopTiming();
        return result;
    }

    private int selectRecursive(int[] arr, int left, int right, int k) {
        metrics.incrementDepth();

        if (left == right) {
            metrics.decrementDepth();
            return arr[left];
        }

        // Find median of medians as pivot
        int pivotIndex = medianOfMedians(arr, left, right);
        pivotIndex = ArrayUtils.partition(arr, left, right, pivotIndex, metrics);

        // Get the rank of pivot
        int pivotRank = pivotIndex - left;

        if (k == pivotRank) {
            metrics.decrementDepth();
            return arr[pivotIndex];
        }
        
        // Recurse on the needed half
        if (k < pivotRank) {
            return selectRecursive(arr, left, pivotIndex - 1, k);
        } else {
            return selectRecursive(arr, pivotIndex + 1, right, k - pivotRank - 1);
        }
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        
        // For small arrays, just return middle element
        if (n < GROUP_SIZE) {
            return left + n/2;
        }

        // Divide array into groups of 5 and find medians
        int numGroups = (n + GROUP_SIZE - 1) / GROUP_SIZE;
        for (int i = 0; i < numGroups; i++) {
            int groupLeft = left + i * GROUP_SIZE;
            int groupRight = Math.min(groupLeft + GROUP_SIZE - 1, right);
            
            // Find median of current group
            int median = findMedian(arr, groupLeft, groupRight);
            
            // Move median to the beginning
            ArrayUtils.swap(arr, left + i, median);
        }

        // Recursively find median of medians
        return selectRecursive(arr, left, left + numGroups - 1, numGroups/2);
    }

    private int findMedian(int[] arr, int left, int right) {
        // Simple insertion sort for small group
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= left) {
                metrics.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        return left + (right - left) / 2;
    }
}