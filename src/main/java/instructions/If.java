package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

public class If extends AbstractInstruction {
	private Iftype ifType;

	public If(Iftype ifType,short opcode,int index,int length,MyClass ownerClass) {
		super(opcode,index,length, ownerClass);
		this.ifType = ifType;
	}

	public enum Iftype {
		IFEQ, IFGE, IFGT, IFLE, IFLT, IFNE, IFNONNULL, IFNULL
	}

	public void execute() {
		System.out.println(toString());
	}
}
