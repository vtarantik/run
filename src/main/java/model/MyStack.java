package model;

import java.util.Stack;

import org.apache.bcel.generic.Instruction;

public class MyStack {
	private static MyStack instance;
	private static Stack<Object> operationsStack;
	
	private MyStack(){
		operationsStack = new Stack<Object>();
	}
	
	public static MyStack getInstance(){
		if(instance == null){
			return new MyStack();
		}else{
			return instance;
		}
	}
	
	public void push(Object i){
		operationsStack.push(i);
	}
	
	public Object pop(){
		return operationsStack.pop();
	}
	
}
