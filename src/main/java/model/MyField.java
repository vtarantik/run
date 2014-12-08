package model;

import org.apache.bcel.classfile.ConstantFieldref;

public class MyField {
	private MyClass className;
	private ConstantFieldref constantPoolFieldRef;
	private Object value;
	
	public MyField(MyClass className, ConstantFieldref constantPoolFieldRef,
			Object value) {
		super();
		this.className = className;
		this.constantPoolFieldRef = constantPoolFieldRef;
		this.value = value;
	}

	public MyClass getClassName() {
		return className;
	}

	public void setClassName(MyClass className) {
		this.className = className;
	}

	public ConstantFieldref getConstantPoolFieldRef() {
		return constantPoolFieldRef;
	}

	public void setConstantPoolFieldRef(ConstantFieldref constantPoolFieldRef) {
		this.constantPoolFieldRef = constantPoolFieldRef;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
