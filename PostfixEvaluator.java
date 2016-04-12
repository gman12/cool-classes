import java.util.Stack;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class PostfixEvaluator {

	public static String evaluate(String exp) {

		// Construct a stack and token scanner
		Stack<Double> s = new Stack<Double>();
		TokenScanner ts = new TokenScanner(exp);
		Token token;

		// try-catch to look for exceptions
		try {
			while (ts.hasNextToken()) {
				token = ts.nextToken();
				if (token.isNumber()) { // token is a number
					s.push(token.numberValue());
				} else if (token.isOperator()) { // token is an operator
					char operator = token.operatorCharValue();
					double result = performOperations(s, operator);
					s.push(result); // push the result from performOperations()
									// to the stack
				} else if (token.isLeftParen() || token.isRightParen()) {
					System.out.println("( and ) have no meaning here."); 
				} 
			}

			// Catch certain exceptions
		} catch (EmptyStackException e) {
			System.out.println("Stack underflow. Not enough operands on stack.");
		} catch (NoSuchElementException e) {
			return e.getMessage();
		} catch (IllegalArgumentException e) {
			System.out.println("No input.");
		}

		if (s.isEmpty() == false) {
			System.out.println("Computed result, but values remain on stack.");
		}
		if (ts.reachedEnd() == false) {
			System.out.println("Computed result, but not all input used.");
		}

		return s.pop().toString();
	}

	// "Pops" the two top values from the stack and performs the specified
	// operation
	// with the given operator
	private static double performOperations(Stack<Double> s, char op) {

		double num1 = s.pop();
		double num2 = s.pop();
		double answer = 0;

		// Switch statement to perform operations
		switch (op) {
		case '+':
			answer = num2 + num1;
			break;
		case '-':
			answer = num2 - num1;
			break;
		case '*':
			answer = num2 * num1;
			break;
		case '/':
			answer = num2 / num1;
			break;
		}

		return answer;
	}
}
