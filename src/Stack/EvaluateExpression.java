package Stack;

import java.util.*;

/* 
 * Question : http://www.geeksforgeeks.org/expression-evaluation/
 * Author : vzhao
 * This solution takes O(n) time complexity and O(n) space complexity for the extra stack
 */
public class EvaluateExpression {
	
	public int expressionEvaluation(String input) {
		if(input == null || input.length() ==0 ) {
			return -1;
		}
		// first stack stores the integer values and second stack stores the operators
		LinkedList<Integer> values = new LinkedList<Integer>();
		LinkedList<Character> operators = new LinkedList<Character>();
		// We will use map to stores the precedency of the operators
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('+',1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		map.put('^', 3);
		
		for(int i=0;i<input.length();i++) {
			char current = input.charAt(i);
			// Skip if the current char is ' '
			if(current ==' ') {
				continue;
			}
			if(Character.isDigit(current)) {
				StringBuilder str = new StringBuilder();
				while(i < input.length() && Character.isDigit(input.charAt(i))) {
					str.append(input.charAt(i));
					i++;
				}
				// We need to decrease i by 1 since it points to the next index after while loop
				i--;
				values.push(Integer.parseInt(str.toString()));
			} else if(current == '(') {
				operators.push(current);
			} else if(current == ')') {
				while(!operators.isEmpty() && operators.peek() != '(') {
					values.push(evaluateOperator(operators.pop(), values.pop(), values.pop()));
				}
				// Pop out the opening parenthesis
				operators.pop();
			} else {
				while(!operators.isEmpty() && map.containsKey(current) && map.containsKey(operators.peek()) && map.get(operators.peek()) >= map.get(current)) {
					values.push(evaluateOperator(operators.pop(), values.pop(), values.pop()));
				}
				operators.push(current);
			}
		}
		while(!operators.isEmpty()) {
			values.push(evaluateOperator(operators.pop(), values.pop(), values.pop()));
		}
		return values.peek();
	}
	
	public int evaluateOperator(char operator, int operand1, int operand2) {
		int result = 0;
		switch(operator) {
		case '+': 
			result = operand2 + operand1;
			break;
		case '-':
			result = operand2 - operand1;
			break;
		case '*':
			result = operand2 * operand1;
			break;
		case '/': 
			if(operand1 ==0) {
				throw new ArithmeticException();
			} else {
				result = operand2 / operand1;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String input = "100 * ( 2 + 12 ) / 14";
		EvaluateExpression exp = new EvaluateExpression();
		System.out.println(exp.expressionEvaluation(input));
	}
}
