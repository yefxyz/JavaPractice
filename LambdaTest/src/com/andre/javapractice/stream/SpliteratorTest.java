package com.andre.javapractice.stream;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorTest {

	public static final String SENTENCE = "The Wheel of Time turns, and Ages come and pass, leaving memories that become legend. Legend fades to myth, and even myth is long forgotten when the Age that gave it birth comes again. "
			+ "In one Age, called the Third Age by some, an Age yet to come, an Age long past, a wind rose in the Mountains of Mist. The wind was not the beginning. There are neither beginnings nor endings to the turning of the Wheel of Time. "
			+ "But it was a beginning.";

	/**
	 * 计算字符串中单词的数量，迭代式。
	 * 
	 * @param s
	 * @return
	 */
	public static int countWordsIteratively(String s) {
		int counter = 0;
		// 默认为true，为第一个单词计数。
		boolean lastSpace = true;
		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				// 当前字符不是空格而前一个是，单词计数+1。
				// 单词间有多个空格也能适应。
				if (lastSpace) {
					counter++;
					lastSpace = false;
				}
			}
		}
		return counter;
	}
	
	public static int countWords(Stream<Character> stream) {
		// 可并行的归约，接受三个参数。
		return stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine).getCounter();
	}

	public static void main(String[] args) {
		// 正确结果： Found 85 words.
		// System.out.println("Found " + countWordsIteratively(SENTENCE) + " words.");
		
		Stream<Character> stream1 = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
		// 顺序处理。
		// System.out.println("Found " + countWords1(stream1) + " words.");
		// 尝试直接并行处理，但结果错误。
		// Found 105 words. 因为分解字符串时可能错误地从一个单词中间拆分。
		System.out.println("Found " + countWords(stream1.parallel()) + " words.");
		
		// 使用自定义可拆分迭代器，并行处理。
		Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
		// 生成一个并行流。
		Stream<Character> stream2 = StreamSupport.stream(spliterator, true);
		System.out.println("Found " + countWords(stream2) + " words.");
	}

}
