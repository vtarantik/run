package instructions;

public class Iadd extends AbstractInstruction{

	public Iadd(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
