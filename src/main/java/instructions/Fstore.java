package instructions;

public class Fstore extends AbstractInstruction{

	public Fstore(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
