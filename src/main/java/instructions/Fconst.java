package instructions;

public class Fconst extends AbstractInstruction{

	public Fconst(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}
	
}
