package Stack;

import java.util.Iterator;
import java.util.Stack;

/*
 * Question : http://www.geeksforgeeks.org/sort-a-stack-using-recursion/
 * Author : vzhao
 */
public class SortStackInRecursion {
	public void sortStack(Stack<Integer> stack) {
		if(stack == null || stack.isEmpty())
			return;
		int temp = stack.pop();
		sortStack(stack);
		sortInsert(stack,temp);
	}
	
	public void sortInsert(Stack<Integer> stack, int value) {
		if(stack.isEmpty() || value > stack.peek()) {
			stack.push(value);
		} else {
			int temp = stack.pop();
			sortInsert(stack, value);
			stack.push(temp);
		}
	}
	
	public static void main(String[] args) {
		Stack<Integer> input = new Stack<Integer>();
		input.push(30);
		input.push(-5);
		input.push(18);
		input.push(14);
		input.push(3);
		SortStackInRecursion sort = new SortStackInRecursion();
		sort.sortStack(input);
		// In java, the iterator of the stack starts from the bottom of the stack
		Iterator<Integer> iterator = input.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next() +" ");
		}
		System.out.println();
		while(!input.isEmpty()){
			System.out.print(input.pop() +" ");
		}
	}
}
