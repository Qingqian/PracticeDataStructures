package Stack;

import java.util.EmptyStackException;

/*
 * Question : http://www.geeksforgeeks.org/efficiently-implement-k-stacks-single-array/
 * Author : vzhao
 * Reference : ByteByByte --https://www.youtube.com/watch?v=DxW7VAsdX0o
 * Idea step-by-step
 * 	1. Think of how can we implement 1 stack in a single array
 * 	2. Think of how can we implement 2 stacks in a single array in a more space-efficient way (hint: leftmost and rightmost pointers)
 * 	3. Think of how can we implement k stacks in a single array
 * Solution details
 * We are going to keep 3 arrays 
 * 	1. topOfStack[] -- stores the index of the top element in each stack
 * 	2. stackData[]  -- stores the actual data in the single array
 * 	3. nextIndex[]  -- If there is no value set at a specific index, it stores the next available index in the stackData[]
 * 			-- If value has already been set at a specific index, it stores the previous index, which will be used in pop operation
 */
public class KStacksInSingleArray {
	// Space complexity is O(n + k)
	int[] topOfStack;
	int[] stackData;
	int[] nextIndex;
	int nextAvailableIndex;
	
	public KStacksInSingleArray(int stackNumber, int capacity) {
		topOfStack = new int[stackNumber];
		for(int i=0;i<stackNumber;i++)
			topOfStack[i] = -1;
		stackData = new int[capacity];
		nextIndex = new int[capacity];
		for(int i=0;i<capacity-1;i++) 
			nextIndex[i] = i+1;
		//For the next element, the next available index is -1, which means the array is full 
		//and there is no space to push additional element
		nextIndex[capacity-1] = -1;
		nextAvailableIndex = 0;
	}
	// Time complexity is O(1)
	public void push(int stack, int value) {
		// Question to interview: how do we handle the corner cases? 
		// Are we going to ignore it sliently or are we going to throw an execption?
		if(stack < 0|| stack >= topOfStack.length)
			throw new IndexOutOfBoundsException();
		if(nextAvailableIndex < 0)
			return;
		int currentIndex = nextAvailableIndex;
		nextAvailableIndex = nextIndex[currentIndex];
		//set the value of the nextIndex[] to the previous index
		nextIndex[currentIndex] = topOfStack[stack];
		stackData[currentIndex] = value;		
		topOfStack[stack] = currentIndex;			
	}
	
	// Time Complexity is O(1)
	public int pop(int stack) {
		if(stack < 0 || stack >= topOfStack.length)
			throw new IndexOutOfBoundsException();
		//Check if the stack'th stack is empty or not. If yes, throw EmptyStackException
		if(topOfStack[stack] <0)
			throw new EmptyStackException();
		int currentIndex = topOfStack[stack];
		int value = stackData[currentIndex];
		// set the index to the previous one
		topOfStack[stack] = nextIndex[currentIndex];
		// set the index to the next available index if there is not value set at the index
		nextIndex[currentIndex] = nextAvailableIndex;
		// set the next available index to the current index since the value is poped out
		nextAvailableIndex = currentIndex;
		return value;		
	}
	
	public static void main(String[] args) {
		// When writing test case, we need to show interviewer different test cases and go through them one by one
		// 1. invalid test cases that trigger corner case handling
		// 2. General correct test cases
		KStacksInSingleArray kStack = new KStacksInSingleArray(3,10);
		kStack.push(2, 15);
		kStack.push(2,45);
	    // Push data in stack #1
		kStack.push(1,17);
		kStack.push(1, 49);
		kStack.push(1, 39);
	    // push data in stack #0
		kStack.push(0, 11);
		kStack.push(0, 9);
		kStack.push(0,7);
		System.out.println(kStack.pop(2));
		System.out.println(kStack.pop(1));
		System.out.println(kStack.pop(0));
	}

}
