package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Operand Stack

 No change

 Description

 The unsigned bytes branchbyte1 and branchbyte2 are used to construct a signed 
 16-bit branchoffset, where branchoffset is (branchbyte1 << 8) | branchbyte2. 
 Execution proceeds at that offset from the address of the opcode of this goto instruction.
 The target address must be that of an opcode of an instruction within the method 
 that contains this goto instruction.
 */
public class Goto extends AbstractInstruction {

	public Goto(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}
	
	@Override
	public boolean isControl() {
		return true;
	}
	
	@Override
	public int nextInstructionPositionOffset() {
		return index;
	}
}
