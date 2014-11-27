package instructions;

public class New extends AbstractInstruction{

	public New(short opcode, int index, int length) {
		super(opcode,index,length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
