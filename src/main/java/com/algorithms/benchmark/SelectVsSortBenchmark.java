package com.algorithms.benchmark;

import com.algorithms.metrics.AlgorithmMetrics;
import com.algorithms.select.Select;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
public class SelectVsSortBenchmark {

    @Param({"100", "1000", "10000", "100000"})
    private int size;

    @Param({"25", "50", "75"}) // percentiles to find
    private int percentile;

    private int[] arrayForSelect;
    private int[] arrayForSort;
    private Select selector;
    private Random random;

    @Setup(Level.Iteration)
    public void setup() {
        random = new Random(42); // Fixed seed for reproducibility
        arrayForSelect = new int[size];
        arrayForSort = new int[size];
        
        // Fill arrays with same random data
        for (int i = 0; i < size; i++) {
            int value = random.nextInt();
            arrayForSelect[i] = value;
            arrayForSort[i] = value;
        }
        
        selector = new Select(new AlgorithmMetrics());
    }

    @Benchmark
    public void selectMethod(Blackhole blackhole) {
        int k = (size * percentile) / 100;
        blackhole.consume(selector.select(arrayForSelect, k));
    }

    @Benchmark
    public void sortMethod(Blackhole blackhole) {
        int k = (size * percentile) / 100;
        Arrays.sort(arrayForSort);
        blackhole.consume(arrayForSort[k]);
    }
}