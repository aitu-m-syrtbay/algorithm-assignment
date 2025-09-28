package com.algorithms.geometry;

import com.algorithms.metrics.AlgorithmMetrics;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    private final AlgorithmMetrics metrics;

    public ClosestPair(AlgorithmMetrics metrics) {
        this.metrics = metrics;
    }

    public double findClosestDistance(Point[] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("Need at least 2 points");
        }

        metrics.startTiming();
        
        // Sort points by x coordinate
        Point[] pointsByX = points.clone();
        Arrays.sort(pointsByX);
        metrics.incrementAllocations();  // for clone

        // Sort points by y coordinate
        Point[] pointsByY = points.clone();
        Arrays.sort(pointsByY, Comparator.comparingDouble(p -> p.y));
        metrics.incrementAllocations();  // for clone

        double result = closestPairRec(pointsByX, pointsByY, 0, points.length - 1);
        metrics.stopTiming();
        return result;
    }

    private double closestPairRec(Point[] pointsByX, Point[] pointsByY, int start, int end) {
        metrics.incrementDepth();

        int count = end - start + 1;
        if (count <= 3) {
            metrics.decrementDepth();
            return bruteForce(pointsByX, start, end);
        }

        int mid = start + (end - start) / 2;
        double medianX = pointsByX[mid].x;

        // Split points into left and right halves, maintaining y-sort
        Point[] leftByY = new Point[mid - start + 1];
        Point[] rightByY = new Point[end - mid];
        metrics.incrementAllocations(); // for left array
        metrics.incrementAllocations(); // for right array
        
        int li = 0, ri = 0;
        for (Point p : pointsByY) {
            metrics.incrementComparisons();
            if (p.x <= medianX && li < leftByY.length) {
                leftByY[li++] = p;
            } else if (ri < rightByY.length) {
                rightByY[ri++] = p;
            }
        }

        double leftMin = closestPairRec(pointsByX, leftByY, start, mid);
        double rightMin = closestPairRec(pointsByX, rightByY, mid + 1, end);
        double delta = Math.min(leftMin, rightMin);

        // Find points in the strip
        Point[] strip = new Point[count];
        metrics.incrementAllocations();
        int j = 0;
        
        for (Point p : pointsByY) {
            metrics.incrementComparisons();
            if (Math.abs(p.x - medianX) < delta) {
                strip[j++] = p;
            }
        }

        // Check points in the strip
        double minDist = delta;
        for (int i = 0; i < j; i++) {
            // Check only next 7 points (proof: only 7 points can fit in the rectangle)
            for (int k = i + 1; k < j && k < i + 8; k++) {
                metrics.incrementComparisons();
                double dist = strip[i].distanceTo(strip[k]);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }

        metrics.decrementDepth();
        return minDist;
    }

    private double bruteForce(Point[] points, int start, int end) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end; j++) {
                metrics.incrementComparisons();
                double dist = points[i].distanceTo(points[j]);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }
}