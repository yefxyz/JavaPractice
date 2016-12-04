package com.andre.javapractice.stream;

import java.util.concurrent.RecursiveTask;

/**
 * 显式以分支/合并框架实现1-n自然数的求和。
 * 
 * @author Andre
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;

	public static final long THRESHOLD = 10_000;

	private final long[] numbers;
	private final int start;
	private final int end;

	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	private ForkJoinSumCalculator(long[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	private long computeSequentially() {
		long sum = 0;
		for (int i = start; i < end; i++) {
			sum = sum + numbers[i];
		}
		return sum;
	}

	@Override
	protected Long compute() {
		int length = end - start;
		// 小于分支阈值，则进行顺序计算。
		if (length <= THRESHOLD) {
			return computeSequentially();
		}
		// 分支。
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
		leftTask.fork();
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
		Long rightRet = rightTask.compute();
		// 合并。
		Long leftRet = leftTask.join();
		return leftRet + rightRet;
	}
}
