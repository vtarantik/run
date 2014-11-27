package stack;

import java.util.List;
import java.util.Stack;

import model.MemberType;

import org.apache.bcel.generic.Instruction;

public class MyStack {
	private static MyStack instance;
	
	private Stack <StackFrame> stackFrames;
	private MyStack() {
	}

	public static MyStack getInstance() {
		if (instance == null) {
			return new MyStack();
		} else {
			return instance;
		}
	}

	public void push(StackFrame stackFrame) {
		stackFrames.push(stackFrame);
	}

	public StackFrame pop(StackFrame stackFrame) {
		return stackFrames.pop();
	}

}
