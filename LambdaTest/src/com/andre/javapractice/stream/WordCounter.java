package com.andre.javapractice.stream;

/**
 * 包装的单词计数器，记录当前单词数和上一个字符是否为空格的状态。
 * 
 * @author Andre
 */
public class WordCounter {

	private final int counter;

	private final boolean lastSpace;

	public int getCounter() {
		return counter;
	}

	public WordCounter(int counter, boolean lastSpace) {
		this.counter = counter;
		this.lastSpace = lastSpace;
	}

	/**
	 * 计数器本身状态不可变，状态转换就是以新的状态重建。
	 * 
	 * @param c
	 * @return
	 */
	public WordCounter accumulate(Character c) {
		if (Character.isWhitespace(c)) {
			return lastSpace ? this : new WordCounter(counter, true);
		} else {
			return lastSpace ? new WordCounter(counter + 1, false) : this;
		}
	}

	/**
	 * 组合两个计数器。
	 * 
	 * @param wc
	 * @return
	 */
	public WordCounter combine(WordCounter wc) {
		// 只关心单词数。
		return new WordCounter(counter + wc.counter, wc.lastSpace);
	}
}
