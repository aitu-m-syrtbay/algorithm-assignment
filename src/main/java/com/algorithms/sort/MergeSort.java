package com.algorithms.sort;

import com.algorithms.metrics.AlgorithmMetrics;

/**
 * MergeSort implementation with:
 * - Reusable merge buffer
 * - Small-n cutoff optimization using insertion sort
 * - Instrumented with performance metrics
 */
public class MergeSort {
    private static final int INSERTION_SORT_THRESHOLD = 10;
    private final AlgorithmMetrics metrics;
    private final int[] mergeBuffer;

    public MergeSort(int maxSize, AlgorithmMetrics metrics) {
        this.metrics = metrics;
        this.mergeBuffer = new int[maxSize];
        metrics.incrementAllocations(); // Count the buffer allocation
    }

    public void sort(int[] arr) {
        metrics.startTiming();
        mergeSort(arr, 0, arr.length - 1);
        metrics.stopTiming();
    }

    private void mergeSort(int[] arr, int left, int right) {
        metrics.incrementDepth();

        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right);
            metrics.decrementDepth();
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);

        metrics.decrementDepth();
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // Copy elements to merge buffer
        int n1 = mid - left + 1;
        for (int i = 0; i < n1; i++) {
            mergeBuffer[i] = arr[left + i];
            metrics.incrementComparisons();
        }

        int i = 0;      // Index for left subarray (in buffer)
        int j = mid + 1; // Index for right subarray (in original array)
        int k = left;    // Index for merged result

        // Merge back to original array
        while (i < n1 && j <= right) {
            metrics.incrementComparisons();
            if (mergeBuffer[i] <= arr[j]) {
                arr[k++] = mergeBuffer[i++];
            } else {
                arr[k++] = arr[j++];
            }
        }

        // Copy remaining elements from buffer, if any
        while (i < n1) {
            arr[k++] = mergeBuffer[i++];
            metrics.incrementComparisons();
        }
    }

    private void insertionSort(int[] arr, int left, int right) {
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
    }
}