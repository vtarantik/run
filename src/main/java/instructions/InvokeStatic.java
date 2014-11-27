package instructions;

public class InvokeStatic extends AbstractInstruction{

	public InvokeStatic(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
