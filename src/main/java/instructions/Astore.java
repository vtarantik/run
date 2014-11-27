package instructions;

public class Astore extends AbstractInstruction{

	public Astore(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
