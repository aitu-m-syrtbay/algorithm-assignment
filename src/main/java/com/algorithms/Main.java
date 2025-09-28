package com.algorithms;

import com.algorithms.metrics.AlgorithmMetrics;
import com.algorithms.metrics.MetricsCollector;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar algorithms.jar <algorithm> <size>");
            System.exit(1);
        }

        String algorithm = args[0];
        int size = Integer.parseInt(args[1]);
        
        // This is a placeholder main class that will be expanded as we implement
        // the various algorithms. For now, it demonstrates the metrics framework.
        AlgorithmMetrics metrics = new AlgorithmMetrics();
        MetricsCollector collector = MetricsCollector.createDefaultCollector();
        
        // Example usage of metrics (will be replaced with actual algorithm calls)
        metrics.startTiming();
        metrics.incrementDepth();
        metrics.incrementComparisons();
        metrics.incrementAllocations();
        metrics.decrementDepth();
        metrics.stopTiming();
        
        // Record measurements
        collector.addMetricRow(algorithm, size, metrics);
        
        try {
            collector.writeToCSV(Path.of("measurements.csv"));
            System.out.println(metrics);
        } catch (Exception e) {
            System.err.println("Error writing metrics: " + e.getMessage());
        }
    }
}