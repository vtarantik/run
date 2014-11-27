package instructions;

public class PutStatic extends AbstractInstruction{

	public PutStatic(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
