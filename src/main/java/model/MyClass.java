package model;

import instructions.AbstractInstruction;

import java.util.ArrayDeque;
import java.util.Map;

import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

import parser.MembersHelper;

public class MyClass {
	private String className;
	private ConstantPool constantPool;
	private Method[] methods;
	private Field[] fields;
	private Attribute[] attributes;
	private JavaClass[]	interfaces;
	private Map<Method,ArrayDeque<AbstractInstruction>> methodsInstructions;
	
	public MyClass(String className, ConstantPool constantPool, Method[] methods,
			Field[] fields,Attribute[] attributes,JavaClass[] interfaces) {
		super();
		this.className = className;
		this.constantPool = constantPool;
		this.methods = methods;
		this.fields = fields;
		this.attributes = attributes;
		this.interfaces = interfaces;
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
	public void runInit(){
		Method m = getInitMethod();
		ArrayDeque<AbstractInstruction>instructions = methodsInstructions.get(m);
		for (AbstractInstruction abstractInstruction : instructions) {
			abstractInstruction.execute();
		}
	}
	
	public void runMain(){
		Method m = getMainMethod();
		ArrayDeque<AbstractInstruction>instructions = methodsInstructions.get(m);
		for (AbstractInstruction abstractInstruction : instructions) {
			abstractInstruction.execute();
		}
	}
	
	private Method getMainMethod(){
		for (Method  m : methods) {
			if (m.getName().equals("main")) {
				return m;
			}
		}
		throw new IllegalArgumentException("NO MAIN METHOD");
	}
	
	private Method getInitMethod(){
		for (Method method : methods) {
			if (method.getName().equals("<init>")) {
				return method;
			}
		}
		throw new IllegalArgumentException("NO CONSTRUCTOR");
	}
}
