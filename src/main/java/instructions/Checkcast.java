package instructions;

public class Checkcast extends AbstractInstruction{

	public Checkcast(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
