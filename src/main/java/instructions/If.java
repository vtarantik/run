package instructions;

public class If implements IAbstractInstruction {
	private Iftype ifType;

	public If(Iftype ifType) {
		this.ifType = ifType;
	}

	public enum Iftype {
		IFEQ, IFGE, IFGT, IFLE, IFLT, IFNE, IFNONNULL, IFNULL
	}

	public void execute() {
		System.out.println("IF "+ifType.name());
	}
}
