package model;

import java.util.HashMap;
import java.util.Map;

import org.apache.bcel.classfile.Field;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.FieldGen;

public class Instance {
	private MyClass instanceType;
	private Map<Integer,MyField> fields;
	
	public Instance(MyClass instanceType){
		this.instanceType = instanceType;
		fields = getFieldsFromClass();
	}

	private Map<Integer, MyField> getFieldsFromClass() {
		HashMap<Integer, MyField> fieldsToReturn = new HashMap<Integer, MyField>();
		for (Field field : instanceType.getFields()) {
			//todo get field ref
		}
		return null;
	}
	
}
