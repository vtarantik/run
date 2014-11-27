package instructions;

public class InvokeVirtual extends AbstractInstruction{

	public InvokeVirtual(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
