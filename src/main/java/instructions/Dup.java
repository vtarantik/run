package instructions;

public class Dup extends AbstractInstruction{

	public Dup(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
