package com.algorithms.geometry;

import org.junit.jupiter.api.Test;
import com.algorithms.metrics.AlgorithmMetrics;
import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {
    
    @Test
    void testBasicCase() {
        Point[] points = {
            new Point(0, 0),
            new Point(1, 1),
            new Point(2, 2),
            new Point(0, 2)
        };
        
        ClosestPair cp = new ClosestPair(new AlgorithmMetrics());
        assertEquals(Math.sqrt(2), cp.findClosestDistance(points), 0.0001);
    }

    @Test
    void testVerticalPair() {
        Point[] points = {
            new Point(1, 0),
            new Point(1, 1),
            new Point(5, 5)
        };
        
        ClosestPair cp = new ClosestPair(new AlgorithmMetrics());
        assertEquals(1.0, cp.findClosestDistance(points), 0.0001);
    }

    @Test
    void testHorizontalPair() {
        Point[] points = {
            new Point(0, 1),
            new Point(1, 1),
            new Point(5, 5)
        };
        
        ClosestPair cp = new ClosestPair(new AlgorithmMetrics());
        assertEquals(1.0, cp.findClosestDistance(points), 0.0001);
    }

    @Test
    void testStripCase() {
        Point[] points = {
            new Point(0, 0),
            new Point(3, 0),
            new Point(1.5, 1),
            new Point(1.6, 1.1)
        };
        
        ClosestPair cp = new ClosestPair(new AlgorithmMetrics());
        assertEquals(0.141, cp.findClosestDistance(points), 0.001);
    }

    @Test
    void testLargeDataset() {
        Point[] points = new Point[1000];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(i, i * i % 100);
        }
        // Add two very close points
        points[500] = new Point(50.0, 50.0);
        points[501] = new Point(50.1, 50.0);
        
        ClosestPair cp = new ClosestPair(new AlgorithmMetrics());
        assertEquals(0.1, cp.findClosestDistance(points), 0.0001);
    }

    @Test
    void testInvalidInput() {
        ClosestPair cp = new ClosestPair(new AlgorithmMetrics());
        
        assertThrows(IllegalArgumentException.class, () -> 
            cp.findClosestDistance(new Point[0]));
        
        assertThrows(IllegalArgumentException.class, () -> 
            cp.findClosestDistance(new Point[]{new Point(0, 0)}));
        
        assertThrows(IllegalArgumentException.class, () -> 
            cp.findClosestDistance(null));
    }
}