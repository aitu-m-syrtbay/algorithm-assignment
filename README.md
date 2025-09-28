# Algorithms Assignment

This project implements several classic divide-and-conquer algorithms with performance analysis and benchmarking.

## Algorithms Implemented

1. **MergeSort**
   - Linear merge with reusable buffer
   - Small-n cut-off optimization
   - Master Theorem Case 2 analysis

2. **QuickSort**
   - Randomized pivot selection
   - Tail recursion optimization
   - Bounded stack space O(log n)

3. **Deterministic Select (Median-of-Medians)**
   - O(n) worst-case selection algorithm
   - Groups of 5 elements
   - In-place partitioning

4. **Closest Pair of Points (2D)**
   - O(n log n) divide-and-conquer solution
   - Strip-based merge step
   - Efficient 7-8 neighbor scanning

## Project Structure

```
src/
├── main/java/com/algorithms/
│   ├── sort/
│   │   ├── MergeSort.java
│   │   └── QuickSort.java
│   ├── select/
│   │   └── Select.java
│   ├── geometry/
│   │   └── ClosestPair.java
│   ├── metrics/
│   │   ├── AlgorithmMetrics.java
│   │   └── MetricsCollector.java
│   ├── util/
│   │   └── ArrayUtils.java
│   └── Main.java
└── test/java/com/algorithms/
    ├── sort/
    ├── select/
    └── geometry/
```

## Building and Running

```bash
# Build the project
mvn clean package

# Run with specific algorithm and input size
java -jar target/algorithms-assignment-0.1-SNAPSHOT.jar <algorithm> <size>
```

## Performance Analysis

### Running Performance Tests
```bash
# Build the project
mvn clean compile

# Run all algorithms with different input sizes
for size in 100 1000 10000 100000; do
  echo "Testing with size $size"
  java -cp target/classes com.algorithms.Main mergesort $size results/all_results.csv
  java -cp target/classes com.algorithms.Main quicksort $size results/all_results.csv
  java -cp target/classes com.algorithms.Main select $size results/all_results.csv
  java -cp target/classes com.algorithms.Main closest $size results/all_results.csv
done
```

### Sample Results
Recent benchmark results on a test machine:

| Algorithm | Input Size | Time (ms) | Comparisons | Allocations | Max Depth |
|-----------|------------|-----------|-------------|-------------|-----------|
| mergesort | 100000     | 20.91     | 2276757     | 1           | 15        |
| quicksort | 100000     | 25.31     | 1984083     | 0           | 12        |
| select    | 100000     | 26.29     | 3704171     | 0           | 10786     |
| closest   | 100000     | 196.60    | 5499573     | 103391      | 17        |

### Analysis by Algorithm

#### 1. MergeSort
- **Time Complexity**: Consistent O(n log n) behavior
- **Space Usage**: Single allocation for merge buffer
- **Depth**: Logarithmic growth (5 → 8 → 11 → 15 for sizes 100 to 100K)
- **Optimizations**: Effective small-array cutoff reduces comparisons

#### 2. QuickSort
- **Time Complexity**: Excellent O(n log n) average case
- **Space Usage**: Zero additional allocations
- **Depth**: Slightly better than MergeSort (5 → 7 → 9 → 12)
- **Efficiency**: Fewer comparisons than MergeSort due to in-place partitioning

#### 3. Select (Median-of-Medians)
- **Time Complexity**: Linear time O(n) with higher constant factors
- **Space Usage**: No extra allocations
- **Depth**: Variable, deeper for median finding (27 → 169 → 840 → 10786)
- **Trade-off**: Guaranteed linear time vs higher comparison count

#### 4. Closest Pair
- **Time Complexity**: O(n log n) with significant geometric overhead
- **Space Usage**: Highest memory usage (array cloning for coordinate sorting)
- **Depth**: Logarithmic (7 → 10 → 13 → 17)
- **Performance**: Most compute-intensive due to distance calculations

## Testing Strategy

- Unit tests for correctness
- Property-based testing for invariants
- Performance benchmarks using JMH
- Edge case validation

## GitHub Workflow

### Branches
- `main` - stable releases only
- Feature branches for each algorithm
- Metrics and documentation branches

### Tags
- v0.1 - Initial implementation
- v1.0 - Complete implementation with analysis

## References

1. CLRS (Introduction to Algorithms)
2. Master Theorem and Akra-Bazzi analysis
3. JMH Benchmarking documentation