package instructions;

public class LdcW extends AbstractInstruction{

	public LdcW(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
