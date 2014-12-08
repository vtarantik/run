package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The value must be of type int. It is popped from the operand stack and compared against zero. 
 All comparisons are signed. The results of the comparisons are as follows:

 ifeq succeeds if and only if value = 0

 ifne succeeds if and only if value ≠ 0

 iflt succeeds if and only if value < 0

 ifle succeeds if and only if value ≤ 0

 ifgt succeeds if and only if value > 0

 ifge succeeds if and only if value ≥ 0

 If the comparison succeeds, the unsigned branchbyte1 and branchbyte2 are used to construct
 a signed 16-bit offset, where the offset is calculated to be (branchbyte1 << 8) | branchbyte2.
 Execution then proceeds at that offset from the address of the opcode of this if<cond> 
 instruction. The target address must be that of an opcode of an instruction within the
 method that contains this if<cond> instruction.

 Otherwise, execution proceeds at the address of the instruction following this if<cond> instruction.
 */
public class If extends AbstractInstruction {
	private Iftype ifType;

	public If(Iftype ifType, short opcode, int index, int length,
			MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
		this.ifType = ifType;
	}

	public enum Iftype {
		IFEQ, IFGE, IFGT, IFLE, IFLT, IFNE, IFNONNULL, IFNULL
	}

	public void execute(Method m) {
		System.out.println(toString());
		switch (ifType) {
		case IFEQ:
			break;
		case IFGE:
			break;
		case IFGT:
			break;
		case IFLE:
			break;
		case IFLT:
			break;
		case IFNE:
			break;
		case IFNONNULL:
			break;
		case IFNULL:
			break;
		default:
			throw new UnsupportedOperationException("Unsupported iftype");
		}
	}
}
