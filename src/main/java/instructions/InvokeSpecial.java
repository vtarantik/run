package instructions;

public class InvokeSpecial extends AbstractInstruction{

	public InvokeSpecial(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
