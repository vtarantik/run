package instructions;

public class Iinc extends AbstractInstruction{
	private int increment;

	public Iinc(int increment,short opcode, int index, int length) {
		super(opcode, index, length);
		this.increment = increment;
	}

	public void execute() {
		System.out.println(toString());
	}

}
