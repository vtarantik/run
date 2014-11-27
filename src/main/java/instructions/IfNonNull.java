package instructions;

public class IfNonNull extends AbstractInstruction{

	public IfNonNull(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
