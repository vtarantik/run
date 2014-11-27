package instructions;

public class AconstNull extends AbstractInstruction{
	

	public AconstNull(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
