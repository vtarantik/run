package instructions;

public class Isub extends AbstractInstruction{

	public Isub(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
