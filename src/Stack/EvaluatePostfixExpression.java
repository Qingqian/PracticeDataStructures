package Stack;

import java.util.Stack;

/*
 * Question : http://quiz.geeksforgeeks.org/stack-set-4-evaluation-postfix-expression/
 * Author : vzhao
 */
public class EvaluatePostfixExpression {
	public int evaluatePostfixExp(String input) {
		Stack<Integer> stack = new Stack<Integer>();
		// Question to interviewer : what should we return if the input string is an empty string?
		if(input == null || input.length() == 0) {
			 return -1;
		}
		for(int i=0;i<input.length();i++) {
			char c = input.charAt(i);
			if(Character.isDigit(c)) {
				stack.push(c-'0');
			} else {
				//Several corner cases to think about : what if the stack is empty? what if the value b is 0 and the operator is /
				int a = stack.pop();
				int b = stack.pop();
				switch(c) {
				// Look careful on the order
				case '+' : stack.push(b+a); break;
				case '-' : stack.push(b-a); break;
				case '*' : stack.push(b*a); break;
				case '/' : stack.push(b/a); break;
				}
			}
		}
		return stack.peek();
	}
	public static void main(String[] args) {
		String input = "231*+9-";
		EvaluatePostfixExpression exp = new EvaluatePostfixExpression();
		System.out.println(exp.evaluatePostfixExp(input));
		
	}
}
