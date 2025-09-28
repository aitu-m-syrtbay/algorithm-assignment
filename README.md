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

(This section will be updated with actual measurements and plots)

### Time Complexity Analysis

### Recursion Depth Analysis

### Constant Factor Effects

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