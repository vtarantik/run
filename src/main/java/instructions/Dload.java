package instructions;

public class Dload extends AbstractInstruction{

	public Dload(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
