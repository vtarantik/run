package instructions;

public class Ldc2W extends AbstractInstruction{

	public Ldc2W(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
