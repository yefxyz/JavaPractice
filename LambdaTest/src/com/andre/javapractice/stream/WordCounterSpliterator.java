package com.andre.javapractice.stream;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 自定义字符串可拆分迭代器，只在空格处拆分。
 * 
 * @author Andre
 */
public class WordCounterSpliterator implements Spliterator<Character> {

	private final String s;
	
	private int currentChar = 0;
	
	public WordCounterSpliterator(String s) {
		this.s = s;
	}
	
	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		// 处理当前字符，游标前进。
		action.accept(s.charAt(currentChar++));
		// 若还有未处理字符，返回true。
		return currentChar < s.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		// 尚未处理的字符串长度。
		int currentSize = s.length() - currentChar;
		// 字符串长度小于10，停止拆分，返回null。
		if (currentSize < 10) {
			return null;
		}
		// 定位到尚未处理的字符串中间，找到下一个空格作为拆分点。
		for (int splitPos = currentChar + currentSize / 2; splitPos < s.length(); splitPos++) {
			if (Character.isWhitespace(s.charAt(splitPos))) {
				// 创建一个新的WordCounterSpliterator来解析拆分的部分。
				Spliterator<Character> spliterator = new WordCounterSpliterator(s.substring(currentChar, splitPos));
				// 将当前WordCounterSpliterator的游标移动到拆分位置。
				currentChar = splitPos;
				return spliterator;
			}
		}
		return null;
	}

	@Override
	public long estimateSize() {
		// 待处理长度。
		return s.length() - currentChar;
	}

	@Override
	public int characteristics() {
		// 有序。
		// estimateSize返回准确值。
		// 拆分后也有准确大小。
		// 元素非空。
		// 源不可变。
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}

}
