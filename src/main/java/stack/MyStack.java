package stack;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.Map;

import model.MemberType;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.Instruction;

public class MyStack {
	private static MyStack instance;
	private Stack<StackFrame> frames;
	
	private MyStack() {
		frames = new Stack<StackFrame>();
	}

	public static MyStack getInstance() {
		if (instance == null) {
			return new MyStack();
		} else {
			return instance;
		}
	}

	public StackFrame peek(){
		return frames.peek();
	}
	
	public StackFrame pop(){
		return frames.pop();
	}
	public void push(StackFrame stackFrame){
		frames.push(stackFrame);
	}

}
