package instructions;

public class Bipush extends AbstractInstruction{

	public Bipush(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}
	
}
