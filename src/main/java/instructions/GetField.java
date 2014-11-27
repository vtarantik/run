package instructions;

public class GetField extends AbstractInstruction{

	public GetField(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
