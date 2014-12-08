package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Do nothing
 */
public class Nop extends AbstractInstruction{

	public Nop(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
