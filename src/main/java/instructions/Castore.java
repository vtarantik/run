package instructions;

public class Castore extends AbstractInstruction{

	public Castore(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}
	
}
