package com.andre.javapractice.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelTest {

	/**
	 * 对1-n的自然数求和。顺序流版本1。
	 * 
	 * @param n
	 * @return
	 */
	public static long sequentialSum1(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
	}

	/**
	 * 对1-n的自然数求和。显式迭代版本。
	 * 
	 * @param n
	 * @return
	 */
	public static long iterativeSum(long n) {
		long ret = 0;
		for (long i = 1L; i <= n; i++) {
			ret = ret + i;
		}
		return ret;
	}

	/**
	 * 对1-n的自然数求和。并行流版本1。
	 * 
	 * @param n
	 * @return
	 */
	public static long parallelSum1(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
	}

	/**
	 * 对1-n的自然数求和。顺序流版本2。
	 * 
	 * @param n
	 * @return
	 */
	public static long sequentialSum2(long n) {
		return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
	}

	/**
	 * 对1-n的自然数求和。并行流版本2。
	 * 
	 * @param n
	 * @return
	 */
	public static long parallelSum2(long n) {
		return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
	}

	/**
	 * 显式分支/合并框架实现的求和。
	 * 
	 * @param n
	 * @return
	 */
	public static long forkJoinSum(long n) {
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
	}

	/**
	 * 测量求和性能。
	 * 
	 * @param adder
	 * @param n
	 * @return
	 */
	public static long measureSumPerf(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		// 执行10次，取最短执行时间。单位：毫秒。
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Result: " + sum);
			if (duration < fastest) {
				fastest = duration;
			}
		}
		return fastest;
	}

	public static void main(String[] args) {
		// Intel i5-4258U @ 2.40GHz, 四核。

		// Sequential sum done in: 116 milliseconds.
		System.out.println("Sequential sum 1 done in: " + measureSumPerf(ParallelTest::sequentialSum1, 10_000_000) + " milliseconds.");

		// Iterative sum done in: 3 milliseconds.
		// 显式迭代更底层，且没有拆箱。
		System.out.println("Iterative sum done in: " + measureSumPerf(ParallelTest::iterativeSum, 10_000_000) + " milliseconds.");

		// Parallel sum 1 done in: 125 milliseconds.
		// iterate生成的是Stream<Long>，需要拆箱，而且iterate操作本身很难并行化处理。
		System.out.println("Parallel sum 1 done in: " + measureSumPerf(ParallelTest::parallelSum1, 10_000_000) + " milliseconds.");

		// Sequential sum 2 done in: 5 milliseconds.
		// 数值流顺序执行，规避拆箱，性能接近显式迭代。
		System.out.println("Sequential sum 2 done in: " + measureSumPerf(ParallelTest::sequentialSum2, 10_000_000) + " milliseconds.");

		// Parallel sum 2 done in: 2 milliseconds.
		// 数值流并行版本，性能超过显式迭代。rangeClosed操作适合并行处理。
		System.out.println("Parallel sum 2 done in: " + measureSumPerf(ParallelTest::parallelSum2, 10_000_000) + " milliseconds.");

		// Fork/Join sum done in: 47 milliseconds.
		// 只是因为需要首先构造完数组才开始计算。
		System.out.println("Fork/Join sum done in: " + measureSumPerf(ParallelTest::forkJoinSum, 10_000_000) + " milliseconds.");
	}

}
