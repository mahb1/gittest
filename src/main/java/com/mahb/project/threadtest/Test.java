package com.mahb.project.threadtest;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;

public class Test {

	public static void main(String[] args) {
		
		
		
    }

	
	public static boolean s(char c ) {
		System.out.println(c);
		return true;
	}
	
	/**
	 * 一层循环 遍历 找出  累加的数字
	 */
	public void show () {
		int[] arr= {15,7,8,11,3,20 ,4};
		int sum = 28;
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i = 0; i < arr.length; i++) {
			if(map.containsKey(sum -arr[i])) {
				System.out.println(map.get(sum-arr[i]));
				System.out.println(i);
			}
			map.put(arr[i], i);
		}
		
	}
	
	
	
}
