package instructions;

public class Areturn extends AbstractInstruction{

	public Areturn(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
