package instructions;

public class Baload extends AbstractInstruction{

	public Baload(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
