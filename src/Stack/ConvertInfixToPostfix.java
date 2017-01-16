package Stack;

import java.util.*;
/*
 * Question: http://quiz.geeksforgeeks.org/stack-set-2-infix-to-postfix/
 * Author : vzhao
 * Noted : The following solution will only work if the representation of a number is a letter. 
 * 		   Otherwise, you should use array or a list to represent the number
 */
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
	
	public static int postfixEvaluation(ArrayList<String> input) {
        Stack<Integer> stack = new Stack<Integer>();
		// Question to interviewer : what should we return if the input string is an empty string?
		if(input == null || input.size() == 0) {
			 return -1;
		}
		for(int i=0;i<input.size();i++) {
            String current = input.get(i);
            if(current.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if(current.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b-a);
            } else if(current.equals("*")) {
                stack.push(stack.pop()*stack.pop());
            } else if(current.equals("/")){
                int a  =stack.pop();
                int b = stack.pop();
                stack.push(b/a);
            } else {
                stack.push(Integer.parseInt(current));
            }
        }
        return stack.peek();
    }
    
    public static ArrayList<String> convertInfixToPostfix(String input) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		LinkedList<Character> stack = new LinkedList<Character>();
		ArrayList<String> result = new ArrayList<String>();
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		int num = 0;
		for (int i = 0; i < input.length(); i++) {
			char current = input.charAt(i);
			if (Character.isDigit(current)) {
			    num = num*10 + (current -'0');
			}
			else {
			    result.add(String.valueOf(num));
			    num = 0;
				while (!stack.isEmpty() && map.containsKey(current) && map.containsKey(stack.peek()) && map.get(stack.peek()) >= map.get(current)) {
					result.add(String.valueOf(stack.pop()));
				}
				stack.push(current);
			}
		}
		while(!stack.isEmpty()) {
			result.add(String.valueOf(stack.pop()));
		}
		return result;
    }
}
