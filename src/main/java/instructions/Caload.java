package instructions;

public class Caload extends AbstractInstruction{

	public Caload(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}
	
}
