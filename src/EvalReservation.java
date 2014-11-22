import java.util.Stack;


public class EvalReservation {

    public int evalRPN(String[] tokens) {
    	Stack<Integer> stack = new Stack<Integer>();
    	for (int i = 0; i < tokens.length; i++) {
    		String value = tokens[i];
    		if (value.equals("+") ||
    				value.equals("-") ||
    				value.equals("*") ||
    				value.equals("/")) {
    			int op2 = stack.pop().intValue();
    			int op1 = stack.pop().intValue();
    			
    			if (value.equals("+")) {
    				stack.push(op1 + op2);
    			} else if (value.equals("-")) {
    				stack.push(op1 - op2);
    			} else if (value.equals("*")) {
    				stack.push(op1 * op2);
    			} else if (value.equals("/")) {
    				stack.push(op1 / op2);
    			}
    		} else {
    			stack.push(Integer.valueOf(value));
    		}
    	}
    	return stack.pop();
    }
    
    public static void main(String[] args) {
    	System.out.println(new EvalReservation().evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }
}
