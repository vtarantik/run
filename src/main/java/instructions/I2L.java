package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

public class I2L extends AbstractInstruction{

	public I2L(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute() {
		System.out.println(toString());
	}

}
