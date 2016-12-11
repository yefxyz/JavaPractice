package com.andre.javapractice.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 函数式编程。
 * 
 * @author Andre
 */
public class FunctionalTest {

	/**
	 * 对一个整数集合，求其所有子集。<br />
	 * 比如{1, 2, 3}，返回{1, 2, 3}、{1, 2}、{1, 3}、{2, 3}、{1}、{2}、{3}、{}。
	 * 
	 * @param list
	 * @return
	 */
	private static List<List<Integer>> subsets(List<Integer> list) {
		// ---- 递归实现 ---- //
		if (list.isEmpty()) {
			// 若输入为空集，返回结果只包含空集。
			List<List<Integer>> ans = new ArrayList<>();
			ans.add(Collections.emptyList());
			return ans;
		}
		// 取出第一个元素。
		Integer first = list.get(0);
		List<Integer> rest = list.subList(1, list.size());
		// 最终结果分为两部分：不包含第一个元素的剩余集合的子集，以及把第一个元素插入到前述所有子集中得到的集合。
		List<List<Integer>> subans1 = subsets(rest);
		List<List<Integer>> subans2 = insertAll(first, subans1);
		return concat(subans1, subans2);
	}
	
	private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
		List<List<Integer>> result = new ArrayList<>();
		for (List<Integer> list : lists) {
			List<Integer> tempList = new ArrayList<>();
			tempList.add(first);
			tempList.addAll(list);
			result.add(tempList);
		}
		return result;
	}
	
	private static List<List<Integer>> concat(List<List<Integer>> list1, List<List<Integer>> list2) {
		List<List<Integer>> result = new ArrayList<>(list1);
		result.addAll(list2);
		return result;
	}
	
	public static void main(String[] args) {
		List<List<Integer>> resList = subsets(Arrays.asList(1, 3, 5));
		resList.stream().forEach(System.out::println);
	}

}
