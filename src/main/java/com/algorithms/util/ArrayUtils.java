package com.algorithms.util;

import com.algorithms.metrics.AlgorithmMetrics;
import java.util.Random;

public class ArrayUtils {
    private static final Random random = new Random();

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void shuffle(int[] arr, AlgorithmMetrics metrics) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(arr, i, j);
            metrics.incrementComparisons();
        }
    }

    public static int partition(int[] arr, int left, int right, int pivotIndex, AlgorithmMetrics metrics) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.incrementComparisons();
            if (arr[i] <= pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    public static void checkArrayBounds(int[] arr, int left, int right) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (left < 0 || right >= arr.length || left > right) {
            throw new IllegalArgumentException("Invalid array bounds");
        }
    }

    public static void validateArray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
    }
}