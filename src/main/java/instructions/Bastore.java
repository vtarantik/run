package instructions;

public class Bastore extends AbstractInstruction{

	public Bastore(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
