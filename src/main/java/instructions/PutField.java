package instructions;

public class PutField extends AbstractInstruction{

	public PutField(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
