package instructions;

public class Dconst extends AbstractInstruction{

	public Dconst(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
