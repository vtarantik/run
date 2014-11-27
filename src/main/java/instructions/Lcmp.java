package instructions;

public class Lcmp extends AbstractInstruction{

	public Lcmp(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
