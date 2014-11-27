package instructions;

public class I2C extends AbstractInstruction{

	public I2C(short opcode, int index, int length) {
		super(opcode, index, length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
