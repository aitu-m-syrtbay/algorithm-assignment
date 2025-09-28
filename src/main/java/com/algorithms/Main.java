package com.algorithms;

import com.algorithms.geometry.ClosestPair;
import com.algorithms.geometry.Point;
import com.algorithms.metrics.AlgorithmMetrics;
import com.algorithms.metrics.MetricsCollector;
import com.algorithms.select.Select;
import com.algorithms.sort.MergeSort;
import com.algorithms.sort.QuickSort;

import java.io.*;
import java.nio.file.Path;
import java.util.Random;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java -jar algorithms.jar <algorithm> <size> <output.csv>");
            System.out.println("Algorithms: mergesort, quicksort, select, closest");
            System.exit(1);
        }

        String algorithm = args[0].toLowerCase();
        int size = Integer.parseInt(args[1]);
        String outputFile = args[2];

        AlgorithmMetrics metrics = new AlgorithmMetrics();
        MetricsCollector collector = MetricsCollector.createDefaultCollector();

        try {
            // Reset metrics before each run
            metrics.reset();
            
            switch (algorithm) {
                case "mergesort":
                    runMergeSort(size, metrics);
                    break;
                case "quicksort":
                    runQuickSort(size, metrics);
                    break;
                case "select":
                    runSelect(size, metrics);
                    break;
                case "closest":
                    runClosestPair(size, metrics);
                    break;
                default:
                    System.out.println("Unknown algorithm: " + algorithm);
                    System.exit(1);
            }

            // Record metrics
            collector.addMetricRow(algorithm, size, metrics);
            
            // Write to CSV, appending if file exists
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true))) {
                // Write header only if file is empty
                if (new File(outputFile).length() == 0) {
                    writer.println(String.join(",", MetricsCollector.getHeaders()));
                }
                // Write the last measurement
                String[] lastRow = collector.getLastMeasurement();
                if (lastRow != null) {
                    writer.println(String.join(",", lastRow));
                }
            }

            // Print summary to console
            System.out.println("Algorithm: " + algorithm);
            System.out.println("Input size: " + size);
            System.out.println(metrics);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void runMergeSort(int size, AlgorithmMetrics metrics) {
        int[] arr = generateRandomArray(size);
        MergeSort sorter = new MergeSort(size, metrics);
        sorter.sort(arr);
    }

    private static void runQuickSort(int size, AlgorithmMetrics metrics) {
        int[] arr = generateRandomArray(size);
        QuickSort sorter = new QuickSort(metrics);
        sorter.sort(arr);
    }

    private static void runSelect(int size, AlgorithmMetrics metrics) {
        int[] arr = generateRandomArray(size);
        Select selector = new Select(metrics);
        // Find median
        selector.select(arr, size / 2);
    }

    private static void runClosestPair(int size, AlgorithmMetrics metrics) {
        Point[] points = generateRandomPoints(size);
        ClosestPair finder = new ClosestPair(metrics);
        finder.findClosestDistance(points);
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    private static Point[] generateRandomPoints(int size) {
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            points[i] = new Point(random.nextDouble() * 1000, random.nextDouble() * 1000);
        }
        return points;
    }
}