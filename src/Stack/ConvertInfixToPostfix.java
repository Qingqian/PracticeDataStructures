package Stack;

import java.util.*;

public class ConvertInfixToPostfix {
	public String InfixToPostfix(String input) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		LinkedList<Character> stack = new LinkedList<Character>();
		StringBuilder result = new StringBuilder();
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		map.put('^', 3);
		if (input == null || input.length() == 0) {
			return "";
		}
		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt(i);
			if (Character.isLetter(current)) {
				result.append(current);
			} else if (current == '(') {
				stack.push(current);
			} else if (current == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					result.append(stack.pop());
				}
				// Pop the opening parenthesis
				stack.pop();
			} else {
				while (!stack.isEmpty() && map.containsKey(current) && map.containsKey(stack.peek()) && map.get(stack.peek()) >= map.get(current)) {
					result.append(stack.pop());
				}
				stack.push(current);
			}
		}
		while(!stack.isEmpty()) {
			result.append(stack.pop());
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String input = "a+b*(c^d-e)^(f+g*h)-i";
		ConvertInfixToPostfix convert = new ConvertInfixToPostfix();
		String postfix = convert.InfixToPostfix(input);
		System.out.println(postfix);
	}
}
