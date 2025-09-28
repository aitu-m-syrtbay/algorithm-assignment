package com.algorithms.sort;

import com.algorithms.metrics.AlgorithmMetrics;
import java.util.Random;

public class QuickSort {
    private final AlgorithmMetrics metrics;
    private final Random random;

    public QuickSort(AlgorithmMetrics metrics) {
        this.metrics = metrics;
        this.random = new Random();
    }

    public void sort(int[] arr) {
        metrics.startTiming();
        quickSort(arr, 0, arr.length - 1);
        metrics.stopTiming();
    }

    private void quickSort(int[] arr, int left, int right) {
        // Recursion depth tracking
        metrics.incrementDepth();

        while (left < right) {
            int pivot = partition(arr, left, right);

            // Recurse on the smaller partition, iterate on the larger one
            if (pivot - left < right - pivot) {
                quickSort(arr, left, pivot - 1);
                left = pivot + 1;
            } else {
                quickSort(arr, pivot + 1, right);
                right = pivot - 1;
            }
        }

        metrics.decrementDepth();
    }

    private int partition(int[] arr, int left, int right) {
        // Pick a random pivot
        int pivotIndex = left + random.nextInt(right - left + 1);
        swap(arr, pivotIndex, right);
        
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            metrics.incrementComparisons();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}