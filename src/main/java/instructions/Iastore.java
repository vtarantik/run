package instructions;

public class Iastore extends AbstractInstruction{

	public Iastore(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
