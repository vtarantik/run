package instructions;

public class Nop extends AbstractInstruction{

	public Nop(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
