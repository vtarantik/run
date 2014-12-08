package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * The value must be of type reference. It is popped from the operand stack. 
 * If value is not null, the unsigned branchbyte1 and branchbyte2 are used
 *  to construct a signed 16-bit offset, where the offset is calculated to be
 *   (branchbyte1 << 8) | branchbyte2. Execution then proceeds at that offset 
 *   from the address of the opcode of this ifnonnull instruction. The target
 *    address must be that of an opcode of an instruction within the method 
 *    that contains this ifnonnull instruction.

 Otherwise, execution proceeds at the address of the instruction following this
 ifnonnull instruction.
 */
public class IfNonNull extends AbstractInstruction {

	public IfNonNull(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
