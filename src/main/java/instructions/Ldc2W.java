package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The unsigned indexbyte1 and indexbyte2 are assembled into an unsigned 16-bit index into the run-time constant pool of the current class (§2.6), where the value of the index is calculated as (indexbyte1 << 8) | indexbyte2. The index must be a valid index into the run-time constant pool of the current class. The run-time constant pool entry at the index must be a run-time constant of type long or double (§5.1). The numeric value of that run-time constant is pushed onto the operand stack as a long or double, respectively.

 Notes

 Only a wide-index version of the ldc2_w instruction exists; there is no ldc2 instruction that pushes a long or double with a single-byte index.

 The ldc2_w instruction can only be used to push a value of type double taken from the double value set (§2.3.2) because a constant of type double in the constant pool (§4.4.5) must be taken from the double value set.
 */
public class Ldc2W extends AbstractInstruction {

	public Ldc2W(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
