package com.algorithms.sort;

import org.junit.jupiter.api.Test;
import com.algorithms.metrics.AlgorithmMetrics;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    @Test
    void basicTest() {
        int[] arr = {5, 2, 9, 1, 7, 6, 3};
        QuickSort sorter = new QuickSort(new AlgorithmMetrics());
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 6, 7, 9}, arr);
    }

    @Test
    void randomArrayTest() {
        Random rand = new Random(42);
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt();
        }
        int[] expected = arr.clone();
        Arrays.sort(expected);

        QuickSort sorter = new QuickSort(new AlgorithmMetrics());
        sorter.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void duplicatesTest() {
        int[] arr = {4, 2, 4, 1, 4, 2, 4, 3};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        QuickSort sorter = new QuickSort(new AlgorithmMetrics());
        sorter.sort(arr);
        assertArrayEquals(expected, arr);
    }
}