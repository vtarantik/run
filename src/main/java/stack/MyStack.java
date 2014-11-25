package stack;

import java.util.Stack;

import model.MemberType;

import org.apache.bcel.generic.Instruction;

public class MyStack {
	private static MyStack instance;
	private static Stack<Object> variableStack;
	private static Stack<Object> operationsStack;

	private MyStack() {
		variableStack = new Stack<Object>();
		operationsStack = new Stack<Object>();
	}

	public static MyStack getInstance() {
		if (instance == null) {
			return new MyStack();
		} else {
			return instance;
		}
	}

	public void push(MemberType type, Object object) {
		switch (type) {
		case VARIABLE:
			variableStack.push(object);
			break;
		case OPERATION:
			operationsStack.push(object);
			break;
		default:
			throw new IllegalArgumentException(
					"Poping unsupported type from stack");
		}
	}

	public Object pop(MemberType type) {
		switch (type) {
		case VARIABLE:
			return variableStack.pop();
		case OPERATION:
			return operationsStack.pop();
		}
		throw new IllegalArgumentException("Poping unsupported type from stack");
	}

}
