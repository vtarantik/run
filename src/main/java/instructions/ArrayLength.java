package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

public class ArrayLength extends AbstractInstruction{

	public ArrayLength(short opcode, int index, int length,MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute() {
		System.out.println(toString());
	}

}
