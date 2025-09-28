package com.algorithms.select;

import org.junit.jupiter.api.Test;
import com.algorithms.metrics.AlgorithmMetrics;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class SelectTest {
    @Test
    void testBasicSelect() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        Select selector = new Select(new AlgorithmMetrics());
        
        assertEquals(1, selector.select(arr, 0));  // Smallest
        assertEquals(9, selector.select(arr, arr.length - 1));  // Largest
        assertEquals(3, selector.select(arr, 4));  // Median
    }

    @Test
    void compareWithSort() {
        Random rand = new Random(42);
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = rand.nextInt(1000);
        }

        Select selector = new Select(new AlgorithmMetrics());
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        // Test multiple k values
        for (int k = 0; k < arr.length; k += 10) {
            int[] testArr = arr.clone();
            assertEquals(sorted[k], selector.select(testArr, k),
                    "Failed for k=" + k);
        }
    }

    @Test
    void testWithDuplicates() {
        int[] arr = {5, 5, 5, 5, 5, 5, 5};
        Select selector = new Select(new AlgorithmMetrics());
        
        assertEquals(5, selector.select(arr, 0));
        assertEquals(5, selector.select(arr, 3));
        assertEquals(5, selector.select(arr, 6));
    }

    @Test
    void testIllegalArguments() {
        int[] arr = {1, 2, 3};
        Select selector = new Select(new AlgorithmMetrics());
        
        assertThrows(IllegalArgumentException.class, () -> 
            selector.select(arr, -1));
        assertThrows(IllegalArgumentException.class, () -> 
            selector.select(arr, 3));
    }
}