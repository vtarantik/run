package stack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import model.MyLocalVariable;

import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Method;

public class StackFrame {
	
	private Map<Integer,MyLocalVariable> localVariables;
	private Stack<Object> operandStack;
	private Method ownerMethod;
	
	public StackFrame(Method ownerMethod){
		this.ownerMethod = ownerMethod;
		localVariables = getLocalVariables();
		
		operandStack = new Stack<Object>();
	}
	
	public void pushOperand(Object variable){
		operandStack.push(variable);
	}
	public Object popOperand(){
		return operandStack.pop();
	}
	public Object peekOnOperand(){
		return operandStack.peek();
	}
	
	public int getOperandStackSize(){
		return operandStack.size();
	}
	
	public void setLocalVariableValue(int variableIndex, Object value){
		localVariables.get(variableIndex).setValue(value);
	}
	public Object getLocalVariableValue(int index){
		return localVariables.get(index).getValue();
	}
	
	private HashMap<Integer,MyLocalVariable> getLocalVariables(){
		HashMap<Integer, MyLocalVariable> localVariablesToReturn = new HashMap<Integer, MyLocalVariable>();
		LocalVariableTable localVariableTable = getLocalVariableTable();
		LocalVariable[] localVars= localVariableTable.getLocalVariableTable();
		for (LocalVariable localVariable : localVars) {
			localVariablesToReturn.put(localVariable.getIndex(), new MyLocalVariable(localVariable));
		}
		return localVariablesToReturn;
	}
	
	private LocalVariableTable getLocalVariableTable(){
		Attribute[] attributes = ownerMethod.getCode().getAttributes();
		for (Attribute attr : attributes) {
			if(attr instanceof LocalVariableTable){
				return (LocalVariableTable)attr;
			}
		}
		return null;
	}
}
