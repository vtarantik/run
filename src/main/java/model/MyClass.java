package model;

import instructions.AbstractInstruction;

import java.util.ArrayDeque;
import java.util.Map;

import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Method;

import parser.MembersHelper;
import stack.MyStack;
import stack.StackFrame;

public class MyClass {
	private String className;
	private ConstantPool constantPool;
	private JavaClass[] superClasses;
	private Method[] methods;
	private Field[] fields;
	private Attribute[] attributes;
	private JavaClass[]	interfaces;
	private Map<Method,Attribute[]> methodsAttributes;
	private Map<Method,ArrayDeque<AbstractInstruction>> methodsInstructions;
	
	public MyClass(String className, JavaClass[] superClasses,ConstantPool constantPool, Method[] methods,
			Field[] fields,Attribute[] attributes,JavaClass[] interfaces) {
		super();
		this.className = className;
		this.superClasses = superClasses;
		this.constantPool = constantPool;
		this.methods = methods;
		this.fields = fields;
		this.attributes = attributes;
		this.interfaces = interfaces;
		methodsAttributes = MembersHelper.getMethodsAttributes(this);
		methodsInstructions = MembersHelper.getMethodsInstructions(this);
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public ConstantPool getConstantPool() {
		return constantPool;
	}
	public void setConstantPool(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}
	public Method[] getMethods() {
		return methods;
	}
	public void setMethods(Method[] methods) {
		this.methods = methods;
	}
	public Field[] getFields() {
		return fields;
	}
	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	
	public void runMethod(String methodName){
		Method m = getMethod(methodName);
		if(m!=null){
			StackFrame stackFrame = new StackFrame(m);
			MyStack.getInstance().push(stackFrame);
			ArrayDeque<AbstractInstruction>instructions = methodsInstructions.get(m);
			
			AbstractInstruction[] instructionsArray = new AbstractInstruction[instructions.size()];
			instructions.toArray(instructionsArray);
			
			for (int i = 0; i < instructionsArray.length; i++) {
				AbstractInstruction instructionToBeProcessed = instructionsArray[i];
				if(instructionToBeProcessed.isControl()){
					i = i+instructionToBeProcessed.nextInstructionPositionOffset();
				}
				instructionsArray[i].execute(m);
			}
		}
	}
	
	public Method getMethod(String methodName){
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		throw new IllegalArgumentException("NO SUCH METHOD");
	}
	
	public LocalVariableTable getLocalVariableTable(Method m){
		Attribute[] attributes = methodsAttributes.get(m);
		for (Attribute attr : attributes) {
			if(attr instanceof LocalVariableTable){
				return (LocalVariableTable)attr;
			}
		}
		return null;
	}
	
}
