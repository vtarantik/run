package heap;

import java.util.Stack;


public class MyHeap {
	private static MyHeap instance;
	private static Stack<Object> variableStack;
	private static Stack<Object> operationsStack;

	private MyHeap() {
		variableStack = new Stack<Object>();
		operationsStack = new Stack<Object>();
	}

	public static MyHeap getInstance() {
		if (instance == null) {
			return new MyHeap();
		} else {
			return instance;
		}
	}
}