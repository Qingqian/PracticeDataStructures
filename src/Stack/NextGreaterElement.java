package Stack;

import java.util.*;

/*
 * Question : http://www.geeksforgeeks.org/next-greater-element/
 * Author : vzhao
 */

public class NextGreaterElement {
	// This solution takes linear time and space. The naive solution will take O(n^2) time but constant space  
	public HashMap<Integer, Integer> nextGreaterEle(int[] input) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		if(input == null || input.length ==0)
			return map;
		for(int i=0;i<input.length;i++) {
			while(!stack.isEmpty() && stack.peek() < input[i]) {
				map.put(stack.pop(), input[i]);
			}
			stack.push(input[i]);
		}
		while(!stack.isEmpty()) {
			map.put(stack.pop(), -1);
		}
		return map;
		
	}
	public static void main(String[] args) {
		int[] input1= {11, 13, 21, 3};
		NextGreaterElement nge = new NextGreaterElement();
		HashMap<Integer, Integer> map1 = nge.nextGreaterEle(input1);
		for(int key : map1.keySet()) {
			System.out.println(key + " -> " + map1.get(key));
		}
	}
}
