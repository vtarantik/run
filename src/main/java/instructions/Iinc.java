package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

public class Iinc extends AbstractInstruction{
	private int increment;

	public Iinc(int increment,short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length,ownerClass);
		this.increment = increment;
	}

	public void execute() {
		System.out.println(toString());
	}

}
