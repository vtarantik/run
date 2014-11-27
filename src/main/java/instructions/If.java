package instructions;

public class If extends AbstractInstruction {
	private Iftype ifType;

	public If(Iftype ifType,short opcode,int index,int length) {
		super(opcode,index,length);
		this.ifType = ifType;
	}

	public enum Iftype {
		IFEQ, IFGE, IFGT, IFLE, IFLT, IFNE, IFNONNULL, IFNULL
	}

	public void execute() {
		System.out.println(toString());
	}
}
