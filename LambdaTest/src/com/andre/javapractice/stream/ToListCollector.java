package com.andre.javapractice.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 将Stream<T>转为List<T>，即Collectors.toList()方法的实现示例。
 * Collector<T, A, R>
 * 
 * @author Andre
 *
 * @param <T>
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	/**
	 * 建立新的结果容器，可变累积容器。
	 * 
	 * @return
	 */
	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	/**
	 * 累积操作，传入Supplier提供的容器和流中项目，归约。
	 * 
	 * @return
	 */
	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}

	/**
	 * 组合操作，用于并行情况，将两部分结果组合到一起。
	 * 
	 * @return
	 */
	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2) ->
		{
			list1.addAll(list2);
			return list1;
		};
	}

	/**
	 * 将可变累积容器A转换为最终结果容器R。
	 * 
	 * @return
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {
		// 本例下此处为恒等操作。
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		// 对于本例，非无序、可并行（仅在无序源情况下）、恒等转换。
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
	}

}
