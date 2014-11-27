package instructions;

public class Istore extends AbstractInstruction{

	public Istore(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
