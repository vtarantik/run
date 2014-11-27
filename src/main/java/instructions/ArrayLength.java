package instructions;

public class ArrayLength extends AbstractInstruction{

	public ArrayLength(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
