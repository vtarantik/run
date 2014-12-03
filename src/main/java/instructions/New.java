package instructions;

import java.lang.reflect.Constructor;

import heap.HeapedObject;
import heap.MyHeap;
import model.MyClass;

import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Method;

import parser.MembersHelper;

public class New extends AbstractInstruction{

	public New(short opcode, int index, int length,MyClass ownerClass) {
		super(opcode,index,length,ownerClass);
	}

	public void execute() {
		System.out.println(toString());
		ConstantPool cp = ownerClass.getConstantPool();
		ConstantClass o = (ConstantClass)cp.getConstant(index);
		Constant con = cp.getConstant(o.getNameIndex());
		String className = cp.getConstantString(index, o.getTag());
		
		MyClass objectClass = MembersHelper.loadClass(className);
		//Object objectToStore = MembersHelper.createObjectFromClassName(className);
//		HeapedObject ho = new HeapedObject(objectClass, objectToStore);
		
//		MyHeap.getInstance().store(ho);
	}

}
