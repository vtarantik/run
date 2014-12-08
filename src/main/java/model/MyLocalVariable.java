package model;

import org.apache.bcel.classfile.LocalVariable;;

public class MyLocalVariable{
	private LocalVariable localVariable;
	private Object value;
	
	public MyLocalVariable(LocalVariable localVariable){
		this.localVariable = localVariable;
		value = null;
	}

	public LocalVariable getLocalVariable() {
		return localVariable;
	}

	public void setLocalVariable(LocalVariable localVariable) {
		this.localVariable = localVariable;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	

}
