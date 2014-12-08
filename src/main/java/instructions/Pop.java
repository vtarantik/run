package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Pop the top value from the operand stack.

 The pop instruction must not be used unless value is a value of a category 1 computational type (§2.11.1).
 */
public class Pop extends AbstractInstruction {

	public Pop(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
