package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Push the long constant <l> (0 or 1) onto the operand stack.
 */
public class Lconst extends AbstractInstruction {

	public Lconst(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
