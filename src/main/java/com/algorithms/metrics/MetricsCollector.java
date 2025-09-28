package com.algorithms.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Collects and writes algorithm performance metrics to CSV files
 */
public class MetricsCollector {
    private final List<String[]> measurements;
    private final String[] headers;

    public MetricsCollector(String... headers) {
        this.headers = headers;
        this.measurements = new ArrayList<>();
    }

    public void addMeasurement(String... values) {
        if (values.length != headers.length) {
            throw new IllegalArgumentException("Number of values must match number of headers");
        }
        measurements.add(values);
    }

    public void writeToCSV(Path filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath.toFile()))) {
            // Write headers
            writer.println(String.join(",", headers));

            // Write measurements
            for (String[] row : measurements) {
                writer.println(String.join(",", row));
            }
        }
    }

    public static MetricsCollector createDefaultCollector() {
        return new MetricsCollector(
            "Algorithm",
            "InputSize",
            "ExecutionTime(ms)",
            "Comparisons",
            "Allocations",
            "MaxDepth"
        );
    }

    public void addMetricRow(String algorithmName, int inputSize, AlgorithmMetrics metrics) {
        addMeasurement(
            algorithmName,
            String.valueOf(inputSize),
            String.format("%.2f", metrics.getExecutionTimeMillis()),
            String.valueOf(metrics.getComparisons()),
            String.valueOf(metrics.getAllocations()),
            String.valueOf(metrics.getMaxDepth())
        );
    }
}