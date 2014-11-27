package instructions;

public class I2L extends AbstractInstruction{

	public I2L(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
