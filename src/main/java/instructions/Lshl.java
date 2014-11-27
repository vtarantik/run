package instructions;

public class Lshl extends AbstractInstruction{

	public Lshl(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
