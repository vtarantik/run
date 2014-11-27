package stack;

import java.util.List;
import java.util.Stack;

import org.apache.bcel.classfile.Method;

public class StackFrame {
	
	private List<Object> localVariables;
	private Stack<Object> operandStack;
	
	public StackFrame(){
		operandStack = new Stack<Object>();
	}
}
