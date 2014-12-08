package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 Both value1 and value2 must be of type int. They are both popped from the operand 
 stack and compared. All comparisons are signed. The results of the comparison are as follows:

 if_icmpeq succeeds if and only if value1 = value2

 if_icmpne succeeds if and only if value1 ≠ value2

 if_icmplt succeeds if and only if value1 < value2

 if_icmple succeeds if and only if value1 ≤ value2

 if_icmpgt succeeds if and only if value1 > value2

 if_icmpge succeeds if and only if value1 ≥ value2

 If the comparison succeeds, the unsigned branchbyte1 and branchbyte2 are used
 to construct a signed 16-bit offset, where the offset is calculated to be 
 (branchbyte1 << 8) | branchbyte2. Execution then proceeds at that offset from 
 the address of the opcode of this if_icmp<cond> instruction. The target address 
 must be that of an opcode of an instruction within the method that contains this
 if_icmp<cond> instruction.

 Otherwise, execution proceeds at the address of the instruction following this if_icmp<cond> instruction.
 */
public class IFIcmp extends AbstractInstruction {
	private IfIcmpType ificmpType;

	public enum IfIcmpType {
		ICMPEQ, ICMPGE, ICMPGT, ICMPLE, ICMPLT, ICMPNE
	}

	public IFIcmp(IfIcmpType ificmpType, short opcode, int index, int length,
			MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
		this.ificmpType = ificmpType;
	}

	public void execute(Method m) {
		// TODO switch for different types
		System.out.println(toString());
	}

}
