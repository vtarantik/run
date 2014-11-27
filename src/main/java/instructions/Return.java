package instructions;

public class Return extends AbstractInstruction{

	public Return(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
