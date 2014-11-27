package instructions;

public class Pop extends AbstractInstruction{

	public Pop(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
