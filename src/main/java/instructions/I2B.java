package instructions;

public class I2B extends AbstractInstruction{

	public I2B(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
