package instructions;

public class Fload extends AbstractInstruction{

	public Fload(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
