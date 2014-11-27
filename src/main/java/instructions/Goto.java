package instructions;

public class Goto extends AbstractInstruction{

	public Goto(short opcode, int index, int length) {
		super(opcode, index, length);
	}
	public void execute() {
		System.out.println(toString());
	}
}
