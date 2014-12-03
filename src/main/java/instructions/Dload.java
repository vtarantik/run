package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

public class Dload extends AbstractInstruction{

	public Dload(short opcode, int index, int length,MyClass ownerClass) {
		super(opcode, index, length,ownerClass);
	}

	public void execute() {
		System.out.println(toString());
	}

}
