package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The index is an unsigned byte that must be an index into the local variable
 array of the current frame (§2.6). The local variable at index must contain an int.
 The value of the local variable at index is pushed onto the operand stack.

 Notes

 The iload opcode can be used in conjunction with the wide instruction (§wide)
 to access a local variable using a two-byte unsigned index.
 */
public class Iload extends AbstractInstruction {

	public Iload(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
