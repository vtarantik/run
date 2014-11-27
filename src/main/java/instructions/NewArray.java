package instructions;

public class NewArray extends AbstractInstruction{

	public NewArray(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
