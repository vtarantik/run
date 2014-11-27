package instructions;

public class Iload extends AbstractInstruction{

	public Iload(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
