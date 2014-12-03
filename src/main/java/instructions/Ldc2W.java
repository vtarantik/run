package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

public class Ldc2W extends AbstractInstruction{

	public Ldc2W(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length,ownerClass);
	}

	public void execute() {
		System.out.println(toString());
	}

}
