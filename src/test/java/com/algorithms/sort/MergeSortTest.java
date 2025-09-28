package com.algorithms.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.algorithms.metrics.AlgorithmMetrics;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    private MergeSort mergeSort;
    private AlgorithmMetrics metrics;
    private Random random;

    @BeforeEach
    void setUp() {
        metrics = new AlgorithmMetrics();
        random = new Random(42); // Fixed seed for reproducibility
    }

    @Test
    void testEmptyArray() {
        int[] arr = new int[0];
        mergeSort = new MergeSort(arr.length, metrics);
        mergeSort.sort(arr);
        assertEquals(0, arr.length);
    }

    @Test
    void testSingleElement() {
        int[] arr = {1};
        mergeSort = new MergeSort(arr.length, metrics);
        mergeSort.sort(arr);
        assertArrayEquals(new int[]{1}, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = arr.clone();
        mergeSort = new MergeSort(arr.length, metrics);
        mergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        mergeSort = new MergeSort(arr.length, metrics);
        mergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArray() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }
        int[] expected = arr.clone();
        Arrays.sort(expected);
        
        mergeSort = new MergeSort(arr.length, metrics);
        mergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        
        mergeSort = new MergeSort(arr.length, metrics);
        mergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSmallArrays() {
        // Test arrays smaller than INSERTION_SORT_THRESHOLD
        for (int size = 1; size <= 10; size++) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = random.nextInt();
            }
            int[] expected = arr.clone();
            Arrays.sort(expected);
            
            mergeSort = new MergeSort(arr.length, metrics);
            mergeSort.sort(arr);
            assertArrayEquals(expected, arr);
        }
    }
}