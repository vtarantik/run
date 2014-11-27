package instructions;

public class Iconst extends AbstractInstruction{

	public Iconst(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}
	
}
