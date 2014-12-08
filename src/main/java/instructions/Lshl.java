package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The value1 must be of type long, and value2 must be of type int. The values are popped from the operand stack. A long result is calculated by shifting value1 left by s bit positions, where s is the low 6 bits of value2. The result is pushed onto the operand stack.

 Notes

 This is equivalent (even if overflow occurs) to multiplication by 2 to the power s. The shift distance actually used is therefore always in the range 0 to 63, inclusive, as if value2 were subjected to a bitwise logical AND with the mask value 0x3f.
 */
public class Lshl extends AbstractInstruction {

	public Lshl(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
