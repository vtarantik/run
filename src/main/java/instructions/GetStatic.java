package instructions;

public class GetStatic extends AbstractInstruction{

	public GetStatic(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
