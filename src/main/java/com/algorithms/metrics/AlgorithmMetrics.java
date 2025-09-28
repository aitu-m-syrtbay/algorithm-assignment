package com.algorithms.metrics;

/**
 * Tracks performance metrics for algorithm execution
 */
public class AlgorithmMetrics {
    private long comparisons;
    private long allocations;
    private int maxDepth;
    private int currentDepth;
    private long startTime;
    private long endTime;

    public AlgorithmMetrics() {
        reset();
    }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
        currentDepth = 0;
        startTime = 0;
        endTime = 0;
    }

    public void startTiming() {
        startTime = System.nanoTime();
    }

    public void stopTiming() {
        endTime = System.nanoTime();
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementAllocations() {
        allocations++;
    }

    public void incrementDepth() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void decrementDepth() {
        if (currentDepth > 0) {
            currentDepth--;
        }
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getAllocations() {
        return allocations;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getExecutionTimeNanos() {
        return endTime - startTime;
    }

    public double getExecutionTimeMillis() {
        return getExecutionTimeNanos() / 1_000_000.0;
    }

    @Override
    public String toString() {
        return String.format(
            "Execution time: %.2f ms%n" +
            "Comparisons: %d%n" +
            "Allocations: %d%n" +
            "Max recursion depth: %d",
            getExecutionTimeMillis(),
            comparisons,
            allocations,
            maxDepth
        );
    }
}