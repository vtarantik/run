package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The index is an unsigned byte that must be an index into the local variable array of the
 current frame (§2.6). The const is an immediate signed byte. The local variable at index 
 must contain an int. The value const is first sign-extended to an int, and then the local
 variable at index is incremented by that amount.

 Notes

 The iinc opcode can be used in conjunction with the wide instruction (§wide) to access
 a local variable using a two-byte unsigned index and to increment it by a two-byte immediate
 signed value.
 */
public class Iinc extends AbstractInstruction {
	private int increment;

	public Iinc(int increment, short opcode, int index, int length,
			MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
		this.increment = increment;
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
