package instructions;

public class Ldc extends AbstractInstruction{

	public Ldc(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
