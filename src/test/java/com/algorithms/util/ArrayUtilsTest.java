package com.algorithms.util;

import org.junit.jupiter.api.Test;
import com.algorithms.metrics.AlgorithmMetrics;
import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {
    
    @Test
    void testPartition() {
        int[] arr = {5, 2, 9, 1, 7, 6, 3};
        AlgorithmMetrics metrics = new AlgorithmMetrics();
        
        int pivotIndex = 3; // value is 1
        int newPivotIndex = ArrayUtils.partition(arr, 0, arr.length - 1, pivotIndex, metrics);
        
        // After partition, all elements before pivot should be <= 1
        for (int i = 0; i < newPivotIndex; i++) {
            assertTrue(arr[i] <= arr[newPivotIndex]);
        }
        
        // All elements after pivot should be > 1
        for (int i = newPivotIndex + 1; i < arr.length; i++) {
            assertTrue(arr[i] > arr[newPivotIndex]);
        }
    }

    @Test
    void testShuffle() {
        int[] original = {1, 2, 3, 4, 5};
        int[] shuffled = original.clone();
        AlgorithmMetrics metrics = new AlgorithmMetrics();
        
        ArrayUtils.shuffle(shuffled, metrics);
        
        // Check that arrays are not equal (very unlikely to be equal after shuffle)
        boolean isDifferent = false;
        for (int i = 0; i < original.length; i++) {
            if (original[i] != shuffled[i]) {
                isDifferent = true;
                break;
            }
        }
        assertTrue(isDifferent, "Array should be different after shuffle");
    }

    @Test
    void testBoundsCheck() {
        int[] arr = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> 
            ArrayUtils.checkArrayBounds(arr, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> 
            ArrayUtils.checkArrayBounds(arr, 0, 3));
        assertThrows(IllegalArgumentException.class, () -> 
            ArrayUtils.checkArrayBounds(arr, 2, 1));
        assertDoesNotThrow(() -> 
            ArrayUtils.checkArrayBounds(arr, 0, 2));
    }
}