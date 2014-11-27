package instructions;

public class Ireturn extends AbstractInstruction{

	public Ireturn(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
